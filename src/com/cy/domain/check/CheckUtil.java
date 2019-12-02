package com.cy.domain.check;

import java.io.Serializable;

public class CheckUtil implements Serializable{

	private static final long serialVersionUID = 1L;
	private String tid;
	private String workerCode;
	private String workerName;
	private String locationCode;
	private String checkTime;
	private String checkDate;
	private Integer checkStatus;
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getWorkerCode() {
		return workerCode;
	}
	public void setWorkerCode(String workerCode) {
		this.workerCode = workerCode;
	}
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public Integer getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	@Override
	public String toString() {
		return "CheckUtil [tid=" + tid + ", workerCode=" + workerCode + ", workerName=" + workerName + ", locationCode="
				+ locationCode + ", checkTime=" + checkTime + ", checkDate=" + checkDate + ", checkStatus="
				+ checkStatus + "]";
	}
	
}
