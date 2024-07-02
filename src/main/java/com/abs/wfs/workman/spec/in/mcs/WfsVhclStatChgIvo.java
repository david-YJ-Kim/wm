package com.abs.wfs.workman.spec.in.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsVhclStatChgIvo extends ApMsgCommonVo {
    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_VHCL_STAT_CHG;

    WfsVhclStatChgBody body;

    @Data
    public static class WfsVhclStatChgBody extends ApMsgBody{
        String vhclId;
        String vhclStat;
        String eventComment;
        String eventUserId;
    }
}
/*
{
  "head" : {
    "tgt" : "WFS",
    "cid" : "WFS_VHCL_STAT_CHG",
    "tid" : "171940909441213328",
    "osrc" : "MCS",
    "otgt" : "WFS",
    "src" : "MCS"
  },
  "body" : {
    "siteId" : "SVM",
    "eqpId" : "AAGV01",
    "vhclId" : "AAGV01_V01",
    "vhclStat" : 1,
    "eventComment" : "",
    "eventUserId" : "IFACTSMCS"
  }
}
 */