package com.abs.wfs.workman.spec.in.rms;
import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.rms.common.recipeListVo;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

import java.util.List;

@Data
public class WfsRecipeValidateRepIvo extends ApMsgCommonVo {

    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_RECIPE_VALIDATE_REP;

    WfsRecipeValidateRepBody body;

    @Data
    public static class WfsRecipeValidateRepBody extends ApMsgBody{
        String prodDefId;
        List<recipeListVo> recipeList;
        String resultCode;
        String procSgmtId;
        String resultMessage;
        String procDefId;
    }
}

/*
{
	"head": {
		"tgt": "WFS",
		"tgtEqp": [
			"AP-EM-08-01"
		],
		"srcEqp": null,
		"osrc": "/rms/WFS_RECIPE_VALIDATE_REQ/20240703140501464",
		"src": "RMS",
		"otgt": null,
		"tid": "AP-EM-08-01_00_20240703140501557",
		"cid": "WFS_RECIPE_VALIDATE_REP"
	},
	"body": {
		"carrId": "CAA0024",
		"prodDefId": "DT05-03-001",
		"recipeList": [
			{
				"recipeId": "0000EM0801SFTEST0001"
			}
		],
		"resultCode": "0",
		"siteId": "SVM",
		"lotId": "D24700030",
		"eqpId": "AP-EM-08-01",
		"procSgmtId": "EM080101",
		"portId": "AP-EM-08-01-BP02",
		"resultMessage": "0000EM0801SFTEST0001-1",
		"procDefId": "DTCR51D00J"
	}
}
 */
