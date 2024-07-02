package com.abs.wfs.workman.spec.in.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsCarrDataQryIvo extends ApMsgCommonVo {

    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_CARR_DATA_QRY;

    WfsCarrDataQryBody body;

    @Data
    public static class WfsCarrDataQryBody extends ApMsgBody{
        String crntEqpId;
        String crntPortId;
        String eventComment;
        String eventUserId;

    }
}

/*
{
  "head" : {
    "tgt" : "WFS",
    "cid" : "WFS_CARR_DATA_QRY",
    "tid" : "171960182090021472",
    "osrc" : "MCS",
    "otgt" : "WFS",
    "src" : "MCS"
  },
  "body" : {
    "siteId" : "SVM",
    "carrId" : "CAA0084",
    "crntEqpId" : "ASTK03",
    "crntPortId" : "ASTK03_IP02.OP",
    "eventComment" : "",
    "eventUserId" : "IFACTSMCS"
  }
}
 */