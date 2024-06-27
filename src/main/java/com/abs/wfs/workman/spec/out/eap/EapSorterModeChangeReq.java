package com.abs.wfs.workman.spec.out.eap;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class EapSorterModeChangeReq extends ApMsgCommonVo {
	private EapSorterModeChangeReqBody body;


	@Data
	public static class EapSorterModeChangeReqBody extends ApMsgBody {
		String modeType;
	}
}
