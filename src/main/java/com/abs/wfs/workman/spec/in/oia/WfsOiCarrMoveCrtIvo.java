package com.abs.wfs.workman.spec.in.oia;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsOiCarrMoveCrtIvo extends ApMsgCommonVo {

	public static String system = ApSystemCodeConstant.WFS;
	public static String cid = WorkManMessageList.WFS_OI_CARR_MOVE_CRT;

	WfsOiCarrMoveCrtBody body;

	@Data
	public static class WfsOiCarrMoveCrtBody extends ApMsgBody {

		String srcEqpId;
		String srcPortId;
		String destEqpId;
		String destPortId;
		String comment;



	}
}
/*
{
  "head": {
    "cid": "WFS_CARR_MOVE_CRT_OI",
    "osrc": "",
    "otgt": "",
    "src": "OIA",
    "srcEqp": "",
    "tgt": "WFS",
    "tgtEqp": [

    ],
    "tid": "UI_20240627151943",
    "lang": "en"
  },
  "body": {
    "carrId": "CAA0104",
    "srcEqpId": "ASTK03",
    "srcPortId": "ASTK03_STORAGE",
    "destEqpId": "AP-TG-07-01",
    "destPortId": "AP-TG-07-01-BP02",
    "comment": "sl2 200\n",
    "userId": "OI_2404002",
    "siteId": "SVM"
  }
}
 */