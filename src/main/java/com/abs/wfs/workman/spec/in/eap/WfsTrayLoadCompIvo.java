package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsTrayLoadCompIvo extends ApMsgCommonVo {

    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_TRAY_LOAD_COMP;
    Body body;

    @Data
    public static class Body extends ApMsgBody {

        String prodMtrlId;
        String prodMtrlExist; // Tray에 Panel 존재 유무(Y/N)
    }
}

/* 빈 Tray 로딩
{
	"head": {
		"tgt": "WFS",
		"tgtEqp": [],
		"osrc": "",
		"srcEqp": "",
		"src": "EAP",
		"tid": "WFS_00_20240620170629897",
		"cid": "WFS_TRAY_LOAD_COMP"
	},
	"body": {
		"siteId": "SVM",
		"eqpId": "AM-RE-00-01",
		"portId": "AM-RE-00-01-BP04",
		"carrId": "PTA0037",
		"prodMtrlId": "",
		"prodMtrlExist": "N",
		"userId": "Skilled User"
	}
}
 */


/* 실 Tray 로딩
{
	"head": {
		"tgt": "WFS",
		"tgtEqp": [],
		"osrc": "",
		"srcEqp": "",
		"src": "EAP",
		"tid": "WFS_00_20240620170629897",
		"cid": "WFS_TRAY_LOAD_COMP"
	},
	"body": {
		"siteId": "SVM",
		"eqpId": "AM-RE-00-01",
		"portId": "AM-RE-00-01-BP04",
		"carrId": "Tray_00001",
		"prodMtrlId": "ET24400043",
		"prodMtrlExist": "Y",
		"userId": "Skilled User"
	}
}
 */