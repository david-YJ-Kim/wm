package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsEqpDekit extends ApMsgCommonVo {
	private BrsEqpDekitBody body;

	@Data
	public static class BrsEqpDekitBody extends ApMsgBody {
		String mtrlDrblTyp;
		String mtrlDrblId;
		String mdfyUserId;
		String mdfyDt;
		String seq;
	}
	
}
