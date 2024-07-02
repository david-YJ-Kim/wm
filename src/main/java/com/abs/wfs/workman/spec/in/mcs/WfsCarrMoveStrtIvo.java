package com.abs.wfs.workman.spec.in.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsCarrMoveStrtIvo extends ApMsgCommonVo {
    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_CARR_MOVE_STRT;

    WfsCarrMoveStrtBody body;

    @Data
    public static class WfsCarrMoveStrtBody extends ApMsgBody{
        String commId;
        String srcEqpId;
        String srcPortId;
        String destEqpId;
        String destPortId;
        String crntEqpId;
        String crntPortId;
        Long prio;
        String eventComment;
        String eventUserId;
    }
}
/*
{
  "head" : {
    "tgt" : "WFS",
    "cid" : "WFS_CARR_MOVE_STRT",
    "tid" : "171985840313474047",
    "osrc" : "MCS",
    "otgt" : "WFS",
    "src" : "MCS"
  },
  "body" : {
    "siteId" : "SVM",
    "carrId" : "CAA0044",
    "commId" : "TRANS_20240701142642617781",
    "srcEqpId" : "ABUF01",
    "srcPortId" : "ABUF01_Z01",
    "destEqpId" : "AP-TG-04-01",
    "destPortId" : "AP-TG-04-01-BP02",
    "crntEqpId" : "ABUF01",
    "crntPortId" : "ABUF01_Z01",
    "prio" : 50,
    "eventComment" : "",
    "eventUserId" : "OI_yuny91"
  }
}
 */
