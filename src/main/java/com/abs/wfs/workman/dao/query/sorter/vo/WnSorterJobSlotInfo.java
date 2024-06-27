package com.abs.wfs.workman.dao.query.sorter.vo;

import lombok.Data;

@Data
public class WnSorterJobSlotInfo {
    private String objId;
    private String siteId;
    private String jobId;
    private String eqpId;
    private String srcLotId;
    private String srcSlotNo;
    private String tgtLotId;
    private String tgtSlotNo;
    private String prodMtrlId;
    private String scanSlotNo;
    private String scanProdMtrlId;
    private String prodMtrlStrtTm;
    private String prodMtrlEndTm;
    private String jobStatCd;
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

    private String pObjId;
    private String pSiteId;
    private String pJobId;
    private String pEqpId;
    private String pSrcLotId;
    private String pProdMtrlId;

}
