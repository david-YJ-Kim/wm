package com.abs.wfs.workman.dao.query.eqp.vo;


import lombok.Builder;
import lombok.Data;

@Data
public class UpdatePortStatAndCarrierDto {

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

    @Builder
    public UpdatePortStatAndCarrierDto(String siteId, String cid, String tid, String userId, String efemCommStateCd, String efemStateCd, String efemToolStateCd, String statCd, String trsfStatCd, String acesModeCd, String ctrlModeCd, String carrId, String eqpId, String portId) {
        this.siteId = siteId;
        this.cid = cid;
        this.tid = tid;
        this.userId = userId;
        this.efemCommStateCd = efemCommStateCd;
        this.efemStateCd = efemStateCd;
        this.efemToolStateCd = efemToolStateCd;
        this.statCd = statCd;
        this.trsfStatCd = trsfStatCd;
        this.acesModeCd = acesModeCd;
        this.ctrlModeCd = ctrlModeCd;
        this.carrId = carrId;
        this.eqpId = eqpId;
        this.portId = portId;
    }
}


/*

 */
