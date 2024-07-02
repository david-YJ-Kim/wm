package com.abs.wfs.workman.spec.in.mcs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsCarrMoveCnclCompIvo extends ApMsgCommonVo {

    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_CARR_MOVE_CNCL_COMP;

    WfsCarrMoveCnclCompBoby body;

    @Data
    public static class WfsCarrMoveCnclCompBoby extends ApMsgBody{
        String commId;
        String srcEqpId;
        String srcPortId;
        String destEqpId;
        String destPortId;
        String crntEqpId;
        String crntPortId;
        Long prio;
        String carrMoveStat;
        Long resultCode;
        String eventComment;
        String eventUserId;
    }



}
/*
{
    "head" : {
        "tgt" : "WFS",
        "cid" : "WFS_CARR_MOVE_CNCL_COMP",
        "tid" : "171946923002085477",
        "osrc" : "MCS",
        "otgt" : "WFS",
        "src" : "MCS"
    },
    "body" : {
        "siteId" : "SVM",
        "carrId" : "CAA0049",
        "commId" : "C20240627021445145-CAA0049",
        "srcEqpId" : "ASTK01",
        "srcPortId" : "ASTK01_IP01.OP",
        "destEqpId" : "ACON01",
        "destPortId" : "ACON01_IP01",
        "crntEqpId" : "AAGV02",
        "crntPortId" : "AAGV02_V06",
        "prio" : 30,
        "resultCode" : 13,
        "eventComment" : "",
        "eventUserId" : "IFACTSMCS",
        "carrMoveStat" : "MOVING"
    }
}
* */
