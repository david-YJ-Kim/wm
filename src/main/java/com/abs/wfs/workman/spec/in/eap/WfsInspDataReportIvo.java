package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.eap.common.PortInfoVo;
import lombok.Data;

import java.util.List;


/*
{
  "head": {
    "tgt": "WFS",
    "tgtEqp": [

    ],
    "osrc": "",
    "srcEqp": "AP-SP-01-01",
    "src": "EAP",
    "tid": "AP-SP-01-01_00_20240618104715711",
    "cid": "WFS_INSP_DATA_REPORT"
  },
  "body": {
    "siteId": "SVM",
    "userId": "EAP",
    "carrId": "CAA0275",
    "lotId": "D24500430",
    "eqpId": "AP-SP-01-01",
    "mtrlFace": "TTT",
    "jobSeqId": "1",
    "resultCode": "1",
    "prodMtrlId": "ET24400835",
    "slotNo": "1",
    "workId": "WORK_20240618104100284"
  }
}
 */
@Data
public class WfsInspDataReportIvo extends ApMsgCommonVo {


    WfsInspDataReportBody body;

    @Data
    public static class WfsInspDataReportBody extends ApMsgBody {

        String mtrlFace;
        String jobSeqId;
        String resultCode;
        String prodMtrlId;
        String slotNo;
        String workId;

    }

}
