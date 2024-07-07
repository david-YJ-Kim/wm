package com.abs.wfs.workman.spec.in.eap;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsProcEndedIvo extends ApMsgCommonVo {



	public static String system = ApSystemCodeConstant.WFS;
	public static String cid = WorkManMessageList.WFS_PROC_ENDED;


	private WfsProcEndedBody body;

	@Data
	public static class WfsProcEndedBody extends ApMsgBody{
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
    "srcEqp": "AP-SR-03-01",
    "src": "EAP",
    "tid": "AP-SR-03-01_00_20240705112848057",
    "cid": "WFS_PROC_ENDED"
  },
  "body": {
    "carrId": "CAA0087",
    "siteId": "SVM",
    "lotId": "D24600074D01",
    "eqpId": "AP-SR-03-01",
    "portId": "AP-SR-03-01-BP01",
    "userId": "Skilled Worker",
    "prodDefId": "DT01-01-001",
    "procSgmtId": "SR030101",
    "procDefId": "DTCR11D008",
    "ppId": "0000SR03SFTEST0001",
    "eventTimestamp": "20240705112848057"
  }
}
 */