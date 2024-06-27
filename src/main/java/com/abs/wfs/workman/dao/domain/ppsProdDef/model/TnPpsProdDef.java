package com.abs.wfs.workman.dao.domain.ppsProdDef.model;

import com.abs.wfs.workman.util.code.UseStatCd;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;


@NoArgsConstructor
@Getter
@Setter
@Entity(name = "TN_PPS_PROD_DEF")
public class TnPpsProdDef {

    @Id
    @GenericGenerator(name = "TN_PPS_PROD_DEF_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "TN_PPS_PROD_DEF_SEQ_GENERATOR")
    @Column(name = "OBJ_ID")
    private String objId;


    private String tid;
    private String siteId;
    private String prodDefId;
    private String prodDefNm;
    private String prodClsId;
    private String prodTyp;
    private String salesCd;
    private String grdCd;
    private String qty;
    private String unitId;
    private String prodDefTyp;
    private String cstmId;
    private String lctnNm;
    private String mtrlTyp;
    private String subMtrlTyp;
    private String subMtrlQty;

    @Column(name = "STRIP_X_SPEC_VAL")
    private Long stripXSpecVal;

    @Column(name = "STRIP_Y_SPEC_VAL")
    private Long stripYSpecVal;


    @Column(name = "CELL_X_SPEC_VAL")
    private Long cellXSpecVal;

    @Column(name = "CELL_Y_SPEC_VAL")
    private Long cellYSpecVal;

    private Long stripPerPanelQty;
    private Long cellPerStripQty;
    private Long cellPerPanelQty;
    private String shipUnitTyp;
    private String cstmProdId;
    private String cstmProdVerVal;
    private String prodMainStrtrVal;
    private String prodSubStrtrVal;
    private String layerVal;
    private Long uiSeq;
    private String evntNm;
    private String prevEvntNm;
    private String cstmEvntNm;
    private String prevCstmEvntNm;


    @Enumerated(EnumType.STRING)
    private UseStatCd useStatCd;

    private String rsnCd;
    private String trnsCm;
    private String crtUserId;
    private Timestamp crtDt;
    private String mdfyUserId;
    private Timestamp mdfyDt;
    private Timestamp fnlEvntDt;
    private String prodGroupId;
    private String mngYn;


    @Builder
    public TnPpsProdDef(String objId, String tid, String siteId, String prodDefId, String prodDefNm, String prodClsId, String prodTyp, String salesCd, String grdCd, String qty, String unitId, String prodDefTyp, String cstmId, String lctnNm, String mtrlTyp, String subMtrlTyp, String subMtrlQty, Long stripXSpecVal, Long stripYSpecVal, Long cellXSpecVal, Long cellYSpecVal, Long stripPerPanelQty, Long cellPerStripQty, Long cellPerPanelQty, String shipUnitTyp, String cstmProdId, String cstmProdVerVal, String prodMainStrtrVal, String prodSubStrtrVal, String layerVal, Long uiSeq, String evntNm, String prevEvntNm, String cstmEvntNm, String prevCstmEvntNm, UseStatCd useStatCd, String rsnCd, String trnsCm, String crtUserId, Timestamp crtDt, String mdfyUserId, Timestamp mdfyDt, Timestamp fnlEvntDt, String prodGroupId, String mngYn) {
        this.objId = objId;
        this.tid = tid;
        this.siteId = siteId;
        this.prodDefId = prodDefId;
        this.prodDefNm = prodDefNm;
        this.prodClsId = prodClsId;
        this.prodTyp = prodTyp;
        this.salesCd = salesCd;
        this.grdCd = grdCd;
        this.qty = qty;
        this.unitId = unitId;
        this.prodDefTyp = prodDefTyp;
        this.cstmId = cstmId;
        this.lctnNm = lctnNm;
        this.mtrlTyp = mtrlTyp;
        this.subMtrlTyp = subMtrlTyp;
        this.subMtrlQty = subMtrlQty;
        this.stripXSpecVal = stripXSpecVal;
        this.stripYSpecVal = stripYSpecVal;
        this.cellXSpecVal = cellXSpecVal;
        this.cellYSpecVal = cellYSpecVal;
        this.stripPerPanelQty = stripPerPanelQty;
        this.cellPerStripQty = cellPerStripQty;
        this.cellPerPanelQty = cellPerPanelQty;
        this.shipUnitTyp = shipUnitTyp;
        this.cstmProdId = cstmProdId;
        this.cstmProdVerVal = cstmProdVerVal;
        this.prodMainStrtrVal = prodMainStrtrVal;
        this.prodSubStrtrVal = prodSubStrtrVal;
        this.layerVal = layerVal;
        this.uiSeq = uiSeq;
        this.evntNm = evntNm;
        this.prevEvntNm = prevEvntNm;
        this.cstmEvntNm = cstmEvntNm;
        this.prevCstmEvntNm = prevCstmEvntNm;
        this.useStatCd = useStatCd;
        this.rsnCd = rsnCd;
        this.trnsCm = trnsCm;
        this.crtUserId = crtUserId;
        this.crtDt = crtDt;
        this.mdfyUserId = mdfyUserId;
        this.mdfyDt = mdfyDt;
        this.fnlEvntDt = fnlEvntDt;
        this.prodGroupId = prodGroupId;
        this.mngYn = mngYn;
    }
}
