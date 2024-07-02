package com.abs.wfs.workman.spec.in.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsCarrDestChgRepIvo extends ApMsgCommonVo {
    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_CARR_DEST_CHG_REP;

    WfsCarrDestChgRepBody wfsCarrDestChgRepBody;

    @Data
    public static class WfsCarrDestChgRepBody extends ApMsgBody{
        String eventUserId;
        String eventComment;
        String commId;
        String destEqpId;
        String destPortId;
        Long prio;
        String resultCode;
    }
}
/*
{
        "head" : {
        "tgt" : "WFS",
        "cid" : "WFS_CARR_DEST_CHG_REP",
        "tid" : "171984973131897443",
        "osrc" : "MCS",
        "otgt" : "WFS",
        "src" : "MCS"
        },
        "body" : {
        "siteId" : "SVM",
        "eventComment" : "",
        "eventUserId" : "OI_cslee0519",
        "carrId" : "CAA0195",
        "commId" : "TRANS_20240701120133289536",
        "destEqpId" : "AP-OL-13-01",
        "destPortId" : "AP-OL-13-01-BP01",
        "prio" : 50,
        "resultCode" : 0
        }
        }
 */