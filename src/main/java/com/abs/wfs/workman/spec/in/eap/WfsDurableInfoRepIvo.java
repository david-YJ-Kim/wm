package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.MsgReasonVo;
import com.abs.wfs.workman.spec.in.eap.common.SpecInfoVo;
import lombok.Data;

import java.util.List;

/*
{
  "head": {
    "tgt": "WFS",
    "tgtEqp": [

    ],
    "srcEqp": "AP-LA-02-01",
    "osrc": "",
    "src": "EAP",
    "tid": "AP-LA-02-01_20240620111804962",
    "cid": "WFS_DURABLE_INFO_REP"
  },
  "body": {
    "reason": {
      "reasonCode": "0",
      "reasonComment": ""
    },
    "siteId": "SVM",
    "eqpId": "AP-LA-02-01",
    "userId": "FUSEI"
    "specInfo": [
      {
        "specId": "MAT005",
        "specType": "Material",
        "specUseCnt": "65535",
        "subEqpId": "MODULE3",
        "specPosition": "Top"
      },
      {
        "specId": "MAT006",
        "specType": "Material",
        "specUseCnt": "65535",
        "subEqpId": "MODULE3",
        "specPosition": "Bottom"
      }
    ],
  }
}
 */
@Data
public class WfsDurableInfoRepIvo extends ApMsgCommonVo {

    WfsDurableInfoRepBody body;

    @Data
    public static class WfsDurableInfoRepBody extends ApMsgBody{

        MsgReasonVo reason;
        List<SpecInfoVo> specInfo;

    }
}
