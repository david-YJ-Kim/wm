package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;


/*
{
  "head": {
    "tgt": "WFS",
    "tgtEqp": [

    ],
    "osrc": "",
    "srcEqp": "AP-TG-09-01",
    "src": "EAP",
    "tid": "AP-TG-09-01_00_20240617181251941",
    "cid": "WFS_INSP_REPORT"
  },
  "body": {
    "siteId": "SVM",
    "lotId": "",
    "userId": "EAP",
    "eqpId": "AP-TG-09-01",
    "mtrlFace": "",
    "fileName": "I_LOT-01_AP-TG-09-01_TEST-PROC_GLASS-02_TOP_20240617180815.csv",
    "filePath": "Y:\\TEST-PROD\\AP-TG-09-01\\TEST-PROC\\LOT-01",
    "prodMtrlId": "GLASS-02",
    "fileType": "inspection"
  }
}
 */
@Data
public class WfsInspReportIvo extends ApMsgCommonVo {

    public static String system = ApSystemCodeConstant.FIS;
    public static String cid = WorkManMessageList.FIS_FILE_REPORT;


    WfsInspReportBody body;

    @Data
    public static class WfsInspReportBody extends ApMsgBody {

        String mtrlFace;
        String fileName;
        String filePath;
        String prodMtrlId;
        String fileType;

    }

}
