package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.eap.common.WorkVo;
import lombok.Data;

@Data
public class WfsWorkStartedIvo extends ApMsgCommonVo {

	private WfsWorkStartedBody body;

	@Data
	public static class WfsWorkStartedBody extends ApMsgBody{
		private String workId;
		private WorkVo work;
	}
}
