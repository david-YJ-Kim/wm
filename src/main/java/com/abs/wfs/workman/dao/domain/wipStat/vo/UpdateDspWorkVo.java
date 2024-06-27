package com.abs.wfs.workman.dao.domain.wipStat.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdateDspWorkVo {

    private String siteId;
    private String cid;
    private String tid;
    private String carrId;
    private String lotId;
    private String userId;
    private String resvEqpId;
    private String resvPortId;
    private String resvGrpId;
    private String resvOutCarrId;
    private String resvOutPortId;

    @Builder
    public UpdateDspWorkVo(String siteId, String cid, String tid, String carrId, String lotId, String userId, String resvEqpId, String resvPortId, String resvGrpId, String resvOutCarrId, String resvOutPortId) {
        this.siteId = siteId;
        this.cid = cid;
        this.tid = tid;
        this.carrId = carrId;
        this.lotId = lotId;
        this.userId = userId;
        this.resvEqpId = resvEqpId;
        this.resvPortId = resvPortId;
        this.resvGrpId = resvGrpId;
        this.resvOutCarrId = resvOutCarrId;
        this.resvOutPortId = resvOutPortId;
    }
}
