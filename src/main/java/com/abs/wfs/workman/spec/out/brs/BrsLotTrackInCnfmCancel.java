package com.abs.wfs.workman.spec.out.brs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Data;

@Data
public class BrsLotTrackInCnfmCancel extends ApMsgCommonVo {
	
	
	private BrsLotTrackInCnfmCancelBody body;

	@Data
	public static class BrsLotTrackInCnfmCancelBody extends ApMsgBody {
		String trnsCm;
	}
	

}
