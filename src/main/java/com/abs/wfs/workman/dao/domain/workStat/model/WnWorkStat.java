package com.abs.wfs.workman.dao.domain.workStat.model;

import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;


@NoArgsConstructor
@Getter
@Setter
@Entity(name = "WN_WORK_STAT")
@DynamicInsert
@DynamicUpdate
public class WnWorkStat {

    @Id
    @GenericGenerator(name = "WN_WORK_STAT_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "WN_WORK_STAT_SEQ_GENERATOR")
    @Column(name = "OBJ_ID")
    private String objId;
    private String siteId;
    private String workId;
    private String eqpId;
    private String workStatCd;
    private Timestamp workStartTm;
    private Long workQty;
    private String fnlTaskNm;
    private String batchYn;
    private String inlineYn;
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
    private String eqpInlineId;
    private String inlineStatCd;
    private String dspWorkId;


    @Builder
    public WnWorkStat(String objId, String siteId, String workId, String eqpId, String workStatCd, Timestamp workStartTm, Long workQty, String fnlTaskNm, String batchYn, String inlineYn, String evntNm, String prevEvntNm, String cstmEvntNm, String prevCstmEvntNm, UseStatCd useStatCd, String rsnCd, String trnsCm, String crtUserId, Timestamp crtDt, String mdfyUserId, Timestamp mdfyDt, Timestamp fnlEvntDt, String tid, String eqpInlineId, String inlineStatCd, String dspWorkId) {
        this.objId = objId;
        this.siteId = siteId;
        this.workId = workId;
        this.eqpId = eqpId;
        this.workStatCd = workStatCd;
        this.workStartTm = workStartTm;
        this.workQty = workQty;
        this.fnlTaskNm = fnlTaskNm;
        this.batchYn = batchYn;
        this.inlineYn = inlineYn;
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
        this.eqpInlineId = eqpInlineId;
        this.inlineStatCd = inlineStatCd;
        this.dspWorkId = dspWorkId;
    }
}
