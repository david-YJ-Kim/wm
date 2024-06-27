package com.abs.wfs.workman.spec.out.brs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.out.brs.common.Slots;
import lombok.Data;

import java.util.List;

@Data
public class BrsLotScrapReport extends ApMsgCommonVo {
	BrsLotScrapReportBody body;
	@Data
	public static class BrsLotScrapReportBody extends ApMsgBody {
		String mdfyUserId;
		String rsnCd;
		String trnsCm;
		List<Slots> slots;

	}
}
