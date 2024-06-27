package com.abs.wfs.workman.spec.out.eap.common;

import lombok.Data;

/*
  {
    "mtrlFace": "TTT",
    "manualInsp": "N",
    "outCarrSlotNo": "1",
    "slotState": "",
    "lotId": "A24600070",
    "prodMtrlId": "FS24500005",
    "inCarrSlotNo": "1",
    "ppId": "0000LA0401UPPERSIDE"
  }
 */

@Data
public class SlotMapVo {

    String inCarrSlotNo;
    String outCarrSlotNo;
    String ppId;
    String lotId;
    String prodMtrlId;
    String slotState;
    String mtrlFace;
    String manualInsp;
}
