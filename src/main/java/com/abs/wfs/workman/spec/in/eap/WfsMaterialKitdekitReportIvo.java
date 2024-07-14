package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.eap.common.SpecTypeVo;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import com.abs.wfs.workman.util.code.UseYn;
import lombok.Data;


@Data
public class WfsMaterialKitdekitReportIvo extends ApMsgCommonVo {

    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_MATERIAL_KITDEKIT_REPORT;


    Body body;

    @Data
    public static class Body extends ApMsgBody {

        String specId;
        String subEqpId;
        SpecTypeVo specType;
        UseYn attachFlag;

    }

}
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