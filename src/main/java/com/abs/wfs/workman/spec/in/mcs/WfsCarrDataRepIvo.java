package com.abs.wfs.workman.spec.in.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsCarrDataRepIvo extends ApMsgCommonVo {
    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_CARR_DATA_REP;

    @Data
    public static class WfsCarrDataRepBody extends ApMsgBody{
        String eventUserId;
        String eventComment;
        String crntEqpId;
        String crntPortId;
        String resultCode;
    }
}

/*
{
  "head" : {
    "tgt" : "WFS",
    "cid" : "WFS_CARR_DATA_REP",
    "tid" : "171960182090021472",
    "osrc" : "MCS",
    "otgt" : "WFS",
    "src" : "MCS"
  },
  "body" : {
    "siteId" : "SVM",
	"eventUserId" : "IFACTSMCS",
	"eventComment" : "",
    "carrId" : "CAA0084",
    "crntEqpId" : "ASTK03",
    "crntPortId" : "ASTK03_IP02.OP",
    "resultCode" : "0"
  }
}
 */