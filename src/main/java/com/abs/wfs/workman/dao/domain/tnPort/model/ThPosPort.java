package com.abs.wfs.workman.dao.domain.tnPort.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@Setter
@Entity(name = "TH_POS_PORT")
@DynamicInsert
@DynamicUpdate
@Cacheable(false)
public class ThPosPort {

    @Id
    @GenericGenerator(name = "TH_POS_PORT_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "TH_POS_PORT_SEQ_GENERATOR")
    private String objId;
    private String refObjId;
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
    private String useStatCd;
    private String rsnCd;
    private String trnsCm;
    private String crtUserId;
    private Timestamp crtDt;
    private String mdfyUserId;
    private Timestamp mdfyDt;
    private Timestamp fnlEvntDt;
    private String tid;
    private String ctrlModeCd;




}