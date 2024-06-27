package com.abs.wfs.workman.dao.domain.transferJob.model;

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
@Entity(name = "WN_TRANSFER_JOB")
@DynamicInsert
@DynamicUpdate
@Cacheable(false)
public class WnTransferJob {

    @Id
    @GenericGenerator(name = "WN_TRANSFER_JOB_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "WN_TRANSFER_JOB_SEQ_GENERATOR")
    private String objId;
    private String siteId;
    private String jobId;
    private String carrId;
    private String crntEqpId;
    private String crntPortId;
    private String srcEqpId;
    private String srcPortId;
    private String destEqpId;
    private String destPortId;
    private String moveStatCd;
    private String prirtNo;
    private String fnlEvntNm;
    private Timestamp fnlEvntDt;
    private String mdfyUserId;
    private Timestamp mdfyDt;
    private String crtUserId;
    private Timestamp crtDt;
    private String cstmEvntNm;
    private String evntNm;
    private String prevCstmEvntNm;
    private String prevEvntNm;
    private String rsnCd;
    private String tid;
    private String trnsCm;
    private String useStatCd;


}
