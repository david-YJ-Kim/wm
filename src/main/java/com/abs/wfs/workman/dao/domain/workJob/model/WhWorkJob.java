package com.abs.wfs.workman.dao.domain.workJob.model;

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
@Entity(name = "WH_WORK_JOB")
@DynamicInsert
@DynamicUpdate
@Cacheable(false)
public class WhWorkJob {

    @Id
    @GenericGenerator(name = "WH_WORK_JOB_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "WH_WORK_JOB_SEQ_GENERATOR")
    private String objId;
    private String refObjId;
    private String siteId;
    private String workId;
    private String jobSeqId;
    private String lotId;
    private String batchId;
    private String crntCarrId;
    private String inPortId;
    private String inCarrId;
    private String inCarrTyp;
    private String outPortId;
    private String outCarrId;
    private String outCarrTyp;
    private String prodDefId;
    private String procDefId;
    private String procSgmtId;
    private Long jobQty;
    private String rcpDefId;
    private String rcpReadyYn;
    private String jobStatCd;
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
    private String workFaceCd;



}
