package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.eap.common.AttachFlagVo;
import com.abs.wfs.workman.spec.in.eap.common.SpecTypeVo;
import lombok.Data;


/*
{
    "head": {
        "cid": "WFS_MATERIAL_KITDEKIT_REPORT",
        "tid": "AP-TG-09-01_00_20240312161459970_18",
        "osrc": "",
        "otgt": "",
        "src": "EAP",
        "tgt": "WFS",
        "srcEqp": "",
        "tgtEqp": []
    },
    "body": {
        "siteId": "SVM",
        "eqpId": "AP-TG-09-01",
        "userId": "WFS",
        "subEqpId": "H10002",
        "specId": "a1233",
        "specType": "Durable",
        "attachFlag": "Y",
    }
}
 */
@Data
public class WfsMaterialKitdekitReportIvo extends ApMsgCommonVo {


    WfsMaterialKitdekitReportBody body;

    @Data
    public static class WfsMaterialKitdekitReportBody extends ApMsgBody {

        String specId;
        String subEqpId;
        SpecTypeVo specType;
        AttachFlagVo attachFlag;

    }

}
