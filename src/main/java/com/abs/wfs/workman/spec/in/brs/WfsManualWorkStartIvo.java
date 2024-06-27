package com.abs.wfs.workman.spec.in.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;


@Data
public class WfsManualWorkStartIvo extends ApMsgCommonVo {

	public static String system = ApSystemCodeConstant.WFS;
	public static String cid = WorkManMessageList.WFS_MANUAL_WORK_START;

	WfsManualWorkStartBody body;

	@Data
	public static class WfsManualWorkStartBody extends ApMsgBody{

		String prodDefId;
		String procDefId;
		String procSgmtId;

	}
}
/*
{
  "head": {
    "cid": "WFS_MANUAL_WORK_START",
    "tid": "BRS_20240625191621068",
    "osrc": null,
    "otgt": null,
    "src": "BRS",
    "srcEqp": null,
    "tgt": "WFS",
    "lang": null,
    "tgtEqp": null
  },
  "body": {
    "siteId": "SVM",
    "eqpId": "AM-RE-04-01",
    "lotId": "D24600015",
    "prodDefId": "DT05-01-001",
    "procDefId": "DTCR53",
    "procSgmtId": "RE040101"
  }
}
 */