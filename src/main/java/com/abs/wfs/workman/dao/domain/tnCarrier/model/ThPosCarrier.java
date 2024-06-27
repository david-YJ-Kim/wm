package com.abs.wfs.workman.dao.domain.tnCarrier.model;

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
@Entity(name = "TH_POS_CARRIER")
@DynamicInsert
@DynamicUpdate
@Cacheable(false)
public class ThPosCarrier {

    @Id
    @GenericGenerator(name = "TH_POS_CARRIER_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "TH_POS_CARRIER_SEQ_GENERATOR")
    private String objId;
    private String refObjId;
    private String siteId;
    private String carrId;
    private String carrNm;
    private String carrDefId;
    private String carrClsId;
    private String statCd;
    private String prevStatCd;
    private String mtrlTyp;
    private String carrTyp;
    private String clnStatCd;
    private String loadStatCd;
    private String eqpId;
    private Long slotQty;
    private Long lotAsgnQty;
    private Long maxCpct;
    private String vndrId;
    private String lctnNm;
    private String prevLctnNm;
    private String usgTyp;
    private Long usgLmtCnt;
    private Long usgCnt;
    private String usgPrdUnitCd;
    private Long usgLmtPrd;
    private Long usgPrd;
    private String clnTyp;
    private Long clnLmtCnt;
    private Long clnCnt;
    private String clnPrdUnitCd;
    private Long clnLmtPrd;
    private Long clnPrd;
    private Timestamp fnlClnDt;
    private Timestamp nextClnDt;
    private String rprTyp;
    private Long rprLmtCnt;
    private Long rprCnt;
    private String rprPrdUnitCd;
    private Long rprLmtPrd;
    private Long rprPrd;
    private Timestamp fnlRprDt;
    private Timestamp nextRprDt;
    private String unitId;
    private Long subMtrlAsgnQty;
    private Long loadQty;
    private Long mtrlAsgnQty;
    private String moveStatCd;
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



}
