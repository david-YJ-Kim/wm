package com.abs.wfs.workman.spec.in.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsEqpStgCpctChgIvo extends ApMsgCommonVo {

    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_EQP_STG_CPCT_CHG;

    WfsEqpStgCpctChgBody body;
    @Data
    public static class WfsEqpStgCpctChgBody extends ApMsgBody{
        String zoneId;
        Long maxCpct;
        Long crntCpct;
        String eventComment;
        String eventUserId;
    }
}
/*
{
  "head" : {
    "tgt" : "WFS",
    "cid" : "WFS_EQP_STG_CPCT_CHG",
    "tid" : "171958059705805561",
    "osrc" : "MCS",
    "otgt" : "WFS",
    "src" : "MCS"
  },
  "body" : {
    "siteId" : "SVM",
    "eqpId" : "ASTK02",
    "zoneId" : "ASTK02_STORAGE",
    "maxCpct" : 55,
    "crntCpct" : 40,
    "eventComment" : "",
    "eventUserId" : "ASTK02"
  }
}

 */