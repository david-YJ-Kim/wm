package com.abs.wfs.workman.dao.domain.transferJob.model;

import com.abs.wfs.workman.util.code.MoveStatCd;
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
@Entity(name = "WN_TRANSFER_JOB")
@DynamicInsert
@DynamicUpdate
@Cacheable(false)
public class WnTransferJob {

    @Id
    @GenericGenerator(name = "WN_TRANSFER_JOB_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "WN_TRANSFER_JOB_SEQ_GENERATOR")
    private String objId;
    private String siteId;
    private String jobId;
    private String carrId;
    private String crntEqpId;
    private String crntPortId;
    private String srcEqpId;
    private String srcPortId;
    private String destEqpId;
    private String destPortId;

    @Enumerated(EnumType.STRING)
    private MoveStatCd moveStatCd;

    private String prirtNo;
    private String fnlEvntNm;
    private Timestamp fnlEvntDt;
    private String mdfyUserId;
    private Timestamp mdfyDt;
    private String crtUserId;
    private Timestamp crtDt;
    private String cstmEvntNm;
    private String evntNm;
    private String prevCstmEvntNm;
    private String prevEvntNm;
    private String rsnCd;
    private String tid;
    private String trnsCm;


    @Enumerated(EnumType.STRING)
    private UseStatCd useStatCd;


    @Builder

    public WnTransferJob(String objId, String siteId, String jobId, String carrId, String crntEqpId, String crntPortId, String srcEqpId, String srcPortId, String destEqpId, String destPortId, MoveStatCd moveStatCd, String prirtNo, String fnlEvntNm, Timestamp fnlEvntDt, String mdfyUserId, Timestamp mdfyDt, String crtUserId, Timestamp crtDt, String cstmEvntNm, String evntNm, String prevCstmEvntNm, String prevEvntNm, String rsnCd, String tid, String trnsCm, UseStatCd useStatCd) {
        this.objId = objId;
        this.siteId = siteId;
        this.jobId = jobId;
        this.carrId = carrId;
        this.crntEqpId = crntEqpId;
        this.crntPortId = crntPortId;
        this.srcEqpId = srcEqpId;
        this.srcPortId = srcPortId;
        this.destEqpId = destEqpId;
        this.destPortId = destPortId;
        this.moveStatCd = moveStatCd;
        this.prirtNo = prirtNo;
        this.fnlEvntNm = fnlEvntNm;
        this.fnlEvntDt = fnlEvntDt;
        this.mdfyUserId = mdfyUserId;
        this.mdfyDt = mdfyDt;
        this.crtUserId = crtUserId;
        this.crtDt = crtDt;
        this.cstmEvntNm = cstmEvntNm;
        this.evntNm = evntNm;
        this.prevCstmEvntNm = prevCstmEvntNm;
        this.prevEvntNm = prevEvntNm;
        this.rsnCd = rsnCd;
        this.tid = tid;
        this.trnsCm = trnsCm;
        this.useStatCd = useStatCd;
    }
}
