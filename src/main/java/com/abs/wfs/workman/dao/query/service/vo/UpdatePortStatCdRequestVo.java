package com.abs.wfs.workman.dao.query.service.vo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatePortStatCdRequestVo {

    String siteId;
    String cid;
    String tid;
    String userId;
    String statCd;
    String trsfStatCd;
    String acesModeCd;
    String ctrlModeCd;
    String eqpId;
    String portId;
}
