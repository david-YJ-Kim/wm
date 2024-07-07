package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class BrsLotProcEndedIvo extends ApMsgCommonVo {

	public static String system = ApSystemCodeConstant.BRS;
	public static String cid = WorkManMessageList.BRS_LOT_PROC_ENDED;

	private BrsLotProcEndedBody body;

	@Data
	public static class BrsLotProcEndedBody extends ApMsgBody {

	}

}

/*
{
  "head": {
    "cid": "BRS_LOT_PROC_ENDED",
    "tid": "AP-BU-03-01_00_20240702170048170",
    "osrc": "",
    "otgt": "",
    "src": "WFS",
    "tgt": "BRS"
  },
  "body": {
    "siteId": "SVM",
    "lotId": "D24500086",
    "eqpId": "AP-BU-03-01",
    "portId": "AP-BU-03-01-BP02",
    "carrId": "CAA0038"
  }
}
 */