package com.abs.wfs.workman.dao.domain.wipStat.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdateWipStatForMoveCompleteByCarrIdReqVo {

    private String siteId;
    private String cid;
    private String tid;
    private String carrId;
    private String userId;
    private String crntEqpId;
    private String crntPortId;
    private String workStatCd;


    @Builder
    public UpdateWipStatForMoveCompleteByCarrIdReqVo(String siteId, String cid, String tid, String carrId, String userId, String crntEqpId, String crntPortId, String workStatCd) {
        this.siteId = siteId;
        this.cid = cid;
        this.tid = tid;
        this.carrId = carrId;
        this.userId = userId;
        this.crntEqpId = crntEqpId;
        this.crntPortId = crntPortId;
        this.workStatCd = workStatCd;
    }
}
