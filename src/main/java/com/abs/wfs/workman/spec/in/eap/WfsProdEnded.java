package com.abs.wfs.workman.spec.in.eap;


import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class WfsProdEnded {

	private WfsProdEndedBody body;

	@Data
	public static class WfsProdEndedBody extends ApMsgBody{
		String subEqpId;
		String ppId;
		String slotNo;
		String prodMtrlId;
		String workFace;
	}
}
