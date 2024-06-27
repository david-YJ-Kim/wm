package com.abs.wfs.workman.spec.out.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class EapWorkCancelReq extends ApMsgCommonVo {
	private EapWorkCancelReqBody body;


	@Data
	public static class EapWorkCancelReqBody extends ApMsgBody {
		String portType;
		String workId;
	}
}
