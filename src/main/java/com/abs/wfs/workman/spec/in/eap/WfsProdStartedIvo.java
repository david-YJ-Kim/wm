package com.abs.wfs.workman.spec.in.eap;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsProdStartedIvo extends ApMsgCommonVo {

	public static String system = ApSystemCodeConstant.WFS;
	public static String cid = WorkManMessageList.WFS_PROD_STARTED;


	private Body body;

	@Data
	public static class Body extends ApMsgBody{
		String subEqpId;
		String procDefId;
		String procSgmtId;
		String ppId;
		String mtrlFace;
		String prodDefId;
		String prodMtrlId;
		String slotNo;
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
    "tid": "AP-SR-02-01_00_20240705114025355",
    "cid": "WFS_PROD_STARTED"
  },
  "body": {
    "siteId": "SVM",
    "eqpId": "AP-SR-02-01",
    "carrId": "CAA0341",
    "lotId": "D24600061",
    "portId": "AP-SR-02-01-IP01",
    "userId": "FUSEI",
    "subEqpId": "EQ",
    "procDefId": "DTCR11D008",
    "procSgmtId": "SR020101",
    "ppId": "0000SR0201SFTEST0001",
    "mtrlFace": "TTT",
    "prodDefId": "DT01-01-001",
    "prodMtrlId": "ET24401413",
    "slotNo": "8",
    "eventTimestamp": "20240705114025355"
  }
}
 */
