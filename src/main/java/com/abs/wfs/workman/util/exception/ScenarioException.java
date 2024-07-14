package com.abs.wfs.workman.util.exception;

import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.MsgReasonVo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.WorkStatCd;
import com.abs.wfs.workman.util.vo.ScenarioExceptionVo;
import lombok.Data;

import javax.annotation.Nullable;

@Data
public class ScenarioException  extends RuntimeException{



    /**
     * 추후 변경될 데이터 객체
     */
    ApFlowProcessVo processInfo;
    ApMsgBody msgBody;

    private String code;
    private String lang;
    private Object[] args;

    public ScenarioException() {super();}


    public ScenarioException (ApFlowProcessVo apFlowProcessVo, ApMsgBody apMsgBody, String code, @Nullable Object[] args){

        this(WorkManCommonUtil.setFailFlowProcessVo(apFlowProcessVo), apMsgBody);
        this.code = code;
        this.args = args;
    }

    public ScenarioException (ApFlowProcessVo apFlowProcessVo, ApMsgBody apMsgBody, ApExceptionCode code, @Nullable  String lang, @Nullable Object[] args){

        this(WorkManCommonUtil.setFailFlowProcessVo(apFlowProcessVo), apMsgBody);
        this.code = code.getCode();
        this.lang = lang == null || lang.isEmpty() ? "en" : lang;
        this.args = args;
    }




    public ScenarioException (ApFlowProcessVo apFlowProcessVo, ApMsgBody apMsgBody){

        this.processInfo = apFlowProcessVo;
        this.msgBody = apMsgBody;

    }


    private ApFlowProcessVo setExceptionContent(ApFlowProcessVo apFlowProcessVo,String code, String exceptionMessage, @Nullable Object[] args){

        return null;

    }



    @Deprecated
    public ScenarioException( String messageKey,  String scenarioType,String eventName,String siteId,
                              String workStateCode,String workId,String lotId,String carrId,String eqpId,String portId,
                              String errorCode,String errorComment) {
        super();
    }



}
