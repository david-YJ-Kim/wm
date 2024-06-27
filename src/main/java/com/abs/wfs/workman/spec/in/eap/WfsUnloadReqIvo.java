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
    "srcEqp": "AP-RD-03-01",
    "src": "EAP",
    "tid": "AP-RD-03-01_00_20240618155231823",
    "cid": "WFS_UNLOAD_REQ"
  },
  "body": {
    "portType": "BP",
    "carrId": "",
    "siteId": "SVM",
    "eqpId": "AP-RD-03-01",
    "portId": "AP-RD-03-01-BP02",
    "userId": "ADMIN"
  }
}
 */
@Data
public class WfsUnloadReqIvo extends ApMsgCommonVo {

    WfsUnloadReqBody body;

    @Data
    public static class WfsUnloadReqBody extends ApMsgBody{

        String portType;
    }
}
