package com.abs.wfs.workman.spec.out.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class EapCarrCancelReq extends ApMsgCommonVo {
	private EapCarrCancelReqBody body;


	@Data
	public static class EapCarrCancelReqBody extends ApMsgBody {
		String portType;
	}
}
