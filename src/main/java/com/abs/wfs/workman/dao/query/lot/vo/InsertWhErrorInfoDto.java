package com.abs.wfs.workman.dao.query.lot.vo;


import lombok.Builder;
import lombok.Data;

@Data
public class InsertWhErrorInfoDto {
    private String siteId;
    private String msgId;
    private String msgCtnsCm;
    private String workStatCd;
    private String lotId;
    private String carrId;
    private String eqpId;
    private String portId;
    private String errCd;
    private String errCm;
    private String cid;
    private String userId;
    private String tid;



    @Builder
    public InsertWhErrorInfoDto(String siteId, String msgId, String msgCtnsCm, String workStatCd, String lotId, String carrId, String eqpId, String portId, String errCd, String errCm, String cid, String userId, String tid) {
        this.siteId = siteId;
        this.msgId = msgId;
        this.msgCtnsCm = msgCtnsCm;
        this.workStatCd = workStatCd;
        this.lotId = lotId;
        this.carrId = carrId;
        this.eqpId = eqpId;
        this.portId = portId;
        this.errCd = errCd;
        this.errCm = errCm;
        this.cid = cid;
        this.userId = userId;
        this.tid = tid;
    }
}
