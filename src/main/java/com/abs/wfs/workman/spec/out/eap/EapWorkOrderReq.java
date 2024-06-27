package com.abs.wfs.workman.spec.out.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class EapWorkOrderReq extends ApMsgCommonVo {
	EapWorkOrderReqBody body;



	@Data
	public static class EapWorkOrderReqBody extends ApMsgBody {

		String workId;
		String prodDefId;
		String procDefId;
		String procSgmtId;

	}
}
