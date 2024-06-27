package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.eap.common.WorkVo;
import lombok.Data;

@Data
public class WfsWorkEndedIvo extends ApMsgCommonVo {
	private WfsWorkEndedBody body;

	@Data
	public static class WfsWorkEndedBody extends ApMsgBody{
		private String workId;
		private WorkVo work;
	}
}
