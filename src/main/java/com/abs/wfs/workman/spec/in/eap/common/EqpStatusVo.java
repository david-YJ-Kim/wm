package com.abs.wfs.workman.spec.in.eap.common;

import lombok.Data;

@Data
public class EqpStatusVo {

    private String eqpStateCd;
    private String eqpCommStateCd;
    private String inPortId;
    private String inPortStateCd;
    private String inPortCommStateCd;
    private String inPortAutoModeFlag;
    private String outPortId;
    private String outPortStateCd;
    private String outPortCommStateCd;
    private String outPortAutoModeFlag;

}
