package com.abs.wfs.workman.spec.out.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class EapToolCondReq extends ApMsgCommonVo {
	EapToolCondReqBody body;


	@Data
	public static class EapToolCondReqBody extends ApMsgBody {
		String inPortId;
		String outPortId;

	}
}
