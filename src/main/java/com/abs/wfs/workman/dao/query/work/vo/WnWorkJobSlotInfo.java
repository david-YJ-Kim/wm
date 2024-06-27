package com.abs.wfs.workman.dao.query.work.vo;

import lombok.Data;

@Data
public class WnWorkJobSlotInfo {
    private String objId;
    private String siteId;
    private String workId;
    private String jobSeqId;
    private String slotNo;
    private String prodMtrlId;
    private String prodMtrlStrtTm;
    private String prodMtrlEndTm;
    private String jobStatCd;
    private String selfInspYn;
    private String mtrlFaceCd;
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
    private String lotId;

    private String pObjId;
    private String pSiteId;
    private String pWorkId;
    private String pJobSeqId;
    private String pSlotNo;
    private String pProdMtrlId;
    private String pUseStatCd;

}
