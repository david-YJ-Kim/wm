package com.abs.wfs.workman.dao.query.common.vo;

import lombok.Data;

@Data
public class WnMtrlUsageInfo {

    private String objId;
    private String siteId;
    private String workId;
    private String eqpId;
    private String subEqpId;
    private String lotId;
    private String specId;
    private String specTyp;
    private Double specUseCnt;
    private Double subSpecUseCnt;
    private String specLimitCnt;
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
    private String posnId;
}
