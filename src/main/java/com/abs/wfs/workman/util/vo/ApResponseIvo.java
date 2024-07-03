package com.abs.wfs.workman.util.vo;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.MsgReasonVo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.WorkStatCd;
import com.abs.wfs.workman.util.exception.ScenarioException;
import lombok.Builder;
import lombok.Data;

@Data
public class ApResponseIvo extends ApMsgCommonVo {


    /**
     * 기존 Scenario Exception 데이터
     */
    String messageKey;
    String scenarioType;
    String eventName;
    String siteId;
    WorkStatCd workStateCode;
    String workId;
    String lotId;
    String carrId;
    String eqpId;
    String portId;
    String errorCode;
    String errorComment;


    /**
     * 추후 변경될 데이터 객체
     */
    ApFlowProcessVo processInfo;
    ApMsgBody msgBody;


    MsgReasonVo reason;


    @Builder
    public ApResponseIvo(ApMsgBody msgBody, ApFlowProcessVo processInfo,
                         ScenarioException scenarioException) {

        /* 정상 처리 */
        if(scenarioException == null){

            this.msgBody = msgBody;
            this.processInfo = processInfo;
            this.setIndividualErrorElement(processInfo, msgBody);
            this.reason = MsgReasonVo.builder()
                    .reasonCode("0")
                    .build();
        }
        /* 비정상 처리 */
        else{

            this.msgBody = scenarioException.getMsgBody();
            this.processInfo = scenarioException.getProcessInfo();
            this.setIndividualErrorElement(scenarioException.getProcessInfo(), scenarioException.getMsgBody());

            this.reason = MsgReasonVo.builder()
                    .reasonCode(scenarioException.getCode())
                    .reasonComment(WorkManCommonUtil.generateMultiLangExceptionMessage(scenarioException.getCode(),
                                                                                scenarioException.getLang(),
                                                                                scenarioException.getArgs()))
                                                                                .build();
        }
    }






    private String generateExceptionMessage(String format, Object[] args){
        return String.format(format, args);
    }




    private void setIndividualErrorElement(ApFlowProcessVo apFlowProcessVo, ApMsgBody apMsgBody){

        this.messageKey = apFlowProcessVo.getTrackingKey();
        this.scenarioType = apFlowProcessVo.getScenarioType();
        this.eventName = apFlowProcessVo.getEventName();
        this.siteId = apMsgBody.getSiteId();
        this.workStateCode = apFlowProcessVo.getWorkStatCd();
        this.workId = "work id를 어디서 받아올지 정의 필요";
        this.lotId = apMsgBody.getLotId();
        this.carrId = apMsgBody.getCarrId();
        this.eqpId = apMsgBody.getEqpId();
        this.portId = apMsgBody.getPortId();
    }

}
