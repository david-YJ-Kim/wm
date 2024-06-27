package com.abs.wfs.workman.dao.query.work.vo;

import lombok.Data;


@Data
public class WnWorkJobCellInfo {
    private String objId;
    private String siteId;
    private String workId;
    private String jobSeqId;
    private String lotId;
    private String subProdMtrlId;
    private String subProdMtrlStrtTm;
    private String subProdMtrlEndTm;
    private String cellGrdCd;
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
