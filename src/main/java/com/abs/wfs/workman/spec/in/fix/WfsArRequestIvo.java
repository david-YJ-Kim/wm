package com.abs.wfs.workman.spec.in.fix;


import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.util.WorkManMessageList;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import com.abs.wfs.workman.util.code.WorkStatCd;
import lombok.Data;

@Data
public class WfsArRequestIvo extends ApMsgCommonVo {

    public static String system = ApSystemCodeConstant.WFS;
    public static String cid = WorkManMessageList.WFS_AR_REQUEST;

    private Body body;

    @Data
    public static class Body extends ApMsgBody{

        String eventName;
        String scenarioType;
        String targetSystem;
        String payload;
        WorkStatCd workStatCd;
        String errCd;
        String errCm;
        int duplicatedIssueCnt;
    }
}


/*
{
  "head": {
    "cid": "WFS_AR_REQUEST",
    "osrc": "",
    "otgt": "",
    "src": "WFS",
    "srcEqp": "",
    "tgt": "WFS",
    "tgtEqp": [

    ],
    "tid": "UI_20240627151943",
    "lang": "en"
  },
  "body": {
    "eventName": "WFS_TOOL_COND_REP",
    "scenarioType": "BP_SINGLE",
    "siteId": "SVM",
    "targetSystem": "EAP",
    "payload": "{\"head\":{\"tgt\":\"WFS\",\"tgtEqp\":[],\"srcEqp\":\"AP-PD-01-01\",\"osrc\":\"\",\"src\":\"EAP\",\"tid\":\"AP-PD-01-01_20241007111449541\",\"cid\":\"WFS_TOOL_COND_REP\"},\"body\":{\"reason\":{\"reasonCode\":\"0\",\"reasonComment\":\"\"},\"materialList\":[],\"siteId\":\"SVM\",\"eqpId\":\"AP-PD-01-01\",\"userId\":\"EAP\",\"status\":{\"inPortAutoModeFlag\":\"Auto\",\"outPortStateCd\":\"Empty\",\"inPortStateCd\":\"Empty\",\"outPortAutoModeFlag\":\"Auto\",\"inPortCommStateCd\":\"InService\",\"outPortId\":\"AP-PD-01-01-BP01\",\"eqpStateCd\":\"Idle\",\"eqpCommStateCd\":\"OnlineRemote\",\"outPortCommStateCd\":\"InService\",\"inPortId\":\"AP-PD-01-01-BP01\"}}}",
    "workStatCd": "Ready",
    "lotId": "",
    "carrId": "",
    "eqpId": "AP-PD-01-01",
    "portId": "AP-PD-01-01-BP01",
    "errCd": "IsLoadedPort",
    "errCm": "IsLoadedPort ERROR : [TN_POS_PORT[STAT_CD=Occupied(Occupied:eq)]- OK, TN_POS_PORT[TRSF_STAT_CD=LoadCompleted(LoadCompleted:eq)]- OK, TN_POS_PORT[CTRL_MODE_CD=OutService(InService:eq)]- NG]",
    "duplicatedIssueCnt": 0,
  }
}
 */