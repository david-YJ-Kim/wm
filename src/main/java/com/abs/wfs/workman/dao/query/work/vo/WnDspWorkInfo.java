package com.abs.wfs.workman.dao.query.work.vo;

import lombok.Data;



@Data
public class WnDspWorkInfo {


    private String objId;
    private String siteId;
    private String dspWorkId;
    private String eqpId;
    private String portId;
    private String lotId;
    private String trackInCnfmYn;
    private String eqpChkYn;
    private String rcpChkYn;
    private String rcpId;
    private String dspStatCd;
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
    private String pDspWorkId;
    private String pEqpId;
    private String pPortId;
    private String pLotId;
}