package com.abs.wfs.workman.dao.domain.stateRuleInfo.vo;


import com.abs.wfs.workman.dao.domain.stateRuleInfo.model.WnStateRuleInfo;
import com.abs.wfs.workman.util.code.StateCondVal;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class WnStateRuleInfoDto {

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
    private Timestamp crtDt;
    private String mdfyUserId;
    private Timestamp mdfyDt;
    private Timestamp fnlEvntDt;
    private String tid;

    @Builder
    public WnStateRuleInfoDto(String siteId, String ruleNm, String tableNm, String columnNm, String columnVal, String condVal, String ruleDesc, String evntNm, String prevEvntNm, String cstmEvntNm, String prevCstmEvntNm, String useStatCd, String rsnCd, String trnsCm, String crtUserId, Timestamp crtDt, String mdfyUserId, Timestamp mdfyDt, Timestamp fnlEvntDt, String tid) {
        this.siteId = siteId;
        this.ruleNm = ruleNm;
        this.tableNm = tableNm;
        this.columnNm = columnNm;
        this.columnVal = columnVal;
        this.condVal = condVal;
        this.ruleDesc = ruleDesc;
        this.evntNm = evntNm;
        this.prevEvntNm = prevEvntNm;
        this.cstmEvntNm = cstmEvntNm;
        this.prevCstmEvntNm = prevCstmEvntNm;
        this.useStatCd = useStatCd;
        this.rsnCd = rsnCd;
        this.trnsCm = trnsCm;
        this.crtUserId = crtUserId;
        this.crtDt = crtDt;
        this.mdfyUserId = mdfyUserId;
        this.mdfyDt = mdfyDt;
        this.fnlEvntDt = fnlEvntDt;
        this.tid = tid;
    }


    public WnStateRuleInfo toEntity(){
        return WnStateRuleInfo.builder()
                .siteId(siteId)
                .ruleNm(ruleNm)
                .tableNm(tableNm)
                .columnNm(columnNm)
                .columnVal(columnVal)
                .condVal(StateCondVal.valueOf(condVal))
                .ruleDesc(ruleDesc)
                .evntNm(evntNm)
                .prevEvntNm(prevEvntNm)
                .cstmEvntNm(cstmEvntNm)
                .prevCstmEvntNm(prevCstmEvntNm)
                .useStatCd(UseStatCd.valueOf(useStatCd))
                .rsnCd(rsnCd)
                .crtUserId(crtUserId)
                .crtDt(crtDt)
                .mdfyUserId(mdfyUserId)
                .mdfyDt(mdfyDt)
                .fnlEvntDt(fnlEvntDt)
                .tid(tid)
                .build();
    }
}
