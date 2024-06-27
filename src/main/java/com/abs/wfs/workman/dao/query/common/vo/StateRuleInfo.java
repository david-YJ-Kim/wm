package com.abs.wfs.workman.dao.query.common.vo;

import lombok.Data;

@Data
public class StateRuleInfo {

    private String objId;
    private String siteId;
    private String ruleNm;
    private String tableNm;
    private String columnNm;
    private String columnVal;
    private String condVal;
    private String ruleDesc;
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
}