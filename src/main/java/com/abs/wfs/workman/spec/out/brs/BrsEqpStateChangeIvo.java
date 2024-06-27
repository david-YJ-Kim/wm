package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsEqpStateChangeIvo extends ApMsgCommonVo {

	public static String system = "BRS";
	public static String cid = "BRS_EQP_STATE_CHANGE";

	private BrsEqpStateChangeBody body;

	@Data
	public static class BrsEqpStateChangeBody extends ApMsgBody {
		String statCd;
		String mdfyUserId;
		String rsnCd;
		String trnsCm;
	}
}
