package com.abs.wfs.workman.spec.in.oia;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsOiCarrDestChgReqIvo extends ApMsgCommonVo {

    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_OI_CARR_DEST_CHG_REQ;

    WfsOiCarrMoveCrtBody body;

    @Data
    public static class WfsOiCarrMoveCrtBody extends ApMsgBody {

        String commId;
        String destEqpId;
        String destPortId;
        String comment;
        String prio;



    }
}
/*
{
  "head": {
    "cid": "WFS_OI_CARR_MOVE_CRT_OI",
    "osrc": "",
    "otgt": "",
    "src": "OIA",
    "srcEqp": "",
    "tgt": "WFS",
    "tgtEqp": [

    ],
    "tid": "UI_20240627151943",
    "lang": "en"
  },
  "body": {
    "carrId": "CAA0104",
    "commId": "CST001_CMD02",
    "destEqpId": "AP-TG-07-01",
    "destPortId": "AP-TG-07-01-BP02",
    "comment": "sl2 200\n",
    "userId": "OI_2404002",
    "siteId": "SVM",
	"prio": "50"
  }
}
 */