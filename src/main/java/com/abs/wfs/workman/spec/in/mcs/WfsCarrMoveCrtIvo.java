package com.abs.wfs.workman.spec.in.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsCarrMoveCrtIvo extends ApMsgCommonVo {

    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_CARR_MOVE_CRT;

    WfsCarrMoveCrtBody body;

    @Data
    public static class WfsCarrMoveCrtBody extends ApMsgBody{
        String commId;
        String srcEqpId;
        String srcPortId;
        String destEqpId;
        String destPortId;
        Long prio;
        String eventComment;
        String eventUserId;
    }

}
/*
{
  "head" : {
    "tgt" : "WFS",
    "cid" : "WFS_CARR_MOVE_CRT",
    "tid" : "171986138024809263",
    "osrc" : "MCS",
    "otgt" : "WFS",
    "src" : "MCS"
  },
  "body" : {
    "siteId" : "SVM",
    "carrId" : "UNK-2024070115152363",
    "commId" : "M20240701151620348-UNK-2024070115152363",
    "srcEqpId" : "ASTK03",
    "srcPortId" : "ASTK03_IP01.OP",
    "destEqpId" : "ASTK03",
    "destPortId" : "ASTK03_IO01.OP",
    "prio" : 50,
    "eventComment" : "",
    "eventUserId" : "IFACTSMCS"
  }
}
 */