package com.abs.wfs.workman.dao.domain.wipStat.model;

import com.abs.wfs.workman.util.code.UseYn;
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
@Entity(name = "WH_WIP_STAT")
public class WhWipStat {

    @Id
    @GenericGenerator(name = "WN_WIP_STAT_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "WN_WIP_STAT_SEQ_GENERATOR")
    @Column(name = "OBJ_ID")
    private String objId;

    @Column(name = "REF_OBJ_ID")
    private String refObjId;

    @Column(name = "SITE_ID")
    private String siteId;

    @Column(name = "LOT_ID")
    private String lotId;

    @Column(name = "CARR_ID")
    private String carrId;

    @Column(name = "CARR_LCTN_NM")
    private String carrLctnNm;

    // TODO Enum 화
    @Column(name = "WORK_STAT_CD")
    private String workStatCd;

    @Column(name = "DTL_WORK_STAT_CD")
    private String dtlWorkStatCd;

    @Column(name = "CSTM_EVNT_NM")
    private String cstmEvntNm;

    @Column(name = "EVNT_NM")
    private String evntNm;

    @Column(name = "FNL_EVNT_DT")
    private Timestamp fnlEvntDt;

    @Column(name = "PREV_CSTM_EVNT_NM")
    private String prevCstmEvntNm;

    @Column(name = "PREV_EVNT_NM")
    private String prevEvntNm;

    @Column(name = "RSN_CD")
    private String rsnCd;

    @Column(name = "TID")
    private String tid;

    @Column(name = "TRNS_CM")
    private String trnsCm;

    // TODO Enum 화
    @Column(name = "USE_STAT_CD")
    private String useStatCd;

    @Column(name = "CRNT_EQP_ID")
    private String crntEqpId;

    @Column(name = "CRNT_PORT_ID")
    private String crntPortId;

    @Column(name = "BATCH_ID")
    private String batchId;

    @Column(name = "RESV_EQP_ID")
    private String resvEqpId;

    @Column(name = "RESV_PORT_ID")
    private String resvPortId;

    @Enumerated(EnumType.STRING)
    @Column(name = "EQP_CHK_YN")
    private UseYn eqpChkYn;

    @Enumerated(EnumType.STRING)
    @Column(name = "RCP_CHK_YN")
    private UseYn rcpChkYn;

    @Enumerated(EnumType.STRING)
    @Column(name = "TRACK_IN_CNFM_YN")
    private UseYn trackInCnfmYn;

    @Column(name = "RESV_GRP_ID")
    private String resvGrpId;

    @Column(name = "BATCH_SEQ")
    private String batchSeq;

    @Column(name = "SELF_INSP_INFO_OBJ_ID")
    private String selfInspInfoObjId;

    @Enumerated(EnumType.STRING)
    @Column(name = "SELF_INSP_YN")
    private UseYn selfInspYn;

    @Column(name = "SELF_INSP_PANEL_CNT")
    private Integer selfInspPanelCnt;

    @Column(name = "RESV_OUT_PORT_ID")
    private String resvOutPortId;

    @Column(name = "RESV_OUT_CARR_ID")
    private String resvOutCarrId;

    @Enumerated(EnumType.STRING)
    @Column(name = "SMPL_LOT_YN")
    private UseYn smplLotYn;

    @Column(name = "SMPL_WORK_TYP")
    private String smplWorkTyp;

    @Column(name = "SMPL_QTY")
    private Integer smplQty;

    @Column(name = "MDFY_USER_ID")
    private String mdfyUserId;

    @Column(name = "MDFY_DT")
    private Timestamp mdfyDt;

    @Column(name = "CRT_USER_ID")
    private String crtUserId;

    @Column(name = "CRT_DT")
    private Timestamp crtDt;


    @Builder

    public WhWipStat(String objId, String refObjId, String siteId, String lotId, String carrId, String carrLctnNm, String workStatCd, String dtlWorkStatCd, String cstmEvntNm, String evntNm, Timestamp fnlEvntDt, String prevCstmEvntNm, String prevEvntNm, String rsnCd, String tid, String trnsCm, String useStatCd, String crntEqpId, String crntPortId, String batchId, String resvEqpId, String resvPortId, UseYn eqpChkYn, UseYn rcpChkYn, UseYn trackInCnfmYn, String resvGrpId, String batchSeq, String selfInspInfoObjId, UseYn selfInspYn, Integer selfInspPanelCnt, String resvOutPortId, String resvOutCarrId, UseYn smplLotYn, String smplWorkTyp, Integer smplQty, String mdfyUserId, Timestamp mdfyDt, String crtUserId, Timestamp crtDt) {
        this.objId = objId;
        this.refObjId = refObjId;
        this.siteId = siteId;
        this.lotId = lotId;
        this.carrId = carrId;
        this.carrLctnNm = carrLctnNm;
        this.workStatCd = workStatCd;
        this.dtlWorkStatCd = dtlWorkStatCd;
        this.cstmEvntNm = cstmEvntNm;
        this.evntNm = evntNm;
        this.fnlEvntDt = fnlEvntDt;
        this.prevCstmEvntNm = prevCstmEvntNm;
        this.prevEvntNm = prevEvntNm;
        this.rsnCd = rsnCd;
        this.tid = tid;
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
        this.mdfyUserId = mdfyUserId;
        this.mdfyDt = mdfyDt;
        this.crtUserId = crtUserId;
        this.crtDt = crtDt;
    }
}
