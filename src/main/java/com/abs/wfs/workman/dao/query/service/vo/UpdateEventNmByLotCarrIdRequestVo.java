package com.abs.wfs.workman.dao.query.service.vo;


import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import lombok.Builder;
import lombok.Data;

@Data
public class UpdateEventNmByLotCarrIdRequestVo {

    String siteId;
    String cid;
    String tid;
    String lotId;
    String carrId;
    String mdfyUserId;


    @Builder
    public UpdateEventNmByLotCarrIdRequestVo(ApFlowProcessVo apFlowProcessVo, String lotId, String carrId, String mdfyUserId) {
        this.siteId = apFlowProcessVo.getApMsgBody().getSiteId();
        this.cid = apFlowProcessVo.getEventName();
        this.tid = apFlowProcessVo.getTid();
        this.lotId = lotId;
        this.carrId = carrId;
        this.mdfyUserId = mdfyUserId;
    }
}
