package com.abs.wfs.workman.spec.out.brs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.out.brs.common.Slots;
import lombok.Data;

import java.util.List;

@Data
public class BrsLotDeassignCarr extends ApMsgCommonVo {

	private BrsLotDeassignCarrBody body;

	@Data
	public static class BrsLotDeassignCarrBody extends ApMsgBody {
		String deasgnQty;
		List<Slots> slots;
		String isCheckClnStat;
		String isCheckSorterResv;
		String jobId;
		String sorterJobTyp;
		String dtlSorterJobTyp;
		String fnlEvntDt;
		String mdfyUserId;
		String rsnCd;
		String trnsCm;

	}
}
