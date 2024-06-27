package com.abs.wfs.workman.spec.in.eap.common;

import lombok.Data;

/*
{
    "specId": "MAT005",
    "specType": "Material",
    "specUseCnt": "65535",
    "specRateUseCnt": "1.100,2.200,3.300,4.400,5.500,6.600,7.700",
    "subEqpId": "MODULE3",
    "specPosition": "Top"
  }
 */

@Data
public class SpecInfoVo {
    String specId;
    String specType;
    String specRateUseCnt;
    String specUseCnt;
    String subEqpId;
    String specPosition;

}
