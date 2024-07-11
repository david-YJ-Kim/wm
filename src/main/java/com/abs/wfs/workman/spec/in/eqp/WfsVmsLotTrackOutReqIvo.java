package com.abs.wfs.workman.spec.in.eqp;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsVmsLotTrackOutReqIvo extends ApMsgCommonVo {

    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_VMS_LOT_TRACK_OUT_REQ;

    private Body body;

    @Data
    public static class Body extends ApMsgBody {
        String autoReposition;
        String asgnQty;
        String workId;
        String carrList;
    }

}

/*
{
  "head": {
    "cid": "WFS_VMS_LOT_TRACK_OUT_REQ",
    "tid": "D24600123_20240710153715",
    "osrc": "",
    "otgt": "",
    "src": "EQP",
    "tgt": "WFS",
    "srcEqp": "AP-TG-10-01",
    "tgtEqp": [

    ]
  },
  "body": {
    "siteId": "SVM",
    "lotId": "D24600123",
    "eqpId": "AP-TG-10-01",
    "autoReposition": "true",
    "asgnQty": "",
    "workId": "",
    "recipeId": "",
    "portId": "",
    "carrId": "",
    "carrList": "",
    "userId": "VMS_MASTER"
  }
}
 */
