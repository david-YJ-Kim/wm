package com.abs.wfs.workman.spec.out.eap.common;

import lombok.Data;

/*
  {
    "ppId": "0000LA0401UPPERSIDE"
    "lotId": "A24600070",
    "slotNo": "",
    "prodMtrlId": "FS24500005",
    "mtrlFace": "TTT",
  }
 */

@Data
public class ProcessSlotMapVo {

    String ppId;
    String lotId;
    String slotNo;
    String prodMtrlId;
    String mtrlFace;
}
