package com.abs.wfs.workman.spec.in.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsCarrMoveRepIvo extends ApMsgCommonVo {
    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_CARR_MOVE_REP;

    WfsCarrMoveRepBody body;

    @Data
    public static class WfsCarrMoveRepBody extends ApMsgBody{
        String commId;
        String srcEqpId;
        String srcPortId;
        String destEqpId;
        String destPortId;
        Long prio;
        Long resultCode;
        String eventComment;
        String eventUserId;
    }

}

/*
{
  "head" : {
    "tgt" : "WFS",
    "cid" : "WFS_CARR_MOVE_REP",
    "tid" : "TID_20240701142642606652",
    "osrc" : "MCS",
    "otgt" : "WFS",
    "src" : "MCS"
  },
  "body" : {
    "siteId" : "SVM",
    "carrId" : "CAA0044",
    "commId" : "TRANS_20240701142642617781",
    "srcEqpId" : "ABUF01",
    "srcPortId" : "ABUF01_Z01_BP01",
    "destEqpId" : "AP-TG-04-01",
    "destPortId" : "AP-TG-04-01-BP02",
    "prio" : 50,
    "resultCode" : 0,
    "eventComment" : "",
    "eventUserId" : "OI_yuny91"
  }
}
*/