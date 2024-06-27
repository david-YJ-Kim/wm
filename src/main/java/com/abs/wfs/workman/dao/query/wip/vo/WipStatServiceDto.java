package com.abs.wfs.workman.dao.query.wip.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WipStatServiceDto {
    private String siteId;
    private String lotId;
    private String carrId;
    private String workStatCd;
    private String eventNm;
    private String trackInCnfmYn;
    private String rcpChkYn;
    private String userId;
    private String cid;
    private String tid;
    private String eqpPortId;
    private String batchId;
    private String workId;
    private String selfInspInfoObjId;
    private int sampleQty;
    private String sampleWorkTyp;

}
