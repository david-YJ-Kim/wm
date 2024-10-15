package com.abs.wfs.workman.dao.domain.issueReport.vo;

import com.abs.wfs.workman.dao.domain.issueReport.model.WnIssueReport;
import com.abs.wfs.workman.util.code.UseStatCd;
import com.abs.wfs.workman.util.code.UseYn;
import com.abs.wfs.workman.util.code.WorkStatCd;
import lombok.Builder;

import java.sql.Timestamp;

public class WnIssueReportDto {

    private String siteId;
    private String trkId;
    private String cid;
    private String payload;
    private WorkStatCd workStatCd;
    String scenarioTyp;
    private UseYn fixedYn;
    private UseYn sysFixedYn;
    private Timestamp fixedDt;
    private String lotId;
    private String carrId;
    private String eqpId;
    private String portId;
    private String errCd;
    private String errCm;
    private String evntNm;
    private String prevEvntNm;
    private String cstmEvntNm;
    private String prevCstmEvntNm;
    private UseStatCd useStatCd;
    private String rsnCd;
    private String trnsCm;
    private String crtUserId;
    private Timestamp crtDt;
    private String mdfyUserId;
    private Timestamp mdfyDt; // NOT NULL ENABLE
    private Timestamp fnlEvntDt;
    private String tid;


    @Builder
    public WnIssueReportDto(String siteId, String trkId, String cid, String payload, WorkStatCd workStatCd, String scenarioTyp, UseYn fixedYn, UseYn sysFixedYn, Timestamp fixedDt, String lotId, String carrId, String eqpId, String portId, String errCd, String errCm, String evntNm, String prevEvntNm, String cstmEvntNm, String prevCstmEvntNm, UseStatCd useStatCd, String rsnCd, String trnsCm, String crtUserId, Timestamp crtDt, String mdfyUserId, Timestamp mdfyDt, Timestamp fnlEvntDt, String tid) {
        this.siteId = siteId;
        this.trkId = trkId;
        this.cid = cid;
        this.payload = payload;
        this.workStatCd = workStatCd;
        this.scenarioTyp = scenarioTyp;
        this.fixedYn = fixedYn;
        this.sysFixedYn = sysFixedYn;
        this.fixedDt = fixedDt;
        this.lotId = lotId;
        this.carrId = carrId;
        this.eqpId = eqpId;
        this.portId = portId;
        this.errCd = errCd;
        this.errCm = errCm;
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
    }


    public WnIssueReport toEntity(){
        return WnIssueReport.builder()
                .siteId(siteId)
                .trkId(trkId)
                .cid(cid)
                .payload(payload)
                .workStatCd(workStatCd)
                .scenarioTyp(scenarioTyp)
                .fixedYn(fixedYn)
                .sysFixedYn(sysFixedYn)
                .fixedDt(fixedDt)
                .lotId(lotId)
                .carrId(carrId)
                .eqpId(eqpId)
                .portId(portId)
                .errCd(errCd)
                .errCm(errCm)
                .evntNm(evntNm)
                .prevEvntNm(prevEvntNm)
                .cstmEvntNm(cstmEvntNm)
                .prevCstmEvntNm(prevCstmEvntNm)
                .useStatCd(useStatCd)
                .rsnCd(rsnCd)
                .trnsCm(trnsCm)
                .crtUserId(crtUserId)
                .crtDt(crtDt)
                .mdfyUserId(mdfyUserId)
                .mdfyDt(mdfyDt)
                .fnlEvntDt(fnlEvntDt)
                .tid(tid)
                .build();
    }
}
