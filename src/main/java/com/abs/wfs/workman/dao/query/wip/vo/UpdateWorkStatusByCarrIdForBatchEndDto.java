package com.abs.wfs.workman.dao.query.wip.vo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateWorkStatusByCarrIdForBatchEndDto {

    private String siteId;
    private String cid;
    private String tid;
    private String carrId;
    private String mdfyUserId;
    private String workStatCd;
    private boolean dspInfoClearFlag;
}
