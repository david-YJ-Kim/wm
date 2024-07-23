package com.abs.wfs.workman.dao.query.service.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatePortStatAndCarrierReqVo {

    String siteId;
    String cid;
    String tid;
    String userId;
    String efemCommStateCd;
    String efemStateCd;
    String efemToolStateCd;
    String statCd;
    String trsfStatCd;
    String acesModeCd;
    String ctrlModeCd;
    String carrId;
    String eqpId;
    String portId;
}
