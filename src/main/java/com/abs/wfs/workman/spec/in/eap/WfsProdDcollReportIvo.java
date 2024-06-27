package com.abs.wfs.workman.spec.in.eap;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.eap.common.DcollDataVo;
import lombok.Data;

import java.util.List;


/*
{
  "head": {
    "tgt": "WFS",
    "tgtEqp": [

    ],
    "osrc": "",
    "srcEqp": "AP-RD-05-01",
    "src": "EAP",
    "tid": "AP-RD-05-01_00_20240617095718643",
    "cid": "WFS_PROD_DCOLL_REPORT"
  },
  "body": {
    "siteId": "SVM",
    "lotId": "D24500086",
    "eqpId": "AP-RD-05-01",
    "userId": "EAP"
    "mtrlFace": "TTT",
    "subEqpId": "IN CV",
    "prodMtrlId": "ET24400204",
    "dcollData": [
      {
        "pName": "PM01_BM_01_MIN",
        "pValue": "3700.0"
      },
      {
        "pName": "PM01_BM_01_MAX",
        "pValue": "3700.0"
      },
      {
        "pName": "PM01_BM_01_AVG",
        "pValue": "3700.0"
      }
    ],
  }
}
 */
@Data
public class WfsProdDcollReportIvo extends ApMsgCommonVo {

    WfsProdDcollReportBody body;

    @Data
    public static class WfsProdDcollReportBody extends ApMsgBody{

        String mtrlFace;
        String subEqpId;
        String prodMtrlId;
        List<DcollDataVo> dcollData;
    }
}
