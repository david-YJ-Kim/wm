package com.abs.wfs.workman.dao.query.service.vo;

import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Builder;
import lombok.Data;

@Data
public class UpdateWnWorkJobSlotInfoForEndTmReqVo {
    String siteId;
    String cid;
    String tid;
    String userId;
    String workId;
    String jobSeqId;
    String prodMtrlId;
    String slotNo;

    @Builder
    public UpdateWnWorkJobSlotInfoForEndTmReqVo(ApFlowProcessVo vo, String workId, String jobSeqId, String prodMtrlId, String slotNo) {
        this.siteId = vo.getApMsgBody().getSiteId();
        this.cid = vo.getEventName();
        this.tid = vo.getTid();
        this.userId = ApSystemCodeConstant.WFS;
        this.workId = workId;
        this.jobSeqId = jobSeqId;
        this.prodMtrlId = prodMtrlId;
        this.slotNo = slotNo;
    }
}
