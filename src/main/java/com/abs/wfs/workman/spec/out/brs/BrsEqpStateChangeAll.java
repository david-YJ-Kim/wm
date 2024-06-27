package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsEqpStateChangeAll extends ApMsgCommonVo {
	private BrsEqpStateChangeAllBody body;

	@Data
	public static class BrsEqpStateChangeAllBody extends ApMsgBody {}
}
