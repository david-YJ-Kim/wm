package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsEqpControlModeChangeIvo extends ApMsgCommonVo {

	public static String system = "BRS";
	public static String cid = "BRS_EQP_CONTROL_MODE_CHANGE";

	private BrsEqpControlModeChangeBody body;
	@Data
	public static class BrsEqpControlModeChangeBody extends ApMsgBody {
		private String ctrlModeCd;
		private String mdfyUserId;
		private String rsnCd;
		private String trnsCm;
	}

}
