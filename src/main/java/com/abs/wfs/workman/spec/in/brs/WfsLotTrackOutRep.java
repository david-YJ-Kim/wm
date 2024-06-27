package com.abs.wfs.workman.spec.in.brs;

import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;


@Data
public class WfsLotTrackOutRep {
	WfsLotTrackOutRepBody body;

	@Data
	public static class WfsLotTrackOutRepBody extends ApMsgBody{

	}
}
