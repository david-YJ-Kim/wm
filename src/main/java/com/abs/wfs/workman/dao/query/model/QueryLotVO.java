package com.abs.wfs.workman.dao.query.model;

@SuppressWarnings("serial")
public class QueryLotVO implements java.io.Serializable {
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
	private String mtrlFaceCd;
	
	public String getLotId() {
		return lotId;
	}
	public void setLotId(String lotId) {
		this.lotId = lotId;
	}
	public String getCarrId() {
		return carrId;
	}
	public void setCarrId(String carrId) {
		this.carrId = carrId;
	}
	public String getHoldYn() {
		return holdYn;
	}
	public void setHoldYn(String holdYn) {
		this.holdYn = holdYn;
	}
	public String getProdDefId() {
		return prodDefId;
	}
	public void setProdDefId(String prodDefId) {
		this.prodDefId = prodDefId;
	}
	public String getProcDefId() {
		return procDefId;
	}
	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}
	public String getProcSgmtId() {
		return procSgmtId;
	}
	public void setProcSgmtId(String procSgmtId) {
		this.procSgmtId = procSgmtId;
	}
	public String getProcStatCd() {
		return procStatCd;
	}
	public void setProcStatCd(String procStatCd) {
		this.procStatCd = procStatCd;
	}
	public String getStatCd() {
		return statCd;
	}
	public void setStatCd(String statCd) {
		this.statCd = statCd;
	}
	public String getUseStatCd() {
		return useStatCd;
	}
	public void setUseStatCd(String useStatCd) {
		this.useStatCd = useStatCd;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getSubMtrlTyp() {
		return subMtrlTyp;
	}
	public void setSubMtrlTyp(String subMtrlTyp) {
		this.subMtrlTyp = subMtrlTyp;
	}
	public String getSubMtrlQty() {
		return subMtrlQty;
	}
	public void setSubMtrlQty(String subMtrlQty) {
		this.subMtrlQty = subMtrlQty;
	}
	public String getSgmtStatCd() {
		return sgmtStatCd;
	}
	public void setSgmtStatCd(String sgmtStatCd) {
		this.sgmtStatCd = sgmtStatCd;
	}
	public String getWorkStatCd() {
		return workStatCd;
	}
	public void setWorkStatCd(String workStatCd) {
		this.workStatCd = workStatCd;
	}
	public String getDtlWorkStatCd() {
		return dtlWorkStatCd;
	}
	public void setDtlWorkStatCd(String dtlWorkStatCd) {
		this.dtlWorkStatCd = dtlWorkStatCd;
	}
	public String getCrntEqpId() {
		return crntEqpId;
	}
	public void setCrntEqpId(String crntEqpId) {
		this.crntEqpId = crntEqpId;
	}
	public String getCrntPortId() {
		return crntPortId;
	}
	public void setCrntPortId(String crntPortId) {
		this.crntPortId = crntPortId;
	}
	public String getResvGrpId() {
		return resvGrpId;
	}
	public void setResvGrpId(String resvGrpId) {
		this.resvGrpId = resvGrpId;
	}
	public String getResvEqpId() {
		return resvEqpId;
	}
	public void setResvEqpId(String resvEqpId) {
		this.resvEqpId = resvEqpId;
	}
	public String getResvPortId() {
		return resvPortId;
	}
	public void setResvPortId(String resvPortId) {
		this.resvPortId = resvPortId;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getBatchSeq() {
		return batchSeq;
	}
	public void setBatchSeq(String batchSeq) {
		this.batchSeq = batchSeq;
	}
	public String getCarrTyp() {
		return carrTyp;
	}
	public void setCarrTyp(String carrTyp) {
		this.carrTyp = carrTyp;
	}
	public String getCarrClsId() {
		return carrClsId;
	}
	public void setCarrClsId(String carrClsId) {
		this.carrClsId = carrClsId;
	}
	public String getResvOutPortId() {
		return resvOutPortId;
	}
	public void setResvOutPortId(String resvOutPortId) {
		this.resvOutPortId = resvOutPortId;
	}
	public String getResvOutCarrId() {
		return resvOutCarrId;
	}
	public void setResvOutCarrId(String resvOutCarrId) {
		this.resvOutCarrId = resvOutCarrId;
	}
	public String getSelfInspYn() {
		return selfInspYn;
	}
	public void setSelfInspYn(String selfInspYn) {
		this.selfInspYn = selfInspYn;
	}
	public String getSelfInspInfoObjId() {
		return selfInspInfoObjId;
	}
	public void setSelfInspInfoObjId(String selfInspInfoObjId) {
		this.selfInspInfoObjId = selfInspInfoObjId;
	}
	public String getSelfInspPanelCnt() {
		return selfInspPanelCnt;
	}
	public void setSelfInspPanelCnt(String selfInspPanelCnt) {
		this.selfInspPanelCnt = selfInspPanelCnt;
	}
	public String getMtrlFaceCd() {
		return mtrlFaceCd;
	}
	public void setMtrlFaceCd(String mtrlFaceCd) {
		this.mtrlFaceCd = mtrlFaceCd;
	}
	
}
