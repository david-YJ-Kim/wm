package com.abs.wfs.workman.spec.out.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class EapPpIdStartReq extends ApMsgCommonVo {
	private EapPpIdStartReqBody body;

	@Data
	public static class EapPpIdStartReqBody extends ApMsgBody {

		 String workId;
	}

}
