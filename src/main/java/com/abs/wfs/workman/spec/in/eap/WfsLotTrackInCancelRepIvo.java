package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.eap.common.WorkVo;
import lombok.Data;

/*
{
  "head": {
    "cid": "WFS_LOT_TRACK_IN_CANCEL_REP",
    "tid": "BRS_20240620133145026",
    "osrc": null,
    "otgt": null,
    "src": "BRS",
    "srcEqp": null,
    "tgt": "WFS",
    "lang": null,
    "tgtEqp": null
  },
  "body": {
    "siteId": "SVM",
    "lotId": "D24600090",
    "carrId": null,
    "userId": "BRS"
  }
}
 */

@Data
public class WfsLotTrackInCancelRepIvo extends ApMsgCommonVo {
	private WfsLotTrackInCancelRepBody body;

	@Data
	public static class WfsLotTrackInCancelRepBody extends ApMsgBody{
	}
}
