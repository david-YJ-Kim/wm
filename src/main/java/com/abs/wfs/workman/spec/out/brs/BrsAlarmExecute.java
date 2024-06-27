package com.abs.wfs.workman.spec.out.brs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsAlarmExecute extends ApMsgCommonVo {
	private BrsAlarmExecuteBody body;

	@Data
	public static class BrsAlarmExecuteBody extends ApMsgBody{

		private String alarmDefId;
		private String alarmSrcId;
		private String alarmCm;
		private String mtrlLotId;
	}
}
