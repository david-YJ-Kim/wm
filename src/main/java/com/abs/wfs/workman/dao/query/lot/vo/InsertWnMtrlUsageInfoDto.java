package com.abs.wfs.workman.dao.query.lot.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InsertWnMtrlUsageInfoDto {
    private String siteId;
    private String workId;
    private String eqpId;
    private String subEqpId;
    private String lotId;
    private String specId;
    private String specTyp;
    private String specUseCnt;
    private String specLimitCnt;
    private String cid;
    private String userId;
    private String tid;
}