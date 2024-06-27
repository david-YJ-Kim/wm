package com.abs.wfs.workman.dao.domain.wipStat.vo;

import com.abs.wfs.workman.dao.domain.wipStat.model.WnWipStat;
import com.abs.wfs.workman.util.code.UseStatCd;
import com.abs.wfs.workman.util.code.UseYn;
import com.abs.wfs.workman.util.code.WorkStatCd;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class WnWipStatSaveRequestVo {

    private String siteId;
    private String lotId;
    private String carrId;
    private String carrLctnNm;
    private String workStatCd;
    private String dtlWorkStatCd;
    private String cstmEvntNm;
    private String evntNm;
    private Timestamp fnlEvntDt;
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
    private String eqpChkYn;
    private String rcpChkYn;
    private String trackInCnfmYn;
    private String resvGrpId;
    private String batchSeq;
    private String selfInspInfoObjId;
    private String selfInspYn;
    private Integer selfInspPanelCnt;
    private String resvOutPortId;
    private String resvOutCarrId;
    private String smplLotYn;
    private String smplWorkTyp;
    private Integer smplQty;
    private String mdfyUserId;
    private Timestamp mdfyDt;
    private String crtUserId;
    private Timestamp crtDt;


    @Builder
    public WnWipStatSaveRequestVo(String siteId, String lotId, String carrId, String carrLctnNm, String workStatCd, String dtlWorkStatCd, String cstmEvntNm, String evntNm, Timestamp fnlEvntDt, String prevCstmEvntNm, String prevEvntNm, String rsnCd, String tid, String trnsCm, String useStatCd, String crntEqpId, String crntPortId, String batchId, String resvEqpId, String resvPortId, String eqpChkYn, String rcpChkYn, String trackInCnfmYn, String resvGrpId, String batchSeq, String selfInspInfoObjId, String selfInspYn, Integer selfInspPanelCnt, String resvOutPortId, String resvOutCarrId, String smplLotYn, String smplWorkTyp, Integer smplQty, String mdfyUserId, Timestamp mdfyDt, String crtUserId, Timestamp crtDt) {
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

    public WnWipStat toEntity(){
        return WnWipStat.builder()
                .siteId(siteId)
                .lotId(lotId)
                .carrId(carrId)
                .carrLctnNm(carrLctnNm)
                .workStatCd(WorkStatCd.valueOf(workStatCd))
                .dtlWorkStatCd(dtlWorkStatCd)
                .cstmEvntNm(cstmEvntNm)
                .evntNm(evntNm)
                .fnlEvntDt(fnlEvntDt)
                .prevCstmEvntNm(prevCstmEvntNm)
                .prevEvntNm(prevEvntNm)
                .rsnCd(rsnCd)
                .tid(tid)
                .trnsCm(trnsCm)
                .useStatCd(UseStatCd.valueOf(useStatCd))
                .crntEqpId(crntEqpId)
                .crntPortId(crntPortId)
                .batchId(batchId)
                .resvEqpId(resvEqpId)
                .resvPortId(resvPortId)
                .eqpChkYn(UseYn.valueOf(eqpChkYn))
                .rcpChkYn(UseYn.valueOf(rcpChkYn))
                .trackInCnfmYn(UseYn.valueOf(trackInCnfmYn))
                .resvGrpId(resvGrpId)
                .batchSeq(batchSeq)
                .selfInspInfoObjId(selfInspInfoObjId)
                .selfInspYn(UseYn.valueOf(selfInspYn))
                .selfInspPanelCnt(selfInspPanelCnt)
                .resvOutPortId(resvOutPortId)
                .resvOutCarrId(resvOutCarrId)
                .smplLotYn(UseYn.valueOf(smplLotYn))
                .smplWorkTyp(smplWorkTyp)
                .smplQty(smplQty)
                .mdfyUserId(mdfyUserId)
                .mdfyDt(mdfyDt)
                .crtUserId(crtUserId)
                .crtDt(crtDt)
                .build();
    }

    @Override
    public String toString() {
        return "WnWipStatSaveRequestVo{" +
                "siteId='" + siteId + '\'' +
                ", lotId='" + lotId + '\'' +
                ", carrId='" + carrId + '\'' +
                ", carrLctnNm='" + carrLctnNm + '\'' +
                ", workStatCd='" + workStatCd + '\'' +
                ", dtlWorkStatCd='" + dtlWorkStatCd + '\'' +
                ", cstmEvntNm='" + cstmEvntNm + '\'' +
                ", evntNm='" + evntNm + '\'' +
                ", fnlEvntDt=" + fnlEvntDt +
                ", prevCstmEvntNm='" + prevCstmEvntNm + '\'' +
                ", prevEvntNm='" + prevEvntNm + '\'' +
                ", rsnCd='" + rsnCd + '\'' +
                ", tid='" + tid + '\'' +
                ", trnsCm='" + trnsCm + '\'' +
                ", useStatCd='" + useStatCd + '\'' +
                ", crntEqpId='" + crntEqpId + '\'' +
                ", crntPortId='" + crntPortId + '\'' +
                ", batchId='" + batchId + '\'' +
                ", resvEqpId='" + resvEqpId + '\'' +
                ", resvPortId='" + resvPortId + '\'' +
                ", eqpChkYn=" + eqpChkYn +
                ", rcpChkYn=" + rcpChkYn +
                ", trackInCnfmYn=" + trackInCnfmYn +
                ", resvGrpId='" + resvGrpId + '\'' +
                ", batchSeq='" + batchSeq + '\'' +
                ", selfInspInfoObjId='" + selfInspInfoObjId + '\'' +
                ", selfInspYn=" + selfInspYn +
                ", selfInspPanelCnt=" + selfInspPanelCnt +
                ", resvOutPortId='" + resvOutPortId + '\'' +
                ", resvOutCarrId='" + resvOutCarrId + '\'' +
                ", smplLotYn=" + smplLotYn +
                ", smplWorkTyp='" + smplWorkTyp + '\'' +
                ", smplQty=" + smplQty +
                ", mdfyUserId='" + mdfyUserId + '\'' +
                ", mdfyDt=" + mdfyDt +
                ", crtUserId='" + crtUserId + '\'' +
                ", crtDt=" + crtDt +
                '}';
    }
}
