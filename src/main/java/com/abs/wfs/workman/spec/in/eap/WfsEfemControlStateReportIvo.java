package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
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

	public static String system = ApSystemCodeConstant.WFS;
	public static String cid = WorkManMessageList.WFS_EFEM_CONTROL_STATE_REPORT;

	private Body body;

	@Data
	public static class Body extends ApMsgBody{
		String eqpCommStateCd;
		String portType;
	}
}
