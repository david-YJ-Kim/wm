package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsTrayUnloadReqIvo extends ApMsgCommonVo {

    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_TRAY_UNLOAD_REQ;
    Body body;

    @Data
    public static class Body extends ApMsgBody {

        String prodMtrlId;
    }
}
/*
{
  "head": {
    "tgt": "WFS",
    "tgtEqp": [

    ],
    "osrc": "",
    "srcEqp": "AM-RE-00-01",
    "src": "EAP",
    "tid": "AM-RE-00-01_00_20240729184240076",
    "cid": "WFS_TRAY_UNLOAD_REQ"
  },
  "body": {
    "carrId": "PTA0008",
    "siteId": "SVM",
    "prodMtrlId": "ET24500043",
    "eqpId": "AM-RE-00-01",
    "portId": "AM-RE-00-01-BP03",
    "userId": "Skilled Worker"
  }
}
 */