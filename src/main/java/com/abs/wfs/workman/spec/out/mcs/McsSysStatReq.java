package com.abs.wfs.workman.spec.out.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class McsSysStatReq extends ApMsgCommonVo {
	private McsSysStatReqBody body;


	@Data
	public static class McsSysStatReqBody extends ApMsgBody {
		private String eventComment;
		private String eventUserId;
		private String mcsId;


	}
}
