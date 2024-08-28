package com.abs.wfs.workman.dao.domain.toolVer.vo;


import com.abs.wfs.workman.dao.domain.toolVer.model.CnPosToolVer;
import com.abs.wfs.workman.spec.in.eap.WfsToolVerReportIvo;
import com.abs.wfs.workman.util.WorkManCommonUtil;
import com.abs.wfs.workman.util.code.AlarmEqpType;
import com.abs.wfs.workman.util.code.ApSystemCodeConstant;
import com.abs.wfs.workman.util.code.PortType;
import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;

@NoArgsConstructor
@Data
public class CnPosToolVerDto {

    private String siteId;
    private String eqpId;
    private AlarmEqpType toolTyp;
    private PortType portTyp;
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
    public CnPosToolVerDto(String siteId, String eqpId, AlarmEqpType toolTyp, PortType portTyp, String version, String evntNm, String prevEvntNm, String cstmEvntNm, String prevCstmEvntNm, String rsnCd, String trnsCm, UseStatCd useStatCd, String crtUserId, Timestamp crtDt, String mdfyUserId, Timestamp mdfyDt, Timestamp fnlEvntDt, String tid) {
        this.siteId = siteId;
        this.eqpId = eqpId;
        this.toolTyp = toolTyp;
        this.portTyp = portTyp;
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

    public CnPosToolVerDto(WfsToolVerReportIvo ivo){
        WfsToolVerReportIvo.Body body = ivo.getBody();

        this.siteId = body.getSiteId();
        this.eqpId = body.getEqpId();
        this.toolTyp = body.getEqpType();
        if(WorkManCommonUtil.nullPointCheck(body.getPortType())){
            this.portTyp = PortType.valueOf(body.getPortType());
        }else{
            this.portTyp = null;
        }
        this.version = body.getVersion();
        this.evntNm = WfsToolVerReportIvo.cid;
        this.useStatCd = UseStatCd.Usable;
        this.crtUserId = ApSystemCodeConstant.WFS;
        this.crtDt = Timestamp.from(Instant.now());
        this.mdfyUserId = ApSystemCodeConstant.WFS;
        this.mdfyDt = Timestamp.from(Instant.now());
        this.fnlEvntDt = Timestamp.from(Instant.now());
    }




    public CnPosToolVer toEntity(){
        return CnPosToolVer.builder()
                .siteId(siteId)
                .eqpId(eqpId)
                .toolTyp(toolTyp)
                .portTyp(portTyp)
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
