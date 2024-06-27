package com.abs.wfs.workman.spec.out.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class McsInvtDataReq extends ApMsgCommonVo {
	private McsInvtDataReqBody body;


	@Data
	public static class McsInvtDataReqBody extends ApMsgBody {

		private String eventComment;
		private String eventUserId;
	}
}
