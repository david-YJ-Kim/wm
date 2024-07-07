package com.abs.wfs.workman.spec.out.brs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class BrsLotProcStartedIvo extends ApMsgCommonVo {

	public static String system = ApSystemCodeConstant.BRS;
	public static String cid = WorkManMessageList.BRS_LOT_PROC_STARTED;


	private BrsLotProcStartedBody body;

	@Data
	public static class BrsLotProcStartedBody extends ApMsgBody {


	}
}
/*
{
  "head": {
    "cid": "BRS_LOT_PROC_STARTED",
    "tid": "TID_20240702170058037065",
    "osrc": "",
    "otgt": "",
    "src": "WFS",
    "tgt": "BRS"
  },
  "body": {
    "siteId": "SVM",
    "lotId": "D24500080",
    "eqpId": "AP-BU-03-01",
    "portId": "AP-BU-03-01-BP01",
    "carrId": "CAA0019"
  }
 */