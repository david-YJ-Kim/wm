package com.abs.wfs.workman.spec.out.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class EapBuzzerReq extends ApMsgCommonVo {
	EapBuzzerReqBody body;

	@Data
	public static class EapBuzzerReqBody extends ApMsgBody {
		String signal;
	}
}
