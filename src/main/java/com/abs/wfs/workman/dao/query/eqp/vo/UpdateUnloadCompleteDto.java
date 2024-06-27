package com.abs.wfs.workman.dao.query.eqp.vo;


import lombok.Builder;
import lombok.Data;

@Data
public class UpdateUnloadCompleteDto {

    String siteId;
    String cid;
    String tid;
    String userId;
    String statCd;
    String trsfStatCd;
    String eqpId;
    String portId;


    @Builder
    public UpdateUnloadCompleteDto(String siteId, String cid, String tid, String userId, String statCd, String trsfStatCd, String eqpId, String portId) {
        this.siteId = siteId;
        this.cid = cid;
        this.tid = tid;
        this.userId = userId;
        this.statCd = statCd;
        this.trsfStatCd = trsfStatCd;
        this.eqpId = eqpId;
        this.portId = portId;
    }
}


/*
{
  "siteId": "SVM",
  "tid": "ABSC01123331",
  "cid": "WFS_UNLOAD_COMP",
  "portId": "AP-LA-03-01-BP01",
  "eqpId": "AP-LA-03-01",
  "statCd": "Empty",
  "trsfStatCd": "ReadyToLoad",
  "userId": "WFS"
}
 */
