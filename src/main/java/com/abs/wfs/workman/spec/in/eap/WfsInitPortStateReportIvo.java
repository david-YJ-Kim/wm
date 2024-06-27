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
    "srcEqp": "AP-PD-09-02",
    "src": "EAP",
    "tid": "AP-PD-09-02_20240618152516091",
    "cid": "WFS_INIT_PORT_STATE_REPORT"
  },
  "body": {
    "siteId": "SVM",
    "userId": "EAP",
    "eqpId": "AP-PD-09-02",
    "efemToolStateCd": "OFF",
    "eqpStateCd": "Down",
    "eqpCommStateCd": "OnlineLocal",
    "portInfo": [
      {
        "trsfStatCd": "ReadyToLoad",
        "portAutoModeFlag": "Auto",
        "portId": "AP-PD-09-02-BP01",
        "portStateCd": "Empty",
        "portCommStateCd": "InService"
      },
      {
        "trsfStatCd": "ReadyToLoad",
        "portAutoModeFlag": "Auto",
        "portId": "AP-PD-09-02-BP02",
        "portStateCd": "Empty",
        "portCommStateCd": "InService"
      }
    ]
  }
}
 */
@Data
public class WfsInitPortStateReportIvo extends ApMsgCommonVo {





    WfsInitPortStateReportBody body;

    @Data
    public static class WfsInitPortStateReportBody extends ApMsgBody {

        String efemToolStateCd;
        String eqpStateCd;
        String eqpCommStateCd;
        List<PortInfoVo> portInfo;

    }

}
