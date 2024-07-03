package com.abs.wfs.workman.spec.out.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class McsCarrMoveReqIvo extends ApMsgCommonVo {

	public static String system = ApSystemCodeConstant.MCS;
	public static String cid = WorkManMessageList.MCS_CARR_MOVE_REQ;

	private McsCarrMoveReqBody body;


	@Data
	public static class McsCarrMoveReqBody extends ApMsgBody {
		private String eventComment;
		private String eventUserId;

		private String commId;
		private String srcEqpId;
		private String srcPortId;
		private String destEqpId;
		private String destPortId;
		private String prio;


	}
}
/*
{
  "head": {
    "cid": "MCS_CARR_MOVE_REQ",
    "tid": "AP-RD-05-01_00_20240702143436884",
    "osrc": "",
    "otgt": "",
    "src": "WFS",
    "tgt": "MCS"
  },
  "body": {
    "siteId": "SVM",
    "eventComment": "",
    "eventUserId": "WFS",
    "carrId": "CAA0279",
    "commId": "TRANS_20240702143437739671",
    "srcEqpId": "ASTK03",
    "srcPortId": "ASTK03_STORAGE",
    "destEqpId": "AP-RD-05-01",
    "destPortId": "AP-RD-05-01-BP01",
    "prio": "50"
  }
}
 */