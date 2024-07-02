package com.abs.wfs.workman.spec.in.mcs;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.mcs.common.AlrmIfrmListVo;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

import java.util.List;

@Data
public class WfsEqpAlrmChgIvo extends ApMsgCommonVo {
    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_EQP_ALRM_CHG;

    WfsEqpAlrmChgBody body;

    @Data
    public static class WfsEqpAlrmChgBody extends ApMsgBody{

        String eventUserId;
        String eventComment;
        List<AlrmIfrmListVo> alrmIfrmList;

    }
}
/*
{
  "head" : {
    "tgt" : "WFS",
    "cid" : "WFS_EQP_ALRM_CHG",
    "tid" : "171985840313474047",
    "osrc" : "MCS",
    "otgt" : "WFS",
    "src" : "MCS"
  },
  "body" : {
    "siteId" : "SVM",
	"eventUserId" : "OI_yuny91",
	"eventComment" : "",
	"eqpId" : "ABUF01",
	"alrmIfrmList":[{
	"alrmId" : "test",
    "alrmTxt" : "test"
	}]
  }
}
* */