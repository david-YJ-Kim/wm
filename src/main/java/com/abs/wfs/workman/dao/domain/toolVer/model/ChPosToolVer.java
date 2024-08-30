package com.abs.wfs.workman.dao.domain.toolVer.model;


import com.abs.wfs.workman.util.code.AlarmEqpType;
import com.abs.wfs.workman.util.code.PortType;
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
@Entity(name = "CH_POS_TOOL_VER")
public class ChPosToolVer {

    @Id
    @GenericGenerator(name = "CH_POS_TOOL_VER_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "CH_POS_TOOL_VER_SEQ_GENERATOR")
    private String objId;

    private String refObjId;
    private String siteId;
    private String eqpId;

    @Enumerated(EnumType.STRING)
    private AlarmEqpType toolTyp;

    @Enumerated(EnumType.STRING)
    private PortType portTyp;

    private String version;
    private String prevVersion;
    private String evntNm;
    private String prevEvntNm;
    private String cstmEvntNm;
    private String prevCstmEvntNm;
    private String rsnCd;
    private String trnsCm;

    private Timestamp versionUpdateDt;

    @Enumerated(EnumType.STRING)
    private UseStatCd useStatCd;

    private String crtUserId;
    private Timestamp crtDt;
    private String mdfyUserId;
    private Timestamp mdfyDt; // NOT NULL ENABLE
    private Timestamp fnlEvntDt;
    private String tid;

    @Builder
    public ChPosToolVer(String objId, String refObjId, String siteId, String eqpId, AlarmEqpType toolTyp, PortType portTyp, String prevVersion, String version, String evntNm, String prevEvntNm, String cstmEvntNm, String prevCstmEvntNm, String rsnCd, String trnsCm, UseStatCd useStatCd, String crtUserId, Timestamp versionUpdateDt, Timestamp crtDt, String mdfyUserId, Timestamp mdfyDt, Timestamp fnlEvntDt, String tid) {
        this.objId = objId;
        this.refObjId = refObjId;
        this.siteId = siteId;
        this.eqpId = eqpId;
        this.toolTyp = toolTyp;
        this.portTyp = portTyp;
        this.prevVersion = prevVersion;
        this.version = version;
        this.evntNm = evntNm;
        this.prevEvntNm = prevEvntNm;
        this.cstmEvntNm = cstmEvntNm;
        this.prevCstmEvntNm = prevCstmEvntNm;
        this.rsnCd = rsnCd;
        this.trnsCm = trnsCm;
        this.versionUpdateDt = versionUpdateDt;
        this.useStatCd = useStatCd;
        this.crtUserId = crtUserId;
        this.crtDt = crtDt;
        this.mdfyUserId = mdfyUserId;
        this.mdfyDt = mdfyDt;
        this.fnlEvntDt = fnlEvntDt;
        this.tid = tid;
    }
}
