package com.abs.wfs.workman.spec.out.brs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsPortTransferStateChange extends ApMsgCommonVo {
	BrsPortTransferStateChangeBody body;

	@Data
	public static class BrsPortTransferStateChangeBody extends ApMsgBody {

		String trsfStatCd;
		String mdfyUserId;
		String rsnCd;
		String trnsCm;
	}
}
