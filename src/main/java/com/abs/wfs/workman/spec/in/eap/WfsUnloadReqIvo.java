package com.abs.wfs.workman.spec.in.eap;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;


@Data
public class WfsUnloadReqIvo extends ApMsgCommonVo {

    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_UNLOAD_REQ;

    Body body;

    @Data
    public static class Body extends ApMsgBody{

        String portType;
    }
}

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