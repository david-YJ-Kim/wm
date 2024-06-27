package com.abs.wfs.workman.spec.out.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.out.eap.common.Lot;
import lombok.Data;

import java.util.List;

@Data
public class EapCellMapRep extends ApMsgCommonVo {
	private EapCellMapRepBody body;


	@Data
	public static class EapCellMapRepBody extends ApMsgBody {
		List<Lot> lot;
	}
}
