package com.abs.wfs.workman.spec.in.eap;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.AlarmEqpType;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;


@Data
public class WfsToolVerReportIvo extends ApMsgCommonVo {

    public static String system = ApSystemCodeConstant.EAP;
    public static String cid = WorkManMessageList.WFS_TOOL_VER_REPORT;

    Body body;

    @Data
    public static class Body extends ApMsgBody{

        AlarmEqpType eqpType;
        String portType;
        String version;
    }
}

/* 설비(EQP) 버전 보고 샘플
{
	    "head": {
	        "tid": "tid",
	        "cid": "WFS_TOOL_VER_REPORT",
	        "src": "EAP",
	        "tgt": "WFS",
	        "osrc": "",
	        "srcEqp": "AM-RE-00-01",
	        "tgtEqp": []
	    },
	    "body": {
	        "siteId": "SVM",
	        "eqpId": "AM-RE-00-01",
	        "eqpType": "EQP",
	        "portType": "",
	        "version": "1.0.0",
	        "userId": "Skilled Worker"
    }
}
 */

/* EFEM 버전 보고 샘플
{
	    "head": {
	        "tid": "tid",
	        "cid": "WFS_TOOL_VER_REPORT",
	        "src": "EAP",
	        "tgt": "WFS",
	        "osrc": "",
	        "srcEqp": "AP-TG-01-01",
	        "tgtEqp": []
	    },
	    "body": {
	        "siteId": "SVM",
	        "eqpId": "AP-TG-01-01",
	        "eqpType": "EFEM",
	        "portType": "IP || OP || BP",
	        "version": "1.0.0",
	        "userId": "Skilled Worker"
    }
}
 */
