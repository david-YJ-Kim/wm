package com.abs.wfs.workman.dao.query.lot.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateCarrierMoveStatCdDto {
    private String siteId;
    private String cid;
    private String tid;
    private String userId;
    private String carrId;
    private String moveStatCd;
}