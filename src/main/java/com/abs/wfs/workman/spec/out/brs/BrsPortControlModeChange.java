package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsPortControlModeChange extends ApMsgCommonVo {
	private BrsPortControlModeChangeBody body;

	@Data
	public static class BrsPortControlModeChangeBody extends ApMsgBody {}
}
