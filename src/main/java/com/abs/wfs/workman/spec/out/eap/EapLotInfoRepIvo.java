package com.abs.wfs.workman.spec.out.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.out.eap.common.ProdMtrlVo;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import com.abs.wfs.workman.util.code.ShipType;
import lombok.Data;

import java.util.List;

@Data
public class EapLotInfoRepIvo extends ApMsgCommonVo {


	public static String system = ApSystemCodeConstant.EAP;
	public static String cid = WorkManMessageList.EAP_LOT_INFO_REP;
	
	private EapLotInfoRepBody body;

	@Data
	public static class EapLotInfoRepBody extends ApMsgBody {

		private String prntLotId;
		private String prodDefId;
		private String procDefId;
		private String procSgmtId;
		private String mtrlFaceCd;
		private String prevEqpId;

		private String shipType;
		private String dimensionX;
		private String dimensionY;
		private String rowCountLimitInspection;
		private String rowCountLimitMeasurement;

		List<ProdMtrlVo> prodMtrlList;

	}
	

}

/*

{
    "head":{
        "tgt": "EQP",
        "tgtEqp": [],
        "osrc": "",
        "srcEqp": "AP-BE-01-01",
        "src": "WFS",
        "tid": "AP-BE-01-01_20240611185833323",
        "cid": "EAP_LOT_INFO_REP"
    },
    "body":{
        "siteId": "SVM",
        "eqpId": "AP-BE-01-01",
        "prntLotId": "",
        "lotId": "D24500471", – WFS에서 lotId 누락하여 메시지 발송
        "prodDefId": "PROD_DEF_ID",
        "procDefId": "PROC_DEF_ID",
        "procSgmtId": "PROC_SGMT_ID",
        "mtrlFaceCd": "Top",
        "prevEqpId": "",
        "shipType": "U",
        "dimensionX": "2",
        "dimensionY": "2",
        "rowCountLimitInspection": "10000",
        "rowCountLimitMeasurement": "10000",
        "userId": "WFS",
        "prodMtrlList": [
            {
                "slotNo": "1",
                "prodMtrlId": "ET24400927",
                "subMtrlGrdCd": "000000000000000000000000000000000000000000000000"
            },
            {
                "slotNo": "3",
                "prodMtrlId": "ET24400929",
                "subMtrlGrdCd": "000000000000000000000000000000000000000000000000"
            },
            {
                "slotNo": "4",
                "prodMtrlId": "ET24400930",
                "subMtrlGrdCd": "000000000000000000000000000000000000000000000000"
            },
            {
                "slotNo": "5",
                "prodMtrlId": "ET24400931",
                "subMtrlGrdCd": "000000000000000000000000000000000000000000000000"
            },
            {
                "slotNo": "6",
                "prodMtrlId": "ET24401051",
                "subMtrlGrdCd": "000000000000000000000000000000000000000000000000"
            }
        ]
    }

}
 */
