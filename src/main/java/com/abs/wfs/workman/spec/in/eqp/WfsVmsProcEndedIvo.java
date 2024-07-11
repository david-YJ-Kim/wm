package com.abs.wfs.workman.spec.in.eqp;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsVmsProcEndedIvo extends ApMsgCommonVo {


	public static String system = ApSystemCodeConstant.WFS;
	public static String cid = WorkManMessageList.WFS_VMS_PROC_ENDED;



	private WfsVmsProcEndedBody body;

	@Data
	public static class WfsVmsProcEndedBody extends ApMsgBody{
		String prodDefId;
		String procSgmtId;
		String procDefId;
		String ppId;
		String eventTimestamp;
	}

}