package com.abs.wfs.workman.spec.out.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class McsCarrDataReq extends ApMsgCommonVo {
	private McsCarrDataReqBody body;


	@Data
	public static class McsCarrDataReqBody extends ApMsgBody {

		private String eventComment;
		private String eventUserId;

	}
}
