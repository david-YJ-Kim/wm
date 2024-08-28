package com.abs.wfs.workman.dao.domain.toolVer.vo;


import com.abs.wfs.workman.dao.domain.toolVer.model.ChPosToolVer;
import com.abs.wfs.workman.dao.domain.toolVer.model.CnPosToolVer;
import com.abs.wfs.workman.util.code.AlarmEqpType;
import com.abs.wfs.workman.util.code.PortType;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;

@NoArgsConstructor
@Data
public class ChPosToolVerDto {

    private String refObjId;
    private String siteId;
    private String eqpId;
    private AlarmEqpType toolType;
    private PortType portType;
    private String version;
    private String evntNm;
    private String prevEvntNm;
    private String cstmEvntNm;
    private String prevCstmEvntNm;
    private String rsnCd;
    private String trnsCm;
    private UseStatCd useStatCd;
    private String crtUserId;
    private Timestamp crtDt;
    private String mdfyUserId;
    private Timestamp mdfyDt; // NOT NULL ENABLE
    private Timestamp fnlEvntDt;
    private String tid;

    @Builder
    public ChPosToolVerDto(String refObjId, String siteId, String eqpId, AlarmEqpType toolType, PortType portType, String version, String evntNm, String prevEvntNm, String cstmEvntNm, String prevCstmEvntNm, String rsnCd, String trnsCm, UseStatCd useStatCd, String crtUserId, Timestamp crtDt, String mdfyUserId, Timestamp mdfyDt, Timestamp fnlEvntDt, String tid) {
        this.refObjId = refObjId;
        this.siteId = siteId;
        this.eqpId = eqpId;
        this.toolType = toolType;
        this.portType = portType;
        this.version = version;
        this.evntNm = evntNm;
        this.prevEvntNm = prevEvntNm;



        this.cstmEvntNm = cstmEvntNm;
        this.prevCstmEvntNm = prevCstmEvntNm;
        this.rsnCd = rsnCd;
        this.trnsCm = trnsCm;
        this.useStatCd = useStatCd;
        this.crtUserId = crtUserId;
        this.crtDt = crtDt;
        this.mdfyUserId = mdfyUserId;
        this.mdfyDt = mdfyDt;
        this.fnlEvntDt = fnlEvntDt;
        this.tid = tid;
    }

    public ChPosToolVerDto(CnPosToolVer cnPosToolVer){

        this.refObjId = cnPosToolVer.getObjId();
        this.siteId = cnPosToolVer.getSiteId();
        this.eqpId = cnPosToolVer.getEqpId();
        this.toolType = cnPosToolVer.getToolTyp();
        this.portType = cnPosToolVer.getPortTyp();
        this.version = cnPosToolVer.getVersion();
        this.evntNm = cnPosToolVer.getEvntNm();
        this.prevEvntNm = cnPosToolVer.getPrevEvntNm();
        this.cstmEvntNm = cnPosToolVer.getCstmEvntNm();
        this.prevCstmEvntNm = cnPosToolVer.getPrevCstmEvntNm();
        this.rsnCd = cnPosToolVer.getRsnCd();
        this.trnsCm = cnPosToolVer.getTrnsCm();
        this.useStatCd = cnPosToolVer.getUseStatCd();
        this.crtUserId = cnPosToolVer.getCrtUserId();
        this.crtDt = cnPosToolVer.getCrtDt();
        this.mdfyUserId = cnPosToolVer.getMdfyUserId();
        this.mdfyDt = Timestamp.from(Instant.now());
        this.fnlEvntDt = Timestamp.from(Instant.now());;
        this.tid = cnPosToolVer.getTid();

    }


    public ChPosToolVer toEntity(){
        return ChPosToolVer.builder()
                .refObjId(refObjId)
                .siteId(siteId)
                .eqpId(eqpId)
                .toolTyp(toolType)
                .portTyp(portType)
                .version(version)
                .evntNm(evntNm)
                .prevEvntNm(prevEvntNm)
                .cstmEvntNm(cstmEvntNm)
                .prevCstmEvntNm(prevCstmEvntNm)
                .rsnCd(rsnCd)
                .trnsCm(trnsCm)
                .useStatCd(useStatCd)
                .crtUserId(crtUserId)
                .crtDt(crtDt)
                .mdfyUserId(mdfyUserId)
                .mdfyDt(mdfyDt)
                .fnlEvntDt(fnlEvntDt)
                .tid(tid)
                .build();
    }
}
