package com.abs.wfs.workman.dao.domain.ppsRecipe.model;


import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CN_PPS_RECIPE")
public class CnPpsRecipe {

    @Id
    @GenericGenerator(name = "CN_PPS_RECIPE_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "CN_PPS_RECIPE_SEQ_GENERATOR")
    @Column(name = "OBJ_ID")
    private String objId;

    private String siteId;
    private String prodDefId;
    private String procDefId;
    private String procSgmtId;
    private String procNodeId;
    private String eqpId;
    private String mtrlFaceCd;
    private String topRcpId;
    private String bottomRcpId;
    private String evntNm;
    private String prevEvntNm;
    private String cstmEvntNm;
    private String prevCstmEvntNm;

    @Enumerated(EnumType.STRING)
    private UseStatCd useStatCd;

    private String rsnCd;
    private String trnsCm;
    private String crtUserId;
    private Timestamp crtDt;
    private String mdfyUserId;
    private Timestamp mdfyDt;
    private Timestamp fnlEvntDt;
    private String tid;


    @Builder
    public CnPpsRecipe(String objId, String siteId, String prodDefId, String procDefId, String procSgmtId, String procNodeId, String eqpId, String mtrlFaceCd, String topRcpId, String bottomRcpId, String evntNm, String prevEvntNm, String cstmEvntNm, String prevCstmEvntNm, UseStatCd useStatCd, String rsnCd, String trnsCm, String crtUserId, Timestamp crtDt, String mdfyUserId, Timestamp mdfyDt, Timestamp fnlEvntDt, String tid) {
        this.objId = objId;
        this.siteId = siteId;
        this.prodDefId = prodDefId;
        this.procDefId = procDefId;
        this.procSgmtId = procSgmtId;
        this.procNodeId = procNodeId;
        this.eqpId = eqpId;
        this.mtrlFaceCd = mtrlFaceCd;
        this.topRcpId = topRcpId;
        this.bottomRcpId = bottomRcpId;
        this.evntNm = evntNm;
        this.prevEvntNm = prevEvntNm;
        this.cstmEvntNm = cstmEvntNm;
        this.prevCstmEvntNm = prevCstmEvntNm;
        this.useStatCd = useStatCd;
        this.rsnCd = rsnCd;
        this.trnsCm = trnsCm;
        this.crtUserId = crtUserId;
        this.crtDt = crtDt;
        this.mdfyUserId = mdfyUserId;
        this.mdfyDt = mdfyDt;
        this.fnlEvntDt = fnlEvntDt;
        this.tid = tid;
    }
}
