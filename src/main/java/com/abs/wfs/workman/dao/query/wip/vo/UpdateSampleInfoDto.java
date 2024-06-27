package com.abs.wfs.workman.dao.query.wip.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateSampleInfoDto {
    private String siteId;
    private String cid;
    private String tid;
    private String userId;
    private String lotId;
    private String smplLotYn;
    private String smplWorkTyp;
    private String smplQty;
}
