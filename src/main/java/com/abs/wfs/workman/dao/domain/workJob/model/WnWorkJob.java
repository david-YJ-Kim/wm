package com.abs.wfs.workman.dao.domain.workJob.model;

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
@Entity(name = "WN_WORK_JOB")
@DynamicInsert
@DynamicUpdate
@Cacheable(false)
public class WnWorkJob {

    @Id
    @GenericGenerator(name = "WN_WORK_JOB_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "WN_WORK_JOB_SEQ_GENERATOR")
    private String objId;
    private String siteId;
    private String workId;
    private String jobSeqId;
    private String lotId;
    private String batchId;
    private String crntCarrId;
    private String inPortId;
    private String inCarrId;
    private String inCarrTyp;
    private String outPortId;
    private String outCarrId;
    private String outCarrTyp;
    private String prodDefId;
    private String procDefId;
    private String procSgmtId;
    private Long jobQty;
    private String rcpDefId;
    private String rcpReadyYn;
    private String jobStatCd;
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
    private String workFaceCd;


    @Builder
    public WnWorkJob(String objId, String siteId, String workId, String jobSeqId, String lotId, String batchId, String crntCarrId, String inPortId, String inCarrId, String inCarrTyp, String outPortId, String outCarrId, String outCarrTyp, String prodDefId, String procDefId, String procSgmtId, Long jobQty, String rcpDefId, String rcpReadyYn, String jobStatCd, String evntNm, String prevEvntNm, String cstmEvntNm, String prevCstmEvntNm, UseStatCd useStatCd, String rsnCd, String trnsCm, String crtUserId, Timestamp crtDt, String mdfyUserId, Timestamp mdfyDt, Timestamp fnlEvntDt, String tid, String workFaceCd) {
        this.objId = objId;
        this.siteId = siteId;
        this.workId = workId;
        this.jobSeqId = jobSeqId;
        this.lotId = lotId;
        this.batchId = batchId;
        this.crntCarrId = crntCarrId;
        this.inPortId = inPortId;
        this.inCarrId = inCarrId;
        this.inCarrTyp = inCarrTyp;
        this.outPortId = outPortId;
        this.outCarrId = outCarrId;
        this.outCarrTyp = outCarrTyp;
        this.prodDefId = prodDefId;
        this.procDefId = procDefId;
        this.procSgmtId = procSgmtId;
        this.jobQty = jobQty;
        this.rcpDefId = rcpDefId;
        this.rcpReadyYn = rcpReadyYn;
        this.jobStatCd = jobStatCd;
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
        this.workFaceCd = workFaceCd;
    }
}
