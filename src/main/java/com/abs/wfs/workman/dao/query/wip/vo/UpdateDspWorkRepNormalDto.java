package com.abs.wfs.workman.dao.query.wip.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateDspWorkRepNormalDto {
    private String siteId;
    private String cid;
    private String tid;
    private String carrId;
    private String lotId;
    private String userId;
    private String resvEqpId;
    private String resvPortId;
    private String resvGrpId;
    private String resvOutCarrId;
    private String resvOutPortId;
}
