package com.abs.wfs.workman.service.flow.far.impl;


import com.abs.wfs.workman.service.common.ApPayloadGenerateService;
import com.abs.wfs.workman.service.common.message.MessageSendService;
import com.abs.wfs.workman.service.flow.far.WfsArCommonUtil;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.fix.WfsArRequestIvo;
import com.abs.wfs.workman.spec.out.eap.EapInitEqpStateReqIvo;
import com.abs.wfs.workman.spec.out.eap.EapToolCondReqIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import com.abs.wfs.workman.util.exception.ApExceptionCode;
import com.abs.wfs.workman.util.exception.ScenarioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class StandbyArServiceImpl {

    @Autowired
    WfsArCommonUtil arCommonUtil;

    @Autowired
    MessageSendService messageSendService;

    @Autowired
    ApPayloadGenerateService  apPayloadGenerateService;


    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsArRequestIvo ivo) throws Exception {

        WfsArRequestIvo.Body body = ivo.getBody();

        String eventName = body.getEventName();
        int duplicatedErrorCnt = body.getDuplicatedIssueCnt();


        switch (eventName){
            case WorkManMessageList.WFS_LOAD_REQ:
            case WorkManMessageList.WFS_DSP_WORK_REP:
                // TODO 현재 정의된 액션 부재
                String[] errorParam = new String[] {"carrId", String.format("Report Eqp: %s, Reserved Eqp: %s"), "eqpId", "resvEqpID"};
                throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_ERR_CARR_RESV_INF_REGISTER, null, errorParam);


            case WorkManMessageList.WFS_TOOL_COND_REP:
                return this.doStdToolCondRecovery(apFlowProcessVo, body, duplicatedErrorCnt);

            case WorkManMessageList.WFS_LOT_TRACK_IN_CANCEL_REP:
            case WorkManMessageList.WFS_RECIPE_VALIDATE_REP:
            default:
                throw new ScenarioException();

        }

    }


    /**
     * Standby - WFS_TOOL_COND_REP AR 처리
     * @param apFlowProcessVo
     * @param body
     * @return
     * @throws Exception
     */
    private ApFlowProcessVo doStdToolCondRecovery(ApFlowProcessVo apFlowProcessVo, WfsArRequestIvo.Body body, int duplicatedErrorCnt) throws Exception {

        log.info("payload: {}, duplicatedCnt: {}", body.toString(), duplicatedErrorCnt);

        // TODO 기준 횟수 하드코딩 3
        int cntStandard = 3;
        if(duplicatedErrorCnt >= cntStandard){
            log.info("Over standard error count. standard:{}, current:{}", cntStandard, duplicatedErrorCnt);

            String[] errorParam = new String[] {String.valueOf(cntStandard), String.valueOf(duplicatedErrorCnt)};
            throw new ScenarioException(apFlowProcessVo, body, ApExceptionCode.WFS_AR_ERR_EXCEED_RETRIAL, null, errorParam);   // TODO 에러 처리 진행 필요
        }

        // 1. INIT 요청하기
        String[] arg = {"eqpId","status>inPortId","status>outPortId"};
        Map<String, String> argMap =  this.arCommonUtil.getValueFromParsedMsgObj(body.getPayload(), arg);

        String eqpId = body.getEqpId();
        String inPortId = argMap.get("status>inPortId");
        String outPortId = argMap.get("status>outPortId");

        if(eqpId.isEmpty()) {
            eqpId = argMap.get("eqpId");
        }

        EapInitEqpStateReqIvo.Body initBody = new EapInitEqpStateReqIvo.Body() ;
        initBody.setSiteId(body.getSiteId()); initBody.setEqpId(eqpId);

        this.messageSendService.sendMessageSend(EapInitEqpStateReqIvo.system, EapInitEqpStateReqIvo.cid,
                this.apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), initBody));
        log.info("Complete send request eqp state init. bodyPayload: {}", initBody.toString());


        // 2. EAP, TOOL COND REQ 발송
        EapToolCondReqIvo.Body toolCondReqBody = new EapToolCondReqIvo.Body();
        toolCondReqBody.setSiteId(body.getSiteId());
        toolCondReqBody.setEqpId(eqpId);
        toolCondReqBody.setInPortId(inPortId); toolCondReqBody.setOutPortId(outPortId);
        toolCondReqBody.setUserId(ApSystemCodeConstant.WFS);

        this.messageSendService.sendMessageSend(EapToolCondReqIvo.system, EapToolCondReqIvo.cid,
                this.apPayloadGenerateService.generateBody(apFlowProcessVo.getTid(), toolCondReqBody));
        log.info("Complete send request eqp state init. bodyPayload: {}", toolCondReqBody.toString());


        return apFlowProcessVo;




    }


}
