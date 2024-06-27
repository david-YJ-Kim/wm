package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsLotTrackInMeasure extends ApMsgCommonVo {
	BrsLotTrackInMeasureBody body;

	@Data
	public static class BrsLotTrackInMeasureBody extends ApMsgBody {
		String lotId;
		String carrId;
		String eqpId;
		String subEqpId;
		String procSgmtId;
		String subProcSgmtId;
		String recipeId;
	}
	
}
