package com.abs.wfs.workman.dao.domain.posEqpGrpRel.model;


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
@Entity(name = "CN_POS_EQP_GRP_REL")
public class CnPosEqpGrpRel {

    @Id
    @GenericGenerator(name = "CN_POS_EQP_GRP_REL_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "CN_POS_EQP_GRP_REL_SEQ_GENERATOR")
    @Column(name = "OBJ_ID")
    private String objId;
    private String siteId;
    private String eqpGrpId;
    private String eqpId;
    private String eqpNm;
    private String evntNm;
    private String prevEvntNm;
    private String cstmEvntNm;
    private String prevCstmEvntNm;

    @Enumerated(EnumType.STRING)
    @Column(name="USE_STAT_CD")
    private UseStatCd useStatCd;

    private String rsnCd;
    private String trnsCm;

    private String crtUserId;
    private Timestamp crtDt;
    private String mdfyUserId;
    private Timestamp mdfyDt; // NOT NULL ENABLE
    private Timestamp fnlEvntDt;
    private String tid;
    private String addtnInfo;


    @Builder
    public CnPosEqpGrpRel(String objId, String siteId, String eqpGrpId, String eqpId, String eqpNm, String evntNm, String prevEvntNm, String cstmEvntNm, String prevCstmEvntNm, UseStatCd useStatCd, String rsnCd, String trnsCm, String crtUserId, Timestamp crtDt, String mdfyUserId, Timestamp mdfyDt, Timestamp fnlEvntDt, String tid, String addtnInfo) {
        this.objId = objId;
        this.siteId = siteId;
        this.eqpGrpId = eqpGrpId;
        this.eqpId = eqpId;
        this.eqpNm = eqpNm;
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
        this.addtnInfo = addtnInfo;
    }
}
