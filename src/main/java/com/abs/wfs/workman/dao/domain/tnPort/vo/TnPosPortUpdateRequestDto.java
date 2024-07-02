package com.abs.wfs.workman.dao.domain.tnPort.vo;

import com.abs.wfs.workman.dao.domain.tnPort.model.TnPosPortMapper;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class TnPosPortUpdateRequestDto {

    private String siteId;
    private String eqpId;
    private String portId;
    private String acesModeCd;
    private String statCd;
    private String prevStatCd;
    private String trsfStatCd;
    private String carrId;
    private String evntNm;
    private String prevEvntNm;
    private String cstmEvntNm;
    private String prevCstmEvntNm;
    private String useStatCd;
    private String rsnCd;
    private String trnsCm;
    private String mdfyUserId;
    private Timestamp mdfyDt;
    private Timestamp fnlEvntDt;
    private String tid;
    private String ctrlModeCd;
    private String efemStatCd;
    private String efemCtrlModeCd;

    @Builder
    public TnPosPortUpdateRequestDto(String siteId, String eqpId, String portId, String acesModeCd, String statCd, String prevStatCd, String trsfStatCd, String carrId, String evntNm, String prevEvntNm, String cstmEvntNm, String prevCstmEvntNm, String useStatCd, String rsnCd, String trnsCm, String mdfyUserId, Timestamp mdfyDt, Timestamp fnlEvntDt, String tid, String ctrlModeCd, String efemStatCd, String efemCtrlModeCd) {
        this.siteId = siteId;
        this.eqpId = eqpId;
        this.portId = portId;
        this.acesModeCd = acesModeCd;
        this.statCd = statCd;
        this.prevStatCd = prevStatCd;
        this.trsfStatCd = trsfStatCd;
        this.carrId = carrId;
        this.evntNm = evntNm;
        this.prevEvntNm = prevEvntNm;
        this.cstmEvntNm = cstmEvntNm;
        this.prevCstmEvntNm = prevCstmEvntNm;
        this.useStatCd = useStatCd;
        this.rsnCd = rsnCd;
        this.trnsCm = trnsCm;
        this.mdfyUserId = mdfyUserId;
        this.mdfyDt = mdfyDt;
        this.fnlEvntDt = fnlEvntDt;
        this.tid = tid;
        this.ctrlModeCd = ctrlModeCd;
        this.efemStatCd = efemStatCd;
        this.efemCtrlModeCd = efemCtrlModeCd;
    }

    public TnPosPortMapper toEntity(){
        return TnPosPortMapper.builder()
                .siteId(siteId)
                .eqpId(eqpId)
                .portId(portId)
                .acesModeCd(acesModeCd)
                .statCd(statCd)
                .prevStatCd(prevStatCd)
                .trsfStatCd(trsfStatCd)
                .carrId(carrId)
                .evntNm(evntNm)
                .prevEvntNm(prevEvntNm)
                .cstmEvntNm(cstmEvntNm)
                .prevCstmEvntNm(prevCstmEvntNm)
                .useStatCd(UseStatCd.valueOf(useStatCd))
                .rsnCd(rsnCd)
                .trnsCm(trnsCm)
                .mdfyUserId(mdfyUserId)
                .mdfyDt(mdfyDt)
                .fnlEvntDt(fnlEvntDt)
                .tid(tid)
                .ctrlModeCd(ctrlModeCd)
                .efemStatCd(efemStatCd)
                .efemCtrlModeCd(efemCtrlModeCd)
                .build();
    }
}
