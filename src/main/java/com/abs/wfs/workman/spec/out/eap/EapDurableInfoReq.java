package com.abs.wfs.workman.spec.out.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class EapDurableInfoReq extends ApMsgCommonVo {
	
	EapDurableInfoReqBody body;


	@Data
	public static class EapDurableInfoReqBody extends ApMsgBody {
		String eqpId;
		String specInfo;


	}
	

}
