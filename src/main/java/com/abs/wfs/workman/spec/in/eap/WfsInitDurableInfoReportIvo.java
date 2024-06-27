package com.abs.wfs.workman.spec.in.eap;

import com.abs.wfs.workman.spec.ApMsgCommonVo;
import com.abs.wfs.workman.spec.common.ApMsgBody;
import com.abs.wfs.workman.spec.in.eap.common.SpecInfoVo;
import lombok.Data;

/*
{
  "head": {
    "tgt": "WFS",
    "tgtEqp": [

    ],
    "srcEqp": "AP-DC-01-01",
    "osrc": "",
    "src": "EAP",
    "tid": "AP-DC-01-01_20240619163500474",
    "cid": "WFS_INIT_DURABLE_INFO_REPORT"
  },
  "body": {
    "siteId": "SVM",
    "specInfo": [
      {
        "specId": "002001",
        "specType": "Durable",
        "specUseCnt": "1.100,2.200,3.300,4.400,5.500,6.600,7.700",
        "specRateUseCnt": "1.100,2.200,3.300,4.400,5.500,6.600,7.700",
        "subEqpId": "GR1_ST1",
        "specPosition": "0"
      },
      {
        "specId": "004002",
        "specType": "Durable",
        "specUseCnt": "36.588,0.000,16.338,3.912,0.000,0.000,0.000",
        "specRateUseCnt": "36.588,0.000,16.338,3.912,0.000,0.000,0.000",
        "subEqpId": "GR4_ST2",
        "specPosition": "0"
      }
    ],
    "eqpId": "AP-DC-01-01",
    "userId": "EAP"
  }
}
 */

@Data
public class WfsInitDurableInfoReportIvo extends ApMsgCommonVo {
	private WfsInitDurableInfoReportBody body;

	@Data
	public static class WfsInitDurableInfoReportBody extends ApMsgBody{
		private SpecInfoVo specInfo;
	}
}
