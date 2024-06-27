package com.abs.wfs.workman.spec.out.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class McsCarrMoveReq extends ApMsgCommonVo {
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
