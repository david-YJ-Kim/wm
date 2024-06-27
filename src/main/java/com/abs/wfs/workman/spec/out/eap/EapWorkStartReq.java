package com.abs.wfs.workman.spec.out.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.out.eap.common.Work;
import lombok.Data;

import java.util.List;

@Data
public class EapWorkStartReq extends ApMsgCommonVo {
	private EapWorkStartReqBody body;



	@Data
	public static class EapWorkStartReqBody extends ApMsgBody {

		private String workId;
		private List<Work> work;
	}
}
