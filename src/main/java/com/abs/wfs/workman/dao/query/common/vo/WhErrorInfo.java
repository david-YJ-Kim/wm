package com.abs.wfs.workman.dao.query.common.vo;

import lombok.Data;

@Data
public class WhErrorInfo {
    private String objId;
    private String siteId;
    private String msgId;
    private String msgCtntsCm;
    private String workStatCd;
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
