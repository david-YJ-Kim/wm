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
    "srcEqp": "AP-LA-03-01",
    "src": "EAP",
    "tid": "AP-LA-03-01_00_20240620092050818",
    "cid": "WFS_LOAD_COMP"
  },
  "body": {
    "portType": "BP",
    "siteId": "SVM",
    "eqpId": "AP-LA-03-01",
    "portId": "AP-LA-03-01-BP01",
    "userId": "Skilled Worker"
  }
}
 */
@Data
public class WfsLoadCompIvo extends ApMsgCommonVo {
    WfsLoadCompBody body;

    @Data
    public static class WfsLoadCompBody extends ApMsgBody{
        String portType;
    }

}
