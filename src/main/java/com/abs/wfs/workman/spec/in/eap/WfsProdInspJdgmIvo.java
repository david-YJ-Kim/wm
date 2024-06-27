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
    "srcEqp": "AP-TG-09-01",
    "src": "EAP",
    "tid": "AP-TG-09-01_00_20240617181251942",
    "cid": "WFS_PROD_INSP_JDGM"
  },
  "body": {
    "siteId": "SVM",
    "lotId": "",
    "eqpId": "AP-TG-09-01",
    "userId": "Admin"
    "mtrlFace": "",
    "judgeMap": "VXXXXXXVXXXXXXXVXXXXXXXXXXVXXXXXVXXXXVVXXVXXXVXX",
    "prodMtrlId": "GLASS-02",
  }
}
 */
@Data
public class WfsProdInspJdgmIvo extends ApMsgCommonVo {

    WfsProdInspJdgmBody body;

    @Data
    public static class WfsProdInspJdgmBody extends ApMsgBody{

        String mtrlFace;
        String judgeMap;
        String prodMtrlId;
    }
}
