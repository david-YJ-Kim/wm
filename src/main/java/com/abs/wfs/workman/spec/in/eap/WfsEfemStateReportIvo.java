package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

/*
{
  "head": {
    "tgt": "WFS",
    "tgtEqp": [

    ],
    "osrc": "",
    "srcEqp": "AP-SR-03-01",
    "src": "EAP",
    "tid": "AP-SR-03-01_00_20240617092026514",
    "cid": "WFS_EFEM_STATE_REPORT"
  },
  "body": {
    "portType": "BP",
    "eqpStateCd": "Idle",
    "siteId": "SVM",
    "eqpId": "AP-SR-03-01",
    "userId": "EAP"
  }
}
 */

@Data
public class WfsEfemStateReportIvo extends ApMsgCommonVo {
	private WfsEfemStateReportBody body;

	@Data
	public static class WfsEfemStateReportBody extends ApMsgBody{
		private String eqpStateCd;
	}
}
