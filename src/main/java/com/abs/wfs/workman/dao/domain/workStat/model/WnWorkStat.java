package com.abs.wfs.workman.dao.domain.workStat.model;

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
@Entity(name = "WN_WORK_STAT")
@DynamicInsert
@DynamicUpdate
@Cacheable(false)
public class WnWorkStat {

    @Id
    @GenericGenerator(name = "WN_WORK_STAT_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "WN_WORK_STAT_SEQ_GENERATOR")
    private String objId;
    private String siteId;
    private String workId;
    private String eqpId;
    private String workStatCd;
    private Timestamp workStartTm;
    private Long workQty;
    private String fnlTaskNm;
    private String batchYn;
    private String inlineYn;
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
    private String eqpInlineId;
    private String inlineStatCd;
    private String dspWorkId;


}
