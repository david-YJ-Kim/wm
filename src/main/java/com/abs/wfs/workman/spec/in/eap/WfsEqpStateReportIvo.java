package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

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
@Data
public class WfsEqpStateReportIvo extends ApMsgCommonVo {

    WfsEqpStateReportBody body;

    @Data
    public static class WfsEqpStateReportBody extends ApMsgBody{

        String eqpStateCd;

    }
}
