package com.abs.wfs.workman.dao.query.model;


public class WnWipStat {
	
	// Table Column
	private String objId;
	private String siteId;
	private String lotId;
	private String carrId;
	private String carrLctnNm;
	private String workStatCd;
	private String dtlWorkStatCd;
	private String mdfyUserId;
	private String mdfyDt;
	private String crtUserId;
	private String crtDt;
	private String cstmEvntNm;
	private String evntNm;
	private String fnlEvntDt;
	private String prevCstmEvntNm;
	private String prevEvntNm;
	private String rsnCd;
	private String tid;
	private String trnsCm;
	private String useStatCd;
	private String crntEqpId;
	private String crntPortId;
	private String batchId;
	private String resvEqpId;
	private String resvPortId;
	private String resvOutPortId;
	private String resvOutCarrId;
	private String eqpChkYn;
	private String rcpChkYn;
	private String trackInCnfmYn;
	private String resvGrpId;
	private String batchSeq;
	private String selfInspYn;
	private String selfInspInfoObjId;
	private Integer selfInspPanelCnt;
	private String smplLotYn;
	private String smplWorkTyp;
	private Integer smplQty;
	private String moveStatCd;
	private String manlLdngYn;
	
	// Where Condition
	private String pObjId;
	private String pSiteId;
	private String pLotId;
	private String pCarrId;
	private String pWorkStatCd;
	private String pDtlWorkStatCd;
	private String pUseStatCd;
	
	
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
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
	public String getCarrLctnNm() {
		return carrLctnNm;
	}
	public void setCarrLctnNm(String carrLctnNm) {
		this.carrLctnNm = carrLctnNm;
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
	public String getMdfyUserId() {
		return mdfyUserId;
	}
	public void setMdfyUserId(String mdfyUserId) {
		this.mdfyUserId = mdfyUserId;
	}
	public String getMdfyDt() {
		return mdfyDt;
	}
	public void setMdfyDt(String mdfyDt) {
		this.mdfyDt = mdfyDt;
	}
	public String getCrtUserId() {
		return crtUserId;
	}
	public void setCrtUserId(String crtUserId) {
		this.crtUserId = crtUserId;
	}
	public String getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}
	public String getCstmEvntNm() {
		return cstmEvntNm;
	}
	public void setCstmEvntNm(String cstmEvntNm) {
		this.cstmEvntNm = cstmEvntNm;
	}
	public String getEvntNm() {
		return evntNm;
	}
	public void setEvntNm(String evntNm) {
		this.evntNm = evntNm;
	}
	public String getFnlEvntDt() {
		return fnlEvntDt;
	}
	public void setFnlEvntDt(String fnlEvntDt) {
		this.fnlEvntDt = fnlEvntDt;
	}
	public String getPrevCstmEvntNm() {
		return prevCstmEvntNm;
	}
	public void setPrevCstmEvntNm(String prevCstmEvntNm) {
		this.prevCstmEvntNm = prevCstmEvntNm;
	}
	public String getPrevEvntNm() {
		return prevEvntNm;
	}
	public void setPrevEvntNm(String prevEvntNm) {
		this.prevEvntNm = prevEvntNm;
	}
	public String getRsnCd() {
		return rsnCd;
	}
	public void setRsnCd(String rsnCd) {
		this.rsnCd = rsnCd;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getTrnsCm() {
		return trnsCm;
	}
	public void setTrnsCm(String trnsCm) {
		this.trnsCm = trnsCm;
	}
	public String getUseStatCd() {
		return useStatCd;
	}
	public void setUseStatCd(String useStatCd) {
		this.useStatCd = useStatCd;
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
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
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
	public String getEqpChkYn() {
		return eqpChkYn;
	}
	public void setEqpChkYn(String eqpChkYn) {
		this.eqpChkYn = eqpChkYn;
	}
	public String getRcpChkYn() {
		return rcpChkYn;
	}
	public void setRcpChkYn(String rcpChkYn) {
		this.rcpChkYn = rcpChkYn;
	}
	public String getTrackInCnfmYn() {
		return trackInCnfmYn;
	}
	public void setTrackInCnfmYn(String trackInCnfmYn) {
		this.trackInCnfmYn = trackInCnfmYn;
	}
	public String getResvGrpId() {
		return resvGrpId;
	}
	public void setResvGrpId(String resvGrpId) {
		this.resvGrpId = resvGrpId;
	}
	public String getBatchSeq() {
		return batchSeq;
	}
	public void setBatchSeq(String batchSeq) {
		this.batchSeq = batchSeq;
	}
	public String getpObjId() {
		return pObjId;
	}
	public void setpObjId(String pObjId) {
		this.pObjId = pObjId;
	}
	public String getpSiteId() {
		return pSiteId;
	}
	public void setpSiteId(String pSiteId) {
		this.pSiteId = pSiteId;
	}
	public String getpLotId() {
		return pLotId;
	}
	public void setpLotId(String pLotId) {
		this.pLotId = pLotId;
	}
	public String getpCarrId() {
		return pCarrId;
	}
	public void setpCarrId(String pCarrId) {
		this.pCarrId = pCarrId;
	}
	public String getpWorkStatCd() {
		return pWorkStatCd;
	}
	public void setpWorkStatCd(String pWorkStatCd) {
		this.pWorkStatCd = pWorkStatCd;
	}
	public String getpDtlWorkStatCd() {
		return pDtlWorkStatCd;
	}
	public void setpDtlWorkStatCd(String pDtlWorkStatCd) {
		this.pDtlWorkStatCd = pDtlWorkStatCd;
	}
	public String getpUseStatCd() {
		return pUseStatCd;
	}
	public void setpUseStatCd(String pUseStatCd) {
		this.pUseStatCd = pUseStatCd;
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
	public String getSmplLotYn() {
		return smplLotYn;
	}
	public void setSmplLotYn(String smplLotYn) {
		this.smplLotYn = smplLotYn;
	}
	public String getSmplWorkTyp() {
		return smplWorkTyp;
	}
	public void setSmplWorkTyp(String smplWorkTyp) {
		this.smplWorkTyp = smplWorkTyp;
	}
	public Integer getSelfInspPanelCnt() {
		return selfInspPanelCnt;
	}
	public void setSelfInspPanelCnt(Integer selfInspPanelCnt) {
		this.selfInspPanelCnt = selfInspPanelCnt;
	}
	public Integer getSmplQty() {
		return smplQty;
	}
	public void setSmplQty(Integer smplQty) {
		this.smplQty = smplQty;
	}
	public String getMoveStatCd() {
		return moveStatCd;
	}
	public void setMoveStatCd(String moveStatCd) {
		this.moveStatCd = moveStatCd;
	}
	public String getManlLdngYn() {
		return manlLdngYn;
	}
	public void setManlLdngYn(String manlLdngYn) {
		this.manlLdngYn = manlLdngYn;
	}
	
}
