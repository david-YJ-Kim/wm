package com.abs.wfs.workman.spec.out.eap;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.MsgReasonVo;
import lombok.Data;

@Data
public class EapPanelIdScanReportRep extends ApMsgCommonVo {
	EapPanelIdScanReportRepBody body;


	@Data
	public static class EapPanelIdScanReportRepBody extends ApMsgBody {

		String slotNo;
		String prodMtrlId;
		MsgReasonVo reason;
	}
	
}
