package com.abs.wfs.workman.spec.out.brs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsLotProdStartReport extends ApMsgCommonVo {
	BrsLotProdStartReportBody body;
	@Data
	public static class BrsLotProdStartReportBody extends ApMsgBody {

		String subEqpId;
		String ppId;
		String inPortId;
		String inCarrierId;
		String slotNo;
		String prodMtrlId;
		String mtrlFaceCd;
	}
}
