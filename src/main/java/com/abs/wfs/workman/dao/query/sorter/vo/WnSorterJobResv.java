package com.abs.wfs.workman.dao.query.sorter.vo;

import lombok.Data;

@Data
public class WnSorterJobResv {
    private String objId;
    private String siteId;
    private String jobId;
    private String eqpId;
    private String srcLotId;
    private String tgtLotId;
    private String srcCarrId;
    private String srcSlotNo;
    private String srcProdMtrlId;
    private String tgtCarrId;
    private String tgtSlotNo;
    private String tgtProdMtrlId;
    private String prirtNo;
    private String sorterJobTyp;
    private String dtlSorterJobTyp;
    private String jobStatCd;
    private String procSgmtId;
    private String procSgmtSeq;
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

    private String pSiteId;
    private String pJobId;
    private String pJobStatCd;
    private String pUseStatCd;
    private String pEqpId;
    private String pPrirtNo;
}