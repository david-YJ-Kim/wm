package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsLoadReqIvo extends ApMsgCommonVo {


    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_LOAD_REQ;

    WfsLoadReqBody body;

    @Data
    public static class WfsLoadReqBody extends ApMsgBody {
        String portState;
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
    "srcEqp": "AP-RD-11-01",
    "src": "EAP",
    "tid": "AP-RD-11-01_00_20240701113835572",
    "cid": "WFS_LOAD_REQ"
  },
  "body": {
    "portType": "BP",
    "siteId": "SVM",
    "eqpId": "AP-RD-11-01",
    "portId": "AP-RD-11-01-BP02",
    "userId": "Skilled Worker"
  }
}
 */