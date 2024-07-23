package com.abs.wfs.workman.spec.in.oia;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsOiPortUnloadReqIvo extends ApMsgCommonVo {

    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_OI_PORT_UNLOAD_REQ;

    Body body;
    @Data
    public static class Body extends ApMsgBody {

        String portType;
    }

}/*
{
  "head": {
    "tgt": "WFS",
    "tgtEqp": [

    ],
    "osrc": "",
    "srcEqp": "AP-RD-06-01",
    "src": "OI",
    "tid": "AP-RD-06-01_00_20240723110337314",
    "cid": "WFS_OI_PORT_UNLOAD_REQ"
  },
  "body": {
    "siteId": "SVM",
    "portType": "BP",
    "carrId": "CAA0260",
    "eqpId": "AP-RD-06-01",
    "portId": "AP-RD-06-01-BP01",
    "userId": "david.kim"
  }
}
 */
