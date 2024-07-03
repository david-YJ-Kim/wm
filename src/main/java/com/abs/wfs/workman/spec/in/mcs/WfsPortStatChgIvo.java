package com.abs.wfs.workman.spec.in.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsPortStatChgIvo extends ApMsgCommonVo {
    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_PORT_STAT_CHG;

    WfsPortStatChgBody body;

    @Data
    public static class WfsPortStatChgBody extends ApMsgBody{
        Long portStst;
        String eventComment;
        String eventUserId;
    }

}
/*
{
  "head" : {
    "tgt" : "WFS",
    "cid" : "WFS_PORT_STAT_CHG",
    "tid" : "171968898388395977",
    "osrc" : "MCS",
    "otgt" : "WFS",
    "src" : "MCS"
  },
  "body" : {
    "siteId" : "SVM",
    "eqpId" : "ASTK03",
    "portId" : "ASTK03_IP02.LP",
    "portStat" : 1,
    "eventComment" : "",
    "eventUserId" : "IFACTSMCS"
  }
}
 */