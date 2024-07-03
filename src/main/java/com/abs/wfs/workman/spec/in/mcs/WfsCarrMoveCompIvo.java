package com.abs.wfs.workman.spec.in.mcs;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Data;

@Data
public class WfsCarrMoveCompIvo extends ApMsgCommonVo {

    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_CARR_MOVE_COMP;

    WfsCarrMoveCompBody body;




    @Data
    public static class WfsCarrMoveCompBody extends ApMsgBody{
        String commId;
        String srcEqpId;
        String srcPortId;
        String destEqpId;
        String destPortId;
        String crntEqpId;
        String crntPortId;
        Long prio;
        String carrMoveStat;
        Long resultCode;
        String eventComment;
        String eventUserId;

    }
}
/*
{
	"head": {
		"tgt": "WFS",
		"cid": "WFS_CARR_MOVE_COMP",
		"tid": "171945692540243842",
		"osrc": "MCS",
		"otgt": "WFS",
		"src": "MCS"
	},
	"body": {
		"siteId": "SVM",
		"carrId": "CAA0049",

		"commId": "C20240626224950577-CAA0049",
		"srcEqpId": "ASTK01",
		"srcPortId": "ASTK01_IP01.OP",
		"destEqpId": "ACON01",
		"destPortId": "ACON01_IP01",
		"crntEqpId": "ACON01",
		"crntPortId": "ACON01_IP01",
		"prio": 30,
		"carrMoveStat": "ON-PORT",
		"resultCode": 0,
		"eventComment": "",
		"eventUserId": "IFACTSMCS"
	}
}
 */