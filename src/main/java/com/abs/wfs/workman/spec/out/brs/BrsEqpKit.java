package com.abs.wfs.workman.spec.out.brs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.out.brs.common.MaterialDurable;
import lombok.Data;

import java.util.List;

@Data
public class BrsEqpKit extends ApMsgCommonVo {
	private BrsEqpKitBody body;


	@Data
	public static class BrsEqpKitBody extends ApMsgBody {
		private List<MaterialDurable> materialDurableList;
		private String mdfyUserId;
		private String rsnCd;
		private String trnsCm;
		private String mdfyDt;
	}


}
