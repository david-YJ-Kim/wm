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
@Entity(name = "TN_RDS_PORT")
@DynamicInsert
@DynamicUpdate
@Cacheable(false)
public class TnRdsPort {

    @Id
    @GenericGenerator(name = "TN_RDS_PORT_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "TN_RDS_PORT_SEQ_GENERATOR")
    private String objId;
    private String siteId;
    private String eqpId;
    private String portId;
    private String acesModeCd;
    private String statCd;
    private String prevStatCd;
    private String portTyp;
    private String modelNo;
    private String srlNo;
    private String vndrId;
    private String lctnNm;
    private String prevLctnNm;
    private String trsfStatCd;
    private String carrId;
    private String evntStatCd;
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
    private String carrTyp;





}
