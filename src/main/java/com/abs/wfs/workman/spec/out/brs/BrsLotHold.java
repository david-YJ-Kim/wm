package com.abs.wfs.workman.spec.out.brs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsLotHold extends ApMsgCommonVo {
 
	private BrsLotHoldBody body;

	@Data
	public static class BrsLotHoldBody extends ApMsgBody {

		String holdCd;
		String holdPw;
		String futureHoldYn;
		String alrmSysId;
		String mdfyUserId;
		String trnsCm;
	}
	
}
