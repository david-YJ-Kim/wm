package com.abs.wfs.workman.spec.in.eap;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class WfsProcEnded extends ApMsgCommonVo {
	private WfsProcEndedBody body;

	@Data
	public static class WfsProcEndedBody extends ApMsgBody{
		String ppId;
		String inPortId;
		String inCarrierId;
	}

}
