package com.abs.wfs.workman.spec.out.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class EapTerminalMessageReqIvo extends ApMsgCommonVo {
	Body body;


	@Data
	public static class Body extends ApMsgBody {
		String message;

	}
}
