package com.abs.wfs.workman.spec.out.brs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsLotProcStarted extends ApMsgCommonVo {
	private BrsLotProcStartedBody body;

	@Data
	public static class BrsLotProcStartedBody extends ApMsgBody {


	}
}
