package com.abs.wfs.workman.spec.out.brs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsLotInspData extends ApMsgCommonVo {
	 BrsLotInspDataBody body;

	@Data
	public static class BrsLotInspDataBody extends ApMsgBody {
		String workId;
		String jobSeqId;
		String slotNo;
		String prodMtrlId;
		String mtrlFaceCd;
		String prodDefId;
		String procDefId;
		String procSgmtId;
		String selfInspRsltCd;
		String selfInspObjId;
		String rsnCd;
		String trnsCm;
		String mdfyUserId;

	}
}
