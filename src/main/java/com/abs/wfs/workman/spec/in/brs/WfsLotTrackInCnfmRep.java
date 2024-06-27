package com.abs.wfs.workman.spec.in.brs;

import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;


@Data
public class WfsLotTrackInCnfmRep {
	WfsLotTrackInCnfmRepBody body;

	@Data
	public static class WfsLotTrackInCnfmRepBody extends ApMsgBody{

	}

}
