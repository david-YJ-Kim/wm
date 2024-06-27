package com.abs.wfs.workman.spec.out.eap.common;

import lombok.Data;

import java.util.List;

@Data
public class SorterInfo {

    String inPortId;
    String inCarrId;
    String outPortId;
    String outCarrId;
    String sorterType;
    String scanYn;
    List<SlotMapVo> slotMap;
}
