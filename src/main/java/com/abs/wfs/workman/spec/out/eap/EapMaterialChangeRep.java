package com.abs.wfs.workman.spec.out.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class EapMaterialChangeRep extends ApMsgCommonVo {

	EapMaterialChangeRepBody body;

	@Data
	public static class EapMaterialChangeRepBody extends ApMsgBody {
		String subEqpId;
		String specId;
		String result;
		String specType;
		String specUseCnt;
		String specRemainCnt;
		String specLimitCnt;
	}
	
}
