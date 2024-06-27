package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsLotProcEnded extends ApMsgCommonVo {
	private BrsLotProcEndedBody body;

	@Data
	public static class BrsLotProcEndedBody extends ApMsgBody {


	}


}
