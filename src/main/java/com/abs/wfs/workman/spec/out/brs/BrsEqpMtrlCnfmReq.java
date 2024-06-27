package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsEqpMtrlCnfmReq extends ApMsgCommonVo {

	private BrsEqpMtrlCnfmReqBody body;

	@Data
	public static class BrsEqpMtrlCnfmReqBody extends ApMsgBody {
		String eqpId;
		String subEqpId;
		String specId;
		String specType;
	}
	

}
