package com.abs.wfs.workman.dao.domain.dspWorkInfo.model;

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
@Entity(name = "WN_DSP_WORK_INFO")
@DynamicInsert
@DynamicUpdate
@Cacheable(false)
public class WnDspWorkInfo {

    @Id
    @GenericGenerator(name = "WN_DSP_WORK_INFO_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "WN_DSP_WORK_INFO_SEQ_GENERATOR")
    private String objId;
    private String siteId;
    private String dspWorkId;
    private String eqpId;
    private String portId;
    private String lotId;
    private String trackInCnfmYn;
    private String eqpChkYn;
    private String rcpChkYn;
    private String rcpId;
    private String dspStatCd;
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


}
