package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsLotTrackInCancelReq extends ApMsgCommonVo {
	BrsLotTrackInCancelReqBody body;

	@Data
	public static class BrsLotTrackInCancelReqBody extends ApMsgBody {

		String rsnCd;
		String trnsCm;
		String mdfyUserId;
	}

}
