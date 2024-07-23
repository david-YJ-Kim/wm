package com.abs.wfs.workman.spec.out.brs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.out.brs.common.Slots;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

import java.util.List;

@Data
public class BrsLotDeassignCarr extends ApMsgCommonVo {


	public static String system = ApSystemCodeConstant.BRS;
	public static String cid = WorkManMessageList.BRS_LOT_CARR_DEASSIGN;

	private Body body;

	@Data
	public static class Body extends ApMsgBody {
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
