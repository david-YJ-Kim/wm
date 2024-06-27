package com.abs.wfs.workman.spec.in.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;


@Data
public class WfsLotDeassignCarrRep extends ApMsgCommonVo {

	WfsLotDeassignCarrRepBody body;

	@Data
	public static class WfsLotDeassignCarrRepBody extends ApMsgBody{

	}
}
