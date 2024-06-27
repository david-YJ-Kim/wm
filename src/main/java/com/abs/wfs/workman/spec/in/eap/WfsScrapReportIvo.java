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
    "srcEqp": "AP-SR-04-01",
    "src": "EAP",
    "tid": "AP-SR-04-01_00_20240617182753551",
    "cid": "WFS_SCRAP_REPORT"
  },
  "body": {
    "siteId": "SVM",
    "lotId": "D24600090",
    "eqpId": "AP-SR-04-01",
    "carrId": "CAA0273",
    "portId": "AP-SR-04-01-IP01",
    "userId": "SS",
    "subProdMtrlId": "",
    "prodMtrlId": "ET24401456",
    "reasonComment": "AAA",
    "ppId": "DR3RCP.000"
  }
}
 */
@Data
public class WfsScrapReportIvo extends ApMsgCommonVo {

    WfsScrapReportBody body;

    @Data
    public static class WfsScrapReportBody extends ApMsgBody{

        String subProdMtrlId;
        String prodMtrlId;
        String reasonComment;
        String ppId;

    }
}
