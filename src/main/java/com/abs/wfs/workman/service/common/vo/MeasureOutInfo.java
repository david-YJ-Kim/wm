package com.abs.wfs.workman.service.common.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MeasureOutInfo {


    String linkedPortId;
    String prevSlotNo;
    String prevCarrId;
}
