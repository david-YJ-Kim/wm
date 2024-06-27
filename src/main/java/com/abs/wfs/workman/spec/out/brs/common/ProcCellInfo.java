package com.abs.wfs.workman.spec.out.brs.common;

import lombok.Data;

import java.util.List;

@Data
public class ProcCellInfo {

    private List<String> process;
    private List<String> scarp;
    private List<String> dump;
    private String carrId;
    private String cellQty;
    private List<Unit> unitList;
}
