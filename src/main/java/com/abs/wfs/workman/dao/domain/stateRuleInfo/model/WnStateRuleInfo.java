package com.abs.wfs.workman.dao.domain.stateRuleInfo.model;

import com.abs.wfs.workman.util.code.StateCondVal;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "WN_STATE_RULE_INFO")
public class WnStateRuleInfo {

    @Id
    @GenericGenerator(name = "WN_STATE_RULE_INFO_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "WN_STATE_RULE_INFO_SEQ_GENERATOR")
    @Column(name = "OBJ_ID")
    private String objId;

    @Column(name = "SITE_ID")
    private String siteId;

    @Column(name = "RULE_NM")
    private String ruleNm;

    @Column(name = "TABLE_NM")
    private String tableNm;

    @Column(name = "COLUMN_NM")
    private String columnNm;

    @Column(name = "COLUMN_VAL")
    private String columnVal;

    @Enumerated(EnumType.STRING)
    @Column(name = "COND_VAL")
    private StateCondVal condVal; // 조건값(eq|ne|in|nin|lt|gt|lte|gte)

    @Column(name = "RULE_DESC")
    private String ruleDesc;

    @Column(name = "EVNT_NM")
    private String evntNm;

    @Column(name = "PREV_EVNT_NM")
    private String prevEvntNm;

    @Column(name = "CSTM_EVNT_NM")
    private String cstmEvntNm;

    @Column(name = "PREV_CSTM_EVNT_NM")
    private String prevCstmEvntNm;

    @Enumerated(EnumType.STRING)
    @Column(name = "USE_STAT_CD")
    private UseStatCd useStatCd;

    @Column(name = "RSN_CD")
    private String rsnCd;

    @Column(name = "TRNS_CM")
    private String trnsCm;

    @Column(name = "CRT_USER_ID")
    private String crtUserId;

    @Column(name = "CRT_DT")
    private Timestamp crtDt;

    @Column(name = "MDFY_USER_ID")
    private String mdfyUserId;

    @Column(name = "MDFY_DT")
    private Timestamp mdfyDt;

    @Column(name = "FNL_EVNT_DT")
    private Timestamp fnlEvntDt;

    @Column(name = "TID")
    private String tid;

    @Builder
    public WnStateRuleInfo(String objId, String siteId, String ruleNm, String tableNm, String columnNm, String columnVal, StateCondVal condVal, String ruleDesc, String evntNm, String prevEvntNm, String cstmEvntNm, String prevCstmEvntNm, UseStatCd useStatCd, String rsnCd, String trnsCm, String crtUserId, Timestamp crtDt, String mdfyUserId, Timestamp mdfyDt, Timestamp fnlEvntDt, String tid) {
        this.objId = objId;
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
}
