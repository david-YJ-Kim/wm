package com.abs.wfs.workman.spec.out.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class McsCarrMoveCnclReqIvo extends ApMsgCommonVo {

	public static final String cid = WorkManMessageList.MCS_CARR_MOVE_CNCL_REQ;
	public static final String system = ApSystemCodeConstant.MCS;

	private Body body;


	@Data
	public static class Body extends ApMsgBody {
		private String eventComment;
		private String eventUserId;

		private String commId;

	}
}
