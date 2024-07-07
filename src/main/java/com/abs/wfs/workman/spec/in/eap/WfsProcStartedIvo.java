package com.abs.wfs.workman.spec.in.eap;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsProcStartedIvo extends ApMsgCommonVo {

	public static String system = ApSystemCodeConstant.WFS;
	public static String cid = WorkManMessageList.WFS_PROC_STARTED;


	private WfsProcStartedBody body;

	@Data
	public static class WfsProcStartedBody extends ApMsgBody{
		String prodDefId;
		String procSgmtId;
		String procDefId;
		String ppId;
		String eventTimestamp;
	}
}
/*
{
  "head": {
    "tgt": "WFS",
    "tgtEqp": [

    ],
    "osrc": "",
    "srcEqp": "AP-SR-02-01",
    "src": "EAP",
    "tid": "AP-SR-02-01_00_20240705114004629",
    "cid": "WFS_PROC_STARTED"
  },
  "body": {
    "carrId": "CAA0341",
    "prodDefId": "DT01-01-001",
    "siteId": "SVM",
    "lotId": "D24600061",
    "procSgmtId": "SR020101",
    "eqpId": "AP-SR-02-01",
    "portId": "AP-SR-02-01-IP01",
    "userId": "Skilled Worker",
    "procDefId": "DTCR11D008",
    "ppId": "0000SR0201SFTEST0001",
    "eventTimestamp": "20240705114004629"
  }
}
 */