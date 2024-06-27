package com.abs.wfs.workman.dao.query.wip.vo;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WnWipStat {

    // Table Column
    private String objId;
    private String siteId;
    private String lotId;
    private String carrId;
    private String carrLctnNm;
    private String workStatCd;
    private String dtlWorkStatCd;
    private String mdfyUserId;
    private String mdfyDt;
    private String crtUserId;
    private String crtDt;
    private String cstmEvntNm;
    private String evntNm;
    private String fnlEvntDt;
    private String prevCstmEvntNm;
    private String prevEvntNm;
    private String rsnCd;
    private String tid;
    private String trnsCm;
    private String useStatCd;
    private String crntEqpId;
    private String crntPortId;
    private String batchId;
    private String resvEqpId;
    private String resvPortId;
    private String resvOutPortId;
    private String resvOutCarrId;
    private String eqpChkYn;
    private String rcpChkYn;
    private String trackInCnfmYn;
    private String resvGrpId;
    private String batchSeq;
    private String selfInspYn;
    private String selfInspInfoObjId;
    private Integer selfInspPanelCnt;
    private String smplLotYn;
    private String smplWorkTyp;
    private Integer smplQty;

    // Where Condition
    private String pObjId;
    private String pSiteId;
    private String pLotId;
    private String pCarrId;
    private String pWorkStatCd;
    private String pDtlWorkStatCd;
    private String pUseStatCd;


    //  Where Condition for selectWnWipStatWithReserveEqpPort
    private String pReserveEqpId;
    private String pReservePortId;


    @Builder
    public WnWipStat(String objId, String siteId, String lotId, String carrId, String carrLctnNm, String workStatCd, String dtlWorkStatCd, String mdfyUserId, String mdfyDt, String crtUserId, String crtDt, String cstmEvntNm, String evntNm, String fnlEvntDt, String prevCstmEvntNm, String prevEvntNm, String rsnCd, String tid, String trnsCm, String useStatCd, String crntEqpId, String crntPortId, String batchId, String resvEqpId, String resvPortId, String resvOutPortId, String resvOutCarrId, String eqpChkYn, String rcpChkYn, String trackInCnfmYn, String resvGrpId, String batchSeq, String selfInspYn, String selfInspInfoObjId, Integer selfInspPanelCnt, String smplLotYn, String smplWorkTyp, Integer smplQty, String pObjId, String pSiteId, String pLotId, String pCarrId, String pWorkStatCd, String pDtlWorkStatCd, String pUseStatCd, String pReserveEqpId, String pReservePortId) {
        this.objId = objId;
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
        this.tid = tid;
        this.trnsCm = trnsCm;
        this.useStatCd = useStatCd;
        this.crntEqpId = crntEqpId;
        this.crntPortId = crntPortId;
        this.batchId = batchId;
        this.resvEqpId = resvEqpId;
        this.resvPortId = resvPortId;
        this.resvOutPortId = resvOutPortId;
        this.resvOutCarrId = resvOutCarrId;
        this.eqpChkYn = eqpChkYn;
        this.rcpChkYn = rcpChkYn;
        this.trackInCnfmYn = trackInCnfmYn;
        this.resvGrpId = resvGrpId;
        this.batchSeq = batchSeq;
        this.selfInspYn = selfInspYn;
        this.selfInspInfoObjId = selfInspInfoObjId;
        this.selfInspPanelCnt = selfInspPanelCnt;
        this.smplLotYn = smplLotYn;
        this.smplWorkTyp = smplWorkTyp;
        this.smplQty = smplQty;
        this.pObjId = pObjId;
        this.pSiteId = pSiteId;
        this.pLotId = pLotId;
        this.pCarrId = pCarrId;
        this.pWorkStatCd = pWorkStatCd;
        this.pDtlWorkStatCd = pDtlWorkStatCd;
        this.pUseStatCd = pUseStatCd;
        this.pReserveEqpId = pReserveEqpId;
        this.pReservePortId = pReservePortId;
    }
}

