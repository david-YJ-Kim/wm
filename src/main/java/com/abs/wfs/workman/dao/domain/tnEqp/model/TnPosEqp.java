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
@Entity(name = "TN_POS_EQP")
@DynamicInsert
@DynamicUpdate
@Cacheable(false)
public class TnPosEqp {

    @Id
    @GenericGenerator(name = "TN_POS_EQP_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "TN_POS_EQP_SEQ_GENERATOR")
    private String objId;
    private String siteId;
    private String eqpId;
    private String eqpNm;
    private String eqpClsId;
    private String sorterModeYn;
    private String statCd;
    private String prevStatCd;
    private Timestamp statCdDt;
    private Timestamp prevStatDt;
    private String procStatCd;
    private String prevProcStatCd;
    private Timestamp procStatCdDt;
    private Timestamp prevProcStatDt;
    private String ctrlModeCd;
    private String prevCtrlModeCd;
    private Timestamp ctrlModeDt;
    private Timestamp prevCtrlModeDt;
    private String oprtnModeCd;
    private String prevOprtnModeCd;
    private Timestamp oprtnModeDt;
    private Timestamp prevOprtnModeDt;
    private Long procLotCnt;
    private Long cpct;
    private String autoDspYn;
    private String intlkYn;
    private String fnlLotId;
    private String selfInspYn;
    private String fnlProdDefId;
    private String recipeId;
    private Timestamp fnlTkoutDt;
    private String maintAftFstYn;
    private String lossStatCd;
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
    private String fnlProcSgmtId;




}
