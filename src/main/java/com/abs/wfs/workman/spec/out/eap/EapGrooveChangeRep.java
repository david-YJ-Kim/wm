package com.abs.wfs.workman.spec.out.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class EapGrooveChangeRep extends ApMsgCommonVo {
	private EapGrooveChangeRepBody body;



	@Data
	public static class EapGrooveChangeRepBody extends ApMsgBody {

		private String subEqpId;
		private String specId;
		private String result;
		private String comment;
		private String grooveType;
		private String rgLimit;
		private String fgLimit;
	}
}
