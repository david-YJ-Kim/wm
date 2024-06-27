package com.abs.wfs.workman.interfaces.solace.broker;

import com.abs.wfs.workman.util.ApMessagePool;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApEnumConstant;
import com.abs.wfs.workman.util.code.WorkManScenarioList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solacesystems.jcsmp.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskRejectedException;

@Slf4j
public class Receiver implements Runnable {


    private JCSMPSession session;
    private EndpointProperties endPointProps;
    private FlowReceiver consumer;
    //	private String module_name;
    private String thread_name;
    private String queue_name;

    private boolean stopFlagOn = false;


    public Receiver(JCSMPSession session, String thread_name, String queue_name) {
        this.session = session;
        this.queue_name = queue_name;
        this.thread_name = thread_name;
    }

    @Override
    public void run() {
        try {
            log.info("Receiver Thread Start # " + this.thread_name);

            // Queue - SolAdmin에서 생성한 queue에 접속, SolAdmin에 생성되지 않은 경우 Application에서 생성
            final Queue queue = JCSMPFactory.onlyInstance().createQueue(queue_name);

            /* ConsumerFlow 설정 */
            final ConsumerFlowProperties flowProps = new ConsumerFlowProperties();
            // Queue에 연결할 flow 설정
            flowProps.setEndpoint(queue);
            // Manual Ack 설정
            flowProps.setAckMode(JCSMPProperties.SUPPORTED_MESSAGE_ACK_CLIENT);

            // FlowReceiver 생성
            consumer = session.createFlow(new MessageListener(this), flowProps, endPointProps);
            // FlowReceiver 실행(start를 해야 Endpoint로부터 메시지를 수신할 수 있음)
            consumer.start();
        } catch (InvalidPropertiesException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JCSMPException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void switchStopFlag(){
        try {
            this.session.deleteSubscriber();
        } catch (JCSMPException e) {
            e.printStackTrace();
        }
        this.stopFlagOn = true;
    }


    public boolean stopReceiver() throws JCSMPInterruptedException {

//		this.consumer.stopSync();
        this.switchStopFlag();
        this.consumer.stop();
        log.info("Consumer Stop!!");
        return true;
    }

    public class MessageListener implements XMLMessageListener {
        public MessageListener(Receiver receiver) {
        }


        @SneakyThrows
        @Override
        public void onReceive(BytesXMLMessage message) {

            if(stopFlagOn){
                log.warn("Stop flag is on. Stop incoming request.");
                return;
            }



            SDTMap userProperty = message.getProperties();
            String cid = userProperty.getString(ApEnumConstant.cid.name());
            String messageId = userProperty.getString(ApEnumConstant.messageId.name());

            // TODO Get received destination.

            // TODO if cid / key fail to get prevent throw

            String messageKey = ApMessagePool.putMessageObject(messageId, message);
            log.info("messageKey: {} is started managed in map : {}", messageKey, ApMessagePool.getMessageManageMap().size());

            try{

                String payload = "";
                if ( message instanceof TextMessage) {
                    payload = ((TextMessage) message).getText();
                } else {
                    payload = new String( message.getBytes(), "UTF-8");
                }

                try {
                    this.validateWorkState(cid);
                }catch (Exception e){
                    e.printStackTrace();
                    // TODO Work State Fail
                }

                // TODO 전문 파싱을 통해 eqpId 획득 후 시나리오 타입 구분 해야 하니,
                //  차라리 이벤트 레벨에서 진행하는 것도 고민필요
                // EQP ID로, Scenario Type 획득
                String eqpId = "EQP ID  획득";
                String scenarioType = this.getScenarioType(eqpId);

                this.messageDispatch(cid, messageKey, payload);

            }catch(TaskRejectedException taskRejectedException){
                taskRejectedException.printStackTrace();
                log.error("Over capacity. It's overflow.");


            }catch (Exception e){
                e.printStackTrace();
                log.error("##  Receiver.onReceive() Exception : ", e);
            }finally {
                ApMessagePool.messageAck(messageKey);
            }

        }


        /**
         * Work State Validation
         * @param cid
         */
        private void validateWorkState(String cid){
            // validate work state with event name.

            // TODO Query current Work state

            // cid == LOAD_REQ, Work State 없음
            // then return void.
            if (cid.equals(WorkManMessageList.WFS_LOAD_REQ)){
                return;
            }
        }

        /**
         * EQP ID로 설정된 시나리오 타입 획득
         * @param eqpId
         * @return
         */
        private String getScenarioType(String eqpId){

            // find scenario Type with eqpId
            return WorkManScenarioList.BP_SINGLE;
        }


        /**
         * Event 명으로 세부 프로세스 실행
         * @param cid
         * @param messageId
         * @param payload
         * @throws Exception
         */
        private void messageDispatch(String cid, String messageId, String payload) throws Exception {

            ObjectMapper mapper = new ObjectMapper()
                    .configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true)
                    .configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);


            log.info("{} Incoming request: {}", messageId, cid);
            switch (cid){
                case WorkManMessageList.WFS_LOAD_REQ:

//                    WfsLoadReqVo wfsLoadReqVo = mapper.readValue(payload, WfsLoadReqVo.class);
//                    log.info("[{}] Request vo: {}", messageId, wfsLoadReqVo.getBody().toString());
//
//                    WfsLoadReq wfsLoadReq = ApplicationContextProvider.getBean(WfsLoadReq.class);
//                    wfsLoadReq.init(WorkManMessageList.WFS_LOAD_REQ, wfsLoadReqVo);
//
//                    wfsLoadReq.execute(messageId);

                    break;
                default:
                    log.error("## Invalied cid : "+cid);
                    break;
            }

        }

        @Override
        public void onException(JCSMPException exception) {

            try {
                if ( session.isClosed()) session.connect();
                if ( consumer.isClosed()) consumer.start();
            } catch (JCSMPException e) {
                // TODO Auto-generated catch block
                log.error("## Receiver , onException : ",e);
            }

            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'onException'");
        }


    }
}
