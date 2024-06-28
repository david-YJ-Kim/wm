package com.abs.wfs.workman.dao.domain.workJobSlotInfo.model;

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
@Entity(name = "WN_WORK_JOB_SLOT_INFO")
@DynamicInsert
@DynamicUpdate
@Cacheable(false)
public class WnWorkJobSlotInfo {

    @Id
    @GenericGenerator(name = "WN_WORK_JOB_SLOT_INFO_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "WN_WORK_JOB_SLOT_INFO_SEQ_GENERATOR")
    private String objId;
    private String siteId;
    private String workId;
    private String jobSeqId;
    private String slotNo;
    private String prodMtrlId;
    private Timestamp prodMtrlStrtTm;
    private Timestamp prodMtrlEndTm;
    private String jobStatCd;
    private String selfInspYn;
    private String mtrlFaceCd;
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
    private String lotId;



    @Builder
    public WnWorkJobSlotInfo(String objId, String siteId, String workId, String jobSeqId, String slotNo, String prodMtrlId, Timestamp prodMtrlStrtTm, Timestamp prodMtrlEndTm, String jobStatCd, String selfInspYn, String mtrlFaceCd, String evntNm, String prevEvntNm, String cstmEvntNm, String prevCstmEvntNm, UseStatCd useStatCd, String rsnCd, String trnsCm, String crtUserId, Timestamp crtDt, String mdfyUserId, Timestamp mdfyDt, Timestamp fnlEvntDt, String tid, String lotId) {
        this.objId = objId;
        this.siteId = siteId;
        this.workId = workId;
        this.jobSeqId = jobSeqId;
        this.slotNo = slotNo;
        this.prodMtrlId = prodMtrlId;
        this.prodMtrlStrtTm = prodMtrlStrtTm;
        this.prodMtrlEndTm = prodMtrlEndTm;
        this.jobStatCd = jobStatCd;
        this.selfInspYn = selfInspYn;
        this.mtrlFaceCd = mtrlFaceCd;
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
        this.lotId = lotId;
    }
}
