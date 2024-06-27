package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsPortStateChange extends ApMsgCommonVo {
	BrsPortStateChangeBody body;

	@Data
	public static class BrsPortStateChangeBody extends ApMsgBody {
		String statCd;
		String mdfyUserId;
		String rsnCd;
		String trnsCm;
	}
}
