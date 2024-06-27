package com.abs.wfs.workman.spec.out.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.out.eap.common.SorterInfo;
import lombok.Data;

@Data
public class EapSorterStartReq extends ApMsgCommonVo {
	private EapSorterStartReqBody body;

	@Data
	public static class EapSorterStartReqBody extends ApMsgBody {
		private String sorterJobId;
		private SorterInfo sorterInfo;

	}
}
