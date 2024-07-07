package com.abs.wfs.workman.spec.out.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class EapToolCondReqIvo extends ApMsgCommonVo {

	public static String system = ApSystemCodeConstant.EAP;
	public static String cid = WorkManMessageList.EAP_TOOL_COND_REQ;


	Body body;


	@Data
	public static class Body extends ApMsgBody {
		String inPortId;
		String outPortId;

	}
}
/*
{
  "head": {
    "cid": "EAP_TOOL_COND_REQ",
    "tid": "TID_20240703161900339415",
    "osrc": "",
    "otgt": "",
    "src": "WFS",
    "tgt": "EAP",
    "srcEqp": "",
    "tgtEqp": [
      "AP-PD-01-01"
    ]
  },
  "body": {
    "siteId": "SVM",
    "eqpId": "AP-PD-01-01",
    "userId": "WFS",
    "inPortId": "AP-PD-01-01-BP01",
    "outPortId": "AP-PD-01-01-BP01"
  }
}
 */