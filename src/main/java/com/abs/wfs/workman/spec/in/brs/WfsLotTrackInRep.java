package com.abs.wfs.workman.spec.in.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class WfsLotTrackInRep extends ApMsgCommonVo {

	WfsLotTrackInRepBody body;

	@Data
	public static class WfsLotTrackInRepBody extends ApMsgBody{

	}
}
