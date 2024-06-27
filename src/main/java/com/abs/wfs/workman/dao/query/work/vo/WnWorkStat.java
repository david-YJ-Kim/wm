package com.abs.wfs.workman.dao.query.work.vo;

import lombok.Data;

@Data
public class WnWorkStat {
    private String objId;
    private String siteId;
    private String workId;
    private String eqpId;
    private String workStatCd;
    private String workStartTm;
    private String workQty;
    private String fnlTaskNm;
    private String batchYn;
    private String inlineYn;
    private String evntNm;
    private String prevEvntNm;
    private String cstmEvntNm;
    private String prevCstmEvntNm;
    private String useStatCd;
    private String rsnCd;
    private String trnsCm;
    private String crtUserId;
    private String crtDt;
    private String mdfyUserId;
    private String mdfyDt;
    private String fnlEvntDt;
    private String tid;
    private String eqpInlineId;
    private String inlineStatCd;
    private String dspWorkId;

    private String pObjId;
    private String pSiteId;
    private String pWorkId;
    private String pEqpId;
    private String pWorkStatCd;
    private String pUseStatCd;

}
