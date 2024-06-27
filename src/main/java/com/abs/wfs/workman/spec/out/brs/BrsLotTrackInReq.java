package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsLotTrackInReq extends ApMsgCommonVo {
	BrsLotTrackInReqBody body;

	@Data
	public static class BrsLotTrackInReqBody extends ApMsgBody {
		String recipeId;
		String srcBtchId;
		String workId;
		String selfInspMasterInfoObjId;
	}
}
