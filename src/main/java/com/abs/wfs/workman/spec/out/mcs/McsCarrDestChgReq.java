package com.abs.wfs.workman.spec.out.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class McsCarrDestChgReq extends ApMsgCommonVo {

	public static String system = ApSystemCodeConstant.MCS;
	public static String cid = WorkManMessageList.MCS_CARR_DEST_CHG_REQ;

	private Body body;


	@Data
	public static class Body extends ApMsgBody {
		private String eventComment;
		private String eventUserId;
		private String commId;
		private String destEqpId;
		private String destPortId;
		private String prio;

	}
}
/*
{
  "head": {
    "cid": "MCS_CARR_DEST_CHG_REQ",
    "osrc": "",
    "otgt": "",
    "src": "OIA",
    "srcEqp": "",
    "tgt": "WFS",
    "tgtEqp": [

    ],
    "tid": "UI_20240627151943",
    "lang": "en"
  },
  "body": {
    "carrId": "CAA0104",
    "commId": "CST001_CMD02",
    "destEqpId": "AP-TG-07-01",
    "destPortId": "AP-TG-07-01-BP02",
    "comment": "sl2 200\n",
    "userId": "OI_2404002",
    "siteId": "SVM",
	"prio": "50"
  }
}
 */