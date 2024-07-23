package com.abs.wfs.workman.dao.query.service.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateCurrentEqpPortByCarrIdReqVo {

    String siteId;
    String cid;
    String tid;
    String carrId;
    String userId;
    String crntEqpId;
    String crntPortId;
}
