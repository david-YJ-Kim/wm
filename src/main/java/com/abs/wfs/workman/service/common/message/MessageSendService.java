package com.abs.wfs.workman.service.common.message;

import com.abs.wfs.workman.config.ApPropertyObject;
import com.abs.wfs.workman.config.ApSharedVariable;
import com.abs.wfs.workman.dao.domain.tnLot.model.TnPosLot;
import com.abs.wfs.workman.dao.domain.tnLot.service.TnPosLotServiceImpl;
import com.abs.wfs.workman.dao.domain.workJobSlotInfo.model.WnWorkJobSlotInfo;
import com.abs.wfs.workman.dao.domain.workJobSlotInfo.service.WnWorkJobSlotInfoServiceImpl;
import com.abs.wfs.workman.dao.query.dao.WorkDAO;
import com.abs.wfs.workman.dao.query.service.vo.SelectWorkJobPortVo;
import com.abs.wfs.workman.interfaces.solace.InterfaceSolacePub;
import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.vo.WorkMessageSendReqVo;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.out.eap.EapWorkOrderReq;
import com.abs.wfs.workman.spec.out.eap.common.SlotMapVo;
import com.abs.wfs.workman.spec.out.eap.common.Work;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import com.abs.wfs.workman.util.code.UseStatCd;
import com.abs.wfs.workman.util.code.UseYn;
import com.abs.wfs.workman.util.exception.ApExceptionCode;
import com.abs.wfs.workman.util.exception.ScenarioException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.netflix.discovery.converters.Auto;
import com.solacesystems.jcsmp.JCSMPException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MessageSendService {

    public void sendMessageSend(String targetSystem, String eventName, String payload) throws JCSMPException {

        log.info("Message send to System: {}, EventName: {}, Payload: {}", targetSystem, eventName, payload);
        this.sendMessageSend(targetSystem, eventName, payload,
                            ApPropertyObject.getInstance().getSequenceManager().getTargetName(targetSystem, eventName, payload));
    }

    public void sendMessageSend(String targetSystem, String eventName, String payload, String topicName) throws JCSMPException {

        log.info("Message send to System: {}, EventName: {}, Payload: {}, topicName: {}", targetSystem, eventName, payload, topicName);
        InterfaceSolacePub.getInstance().sendTopicMessage(eventName, payload, topicName);
    }



    @Autowired
    ApPayloadGenerateService apPayloadGenerateService;




    /**
     * Work 테이블에 적재된 데이터를 살펴보고 Work 메시지를 생성해서 발송한다.
     * @param workMessageSendReqVo
     */


    @Autowired
    WnWorkJobSlotInfoServiceImpl wnWorkJobSlotInfoService;

    @Autowired
    TnPosLotServiceImpl tnPosLotService;

    @Autowired
    WorkDAO workDAO;

    public void generateWorkMessageSend(ApFlowProcessVo apFlowProcessVo) throws JsonProcessingException, JCSMPException {

        String siteId = apFlowProcessVo.getApMsgBody().getSiteId();
        String workId = apFlowProcessVo.getWorkId();
        String eqpId = apFlowProcessVo.getApMsgBody().getEqpId();
        String lotId = apFlowProcessVo.getApMsgBody().getLotId();




        /* Work 테이블 생성 된 Slot 리스트 */
        Optional<List<WnWorkJobSlotInfo>> slotInfoQueryList =  this.wnWorkJobSlotInfoService.findByWorkIdAndSiteIdAndUseStatCd(workId, siteId);
        if(!slotInfoQueryList.isPresent() || slotInfoQueryList.get().size() == 0){
            throw  new ScenarioException(apFlowProcessVo, apFlowProcessVo.getApMsgBody(), ApExceptionCode.WFS_ERR_JOB_SLOT_INF_NOTFOUND, apFlowProcessVo.getLang()
                    , new String[] {workId, apFlowProcessVo.getApMsgBody().getLotId(), eqpId});
        }



        SelectWorkJobPortVo selectWorkJobPortVo = SelectWorkJobPortVo.builder().workId(workId).build();
        log.info("{} Query work job info with requestVo : {}", apFlowProcessVo.printLog(), selectWorkJobPortVo.toString());

        /* Work 테이블 생성된  Job 리스트 */
        Optional<List<SelectWorkJobPortVo>> workJobInfoQuery = this.workDAO.selectWorkJobPort(selectWorkJobPortVo); // jobXml
        if(!workJobInfoQuery.isPresent() || workJobInfoQuery.get().size() == 0){
            throw  new ScenarioException(apFlowProcessVo, apFlowProcessVo.getApMsgBody(), ApExceptionCode.WFS_ERR_WORK_JOB_INF_NOTFOUND, apFlowProcessVo.getLang()
                , new String[] {workId, apFlowProcessVo.getApMsgBody().getLotId(), eqpId});
        }

        
        /* Lot 공정 정보 조회 */
        Optional<TnPosLot> tnPosLot = this.tnPosLotService.findBySiteIdAndLotIdAndUseStatCd(siteId, lotId, UseStatCd.Usable);
        if(!tnPosLot.isPresent() || tnPosLot.get().getLotId() == null){
            throw  new ScenarioException(apFlowProcessVo, apFlowProcessVo.getApMsgBody(), ApExceptionCode.WFS_ERR_LOT_INF_NOTFOUND, apFlowProcessVo.getLang()
                    , new String[] {"LotId : " + lotId});

        }


        /**
         * Work 메시지 생성
         */
        EapWorkOrderReq.Body body = new EapWorkOrderReq.Body();
        body.setSiteId(siteId); body.setEqpId(eqpId); body.setUserId(ApSystemCodeConstant.WFS);
        body.setWorkId(workId); body.setProdDefId(tnPosLot.get().getProdDefId());
        body.setProcDefId(tnPosLot.get().getProcDefId()); body.setProcSgmtId(tnPosLot.get().getProcSgmtId());
        body.setWork(this.generateWorkObject(workJobInfoQuery.get(), slotInfoQueryList.get()));


        this.sendMessageSend(EapWorkOrderReq.system, EapWorkOrderReq.cid, this.apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), body));

    }


    /**
     * Work Order Req 메시지 중,  Work 객체를 생성하는 메소드
     * @param selectWorkJobPortVos
     * @param wnWorkJobSlotInfos
     * @return
     */
    private List<Work> generateWorkObject(List<SelectWorkJobPortVo> selectWorkJobPortVos, List<WnWorkJobSlotInfo> wnWorkJobSlotInfos){

        List<Work> workList = new ArrayList<>();
        for(SelectWorkJobPortVo workVo: selectWorkJobPortVos){

            String jobSeqId = workVo.getJobSeqId();
            String recipeId = workVo.getRcpDefId();

            Work work = Work.builder()
                    .jobSeqId(workVo.getJobSeqId())
                    .inPortId(workVo.getInPortId())
                    .inCarrId(workVo.getInCarrId())
                    .outPortId(workVo.getOutPortId())
                    .outCarrId(workVo.getOutCarrId())
                    .portType(workVo.getPortTyp())
                    .scanYn(UseYn.N.name())     // TODO Work의 성격에 따라서 Scan 진행하는 경우 생길 수도  있음.
                    .slotMap(this.generateSlotMapObject(jobSeqId, recipeId, wnWorkJobSlotInfos))
                    .build();

            workList.add(work);
        }

        return workList;
    }


    /**
     * Work Order Req 메시지 중, Work 내부 SlotMap 객체를 생성하는 메소드
     * @param jobSeqId
     * @param recipeId
     * @param wnWorkJobSlotInfos
     * @return
     */
    private List<SlotMapVo> generateSlotMapObject(String jobSeqId, String recipeId, List<WnWorkJobSlotInfo> wnWorkJobSlotInfos){

        List<SlotMapVo> slotMapVos = new ArrayList<>();
        for(WnWorkJobSlotInfo slotVo : wnWorkJobSlotInfos){

            if(jobSeqId.equals(slotVo.getJobSeqId())){

                SlotMapVo vo = SlotMapVo.builder()
                        .ppId((recipeId == null || !WorkManCommonUtil.nullPointCheck(recipeId)) ? "" : recipeId)
                        .inCarrSlotNo(slotVo.getSlotNo())
                        .outCarrSlotNo(slotVo.getSlotNo())
                        .lotId(slotVo.getLotId())
                        .prodMtrlId(slotVo.getProdMtrlId())
                        .slotState("")
                        .mtrlFace(slotVo.getMtrlFaceCd())
                        .manualInsp(slotVo.getSelfInspYn())
                        .build();
                slotMapVos.add(vo);
            }

        }

        return slotMapVos;

    }
}
