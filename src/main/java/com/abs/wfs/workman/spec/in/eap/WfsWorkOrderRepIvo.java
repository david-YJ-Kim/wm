package com.abs.wfs.workman.spec.in.eap;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.MsgReasonVo;
import com.abs.wfs.workman.spec.in.eap.common.WorkVo;
import lombok.Data;

import java.util.List;


/*
{
  "head": {
    "tgt": "WFS",
    "tgtEqp": [

    ],
    "srcEqp": "AP-LA-04-01",
    "osrc": "",
    "src": "EAP",
    "tid": "AP-LA-04-01_20240618200435991",
    "cid": "WFS_WORK_ORDER_REP"
  },
  "body": {
    "reason": {
      "reasonCode": "0",
      "reasonComment": ""
    },
    "prodDefId": "SE24-05-001",
    "work": [
      {
        "jobSeqId": "1",
        "portType": "BP",
        "slotMap": [
          {
            "mtrlFace": "TTT",
            "manualInsp": "N",
            "outCarrSlotNo": "1",
            "slotState": "",
            "lotId": "A24600070",
            "prodMtrlId": "FS24500005",
            "inCarrSlotNo": "1",
            "ppId": "0000LA0401UPPERSIDE"
          },
          {
            "mtrlFace": "TTT",
            "manualInsp": "N",
            "outCarrSlotNo": "2",
            "slotState": "",
            "lotId": "A24600070",
            "prodMtrlId": "FS24500004",
            "inCarrSlotNo": "2",
            "ppId": "0000LA0401UPPERSIDE"
          }
        ],
        "inCarrId": "CAA0220",
        "outPortId": "AP-LA-04-01-BP01",
        "scanYn": "N",
        "outCarrId": "CAA0220",
        "inPortId": "AP-LA-04-01-BP01"
      }
    ],
    "siteId": "SVM",
    "eqpId": "AP-LA-04-01",
    "procSgmtId": "P30101",
    "procDefId": "R00002",
    "userId": "EAP",
    "workId": "WORK_20240618200435933"
  }
}
 */
@Data
public class WfsWorkOrderRepIvo extends ApMsgCommonVo {

    WfsWorkOrderRepBody body;

    @Data
    public static class WfsWorkOrderRepBody extends ApMsgBody{

        String procSgmtId;
        String procDefId;
        String workId;
        MsgReasonVo reason;
        List<WorkVo> work;
    }
}
