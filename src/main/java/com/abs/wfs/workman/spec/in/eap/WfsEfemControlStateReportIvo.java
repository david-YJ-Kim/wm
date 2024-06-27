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
    "srcEqp": "AP-EM-01-01",
    "src": "EAP",
    "tid": "AP-EM-01-01_00_20240617083241855",
    "cid": "WFS_EFEM_CONTROL_STATE_REPORT"
  },
  "body": {
    "portType": "BP",
    "siteId": "SVM",
    "eqpCommStateCd": "OnlineRemote",
    "eqpId": "AP-EM-01-01",
    "userId": "EAP"
  }
}
 */

@Data
public class WfsEfemControlStateReportIvo extends ApMsgCommonVo {
	private WfsEfemControlStateReportBody body;

	@Data
	public static class WfsEfemControlStateReportBody extends ApMsgBody{
		private String eqpCommStateCd;
	}
}
