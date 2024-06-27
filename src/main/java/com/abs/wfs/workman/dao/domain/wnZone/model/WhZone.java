package com.abs.wfs.workman.dao.domain.wnZone.model;

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
@Entity(name = "WH_ZONE")
@DynamicInsert
@DynamicUpdate
@Cacheable(false)
public class WhZone {

    @Id
    @GenericGenerator(name = "WH_ZONE_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "WH_ZONE_SEQ_GENERATOR")
    private String objId;
    private String refObjId;
    private String siteId;
    private String zoneId;
    private String zoneTyp;
    private String manlYn;
    private Long maxCpctVal;
    private Long crntCpctVal;
    private String carrTyp;
    private String mdfyUserId;
    private Timestamp mdfyDt;
    private String crtUserId;
    private Timestamp crtDt;
    private String cstmEvntNm;
    private String evntNm;
    private Timestamp fnlEvntDt;
    private String prevCstmEvntNm;
    private String prevEvntNm;
    private String rsnCd;
    private String tid;
    private String trnsCm;
    private String useStatCd;
    private String facilityId;
    private String eqpId;


}
