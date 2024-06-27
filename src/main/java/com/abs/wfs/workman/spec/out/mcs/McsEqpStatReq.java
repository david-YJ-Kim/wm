package com.abs.wfs.workman.spec.out.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class McsEqpStatReq extends ApMsgCommonVo {
	private McsEqpStatReqBody body;


	@Data
	public static class McsEqpStatReqBody extends ApMsgBody {
		private String eventComment;
		private String eventUserId;


	}
}
