package com.abs.wfs.workman.spec.out.eap.common;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Work {

    private String jobSeqId;
    private String scanYn;
    private String inPortId;
    private String inCarrId;
    private String outPortId;
    private String outCarrId;
    private String portType;
    private List<SlotMapVo> slotMap;
}
