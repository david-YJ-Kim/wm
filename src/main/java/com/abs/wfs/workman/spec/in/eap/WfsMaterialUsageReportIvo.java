package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.eap.common.SpecTypeVo;
import lombok.Data;


/*
{
  "head": {
    "tgt": "WFS",
    "tgtEqp": [

    ],
    "osrc": "",
    "srcEqp": "AP-TG-02-01",
    "src": "EAP",
    "tid": "AP-TG-02-01_00_20240617112619807",
    "cid": "WFS_MATERIAL_USAGE_REPORT"
  },
  "body": {
    "siteId": "SVM",
    "lotId": "Lot_20240617111154133",
    "eqpId": "AP-TG-02-01",
    "userId": "MASTER"
    "specId": "MAT002",
    "specType": "Material",
    "specUseCnt": "758",
    "subEqpId": "PM1",
    "specLimitCnt": "5000",
  }
}
 */
@Data
public class WfsMaterialUsageReportIvo extends ApMsgCommonVo {


    WfsMaterialUsageReportBody body;

    @Data
    public static class WfsMaterialUsageReportBody extends ApMsgBody {

        String specId;
        SpecTypeVo specType;
        String specUseCnt;
        String subEqpId;
        String specLimitCnt;


    }

}
