package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsLotTrackInCnfmReq extends ApMsgCommonVo {
	private BrsLotTrackInCnfmReqBody body;

	@Data
	public static class BrsLotTrackInCnfmReqBody extends ApMsgBody {
		String workId;
	}
}
