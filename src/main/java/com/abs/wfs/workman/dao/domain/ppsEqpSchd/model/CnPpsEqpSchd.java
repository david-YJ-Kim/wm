package com.abs.wfs.workman.dao.domain.ppsEqpSchd.model;

import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CN_PPS_EQP_SCHD")
public class CnPpsEqpSchd {
    @Id
    @GenericGenerator(name = "CN_PPS_EQP_SCHD_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "CN_PPS_EQP_SCHD_SEQ_GENERATOR")
    @Column(name = "OBJ_ID")
    private String objId;
    private String siteId;
    private String eqpId;
    private String taskId;
    private String taskNm;
    private String etcCmmt;
    private String fromDt;
    private String toDt;
    private String engrId;
    private String lotId;
    private String prodDefId;
    private String procDefId;
    private String procSgmtId;
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
    private String crtDt;
    private String mdfyUserId;
    private String mdfyDt;
    private String fnlEvntDt;
    private String tid;
    private String autoRelease;
    private String autoSkipYn;
}
