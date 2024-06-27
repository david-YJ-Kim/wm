package com.abs.wfs.workman.spec.in.eap;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.MsgReasonVo;
import lombok.Data;


/*
{
  "head": {
    "tgt": "WFS",
    "tgtEqp": [

    ],
    "osrc": "",
    "srcEqp": "AP-BU-03-01",
    "src": "EAP",
    "tid": "AP-BU-03-01-Both_20240620095502685",
    "cid": "WFS_SORTER_MODE_CHANGE_REP"
  },
  "body": {
    "reason": {
      "reasonCode": "0",
      "reasonComment": ""
    },
    "modeType": "Sorter",
    "siteId": "SVM",
    "eqpId": "AP-BU-03-01"
  }
}
 */
@Data
public class WfsSorterModeChangeRepIvo extends ApMsgCommonVo {

    WfsSorterModeChangeRepBody body;

    @Data
    public static class WfsSorterModeChangeRepBody extends ApMsgBody{

        String modeType;
        MsgReasonVo reason;
    }
}
