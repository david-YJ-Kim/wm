package com.abs.wfs.workman.spec.out.brs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class BrsAlarmExecute extends ApMsgCommonVo {


	public static String system = ApSystemCodeConstant.BRS;
//	public static String cid = WorkManMessageList.BRS_;


	private BrsAlarmExecuteBody body;

	@Data
	public static class BrsAlarmExecuteBody extends ApMsgBody{

		private String alarmDefId;
		private String alarmSrcId;
		private String alarmCm;
		private String mtrlLotId;
	}
}
