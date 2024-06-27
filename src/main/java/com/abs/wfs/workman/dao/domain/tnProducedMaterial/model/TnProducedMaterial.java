package com.abs.wfs.workman.dao.domain.tnProducedMaterial.model;

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
@Entity(name = "TN_POS_PRODUCED_MATERIAL")
public class TnProducedMaterial {

    @Id
    @GenericGenerator(name = "TN_POS_PRODUCED_MATERIAL_SEQ_GENERATOR", strategy = "com.abs.wfs.workman.util.ObjIdGenerator")
    @GeneratedValue(generator = "TN_POS_PRODUCED_MATERIAL_SEQ_GENERATOR")
    @Column(name = "OBJ_ID")
    private String objId;

    private String tid;
    private String siteId;
    private String prodMtrlId;
    private String prodMtrlNm;
    private String prodDefId;
    private String lctnNm;
    private String prevLctnNm;
    private String btchId;
    private String rootProdMtrlId;
    private String prntProdMtrlId;
    private String chldProdMtrlId;
    private String lotId;
    private String prevLotId;
    private String carrId;
    private Long slotNo;
    private String prodTyp;
    private String prevProdTyp;
    private String mtrlTyp;
    private String prevMtrlTyp;
    private String subMtrlOrgnQty;
    private String subMtrlQty;
    private String prevSubMtrlQty;
    private String grdCd;
    private String prevGrdCd;
    private String subMtrlGrdCd;
    private String prevSubMtrlGrdCd;
    private Timestamp dueDt;
    private Long pono;
    private String prevSiteId;
    private String destSiteId;
    private String prevEqpId;
    private String prevProcNodeId;
    private String prevSubProcDefId;
    private String subProcDefId;
    private String subMtrlTyp;
    private String prevSubMtrlTyp;
    private String prevDestSiteId;
    private String statCd;
    private String prevStatCd;
    private String procStatCd;
    private String holdYn;
    private String rwrkYn;
    private Timestamp mvinDt;
    private String mvinUserId;
    private Timestamp mvoutDt;
    private String mvoutUserId;
    private Timestamp lotFnshdDt;
    private Timestamp lotShipDt;
    private Timestamp procStrtDt;
    private Timestamp procEndDt;
    private String procSgmtId;
    private String prevProcSgmtId;
    private String procDefId;
    private String prevProcDefId;
    private String procNodeId;
    private String eqpId;
    private String eqpRcpId;
    private Long totRwrkCnt;
    private Long rwrkCnt;
    private String rwrkTyp;
    private Long useCnt;
    private String workOrdId;
    private String prodOrdId;
    private String smplYn;
    private Timestamp fnshdDt;
    private Timestamp shipDt;
    private Timestamp tmnDt;
    private String prevProdDefId;
    private String prevCarrId;
    private Long prevSlotNo;
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
    private String subMtrlDfctQty;
    private String prevSubMtrlDfctQty;
    private String initSubMtrlDfctQty;
    private String subEqpId;
    private String procSgmtRsltCd;
    private String mtrlFaceCd;
    private String mdfySubMtrlGrdCd;
    private String vndrLotId;
    private String batchCode;


