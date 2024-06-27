package com.abs.wfs.workman.spec.out.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class EapCarrRechuckReq extends ApMsgCommonVo {
	private EapCarrRechuckReqBody body;


	@Data
	public static class EapCarrRechuckReqBody extends ApMsgBody {
		String portType;
	}
}
