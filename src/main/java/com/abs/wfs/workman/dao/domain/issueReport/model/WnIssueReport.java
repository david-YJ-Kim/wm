package com.abs.wfs.workman.dao.domain.issueReport.model;


import com.abs.wfs.workman.dao.query.model.WnWorkStat;
import com.abs.wfs.workman.util.code.AlarmEqpType;
import com.abs.wfs.workman.util.code.UseStatCd;
import com.abs.wfs.workman.util.code.UseYn;
import com.abs.wfs.workman.util.code.WorkStatCd;
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
@Entity(name = "WN_ISSUE_REPORT")
public class WnIssueReport {

    @Id
    @GenericGenerator(name = "WN_ISSUE_REPORT_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "WN_ISSUE_REPORT_SEQ_GENERATOR")
    @Column(name = "OBJ_ID")
    private String objId;
    private String siteId;
    private String trkId;
    private String cid;
    private String payload;

    @Enumerated(EnumType.STRING)
    private WorkStatCd workStatCd;

    String scenarioTyp;

    @Enumerated(EnumType.STRING)
    private UseYn fixedYn;

    @Enumerated(EnumType.STRING)
    private UseYn sysFixedYn;

    private Timestamp fixedDt;

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

    @Enumerated(EnumType.STRING)
    private UseStatCd useStatCd;

    private String rsnCd;
    private String trnsCm;

    private String crtUserId;
    private Timestamp crtDt;
    private String mdfyUserId;
    private Timestamp mdfyDt; // NOT NULL ENABLE
    private Timestamp fnlEvntDt;
    private String tid;


    @Builder
    public WnIssueReport(String objId, String siteId, String trkId, String cid, String payload, WorkStatCd workStatCd, String scenarioTyp, UseYn fixedYn, UseYn sysFixedYn, Timestamp fixedDt, String lotId, String carrId, String eqpId, String portId, String errCd, String errCm, String evntNm, String prevEvntNm, String cstmEvntNm, String prevCstmEvntNm, UseStatCd useStatCd, String rsnCd, String trnsCm, String crtUserId, Timestamp crtDt, String mdfyUserId, Timestamp mdfyDt, Timestamp fnlEvntDt, String tid) {
        this.objId = objId;
        this.siteId = siteId;
        this.trkId = trkId;
        this.cid = cid;
        this.payload = payload;
        this.workStatCd = workStatCd;
        this.scenarioTyp = scenarioTyp;
        this.fixedYn = fixedYn;
        this.sysFixedYn = sysFixedYn;
        this.fixedDt = fixedDt;
        this.lotId = lotId;
        this.carrId = carrId;
        this.eqpId = eqpId;
        this.portId = portId;
        this.errCd = errCd;
        this.errCm = errCm;
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
