package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class WfsLoadReqIvo extends ApMsgCommonVo {

    WfsLoadReqBody body;

    @Data
    public static class WfsLoadReqBody extends ApMsgBody {
        String portState;
        String portType;
    }


    public static String getMessageSampleFormat(String eppId, String portId, String portType){
        String format =  "{\n" +
                "\"head\": {\n" +
                "        \"cid\": \"WFS_LOAD_REQ\",\n" +
                "        \"tid\": \"%s\",\n" +
                "        \"osrc\": \"\",\n" +
                "        \"otgt\": \"\",\n" +
                "        \"src\": \"EAP\",\n" +
                "        \"srcEqp\": \"\",\n" +
                "        \"tgt\": \"WFS\",\n" +
                "        \"tgtEqp\": [] \n" +
                "    },\n" +
                "\"body\": {\n" +
                "        \"siteId\": \"SVM\",\n" +
                "        \"eqpId\": \"%s\",\n" +
                "        \"portId\": \"%s\",\n" +
                "        \"portType\": \"%s\",\n" +
                "        \"userId\": \"EAP\"\n" +
                "    }\n" +
                "}";
        return String.format(format,
                System.currentTimeMillis(),eppId, portId, portType);
    }


}
