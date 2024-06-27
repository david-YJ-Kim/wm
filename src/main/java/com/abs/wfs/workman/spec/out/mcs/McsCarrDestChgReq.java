package com.abs.wfs.workman.spec.out.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class McsCarrDestChgReq extends ApMsgCommonVo {
	private McsCarrDestChgReqBody body;


	@Data
	public static class McsCarrDestChgReqBody extends ApMsgBody {
		private String eventComment;
		private String eventUserId;
		private String commId;
		private String destEqpId;
		private String destPortId;
		private String prio;

	}
}
