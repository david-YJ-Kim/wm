package com.abs.wfs.workman.dao.domain.tnPort.model;

import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "TN_POS_PORT")
public class TnPosPort {

    @Id
    @GenericGenerator(name = "TN_POS_PORT_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "TN_POS_PORT_SEQ_GENERATOR")
    @Column(name = "OBJ_ID")
    private String objId;
    private String siteId;
    private String eqpId;
    private String portId;
    private String acesModeCd;
    private String statCd;
    private String prevStatCd;
    private String trsfStatCd;
    private String carrId;
    private String evntNm;
    private String prevEvntNm;
    private String cstmEvntNm;
    private String prevCstmEvntNm;

    @Enumerated(EnumType.STRING)
    @Column(name="USE_STAT_CD")
    private UseStatCd useStatCd;

    private String rsnCd;
    private String trnsCm;
    private String crtUserId;
    private Timestamp crtDt;
    private String mdfyUserId;
    private Timestamp mdfyDt;
    private Timestamp fnlEvntDt;
    private String tid;
    private String ctrlModeCd;
    private String efemStatCd;
    private String efemCtrlModeCd;


    private String pSiteId;
    private String pEqpId;
    private String pPortId;
    private String pUseStatCd;


    @Builder
    public TnPosPort(String objId, String siteId, String eqpId, String portId, String acesModeCd, String statCd, String prevStatCd, String trsfStatCd, String carrId, String evntNm, String prevEvntNm, String cstmEvntNm, String prevCstmEvntNm, UseStatCd useStatCd, String rsnCd, String trnsCm, String crtUserId, Timestamp crtDt, String mdfyUserId, Timestamp mdfyDt, Timestamp fnlEvntDt, String tid, String ctrlModeCd, String efemStatCd, String efemCtrlModeCd) {
        this.objId = objId;
        this.siteId = siteId;
        this.eqpId = eqpId;
        this.portId = portId;
        this.acesModeCd = acesModeCd;
        this.statCd = statCd;
        this.prevStatCd = prevStatCd;
        this.trsfStatCd = trsfStatCd;
        this.carrId = carrId;
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
        this.ctrlModeCd = ctrlModeCd;
        this.efemStatCd = efemStatCd;
        this.efemCtrlModeCd = efemCtrlModeCd;
    }
}
