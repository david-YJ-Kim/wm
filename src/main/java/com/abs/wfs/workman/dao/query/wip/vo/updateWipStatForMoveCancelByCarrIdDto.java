package com.abs.wfs.workman.dao.query.wip.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class updateWipStatForMoveCancelByCarrIdDto {

    private String siteId;
    private String cid;
    private String tid;
    private String userId;
    private String carrId;
    private String crntEqpId;
    private String crntPortId;
    private String workStatCd;
    private String resvEqpId;
    private String resvPortId;
    private String resvGrpId;
    private String resvOutCarrId;
    private String resvOutPortId;
    private String mdfyUserId;
    private String evntNm;
    private String objId;
    private String useStatCd;
}
