package com.abs.wfs.workman.spec.out.brs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class BrsLotSelfInspDataIvo extends ApMsgCommonVo {

	public static String system = "BRS";
	public static String cid = "BRS_LOT_SELF_INSP_DATA";


	BrsLotSelfInspDataBody body;

	@Data
	public static class BrsLotSelfInspDataBody extends ApMsgBody {

		String workId;
		String jobSeqId;
		String slotNo;
		String prodMtrlId;
		String mtrlFaceCd;
		String prodDefId;
		String procDefId;
		String procSgmtId;
		String selfInspRsltCd;
		String selfInspObjId;
		String mdfyUserId;
	}

}

/*
{
  "head": {
    "cid": "BRS_LOT_SELF_INSP_DATA",
    "tid": "AP-RD-06-01_00_20240517190205590",
    "osrc": "",
    "otgt": "",
    "src": "WFS",
    "tgt": "BRS",
    "srcEqp": "",
    "tgtEqp": [
      ""
    ]
  },
  "body": {
    "siteId": "SVM",
    "lotId": "A23400022",
    "eqpId": "AP-RD-06-01",
    "carrId": "CAA0214",
    "userId": "WFS",
    "workId": "WORK_20240517185510235",
    "jobSeqId": "1",
    "slotNo": "2",
    "prodMtrlId": "ET24400175",
    "mtrlFaceCd": "Top",
    "prodDefId": "SE23-01-001",
    "procDefId": "T00003D00C",
    "procSgmtId": "P41401",
    "selfInspRsltCd": "OK",
    "selfInspObjId": "20240517184934274-494dba86-acca-4ef3-bef6-cff8fe0ce5aa",
    "mdfyUserId": "WFS"
  }
}
 */