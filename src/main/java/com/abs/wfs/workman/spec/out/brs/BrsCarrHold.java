package com.abs.wfs.workman.spec.out.brs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsCarrHold extends ApMsgCommonVo {
	private BrsCarrHoldBody body;

	@Data
	public static class BrsCarrHoldBody extends ApMsgBody {
		private String mdfyUserId;
		private String rsnCd;
		private String trnsCm;
	}
}
