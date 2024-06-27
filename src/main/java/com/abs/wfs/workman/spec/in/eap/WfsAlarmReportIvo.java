package com.abs.wfs.workman.spec.in.eap;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.code.AlarmEqpType;
import lombok.Data;


@Data
public class WfsAlarmReportIvo extends ApMsgCommonVo {

    WfsAlarmReportBody body;

    @Data
    public static class WfsAlarmReportBody extends ApMsgBody{

        AlarmEqpType eqpType;
        String alarmState;
        String alarmText;
        String alarmId;
        String alarmCode;
    }
}
/**
 * {
 *     "head": {
 *         "tgt": "",
 *         "tgtEqp": [],
 *         "osrc": "",
 *         "srcEqp": "AP-RD-06-01",
 *         "src": "EAP",
 *         "tid": "AP-RD-06-01-Both_00_20240516104254726",
 *         "cid": "EAP_ALARM_REPORT"
 *     },
 *     "body": {
 *         "alarmState": "1",
 *         "alarmText": "SafetyKey Set to Manual(Teach)",
 *         "alarmId": "317",
 *         "siteId": "SVM",
 *         "eqpId": "AP-RD-06-01",
 *         "alarmCode": 5
 *     }
 * }
 */

/* 설비 (EQP) 알람
{
	"head": {
		"tgt": "WFS",
		"tgtEqp": [],
		"osrc": "",
		"srcEqp": "WFS",
		"src": "EAP",
		"tid": "WFS_00_20240620170629897",
		"cid": "WFS_ALARM_REPORT"
	},
	"body": {
		"alarmState": "0",
		"eqpType": "EQP",
		"alarmText": "IONIZER#1 OFF",
		"alarmId": "10760",
		"siteId": "SVM",
		"eqpId": "WFS",
		"alarmCode": 6
	}
}
 */

/* EFEM 알람
{
	"head": {
		"tgt": "WFS",
		"tgtEqp": [],
		"osrc": "",
		"srcEqp": "WFS",
		"src": "EAP",
		"tid": "AP-SR-02-01-In_00_20240620170654877",
		"cid": "WFS_ALARM_REPORT"
	},
	"body": {
		"alarmState": "0",
		"eqpType": "EFEM",
		"alarmText": "IONIZER#1 OFF",
		"alarmId": "10760",
		"siteId": "SVM",
		"eqpId": "WFS",
		"alarmCode": 6
	}
}
 */
