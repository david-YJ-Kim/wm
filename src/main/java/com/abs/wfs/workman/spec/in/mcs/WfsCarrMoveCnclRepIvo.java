package com.abs.wfs.workman.spec.in.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsCarrMoveCnclRepIvo extends ApMsgCommonVo {
    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_CARR_MOVE_CNCL_REP;

    WfsCarrMoveCnclRepBody body;

    @Data
    public static class WfsCarrMoveCnclRepBody extends ApMsgBody{
        String eventUserId;
        String eventComment;
        String commId;
        String resultCode;

    }
}
/*
{
  "head" : {
    "tgt" : "WFS",
    "cid" : "WFS_CARR_MOVE_CNCL_REP,
    "tid" : "171983728440055184",
    "osrc" : "MCS",
    "otgt" : "WFS",
    "src" : "MCS"
  },
  "body" : {
    "siteId" : "SVM",
	"eventUserId" : "WFS",
	"eventComment" : "",
    "carrId" : "CAA0061",
    "commId" : "TRANS_20240701083427884",
    "resultCode" : "0"
  }
}
 */