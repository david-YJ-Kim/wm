package com.abs.wfs.workman.service.common.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MeasureOutPortCarrInfoReqVo {

    String siteId;
    String lotId;
    String portId;
    String carrId;
    String prodMtrlId;

    boolean panelInputYn;
}
