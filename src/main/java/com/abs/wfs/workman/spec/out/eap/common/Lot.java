package com.abs.wfs.workman.spec.out.eap.common;

import lombok.Data;

import java.util.List;

@Data
public class Lot {

    private String lotId;
    private List<SlotMapCell> slotMap;
}
