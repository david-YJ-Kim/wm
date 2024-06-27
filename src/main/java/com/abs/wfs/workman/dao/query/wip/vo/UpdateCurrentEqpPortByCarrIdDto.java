package com.abs.wfs.workman.dao.query.wip.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateCurrentEqpPortByCarrIdDto {
    private String siteId;
    private String cid;
    private String tid;
    private String carrId;
    private String userId;
    private String crntEqpId;
    private String crntPortId;
}