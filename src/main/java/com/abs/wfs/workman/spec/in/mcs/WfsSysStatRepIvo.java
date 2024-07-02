package com.abs.wfs.workman.spec.in.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsSysStatRepIvo extends ApMsgCommonVo {
    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_SYS_STAT_REP;

    WfsSysStatRepBody body;

    @Data
    public static class WfsSysStatRepBody extends ApMsgBody{
        String eventUserId;
        String eventComment;
        String mcsId;
        String mcsStat;
        String resultCode;
    }
}
/*
{
  "head" : {
    "tgt" : "WFS",
    "cid" : "WFS_SYS_STAT_REP",
    "tid" : "171940909441213328",
    "osrc" : "MCS",
    "otgt" : "WFS",
    "src" : "MCS"
  },
  "body" : {
    "siteId" : "SVM",
    "eventUserId" : "IFACTSMCS",
    "eventComment" : "",
    "mcsId" : "test",
    "mcsStat" : "test",
	"resultCode" : "0"
  }
}
 */