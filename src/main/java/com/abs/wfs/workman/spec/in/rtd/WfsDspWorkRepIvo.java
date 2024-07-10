package com.abs.wfs.workman.spec.in.rtd;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsDspWorkRepIvo extends ApMsgCommonVo {

    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_DSP_WORK_REP;

    Body body;

    @Data
    public static class Body extends ApMsgBody {
        String dspType;
        String prodDefId;
        String procDefId;
        String evntCm;
        String rsnCd;
        String rsltCm;
        String evntUserId;
    }
}
/*
{
  "head": {
    "tgt": "WFS",
    "src": "RTD",
    "tid": "202407091151209765405",
    "cid": "WFS_DSP_WORK_REP"
  },
  "body": {
    "rsnCd": "FAIL",
    "evntUserId": "RTD",
    "dspType": "LOT",
    "carrId": "",
    "prodDefId": "",
    "siteId": "SVM",
    "lotId": "",
    "eqpId": "AP-RD-04-02",
    "portId": "AP-RD-04-02-BP01",
    "rsltCm": "[ERR CODE:21] [AP-RD-04-02-BP01] EFEM CTRL MODE (Offline)",
    "procDefId": "",
    "evntCm": ""
  }
}
 */
