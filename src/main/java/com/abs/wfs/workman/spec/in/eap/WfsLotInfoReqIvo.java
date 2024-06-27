package com.abs.wfs.workman.spec.in.eap;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

import java.util.List;


/*
{
    "head": {
        "cid": "WFS_LOT_INFO_REQ",
        "tgt": "WFS",
        "tid": "AP-TG-09-01_00_20240318165550824",
        "tgtEqp": [],
        "osrc": "",
        "srcEqp": "AP-TG-09-01",
        "src": "EAP",
        "otgt": ""
    },
    "body": {
        "siteId": "SVM",
        "eqpId": "AP-TG-09-01",
        "carrId": "CAA0138",
        "workId": "WORK_202303220000575",
        "userId": "EAP"
    }
}
 */
@Data
public class WfsLotInfoReqIvo extends ApMsgCommonVo {

    WfsLotInfoReqBody body;

    @Data
    public static class WfsLotInfoReqBody extends ApMsgBody{

        String workId;
    }
}
