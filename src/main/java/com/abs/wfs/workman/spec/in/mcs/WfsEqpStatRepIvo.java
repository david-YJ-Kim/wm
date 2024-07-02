package com.abs.wfs.workman.spec.in.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsEqpStatRepIvo extends ApMsgCommonVo {
    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_EQP_STAT_REP;

    WfsEqpStatRepBody body;

    @Data
    public static class WfsEqpStatRepBody extends ApMsgBody{
        String eventUserId;
        String eventComment;
        Long eqpStat;
        String resultCode;
    }
}

/*

{
  "head" : {
    "tgt" : "WFS",
    "cid" : "WFS_EQP_STAT_REP",
    "tid" : "171957823512728777",
    "osrc" : "MCS",
    "otgt" : "WFS",
    "src" : "MCS"
  },
  "body" : {
    "siteId" : "SVM",
	"eventUserId" : "AAGV02",
	"eventComment" : "",
    "eqpId" : "AAGV02",
    "eqpStat" : 0,
	"resultCode" : "0"
  }
}
*/