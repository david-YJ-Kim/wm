package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.MsgReasonVo;
import lombok.Data;

@Data
public class WfsReadyToWorkVo extends ApMsgCommonVo {

    WfsWorkOrderRepBody body;

    @Data
    public static class WfsWorkOrderRepBody extends ApMsgBody {
        String eqpId;
        String workId;
        String portId;
        MsgReasonVo reason;
    }


    public static String getMessageSampleFormat(String eppId, String portId, String portType){
        return "";
    }


}
