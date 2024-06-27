package com.abs.wfs.workman.dao.query.wip.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateSelfInspInfoDto {
    private String siteId;
    private String cid;
    private String tid;
    private String userId;
    private String lotId;
    private String selfInspYn;
    private String selfInspPanelCnt;
    private String selfInspObjId;
}
