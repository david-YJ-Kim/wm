package com.abs.wfs.workman.spec.out.brs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.out.brs.common.Drbl;
import lombok.Data;

import java.util.List;

@Data
public class BrsDrblUse extends ApMsgCommonVo {
	private BrsDrblUseBody body;

	@Data
	public static class BrsDrblUseBody extends ApMsgBody {
		private String rsnCd;
		private String mdfyUserId;
		private String trnsCm;
		private List<Drbl> drblList;
	}
}
