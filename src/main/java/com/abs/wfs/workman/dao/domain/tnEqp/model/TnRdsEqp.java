package com.abs.wfs.workman.dao.domain.tnEqp.model;

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
@Entity(name = "TN_RDS_EQP")
@DynamicInsert
@DynamicUpdate
@Cacheable(false)
public class TnRdsEqp {

    @Id
    @GenericGenerator(name = "TN_RDS_EQP_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "TN_RDS_EQP_SEQ_GENERATOR")
    private String objId;
    private String siteId;
    private String eqpId;
    private String eqpNm;
    private String eqpClsId;
    private String eqpTyp;
    private String prntId;
    private Long eqpLvlNo;
    private Long runCnt;
    private String rcpId;
    private String alrmId;
    private String cpctUnitCd;
    private Long maxCpct;
    private Long minCpct;
    private Long tactTm;
    private String tactTmUnitCd;
    private Long leadTm;
    private String leadTmUnitCd;
    private String lctnNm;
    private String prevLctnNm;
    private String vndrId;
    private String modelNo;
    private String srlNo;
    private String ecSvrNm;
    private String mtrlStkTyp;
    private String eqpInlineId;
    private Long eqpInlineSeq;
    private String inlineYn = "N"; // DEFAULT 'N' NOT NULL ENABLE
    private String batchYn = "N"; // DEFAULT 'N' NOT NULL ENABLE
    private Long batchCnt;
    private String sorterModeAvailYn;
    private String modelTyp;
    private Long uiSeq;
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
    private Timestamp mdfyDt; // NOT NULL ENABLE
    private Timestamp fnlEvntDt;
    private String tid;
    private String lctnId;
    private String lineId;
    private String floorId;
    private String zoneId;
    private String areaId;




}
