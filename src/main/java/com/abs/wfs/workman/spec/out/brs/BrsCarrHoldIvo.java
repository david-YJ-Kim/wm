package com.abs.wfs.workman.spec.out.brs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class BrsCarrHoldIvo extends ApMsgCommonVo {


	public static String cid = WorkManMessageList.BRS_CARR_HOLD;
	public static String system = ApSystemCodeConstant.BRS;

	private Body body;

	@Data
	public static class Body extends ApMsgBody {
		private String mdfyUserId;
		private String rsnCd;
		private String trnsCm;
	}
}
