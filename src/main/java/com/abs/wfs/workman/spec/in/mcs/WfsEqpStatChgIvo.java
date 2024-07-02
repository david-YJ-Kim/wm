package com.abs.wfs.workman.spec.in.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsEqpStatChgIvo extends ApMsgCommonVo {
    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_EQP_STAT_CHG;

    WfsEqpStatChgBody body;

    @Data
    public static class WfsEqpStatChgBody extends ApMsgBody{
        String eqpStat;
        String eventUserId;
        String evnetComment;
    }
}
/*
{
  "head" : {
    "tgt" : "WFS",
    "cid" : "WFS_EQP_STAT_CHG",
    "tid" : "171957823512728777",
    "osrc" : "MCS",
    "otgt" : "WFS",
    "src" : "MCS"
  },
  "body" : {
    "siteId" : "SVM",
    "eqpId" : "AAGV02",
    "eqpStat" : 0,
    "eventComment" : "",
    "eventUserId" : "AAGV02"
  }
}
 */