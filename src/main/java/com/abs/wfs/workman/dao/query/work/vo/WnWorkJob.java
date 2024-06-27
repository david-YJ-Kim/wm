package com.abs.wfs.workman.dao.query.work.vo;


import lombok.Data;

@Data
public class WnWorkJob {
    private String objId;
    private String siteId;
    private String workId;
    private String jobSeqId;
    private String lotId;
    private String batchId;
    private String crntCarrId;
    private String inPortId;
    private String inCarrId;
    private String inCarrTyp;
    private String outPortId;
    private String outCarrId;
    private String outCarrTyp;
    private String prodDefId;
    private String procDefId;
    private String procSgmtId;
    private String jobQty;
    private String rcpDefId;
    private String rcpReadyYn;
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
    private String workFaceCd;

    private String pObjId;
    private String pSiteId;
    private String pWorkId;
    private String pJobSeqId;
    private String pJobStatCd;
    private String pUseStatCd;

}