    @Builder
    public TnProducedMaterial(String objId, String tid, String siteId, String prodMtrlId, String prodMtrlNm, String prodDefId, String lctnNm, String prevLctnNm, String btchId, String rootProdMtrlId, String prntProdMtrlId, String chldProdMtrlId, String lotId, String prevLotId, String carrId, Long slotNo, String prodTyp, String prevProdTyp, String mtrlTyp, String prevMtrlTyp, String subMtrlOrgnQty, String subMtrlQty, String prevSubMtrlQty, String grdCd, String prevGrdCd, String subMtrlGrdCd, String prevSubMtrlGrdCd, Timestamp dueDt, Long pono, String prevSiteId, String destSiteId, String prevEqpId, String prevProcNodeId, String prevSubProcDefId, String subProcDefId, String subMtrlTyp, String prevSubMtrlTyp, String prevDestSiteId, String statCd, String prevStatCd, String procStatCd, String holdYn, String rwrkYn, Timestamp mvinDt, String mvinUserId, Timestamp mvoutDt, String mvoutUserId, Timestamp lotFnshdDt, Timestamp lotShipDt, Timestamp procStrtDt, Timestamp procEndDt, String procSgmtId, String prevProcSgmtId, String procDefId, String prevProcDefId, String procNodeId, String eqpId, String eqpRcpId, Long totRwrkCnt, Long rwrkCnt, String rwrkTyp, Long useCnt, String workOrdId, String prodOrdId, String smplYn, Timestamp fnshdDt, Timestamp shipDt, Timestamp tmnDt, String prevProdDefId, String prevCarrId, Long prevSlotNo, String evntNm, String prevEvntNm, String cstmEvntNm, String prevCstmEvntNm, UseStatCd useStatCd, String rsnCd, String trnsCm, String crtUserId, Timestamp crtDt, String mdfyUserId, Timestamp mdfyDt, Timestamp fnlEvntDt, String subMtrlDfctQty, String prevSubMtrlDfctQty, String initSubMtrlDfctQty, String subEqpId, String procSgmtRsltCd, String mtrlFaceCd, String mdfySubMtrlGrdCd, String vndrLotId, String batchCode) {
        this.objId = objId;
        this.tid = tid;
        this.siteId = siteId;
        this.prodMtrlId = prodMtrlId;
        this.prodMtrlNm = prodMtrlNm;
        this.prodDefId = prodDefId;
        this.lctnNm = lctnNm;
        this.prevLctnNm = prevLctnNm;
        this.btchId = btchId;
        this.rootProdMtrlId = rootProdMtrlId;
        this.prntProdMtrlId = prntProdMtrlId;
        this.chldProdMtrlId = chldProdMtrlId;
        this.lotId = lotId;
        this.prevLotId = prevLotId;
        this.carrId = carrId;
        this.slotNo = slotNo;
        this.prodTyp = prodTyp;
        this.prevProdTyp = prevProdTyp;
        this.mtrlTyp = mtrlTyp;
        this.prevMtrlTyp = prevMtrlTyp;
        this.subMtrlOrgnQty = subMtrlOrgnQty;
        this.subMtrlQty = subMtrlQty;
        this.prevSubMtrlQty = prevSubMtrlQty;
        this.grdCd = grdCd;
        this.prevGrdCd = prevGrdCd;
        this.subMtrlGrdCd = subMtrlGrdCd;
        this.prevSubMtrlGrdCd = prevSubMtrlGrdCd;
        this.dueDt = dueDt;
        this.pono = pono;
        this.prevSiteId = prevSiteId;
        this.destSiteId = destSiteId;
        this.prevEqpId = prevEqpId;
        this.prevProcNodeId = prevProcNodeId;
        this.prevSubProcDefId = prevSubProcDefId;
        this.subProcDefId = subProcDefId;
        this.subMtrlTyp = subMtrlTyp;
        this.prevSubMtrlTyp = prevSubMtrlTyp;
        this.prevDestSiteId = prevDestSiteId;
        this.statCd = statCd;
        this.prevStatCd = prevStatCd;
        this.procStatCd = procStatCd;
        this.holdYn = holdYn;
        this.rwrkYn = rwrkYn;
        this.mvinDt = mvinDt;
        this.mvinUserId = mvinUserId;
        this.mvoutDt = mvoutDt;
        this.mvoutUserId = mvoutUserId;
        this.lotFnshdDt = lotFnshdDt;
        this.lotShipDt = lotShipDt;
        this.procStrtDt = procStrtDt;
        this.procEndDt = procEndDt;
        this.procSgmtId = procSgmtId;
        this.prevProcSgmtId = prevProcSgmtId;
        this.procDefId = procDefId;
        this.prevProcDefId = prevProcDefId;
        this.procNodeId = procNodeId;
        this.eqpId = eqpId;
        this.eqpRcpId = eqpRcpId;
        this.totRwrkCnt = totRwrkCnt;
        this.rwrkCnt = rwrkCnt;
        this.rwrkTyp = rwrkTyp;
        this.useCnt = useCnt;
        this.workOrdId = workOrdId;
        this.prodOrdId = prodOrdId;
        this.smplYn = smplYn;
        this.fnshdDt = fnshdDt;
        this.shipDt = shipDt;
        this.tmnDt = tmnDt;
        this.prevProdDefId = prevProdDefId;
        this.prevCarrId = prevCarrId;
        this.prevSlotNo = prevSlotNo;
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
        this.subMtrlDfctQty = subMtrlDfctQty;
        this.prevSubMtrlDfctQty = prevSubMtrlDfctQty;
        this.initSubMtrlDfctQty = initSubMtrlDfctQty;
        this.subEqpId = subEqpId;
        this.procSgmtRsltCd = procSgmtRsltCd;
        this.mtrlFaceCd = mtrlFaceCd;
        this.mdfySubMtrlGrdCd = mdfySubMtrlGrdCd;
        this.vndrLotId = vndrLotId;
        this.batchCode = batchCode;
    }
}
