package com.abs.wfs.workman.dao.query.transfer.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@Data
public class WnTransferJob {
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
    private String moveStatCd;
    private String prirtNo;
    private String fnlEvntNm;
    private String fnlEvntDt;
    private String mdfyUserId;
    private String mdfyDt;
    private String crtUserId;
    private String crtDt;
    private String cstmEvntNm;
    private String evntNm;
    private String prevCstmEvntNm;
    private String prevEvntNm;
    private String rsnCd;
    private String tid;
    private String trnsCm;
    private String useStatCd;

    private String pSiteId;
    private String pJobId;
    private String pCarrId;
    private String pMoveStatCd;
    private String pUseStatCd;

    @Builder
    public WnTransferJob(String objId, String siteId, String jobId, String carrId, String crntEqpId, String crntPortId, String srcEqpId, String srcPortId, String destEqpId, String destPortId, String moveStatCd, String prirtNo, String fnlEvntNm, String fnlEvntDt, String mdfyUserId, String mdfyDt, String crtUserId, String crtDt, String cstmEvntNm, String evntNm, String prevCstmEvntNm, String prevEvntNm, String rsnCd, String tid, String trnsCm, String useStatCd, String pSiteId, String pJobId, String pCarrId, String pMoveStatCd, String pUseStatCd) {
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
        this.pSiteId = pSiteId;
        this.pJobId = pJobId;
        this.pCarrId = pCarrId;
        this.pMoveStatCd = pMoveStatCd;
        this.pUseStatCd = pUseStatCd;
    }
}
