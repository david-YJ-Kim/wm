package com.abs.wfs.workman.spec.in.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsCarrLocChgIvo extends ApMsgCommonVo {

    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_CARR_LOC_CHG;

    WfsCarrLocChgBoby boby;

    @Data
    public static class WfsCarrLocChgBoby extends ApMsgBody{
        String commId;
        String crntEqpId;
        String crntPortId;
        String carrMoveStat;
        String eventComment;
        String eventUserId;

    }

}

/*
{
  "head" : {
    "tgt" : "WFS",
    "cid" : "WFS_CARR_LOC_CHG",
    "tid" : "171983728440055184",
    "osrc" : "MCS",
    "otgt" : "WFS",
    "src" : "MCS"
  },
  "body" : {
    "siteId" : "SVM",
    "carrId" : "CAA0061",
    "commId" : "TRANS_20240701083427884",
    "crntEqpId" : "ASTK01",
    "crntPortId" : "ASTK01_RM",
    "carrMoveStat" : "MOVING",
    "eventComment" : "",
    "eventUserId" : "WFS"
  }
}
* */