package com.abs.wfs.workman.spec.in.eap;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsQuantityMatchStateIvo extends ApMsgCommonVo {


    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_QUANTITY_MATCH_STATE;

    Body body;

    @Data
    public static class Body extends ApMsgBody {

        String workId;
        String trayId;
        String eventType;
    }
}
/*
{
  "head": {
    "tgt": "WFS",
    "tgtEqp": [

    ],
    "osrc": "",
    "srcEqp": "AP-MR-01-01",
    "src": "EAP",
    "tid": "AP-MR-01-01_00_20240604202856255",
    "cid": "WFS_QUANTITY_MATCH_STATE"
  },
  "body": {
    "carrId": "CAA0042",
    "siteId": "SVM",
    "lotId": "A24600060",
    "eventType": "Under",
    "eqpId": "AP-MR-01-01",
    "userId": "EAP",
    "workId": "WORK_2023032200005"
  }
}
 */