package com.abs.wfs.workman.spec.out.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class EapCarrCancelReq extends ApMsgCommonVo {
	public static String system = ApSystemCodeConstant.EAP;
	public static String cid = WorkManMessageList.EAP_CARR_CANCEL_REQ;
	Body body;


	@Data
	public static class Body extends ApMsgBody {
		String portType;
	}
}
