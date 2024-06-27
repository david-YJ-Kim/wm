package com.abs.wfs.workman.spec.in.eap.common;

import com.abs.wfs.workman.spec.out.eap.common.SlotMapVo;
import lombok.Data;

import java.util.List;

@Data
public class WorkVo {
    String jobSeqId;
    String portType;
    String inPortId;
    String outPortId;
    String scanYn;
    String inCarrId;
    String outCarrId;
    List<SlotMapVo> slotMap;
}
