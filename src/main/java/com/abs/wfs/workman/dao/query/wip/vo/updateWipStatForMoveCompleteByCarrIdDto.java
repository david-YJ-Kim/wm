package com.abs.wfs.workman.dao.query.wip.vo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class updateWipStatForMoveCompleteByCarrIdDto {

    private String siteId;
    private String cid;
    private String tid;
    private String userId;
    private String carrId;
    private String crntEqpId;
    private String crntPortId;
    private String workStatCd;
    private String trackInCnfmYn;
    private String rcpChkYn;
    private String eqpChkYn;
    private String mdfyUserId;
    private String evntNm;
    private String objId;
    private String useStatCd;
}
