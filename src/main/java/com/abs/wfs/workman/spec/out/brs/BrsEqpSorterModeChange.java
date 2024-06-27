package com.abs.wfs.workman.spec.out.brs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsEqpSorterModeChange extends ApMsgCommonVo {
	private BrsEqpSorterModeChangeBody body;

	@Data
	public static class BrsEqpSorterModeChangeBody extends ApMsgBody {
		String mdfyUserId;
		String sorterModeYn;
	}
}
