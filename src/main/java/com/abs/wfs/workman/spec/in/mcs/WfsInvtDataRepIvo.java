package com.abs.wfs.workman.spec.in.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsInvtDataRepIvo extends ApMsgCommonVo {
    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_INVT_DATA_REP;

    WfsInvtDataRepBody body;

    @Data
    public static class WfsInvtDataRepBody extends ApMsgBody{
        String eventUserId;
        String eventComment;
        String resultCode;
    }
}
/*
{
  "head" : {
    "tgt" : "WFS",
    "cid" : "WFS_INVT_DATA_REP",
    "tid" : "171958059705805561",
    "osrc" : "MCS",
    "otgt" : "WFS",
    "src" : "MCS"
  },
  "body" : {
    "siteId" : "SVM",
	"eventUserId" : "ASTK02",
	"eventComment" : "",
    "eqpId" : "ASTK02",
    "resultCode":""
  }
}
 */