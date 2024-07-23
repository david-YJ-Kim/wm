package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.out.brs.common.Slots;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

import java.util.List;

@Data
public class BrsLotCarrAssign extends ApMsgCommonVo {

	public static String system = ApSystemCodeConstant.BRS;
	public static String cid = WorkManMessageList.BRS_LOT_CARR_ASSIGN;

	private Body body;
	@Data
	public static class Body extends ApMsgBody {
		String lotId;
		String carrId;
		String rsnCd;
		String trnsCm;
		String mdfyUserId;
		boolean checkClnStat;
		String asgnQty;
		List<Slots> slots;
	}
}
