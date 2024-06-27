package com.abs.wfs.workman.spec.in.eap;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.MsgReasonVo;
import lombok.Data;

@Data
public class WfsReadyToWork extends ApMsgCommonVo{

	private WfsReadyToWorkBody body;

	@Data
	public static class WfsReadyToWorkBody extends ApMsgBody{
		private String workId;
		private MsgReasonVo reason;
	}
}
