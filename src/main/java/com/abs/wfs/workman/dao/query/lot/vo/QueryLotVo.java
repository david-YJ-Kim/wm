package com.abs.wfs.workman.dao.query.lot.vo;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class QueryLotVo {

    private String siteId;
    private String lotId;
    private String carrId;
    private String carrTyp;
    private String carrClsId;
    private String holdYn;
    private String prodDefId;
    private String procDefId;
    private String procSgmtId;
    private String procStatCd;
    private String statCd;
    private String useStatCd;
    private String qty;
    private String subMtrlTyp;
    private String subMtrlQty;
    private String sgmtStatCd;
    private String workStatCd;
    private String dtlWorkStatCd;
    private String crntEqpId;
    private String crntPortId;
    private String resvGrpId;
    private String resvEqpId;
    private String resvOutPortId;
    private String resvOutCarrId;
    private String resvPortId;
    private String batchId;
    private String batchSeq;
    private String selfInspYn;
    private String selfInspInfoObjId;
    private String selfInspPanelCnt;


    @Builder
    public QueryLotVo(String siteId, String lotId, String carrId, String carrTyp, String carrClsId, String holdYn, String prodDefId, String procDefId, String procSgmtId, String procStatCd, String statCd, String useStatCd, String qty, String subMtrlTyp, String subMtrlQty, String sgmtStatCd, String workStatCd, String dtlWorkStatCd, String crntEqpId, String crntPortId, String resvGrpId, String resvEqpId, String resvOutPortId, String resvOutCarrId, String resvPortId, String batchId, String batchSeq, String selfInspYn, String selfInspInfoObjId, String selfInspPanelCnt) {
        this.siteId = siteId;
        this.lotId = lotId;
        this.carrId = carrId;
        this.carrTyp = carrTyp;
        this.carrClsId = carrClsId;
        this.holdYn = holdYn;
        this.prodDefId = prodDefId;
        this.procDefId = procDefId;
        this.procSgmtId = procSgmtId;
        this.procStatCd = procStatCd;
        this.statCd = statCd;
        this.useStatCd = useStatCd;
        this.qty = qty;
        this.subMtrlTyp = subMtrlTyp;
        this.subMtrlQty = subMtrlQty;
        this.sgmtStatCd = sgmtStatCd;
        this.workStatCd = workStatCd;
        this.dtlWorkStatCd = dtlWorkStatCd;
        this.crntEqpId = crntEqpId;
        this.crntPortId = crntPortId;
        this.resvGrpId = resvGrpId;
        this.resvEqpId = resvEqpId;
        this.resvOutPortId = resvOutPortId;
        this.resvOutCarrId = resvOutCarrId;
        this.resvPortId = resvPortId;
        this.batchId = batchId;
        this.batchSeq = batchSeq;
        this.selfInspYn = selfInspYn;
        this.selfInspInfoObjId = selfInspInfoObjId;
        this.selfInspPanelCnt = selfInspPanelCnt;
    }

    @Override
    public String toString() {
        return "QueryLotVo{" +
                "siteId='" + siteId + '\'' +
                ", lotId='" + lotId + '\'' +
                ", carrId='" + carrId + '\'' +
                ", carrTyp='" + carrTyp + '\'' +
                ", carrClsId='" + carrClsId + '\'' +
                ", holdYn='" + holdYn + '\'' +
                ", prodDefId='" + prodDefId + '\'' +
                ", procDefId='" + procDefId + '\'' +
                ", procSgmtId='" + procSgmtId + '\'' +
                ", procStatCd='" + procStatCd + '\'' +
                ", statCd='" + statCd + '\'' +
                ", useStatCd='" + useStatCd + '\'' +
                ", qty='" + qty + '\'' +
                ", subMtrlTyp='" + subMtrlTyp + '\'' +
                ", subMtrlQty='" + subMtrlQty + '\'' +
                ", sgmtStatCd='" + sgmtStatCd + '\'' +
                ", workStatCd='" + workStatCd + '\'' +
                ", dtlWorkStatCd='" + dtlWorkStatCd + '\'' +
                ", crntEqpId='" + crntEqpId + '\'' +
                ", crntPortId='" + crntPortId + '\'' +
                ", resvGrpId='" + resvGrpId + '\'' +
                ", resvEqpId='" + resvEqpId + '\'' +
                ", resvOutPortId='" + resvOutPortId + '\'' +
                ", resvOutCarrId='" + resvOutCarrId + '\'' +
                ", resvPortId='" + resvPortId + '\'' +
                ", batchId='" + batchId + '\'' +
                ", batchSeq='" + batchSeq + '\'' +
                ", selfInspYn='" + selfInspYn + '\'' +
                ", selfInspInfoObjId='" + selfInspInfoObjId + '\'' +
                ", selfInspPanelCnt='" + selfInspPanelCnt + '\'' +
                '}';
    }
}
