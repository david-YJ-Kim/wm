package com.abs.wfs.workman.spec.out.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class EapTerminalMessageReq extends ApMsgCommonVo {
	EapTerminalMessageReqBody body;


	@Data
	public static class EapTerminalMessageReqBody extends ApMsgBody {
		String message;

	}
}
