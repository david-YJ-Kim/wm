package com.abs.wfs.workman.spec.in.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.mcs.common.carrIfrmListVo;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

import java.util.List;

@Data
public class WfsInvtCarrDataIvo extends ApMsgCommonVo {
    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_INVT_CARR_DATA;

    WfsInvtCarrDataBody body;
    @Data
    public static class WfsInvtCarrDataBody extends ApMsgBody{
        String eventUserId;
        String eventComment;
        List<carrIfrmListVo> carrIfrmList;
    }
}
/*
{
  "head" : {
    "tgt" : "WFS",
    "cid" : "WFS_INVT_CARR_DATA",
    "tid" : "171958059705805561",
    "osrc" : "MCS",
    "otgt" : "WFS",
    "src" : "MCS"
  },
  "body" : {
    "siteId" : "SVM",
	"eventUserId" : "ASTK02",
	"eventComment" : "",
    "eqpId" : "ASTK02",
    "carrIfrmList":[{
		"carrId":"",
		"crntEqpId":"",
		"crntPortId":""
	}]
  }
}
 */