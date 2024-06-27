package com.abs.wfs.workman.spec.in.eap;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.MsgReasonVo;
import lombok.Data;


/*
{
  "head": {
    "tgt": "WFS",
    "tgtEqp": [

    ],
    "osrc": "",
    "srcEqp": "AP-AT-05-03",
    "src": "EAP",
    "tid": "AP-AT-05-03_00_20240618165005578",
    "cid": "WFS_UNLOAD_COMP"
  },
  "body": {
    "portType": "BP",
    "siteId": "SVM",
    "eqpId": "AP-AT-05-03",
    "portId": "AP-AT-05-03-BP02",
    "userId": "Skilled Worker"
  }
}
 */
@Data
public class WfsUnloadCompIvo extends ApMsgCommonVo {

    WfsUnloadCompBody body;

    @Data
    public static class WfsUnloadCompBody extends ApMsgBody{

        String portType;
    }
}
