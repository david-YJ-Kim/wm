package com.abs.wfs.workman.spec.out.brs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsEqpOperationModeChange extends ApMsgCommonVo {
	private BrsEqpOperationModeChangeBody body;
	@Data
	public static class BrsEqpOperationModeChangeBody extends ApMsgBody {}
}
