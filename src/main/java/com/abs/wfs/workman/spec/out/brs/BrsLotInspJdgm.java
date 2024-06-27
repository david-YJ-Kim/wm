package com.abs.wfs.workman.spec.out.brs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsLotInspJdgm extends ApMsgCommonVo {

	BrsLotInspJdgmBody body;

	@Data
	public static class BrsLotInspJdgmBody extends ApMsgBody {
		String lotId;
		String mtrlFaceCd;
		String procSgmtId;
		String prodMtrlId;
		String judgeMap;
		String rsnCd;
		String trnsCm;
		String mdfyUserId;
	}
	

}
