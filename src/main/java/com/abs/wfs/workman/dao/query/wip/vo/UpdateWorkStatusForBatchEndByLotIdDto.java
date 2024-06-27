package com.abs.wfs.workman.dao.query.wip.vo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateWorkStatusForBatchEndByLotIdDto {

    private String siteId;
    private String cid;
    private String tid;
    private String lotId;
    private String mdfyUserId;
    private String workStatCd;
    private boolean dspInfoClearFlag;
}
