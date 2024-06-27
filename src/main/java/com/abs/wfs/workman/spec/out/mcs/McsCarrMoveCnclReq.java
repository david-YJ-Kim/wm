package com.abs.wfs.workman.spec.out.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class McsCarrMoveCnclReq extends ApMsgCommonVo {
	private McsCarrMoveCnclReqBody body;


	@Data
	public static class McsCarrMoveCnclReqBody extends ApMsgBody {
		private String eventComment;
		private String eventUserId;

		private String commId;

	}
}
