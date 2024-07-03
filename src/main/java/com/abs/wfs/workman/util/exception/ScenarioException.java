package com.abs.wfs.workman.util.exception;

import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.MsgReasonVo;
import com.abs.wfs.workman.util.code.WorkStatCd;
import com.abs.wfs.workman.util.vo.ScenarioExceptionVo;
import lombok.Data;

import javax.annotation.Nullable;

@Data
public class ScenarioException  extends RuntimeException{

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

    private Object[] args;
    MsgReasonVo reason;


    public ScenarioException() {super();}


    public ScenarioException (ApFlowProcessVo apFlowProcessVo, ApMsgBody apMsgBody, String code, String exceptionMessage, @Nullable Object[] args){

        this(apFlowProcessVo, apMsgBody);
    }


    public ScenarioException (ApFlowProcessVo apFlowProcessVo, ApMsgBody apMsgBody){

        this.processInfo = apFlowProcessVo;
        this.msgBody = apMsgBody;

        this.setIndividualErrorElement(apFlowProcessVo, apMsgBody);


    }


    private ApFlowProcessVo setExceptionContent(ApFlowProcessVo apFlowProcessVo,String code, String exceptionMessage, @Nullable Object[] args){

        return null;

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




    @Deprecated
    public ScenarioException( String messageKey,  String scenarioType,String eventName,String siteId,
                              String workStateCode,String workId,String lotId,String carrId,String eqpId,String portId,
                              String errorCode,String errorComment) {
        super();
    }



    @Deprecated
    public ScenarioException(ScenarioExceptionVo vo) {

        super();
        this.messageKey = vo.getMessageKey();
        this.scenarioType = vo.getScenarioType();
        this.eventName = vo.getEventName();
        this.siteId = vo.getSiteId();
        this.workStateCode = vo.getWorkStateCode();
        this.workId = vo.getWorkId();
        this.lotId = vo.getLotId();
        this.carrId = vo.getCarrId();
        this.eqpId = vo.getEqpId();
        this.portId = vo.getPortId();
        this.errorCode = vo.getErrorCode();
        this.errorComment = vo.getErrorComment();
    }

}
