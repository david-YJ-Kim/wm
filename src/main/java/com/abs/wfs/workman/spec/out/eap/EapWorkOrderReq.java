package com.abs.wfs.workman.spec.out.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.out.eap.common.Work;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

import java.util.List;

@Data
public class EapWorkOrderReq extends ApMsgCommonVo {

	public static String system = ApSystemCodeConstant.EAP;
	public static String cid = WorkManMessageList.EAP_WORK_ORDER_REQ;
	Body body;



	@Data
	public static class Body extends ApMsgBody {

		String workId;
		String prodDefId;
		String procDefId;
		String procSgmtId;

		List<Work> work;

	}
}
/*
{
  "head": {
    "cid": "EAP_WORK_ORDER_REQ",
    "tid": "TID_20240722140014593704",
    "osrc": "",
    "otgt": "",
    "src": "WFS",
    "tgt": "EAP",
    "srcEqp": "",
    "tgtEqp": [
      "AP-TG-02-01"
    ]
  },
  "body": {
    "siteId": "SVM",
    "eqpId": "AP-TG-02-01",
    "userId": "WFS",
    "workId": "WORK_20240722140014666",
    "prodDefId": "SE24-04-001",
    "procDefId": "R00032D007",
    "procSgmtId": "P40301",
    "work": [
      {
        "jobSeqId": "1",
        "scanYn": "N",
        "inPortId": "AP-TG-02-01-BP02",
        "inCarrId": "CAA0349",
        "outPortId": "AP-TG-02-01-BP02",
        "outCarrId": "CAA0349",
        "portType": "BP",
        "slotMap": [
          {
            "inCarrSlotNo": "1",
            "outCarrSlotNo": "1",
            "ppId": "RY5110",
            "lotId": "A24700030",
            "prodMtrlId": "FS24600101",
            "slotState": "",
            "mtrlFace": "TTT",
            "manualInsp": "N"
          }
        ]
      }
    ]
  }
}
 */