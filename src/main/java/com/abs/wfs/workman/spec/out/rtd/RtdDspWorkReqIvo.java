package com.abs.wfs.workman.spec.out.rtd;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class RtdDspWorkReqIvo extends ApMsgCommonVo {

	public static String system = ApSystemCodeConstant.RTD;
	public static String cid = WorkManMessageList.RTD_DSP_WORK_REQ;


	private RtdDspWorkReqBody body;

	@Data
	public static class RtdDspWorkReqBody extends ApMsgBody {

		private String dspType;
		private String prodDefId;
		private String procDefId;
		private String rsltCm;
		private String rsnCd;
		private String evntCm;
		private String evntUserId;

	}
}
/*
{
  "head": {
    "cid": "RTD_DSP_WORK_REQ",
    "tid": "172027766718931596",
    "osrc": "",
    "otgt": "",
    "src": "WFS",
    "tgt": "RTD"
  },
  "body": {
    "siteId": "SVM",
    "lotId": "",
    "eqpId": "ASTK02",
    "portId": "ASTK02_MP01.OP",
    "carrId": "CAA0012",
    "dspType": "AMHS",
    "prodDefId": "",
    "procDefId": "",
    "evntCm": "",
    "evntUserId": "WFS"
  }
}
 */
