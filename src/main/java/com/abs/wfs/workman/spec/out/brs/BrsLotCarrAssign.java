package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.out.brs.common.Slots;
import lombok.Data;

import java.util.List;

@Data
public class BrsLotCarrAssign extends ApMsgCommonVo {
	private BrsLotCarrAssignBody body;
	@Data
	public static class BrsLotCarrAssignBody extends ApMsgBody {
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
