package com.abs.wfs.workman.dao.query.service.vo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatePortCarrierRequestVo {

    String siteId;
    String cid;
    String tid;
    String userId;
    String carrierId;
    String eqpId;
    String portId;
}
