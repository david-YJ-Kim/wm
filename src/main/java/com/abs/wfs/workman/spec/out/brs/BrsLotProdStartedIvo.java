package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class BrsLotProdStartedIvo extends ApMsgCommonVo {

	public static String system = ApSystemCodeConstant.BRS;
	public static String cid = WorkManMessageList.BRS_LOT_PROD_STARTED;

	private Body body;

	@Data
	public static class Body extends ApMsgBody {
		String subEqpId;
		String ppId;
		String inPortId;
		String inCarrierId;
		String slotNo;
		String prodMtrlId;
		String mtrlFaceCd;

	}

}

/*
{
  "head": {
    "cid": "BRS_LOT_PROD_START_REPORT",
    "tid": "TID_20240703141804323072",
    "osrc": "",
    "otgt": "",
    "src": "WFS",
    "tgt": "BRS"
  },
  "body": {
    "siteId": "SVM",
    "lotId": "D24600081",
    "eqpId": "AP-PD-09-01",
    "userId": "WFS",
    "subEqpId": "CO2 LASER DRILL",
    "ppId": "0000PD0901SFTEST0001",
    "inPortId": "AP-PD-09-01-BP01",
    "inCarrierId": "CAA0267",
    "slotNo": "5",
    "prodMtrlId": "ET24401454",
    "mtrlFaceCd": "Top"
  }
}
 */