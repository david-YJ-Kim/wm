package com.abs.wfs.workman.dao.query.service.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatePortAutoUnloadYnReqVo {

    String siteId;
    String cid;
    String tid;
    String userId;
    String autoUnloadYn;
    String eqpId;
    String portId;
}
