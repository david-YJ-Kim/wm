package com.abs.wfs.workman.dao.query.model;

@SuppressWarnings("serial")
public class QueryPortVO implements java.io.Serializable {
	private String portId;
	private String carrTyp;
	private String portTyp;
	private String acesModeCd;
	private String ctrlModeCd;
	private String statCd;
	private String trsfStatCd;
	private String carrId;
	public String getPortId() {
		return portId;
	}
	public void setPortId(String portId) {
		this.portId = portId;
	}
	public String getCarrTyp() {
		return carrTyp;
	}
	public void setCarrTyp(String carrTyp) {
		this.carrTyp = carrTyp;
	}
	public String getPortTyp() {
		return portTyp;
	}
	public void setPortTyp(String portTyp) {
		this.portTyp = portTyp;
	}
	public String getAcesModeCd() {
		return acesModeCd;
	}
	public void setAcesModeCd(String acesModeCd) {
		this.acesModeCd = acesModeCd;
	}
	public String getCtrlModeCd() {
		return ctrlModeCd;
	}
	public void setCtrlModeCd(String ctrlModeCd) {
		this.ctrlModeCd = ctrlModeCd;
	}
	public String getStatCd() {
		return statCd;
	}
	public void setStatCd(String statCd) {
		this.statCd = statCd;
	}
	public String getTrsfStatCd() {
		return trsfStatCd;
	}
	public void setTrsfStatCd(String trsfStatCd) {
		this.trsfStatCd = trsfStatCd;
	}
	public String getCarrId() {
		return carrId;
	}
	public void setCarrId(String carrId) {
		this.carrId = carrId;
	}
	@Override
	public String toString() {
		return "QueryPortVO [portId=" + portId + ", carrTyp=" + carrTyp + ", portTyp=" + portTyp + ", acesModeCd="
				+ acesModeCd + ", ctrlModeCd=" + ctrlModeCd + ", statCd=" + statCd + ", trsfStatCd=" + trsfStatCd
				+ ", carrId=" + carrId + "]";
	}
	
	
}
