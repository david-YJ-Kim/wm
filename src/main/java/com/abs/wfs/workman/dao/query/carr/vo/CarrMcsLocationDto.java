package com.abs.wfs.workman.dao.query.carr.vo;


import lombok.Builder;
import lombok.Data;

@Data
public class CarrMcsLocationDto {

    String machinename;
    String name;
    String reserved;
    String occupied;
    String state;
    String carrId;
    String lotId;
    Long orgnQty;
    Long qty;

    String pUnitkind;
    String pMachinename;
    String pName;


    @Builder
    public CarrMcsLocationDto(String pUnitkind, String pMachinename, String pName) {
        this.pUnitkind = pUnitkind;
        this.pMachinename = pMachinename;
        this.pName = pName;
    }
}
