package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.out.brs.common.Mtrl;
import lombok.Data;

import java.util.List;

@Data
public class BrsLotConsumeMtrl extends ApMsgCommonVo {
	private BrsLotConsumeMtrlBody body;

	@Data
	public static class BrsLotConsumeMtrlBody extends ApMsgBody {

		private String procSgmtId;
		private List<Mtrl> mtrlList;
	}
}
