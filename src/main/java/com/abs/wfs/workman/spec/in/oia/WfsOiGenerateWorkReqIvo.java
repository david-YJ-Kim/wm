package com.abs.wfs.workman.spec.in.oia;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsOiGenerateWorkReqIvo extends ApMsgCommonVo {

    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_OI_GENERATE_WORK_REQ;

    Body body;
    @Data
    public static class Body extends ApMsgBody {

        String inCarrTyp;
        String lotQty;
        String prodMtrlId;
        String prodDefId;
        String procDefId;
        String procSgmtId;

        String slotNo; // measurement room (re-00) 전용 파라미터로, 투입할 CARR slot 위치, Tray 는 1 고정
        String panelInputYn;  // measurement room (re-00) 전용 파라미터로, 투입 작업 시 Y
    }

}
/*
{
  "head": {
    "tgt": "WFS",
    "tgtEqp": [

    ],
    "osrc": "",
    "srcEqp": "AP-RD-06-01",
    "src": "OI",
    "tid": "AP-RD-06-01_00_20240723110337314",
    "cid": "WFS_OI_GENERATE_WORK_REQ"
  },
  "body": {
    "siteId": "SVM",
    "eqpId": "AM-RE-00-01",
    "lotId": "S23C00010",
    "inCarrTyp": "BP",
    "lotQty": "1",
    "carrId": "CAA0203",
    "panelInputYn": "Y",
    "portId": "AM-RE-00-01-BP01",
    "prodMtrlId": "PANEL_23012501_020",
    "prodDefId": "HB1-220101-09",
    "procDefId": "MF-HB-220101-09",
    "procSgmtId": "G2004",
    "userId": "david.kim",

    "panelInputYn": "Y"
  }
}
 */