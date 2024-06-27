package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsLotDcolDataSave extends ApMsgCommonVo {
	private BrsLotDcolDataSaveBody body;

	@Data
	public static class BrsLotDcolDataSaveBody extends ApMsgBody {
		String subEqpId;
		String mtrlId;
		String mtrlTyp;
		String prodMtrlId;
		String prodDefId;
		String procDefId;
		String procSgmtId;
		String dcdataSgmtId;
		String evntNm;
		String mtrlFaceCd;
	}
}
