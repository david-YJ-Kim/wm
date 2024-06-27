package com.abs.wfs.workman.spec.out.rtd;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class RtdDspWorkReq extends ApMsgCommonVo {
	private RtdDspWorkReqBody body;

	@Data
	public static class RtdDspWorkReqBody extends ApMsgBody {

		private String dspType;
		private String prodDefId;
		private String procDefId;
		private String evntCm;
		private String evntUserId;

	}
}
