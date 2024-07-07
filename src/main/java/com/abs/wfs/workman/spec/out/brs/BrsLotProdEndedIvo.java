package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class BrsLotProdEndedIvo extends ApMsgCommonVo {

	public static String system = ApSystemCodeConstant.BRS;
	public static String cid = WorkManMessageList.BRS_LOT_PROD_ENDED;

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
    "cid": "BRS_LOT_PROD_END_REPORT",
    "tid": "TID_20240702170500628957",
    "osrc": "",
    "otgt": "",
    "src": "WFS",
    "tgt": "BRS"
  },
  "body": {
    "siteId": "SVM",
    "lotId": "D24500434",
    "eqpId": "AP-PD-09-02",
    "userId": "WFS",
    "subEqpId": "AP-PD-09-02",
    "ppId": "0000PD0902SFTEST0001",
    "inPortId": "AP-PD-09-02-BP01",
    "inCarrierId": "CAA0317",
    "slotNo": "3",
    "prodMtrlId": "ET24400851",
    "mtrlFaceCd": "Top"
  }
}
 */