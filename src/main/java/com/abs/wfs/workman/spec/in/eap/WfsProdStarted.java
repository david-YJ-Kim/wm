package com.abs.wfs.workman.spec.in.eap;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class WfsProdStarted extends ApMsgCommonVo {

	private WfsProdStartedBody body;

	@Data
	public static class WfsProdStartedBody extends ApMsgBody{
		String subEqpId;
		String ppId;
		String inPortId;
		String inCarrierId;
		String slotNo;
		String prodMtrlId;
		String workFace;
	}

}
