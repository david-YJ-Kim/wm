package com.abs.wfs.workman.dao.domain.tnPort.model;

import com.abs.wfs.workman.util.code.*;
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
@Entity(name = "TN_POS_PORT")
public class
TnPosPort {

    @Id
    @GenericGenerator(name = "TN_POS_PORT_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "TN_POS_PORT_SEQ_GENERATOR")
    @Column(name = "OBJ_ID")
    private String objId;

    private String siteId;
    private String eqpId;
    private String portId;

    @Enumerated(EnumType.STRING)
    private AcesModeCd acesModeCd;

    @Enumerated(EnumType.STRING)
    private PortStatCd statCd;
    private String prevStatCd;

    @Enumerated(EnumType.STRING)
    private TrsfStatCd trsfStatCd;

    private String carrId;
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
    private Timestamp mdfyDt;
    private Timestamp fnlEvntDt;
    private String tid;

    @Enumerated(EnumType.STRING)
    private PortCtrlModeCd ctrlModeCd;

    @Enumerated(EnumType.STRING)
    private EfemStatCd efemStatCd;

    @Enumerated(EnumType.STRING)
    private EfemCtrlModeCd efemCtrlModeCd;


    @Builder
    public TnPosPort(String objId, String siteId, String eqpId, String portId, AcesModeCd acesModeCd, PortStatCd statCd, String prevStatCd, TrsfStatCd trsfStatCd, String carrId, String evntNm, String prevEvntNm, String cstmEvntNm, String prevCstmEvntNm, UseStatCd useStatCd, String rsnCd, String trnsCm, String crtUserId, Timestamp crtDt, String mdfyUserId, Timestamp mdfyDt, Timestamp fnlEvntDt, String tid, PortCtrlModeCd ctrlModeCd, EfemStatCd efemStatCd, EfemCtrlModeCd efemCtrlModeCd) {
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
