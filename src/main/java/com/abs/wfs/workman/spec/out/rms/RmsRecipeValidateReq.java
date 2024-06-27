package com.abs.wfs.workman.spec.out.rms;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

import java.util.List;

@Data
public class RmsRecipeValidateReq extends ApMsgCommonVo {
	private RmsRecipeValidateReqBody body;


	@Data
	public static class RmsRecipeValidateReqBody extends ApMsgBody {

		private String prodDefId;
		private String procDefId;
		private String procSgmtId;
		private List<String> recipeList;
		private List<String> doeRecipeList;
	}
}
