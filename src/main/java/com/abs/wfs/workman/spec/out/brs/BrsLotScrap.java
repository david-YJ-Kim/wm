package com.abs.wfs.workman.spec.out.brs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.out.brs.common.Slots;
import lombok.Data;

import java.util.List;

@Data
public class BrsLotScrap extends ApMsgCommonVo {
	BrsLotScrapBody body;

	@Data
	public static class BrsLotScrapBody extends ApMsgBody {

		String mdfyUserId;
		String rsnCd;
		String trsnCm;
		List<Slots> slots;
		String checkSorterResv;
		String jobId;
		String dtlSorterJobTyp;
		String fnlEvntDt;
		String cellQty;
		String occurProcSgmtId;
		String causeProcSgmtId;
		String dfctJdgmTyp;
		String dfctId;
		String dfctCauseCm;
	}
}
