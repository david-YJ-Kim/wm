package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.MsgReasonVo;
import com.abs.wfs.workman.spec.in.eap.common.WorkVo;
import lombok.Data;

/*
{
  "head": {
    "tgt": "WFS",
    "tgtEqp": [

    ],
    "srcEqp": "AP-TG-08-01",
    "osrc": "",
    "src": "EAP",
    "tid": "AP-TG-08-01_20240620102533050",
    "cid": "WFS_WORK_START_REP"
  },
  "body": {
    "reason": {
      "reasonCode": "0",
      "reasonComment": ""
    },
    "work": [
      {
        "jobSeqId": "1",
        "portType": "BP",
        "slotMap": [
          {
            "mtrlFace": "TTT",
            "manualInsp": "N",
            "outCarrSlotNo": "4",
            "slotState": "",
            "lotId": "D24500080",
            "prodMtrlId": "ET24400194",
            "inCarrSlotNo": "4",
            "ppId": "0000TG0801SFTEST0001.000"
          },
          {
            "mtrlFace": "TTT",
            "manualInsp": "N",
            "outCarrSlotNo": "6",
            "slotState": "",
            "lotId": "D24500080",
            "prodMtrlId": "ET24400196",
            "inCarrSlotNo": "6",
            "ppId": "0000TG0801SFTEST0001.000"
          }
        ],
        "inCarrId": "CAA0019",
        "outPortId": "AP-TG-08-01-BP01",
        "scanYn": "N",
        "outCarrId": "CAA0019",
        "inPortId": "AP-TG-08-01-BP01"
      }
    ],
    "siteId": "SVM",
    "eqpId": "AP-TG-08-01",
    "userId": "EAP",
    "workId": "WORK_20240620102532436"
  }
}
 */

@Data
public class WfsWorkStartRepIvo extends ApMsgCommonVo {

	private WfsWorkStartRepBody body;

	@Data
	public static class WfsWorkStartRepBody extends ApMsgBody{
		String workId;
		WorkVo work;
		MsgReasonVo reason;
	}
}
