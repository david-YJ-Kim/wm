package com.abs.wfs.workman.dao.query.wip.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateDspWorkRepBatchDto {
    private String siteId;
    private String cid;
    private String tid;
    private String carrId;
    private String userId;
    private String batchId;
    private String batchSeq;
    private String resvEqpId;
    private String resvPortId;
    private String resvGrpId;
}
