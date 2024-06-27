package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsPortAccessModeChange extends ApMsgCommonVo {
	private BrsPortAccessModeChangeBody body;


	@Data
	public static class BrsPortAccessModeChangeBody extends ApMsgBody {}
}
