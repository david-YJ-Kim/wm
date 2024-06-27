package com.abs.wfs.workman.spec.out.brs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsLotProdEndReport extends ApMsgCommonVo {
	BrsLotProdEndReportBody body;


	@Data
	public static class BrsLotProdEndReportBody extends ApMsgBody {
		String subEqpId;
		String ppId;
		String inPortId;
		String inCarrierId;
		String slotNo;
		String prodMtrlId;
		String mtrlFaceCsd;

	}
}
