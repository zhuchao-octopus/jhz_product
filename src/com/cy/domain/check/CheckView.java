package com.cy.domain.check;

import java.io.Serializable;

public class CheckView implements Serializable {
	private static final long serialVersionUID = 1L;
	private String workerCode;
	private String workerName;
	private String checkDate;
	private String week;
	private String beforeNoonUp;
	private String beforeNoonDown;
	private String fupImg;
	private String fdownImg;
	private String afterNoonUp;
	private String afterNoonDown;
	private String aupImg;
	private String adownImg;
	private String overWorkUp;
	private String overWorkDown;
	private String oupImg;
	private String odownImg;
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
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getBeforeNoonUp() {
		return beforeNoonUp;
	}
	public void setBeforeNoonUp(String beforeNoonUp) {
		this.beforeNoonUp = beforeNoonUp;
	}
	public String getBeforeNoonDown() {
		return beforeNoonDown;
	}
	public void setBeforeNoonDown(String beforeNoonDown) {
		this.beforeNoonDown = beforeNoonDown;
	}
	public String getFupImg() {
		return fupImg;
	}
	public void setFupImg(String fupImg) {
		this.fupImg = fupImg;
	}
	public String getFdownImg() {
		return fdownImg;
	}
	public void setFdownImg(String fdownImg) {
		this.fdownImg = fdownImg;
	}
	public String getAfterNoonUp() {
		return afterNoonUp;
	}
	public void setAfterNoonUp(String afterNoonUp) {
		this.afterNoonUp = afterNoonUp;
	}
	public String getAfterNoonDown() {
		return afterNoonDown;
	}
	public void setAfterNoonDown(String afterNoonDown) {
		this.afterNoonDown = afterNoonDown;
	}
	public String getAupImg() {
		return aupImg;
	}
	public void setAupImg(String aupImg) {
		this.aupImg = aupImg;
	}
	public String getAdownImg() {
		return adownImg;
	}
	public void setAdownImg(String adownImg) {
		this.adownImg = adownImg;
	}
	public String getOverWorkUp() {
		return overWorkUp;
	}
	public void setOverWorkUp(String overWorkUp) {
		this.overWorkUp = overWorkUp;
	}
	public String getOverWorkDown() {
		return overWorkDown;
	}
	public void setOverWorkDown(String overWorkDown) {
		this.overWorkDown = overWorkDown;
	}
	public String getOupImg() {
		return oupImg;
	}
	public void setOupImg(String oupImg) {
		this.oupImg = oupImg;
	}
	public String getOdownImg() {
		return odownImg;
	}
	public void setOdownImg(String odownImg) {
		this.odownImg = odownImg;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CheckView [workerCode=" + workerCode + ", workerName=" + workerName + ", checkDate=" + checkDate
				+ ", week=" + week + ", beforeNoonUp=" + beforeNoonUp + ", beforeNoonDown=" + beforeNoonDown
				+ ", fupImg=" + fupImg + ", fdownImg=" + fdownImg + ", afterNoonUp=" + afterNoonUp + ", afterNoonDown="
				+ afterNoonDown + ", aupImg=" + aupImg + ", adownImg=" + adownImg + ", overWorkUp=" + overWorkUp
				+ ", overWorkDown=" + overWorkDown + ", oupImg=" + oupImg + ", odownImg=" + odownImg + "]";
	}
	
	

}
