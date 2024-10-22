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

import java.util.Map;

@Data
public class ApResponseIvo extends ApMsgCommonVo {


    /**
     * 기존 Scenario Exception 데이터
     */
    String messageKey;
    String scenarioTyp;
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
    ApFlowProcessVo processInfo;            // ApFlowProcessVo를 assign 해야하는 변수, 너무 많음... // TODO 추후 삭제 및 축소
    Map<String, String> additionData;       // ApFlowProcessVo 진행하면서 추가된 항목
    ApMsgBody msgBody;                      // 요청이 들어온 메시지


    MsgReasonVo reason;


    @Builder
    public ApResponseIvo(ApMsgBody msgBody, ApFlowProcessVo processInfo,
                         ScenarioException scenarioException) {

        /* 정상 처리 */
        if(scenarioException == null){

            this.setIndividualElement(processInfo, processInfo.getApMsgBody());
            this.processInfo = processInfo;
            this.additionData = processInfo.getAdditionData();

            this.errorCode = "0";

            this.reason = MsgReasonVo.builder()
                    .reasonCode("0")
                    .build();
        }
        /* 비정상 처리 */
        else{

            String errorComment = WorkManCommonUtil.generateMultiLangExceptionMessage(scenarioException.getCode(),
                    scenarioException.getLang(),
                    scenarioException.getArgs());

            this.msgBody = scenarioException.getMsgBody();
            this.processInfo = scenarioException.getProcessInfo();

            this.errorCode = scenarioException.getCode();
            this.errorComment = errorComment;

            this.setIndividualElement(scenarioException.getProcessInfo(), scenarioException.getMsgBody());
            this.additionData = scenarioException.getProcessInfo().getAdditionData();

            this.reason = MsgReasonVo.builder()
                    .reasonCode(scenarioException.getCode())
                    .reasonComment(errorComment)
                    .build();
        }
    }






    private String generateExceptionMessage(String format, Object[] args){
        return String.format(format, args);
    }




    private void setIndividualElement(ApFlowProcessVo apFlowProcessVo, ApMsgBody apMsgBody){

        this.messageKey = apFlowProcessVo.getTrackingKey();
        this.scenarioTyp = apFlowProcessVo.getScenarioType();
        this.eventName = apFlowProcessVo.getEventName();
        this.workStateCode = apFlowProcessVo.getWorkStatCd();

        if(apMsgBody != null){
            this.siteId = apMsgBody.getSiteId() == null ? "SVM" : apMsgBody.getSiteId();
            this.workId = "work id를 어디서 받아올지 정의 필요";
            this.lotId = apMsgBody.getLotId() == null ? "" : apMsgBody.getLotId();
            this.carrId = apMsgBody.getCarrId() == null ? "" : apMsgBody.getCarrId();
            this.eqpId = apMsgBody.getEqpId() == null ? "" : apMsgBody.getEqpId();
            this.portId = apMsgBody.getPortId() == null ? "" : apMsgBody.getPortId();
        }
    }

}
