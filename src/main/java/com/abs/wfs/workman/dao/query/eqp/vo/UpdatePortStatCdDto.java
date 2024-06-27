package com.abs.wfs.workman.dao.query.eqp.vo;


import lombok.Builder;
import lombok.Data;

@Data
public class UpdatePortStatCdDto {

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

    @Builder
    public UpdatePortStatCdDto(String siteId, String cid, String tid, String userId, String statCd, String trsfStatCd, String acesModeCd, String ctrlModeCd, String eqpId, String portId) {
        this.siteId = siteId;
        this.cid = cid;
        this.tid = tid;
        this.userId = userId;
        this.statCd = statCd;
        this.trsfStatCd = trsfStatCd;
        this.acesModeCd = acesModeCd;
        this.ctrlModeCd = ctrlModeCd;
        this.eqpId = eqpId;
        this.portId = portId;
    }
}


/*

 */
