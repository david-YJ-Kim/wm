package com.abs.wfs.workman.dao.domain.sorterJobResv.model;

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
@Entity(name = "WN_SORTER_JOB_RESV")
@DynamicInsert
@DynamicUpdate
@Cacheable(false)
public class WnSorterJobResv {

    @Id
    @GenericGenerator(name = "WN_SORTER_JOB_RESV_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "WN_SORTER_JOB_RESV_SEQ_GENERATOR")
    private String objId;
    private String siteId;
    private String jobId;
    private String eqpId;
    private String srcLotId;
    private String tgtLotId;
    private String srcCarrId;
    private String srcSlotNo;
    private String srcProdMtrlId;
    private String tgtCarrId;
    private String tgtSlotNo;
    private String tgtProdMtrlId;
    private Long prirtNo;
    private String sorterJobTyp;
    private String dtlSorterJobTyp;
    private String jobStatCd;
    private String procSgmtId;
    private Long procSgmtSeq;
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

}
