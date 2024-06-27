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
    "srcEqp": "AP-PD-01-01",
    "src": "EAP",
    "tid": "AP-PD-01-01_00_20240619094142370",
    "cid": "WFS_EQP_CONTROL_STATE_REPORT"
  },
  "body": {
    "siteId": "SVM",
    "eqpId": "AP-PD-01-01",
    "eqpCommStateCd": "Offline",
    "userId": "ABSOLICS"
  }
}
 */
@Data
public class WfsEqpControlStateReportIvo extends ApMsgCommonVo {


    WfsEqpControlStateReportBody body;

    @Data
    public static class WfsEqpControlStateReportBody extends ApMsgBody {
        String eqpCommStateCd;
    }
}
