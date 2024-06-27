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
    "srcEqp": "AP-EM-02-01",
    "osrc": "",
    "src": "EAP",
    "tid": "AP-EM-02-01_20240619163903202",
    "cid": "WFS_MATERIAL_CHANGE_REQ"
  },
  "body": {
    "siteId": "SVM",
    "eqpId": "AP-EM-02-01",
    "userId": "EAP"
    "specId": "TESTEST",
    "subEqpId": "SX2V3_R2570G",
  }
}
 */
@Data
public class WfsMaterialChangeReqIvo extends ApMsgCommonVo {


    WfsMaterialChangeReqBody body;

    @Data
    public static class WfsMaterialChangeReqBody extends ApMsgBody {

        String specId;
        String subEqpId;

    }

}
