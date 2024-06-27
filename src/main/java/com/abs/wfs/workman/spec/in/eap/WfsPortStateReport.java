package com.abs.wfs.workman.spec.in.eap;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class WfsPortStateReport extends ApMsgCommonVo {


	private WfsPortStateReportBody body;

	@Data
	public static class WfsPortStateReportBody extends ApMsgBody{
		String portType;
		String portStatus;
		String portState;
		String portAccessMode;
	}
	
}
