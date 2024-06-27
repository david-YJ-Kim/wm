package com.abs.wfs.workman.spec.in.eap;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class WfsProcStarted extends ApMsgCommonVo {

	private WfsProcStartedBody body;

	@Data
	public static class WfsProcStartedBody extends ApMsgBody{
		String ppId;
		String inPortId;
		String inCarrierId;
	}
}
