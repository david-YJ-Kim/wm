package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class WfsCarrIdReadIvo extends ApMsgCommonVo {

    WfsCarrIdReadBody body;

    @Data
    public static class WfsCarrIdReadBody extends ApMsgBody {
        String portType;
    }


    public static String getMessageSampleFormat(String eppId, String portId, String portType){
        return "";
    }


}
