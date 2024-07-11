package com.abs.wfs.workman.spec.in.eqp;


import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsVmsProdEndedIvo {

	public static String system = ApSystemCodeConstant.WFS;
	public static String cid = WorkManMessageList.WFS_VMS_PROD_ENDED;

	private WfsVmsProdEndedBody body;

	@Data
	public static class WfsVmsProdEndedBody extends ApMsgBody{
		String mtrlFace;
		String subEqpId;
		String prodMtrlId;
		String slotNo;
		String ppId;
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
    "tid": "AP-SR-02-01_00_20240705114840346",
    "cid": "WFS_PROD_ENDED"
  },
  "body": {
    "siteId": "SVM",
    "lotId": "D24600061",
    "eqpId": "AP-SR-02-01",
    "portId": "AP-SR-02-01-IP01",
    "carrId": "CAA0341",
    "userId": "FUSEI",
    "mtrlFace": "TTT",
    "subEqpId": "EQ",
    "prodMtrlId": "ET24401414",
    "slotNo": "9",
    "ppId": "0000SR0201SFTEST0001"
  }
}
 */