package com.abs.wfs.workman.dao.query.eqp.vo;


import lombok.Builder;
import lombok.Data;

@Data
public class UpdatePortCarrierDto {

    String siteId;
    String cid;
    String tid;
    String userId;
    String carrierId;
    String eqpId;
    String portId;


    @Builder
    public UpdatePortCarrierDto(String siteId, String cid, String tid, String userId, String carrierId, String eqpId, String portId) {
        this.siteId = siteId;
        this.cid = cid;
        this.tid = tid;
        this.userId = userId;
        this.carrierId = carrierId;
        this.eqpId = eqpId;
        this.portId = portId;
    }
}


/*

 */
