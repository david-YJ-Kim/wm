package com.abs.wfs.workman.spec.out.brs.common;

import lombok.Data;

import java.util.List;

@Data
public class CarrInfo {

    String carrId;
    String cellQty;
    List<Slots> slots;
}
