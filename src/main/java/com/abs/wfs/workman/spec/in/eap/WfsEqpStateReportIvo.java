package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsEqpStateReportIvo extends ApMsgCommonVo {

    public static String cid = WorkManMessageList.WFS_EQP_STATE_REPORT;
    public static String system = ApSystemCodeConstant.WFS;

    Body body;

    @Data
    public static class Body extends ApMsgBody{

        String eqpStateCd;

    }


    public static String sampleMessage = "{\n" +
            "  \"head\": {\n" +
            "    \"tgt\": \"WFS\",\n" +
            "    \"tgtEqp\": [\n" +
            "\n" +
            "    ],\n" +
            "    \"osrc\": \"\",\n" +
            "    \"srcEqp\": \"AP-PD-08-01\",\n" +
            "    \"src\": \"EAP\",\n" +
            "    \"tid\": \"AP-PD-08-01_00_20240618111657587\",\n" +
            "    \"cid\": \"WFS_EQP_STATE_REPORT\"\n" +
            "  },\n" +
            "  \"body\": {\n" +
            "    \"eqpStateCd\": \"Idle\",\n" +
            "    \"siteId\": \"SVM\",\n" +
            "    \"eqpId\": \"AP-PD-08-01\",\n" +
            "    \"userId\": \"EAP\"\n" +
            "  }\n" +
            "}";
}
/*
{
  "head": {
    "tgt": "WFS",
    "tgtEqp": [

    ],
    "osrc": "",
    "srcEqp": "AP-PD-08-01",
    "src": "EAP",
    "tid": "AP-PD-08-01_00_20240618111657587",
    "cid": "WFS_EQP_STATE_REPORT"
  },
  "body": {
    "eqpStateCd": "Idle",
    "siteId": "SVM",
    "eqpId": "AP-PD-08-01",
    "userId": "EAP"
  }
}
 */
