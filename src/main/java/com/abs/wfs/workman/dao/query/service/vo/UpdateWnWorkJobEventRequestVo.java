package com.abs.wfs.workman.dao.query.service.vo;

import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import lombok.Builder;
import lombok.Data;

@Data
public class UpdateWnWorkJobEventRequestVo{


    String siteId;
    String cid;
    String tid;
    String userId;
    String workId;
    String jobSeqId;


    @Builder
    public UpdateWnWorkJobEventRequestVo(ApFlowProcessVo apFlowProcessVo, String workId, String jobSeqId) {
        this.siteId = apFlowProcessVo.getApMsgBody().getSiteId();
        this.cid = apFlowProcessVo.getEventName();
        this.tid = apFlowProcessVo.getTid();
        this.userId = ApSystemCodeConstant.WFS;
        this.workId = workId;
        this.jobSeqId = jobSeqId;
    }
}
