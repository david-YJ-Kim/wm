package com.abs.wfs.workman.spec.out.brs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsEqpProcStateChange extends ApMsgCommonVo {
	private BrsEqpProcStateChangeBody body;

	@Data
	public static class BrsEqpProcStateChangeBody extends ApMsgBody {
		String procStatCd;
		String mdfyUserId;
		String rsnCd;
		String trnsCm;

	}
}
