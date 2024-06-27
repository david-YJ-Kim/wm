package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class WfsCarrSlotmapReportReqVo extends ApMsgCommonVo {

    WfsCarrSlotmapReportReqBody body;

    @Data
    public static class WfsCarrSlotmapReportReqBody extends ApMsgBody {
        String portType;
        String slotMap;
    }


    public static String getMessageSampleFormat(String eppId, String portId, String portType){
        return "";
    }


}
