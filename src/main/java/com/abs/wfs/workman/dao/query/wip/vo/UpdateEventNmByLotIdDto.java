package com.abs.wfs.workman.dao.query.wip.vo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateEventNmByLotIdDto {

    private String siteId;
    private String cid;
    private String tid;
    private String lotId;
    private String mdfyUserId;
}
