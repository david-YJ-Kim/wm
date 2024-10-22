package com.abs.wfs.workman.spec.out.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class EapInitEqpStateReqIvo extends ApMsgCommonVo {

	public static String system = ApSystemCodeConstant.EAP;
	public static String cid = WorkManMessageList.EAP_INIT_EQP_STATE_REQ;
	
	Body body;


	@Data
	public static class Body extends ApMsgBody {
	}
}
/*
{
  "head": {
    "cid": "EAP_INIT_EQP_STATE_REQ",
    "tid": "AP-EM-09-01_00_20240712112945558",
    "osrc": "",
    "otgt": "",
    "src": "WFS",
    "tgt": "EAP"
  },
  "body": {
    "siteId": "SVM",
    "eqpId": "AP-EM-09-01",
  }
}
 */