package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.eap.common.WorkVo;
import lombok.Data;

/*
{
  "head": {
    "cid": "WFS_PRECEDE_LOT_RESUME",
    "tid": "BRS_20240620102527634",
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
    "lotId": "D24500080",
    "carrId": "CAA0019",
    "operationType": "process",
    "userId": "BRS"
  }
}
 */

@Data
public class WfsPrecedeLotResumeIvo extends ApMsgCommonVo {
	private WfsPrecedeLotResumeBody body;

	@Data
	public static class WfsPrecedeLotResumeBody extends ApMsgBody{
		private String operationType;
	}
}
