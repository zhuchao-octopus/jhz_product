package com.cy.domain.line;

import java.io.Serializable;

public class CheckStatus implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private String workerName;
	private String workerCode;
	private String nowDate;
	private Integer amLate;//上午迟到
	private Integer amLeave;//上午早退
	private Integer pmLate;//下午迟到
	private Integer pmLeave;//下午早退
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public String getWorkerCode() {
		return workerCode;
	}
	public void setWorkerCode(String workerCode) {
		this.workerCode = workerCode;
	}
	public String getNowDate() {
		return nowDate;
	}
	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}
	public Integer getAmLate() {
		return amLate;
	}
	public void setAmLate(Integer amLate) {
		this.amLate = amLate;
	}
	public Integer getAmLeave() {
		return amLeave;
	}
	public void setAmLeave(Integer amLeave) {
		this.amLeave = amLeave;
	}
	public Integer getPmLate() {
		return pmLate;
	}
	public void setPmLate(Integer pmLate) {
		this.pmLate = pmLate;
	}
	public Integer getPmLeave() {
		return pmLeave;
	}
	public void setPmLeave(Integer pmLeave) {
		this.pmLeave = pmLeave;
	}
	
	

}
