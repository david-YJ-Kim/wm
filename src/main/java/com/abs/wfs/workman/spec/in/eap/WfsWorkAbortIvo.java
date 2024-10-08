package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.out.eap.common.ProcessSlotMapVo;
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
    "srcEqp": "AP-LA-01-01",
    "src": "EAP",
    "tid": "AP-LA-01-01_00_20240618120931276",
    "cid": "WFS_WORK_ABORT"
  },
  "body": {
    "processSlotMap": [

    ],
    "siteId": "SVM",
    "eqpId": "AP-LA-01-01",
    "portId": "",
    "userId": "ABSOLICS",
    "workId": "20240618105132498"
  }
}
 */
@Data
public class WfsWorkAbortIvo extends ApMsgCommonVo {


	public static String system = ApSystemCodeConstant.WFS;
	public static String cid = WorkManMessageList.WFS_WORK_ABORT;

	private WfsWorkAbortBody body;

	@Data
	public static class WfsWorkAbortBody extends ApMsgBody{
		String workId;
		ProcessSlotMapVo processSlotMap;
	}
}