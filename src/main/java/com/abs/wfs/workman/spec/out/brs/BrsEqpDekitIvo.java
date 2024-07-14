package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class BrsEqpDekitIvo extends ApMsgCommonVo {

	public static String system = ApSystemCodeConstant.BRS;
	public static String cid = WorkManMessageList.BRS_EQP_DEKIT;

	private Body body;

	@Data
	public static class Body extends ApMsgBody {
		String mtrlDrblTyp;
		String mtrlDrblId;
		String mdfyUserId;
	}
	
}
