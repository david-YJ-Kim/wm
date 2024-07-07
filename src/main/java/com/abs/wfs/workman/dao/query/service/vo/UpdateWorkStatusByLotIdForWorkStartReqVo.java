package com.abs.wfs.workman.dao.query.service.vo;


import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import lombok.Builder;
import lombok.Data;

@Data
public class UpdateWorkStatusByLotIdForWorkStartReqVo {
    String siteId;
    String cid;
    String tid;
    String lotId;
    String mdfyUserId;
    String workStatCd;

    @Builder
    public UpdateWorkStatusByLotIdForWorkStartReqVo(ApFlowProcessVo apFlowProcessVo, String workStatCd) {
        this.siteId = apFlowProcessVo.getApMsgBody().getSiteId();
        this.cid = apFlowProcessVo.getEventName();
        this.tid = apFlowProcessVo.getTid();
        this.lotId = apFlowProcessVo.getApMsgBody().getLotId();
        this.mdfyUserId = apFlowProcessVo.getApMsgBody().getUserId();
        this.workStatCd = workStatCd;
    }
}
