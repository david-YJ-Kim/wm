package com.abs.wfs.workman.dao.domain.workJobSlotInfo.model;

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
@Entity(name = "WH_WORK_JOB_SLOT_INFO")
@DynamicInsert
@DynamicUpdate
@Cacheable(false)
public class WhWorkJobSlotInfo {

    @Id
    @GenericGenerator(name = "WH_WORK_JOB_SLOT_INFO_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "WH_WORK_JOB_SLOT_INFO_SEQ_GENERATOR")
    private String objId;
    private String refObjId;
    private String siteId;
    private String workId;
    private String jobSeqId;
    private String slotNo;
    private String prodMtrlId;
    private Timestamp prodMtrlStrtTm;
    private Timestamp prodMtrlEndTm;
    private String jobStatCd;
    private String selfInspYn;
    private String mtrlFaceCd;
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
    private String lotId;
    private String scanProdMtrlId;



}
