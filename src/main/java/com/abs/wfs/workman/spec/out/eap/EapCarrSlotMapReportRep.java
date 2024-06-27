package com.abs.wfs.workman.spec.out.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.MsgReasonVo;
import lombok.Data;

@Data
public class EapCarrSlotMapReportRep extends ApMsgCommonVo {
	EapCarrSlotMapReportRepBody body;


	@Data
	public static class EapCarrSlotMapReportRepBody extends ApMsgBody {

		String portType;
		String slotMap;
		MsgReasonVo reason;
	}
}
