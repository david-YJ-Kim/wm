package com.abs.wfs.workman.dao.domain.wipStat.model;

import com.abs.wfs.workman.util.code.UseStatCd;
import com.abs.wfs.workman.util.code.UseYn;
import com.abs.wfs.workman.util.code.WorkStatCd;
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
@Entity(name = "WN_WIP_STAT")
@DynamicInsert
@DynamicUpdate
public class WnWipStat {

    @Id
    @GenericGenerator(name = "WN_WIP_STAT_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "WN_WIP_STAT_SEQ_GENERATOR")
    @Column(name = "OBJ_ID")
    private String objId;


    private String tid;
    private String siteId;
    private String lotId;
    private String carrId;
    private String carrLctnNm;


    @Enumerated(EnumType.STRING)
    private WorkStatCd workStatCd;

    private String dtlWorkStatCd;
    private String mdfyUserId;
    private Timestamp mdfyDt;
    private String crtUserId;
    private Timestamp crtDt;
    private String cstmEvntNm;
    private String evntNm;
    private Timestamp fnlEvntDt;
    private String prevCstmEvntNm;
    private String prevEvntNm;
    private String rsnCd;
    private String trnsCm;

    @Enumerated(EnumType.STRING)
    private UseStatCd useStatCd;

    private String crntEqpId;
    private String crntPortId;
    private String batchId;
    private String resvEqpId;
    private String resvPortId;

    @Enumerated(EnumType.STRING)
    private UseYn eqpChkYn;

    @Enumerated(EnumType.STRING)
    private UseYn rcpChkYn;

    @Enumerated(EnumType.STRING)
    private UseYn trackInCnfmYn;

    private String resvGrpId;
    private String batchSeq;
    private String selfInspInfoObjId;

    @Enumerated(EnumType.STRING)
    private UseYn selfInspYn;

    private Long selfInspPanelCnt;
    private String resvOutPortId;
    private String resvOutCarrId;


    @Enumerated(EnumType.STRING)
    private UseYn smplLotYn;


    private String smplWorkTyp;
    private Long smplQty;
    private String moveStatCd;


    @Enumerated(EnumType.STRING)
    private UseYn manlLdngYn;


    @Builder
    public WnWipStat(String objId, String tid, String siteId, String lotId, String carrId, String carrLctnNm, WorkStatCd workStatCd, String dtlWorkStatCd, String mdfyUserId, Timestamp mdfyDt, String crtUserId, Timestamp crtDt, String cstmEvntNm, String evntNm, Timestamp fnlEvntDt, String prevCstmEvntNm, String prevEvntNm, String rsnCd, String trnsCm, UseStatCd useStatCd, String crntEqpId, String crntPortId, String batchId, String resvEqpId, String resvPortId, UseYn eqpChkYn, UseYn rcpChkYn, UseYn trackInCnfmYn, String resvGrpId, String batchSeq, String selfInspInfoObjId, UseYn selfInspYn, Long selfInspPanelCnt, String resvOutPortId, String resvOutCarrId, UseYn smplLotYn, String smplWorkTyp, Long smplQty, String moveStatCd, UseYn manlLdngYn) {
        this.objId = objId;
        this.tid = tid;
        this.siteId = siteId;
        this.lotId = lotId;
        this.carrId = carrId;
        this.carrLctnNm = carrLctnNm;
        this.workStatCd = workStatCd;
        this.dtlWorkStatCd = dtlWorkStatCd;
        this.mdfyUserId = mdfyUserId;
        this.mdfyDt = mdfyDt;
        this.crtUserId = crtUserId;
        this.crtDt = crtDt;
        this.cstmEvntNm = cstmEvntNm;
        this.evntNm = evntNm;
        this.fnlEvntDt = fnlEvntDt;
        this.prevCstmEvntNm = prevCstmEvntNm;
        this.prevEvntNm = prevEvntNm;
        this.rsnCd = rsnCd;
        this.trnsCm = trnsCm;
        this.useStatCd = useStatCd;
        this.crntEqpId = crntEqpId;
        this.crntPortId = crntPortId;
        this.batchId = batchId;
        this.resvEqpId = resvEqpId;
        this.resvPortId = resvPortId;
        this.eqpChkYn = eqpChkYn;
        this.rcpChkYn = rcpChkYn;
        this.trackInCnfmYn = trackInCnfmYn;
        this.resvGrpId = resvGrpId;
        this.batchSeq = batchSeq;
        this.selfInspInfoObjId = selfInspInfoObjId;
        this.selfInspYn = selfInspYn;
        this.selfInspPanelCnt = selfInspPanelCnt;
        this.resvOutPortId = resvOutPortId;
        this.resvOutCarrId = resvOutCarrId;
        this.smplLotYn = smplLotYn;
        this.smplWorkTyp = smplWorkTyp;
        this.smplQty = smplQty;
        this.moveStatCd = moveStatCd;
        this.manlLdngYn = manlLdngYn;
    }
}
