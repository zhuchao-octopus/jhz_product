package com.cy.domain.check;

import java.io.Serializable;

public class CheckForenoon implements Serializable {
	private static final long serialVersionUID = 1L;
	private String workerName;//员工姓名
	private String workerCode;//员工工号
	private String checkDate;//日期
	private String week;//星期
	private String beforeNoonUp;//上午上班时间
	private String beforeNoonDown;//上午下班时间
	private String fupImg;//上午上班打卡照片
	private String fdownImg;//上午下班打卡照片
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CheckForenoon [workerName=" + workerName + ", workerCode=" + workerCode + ", checkDate=" + checkDate
				+ ", week=" + week + ", beforeNoonUp=" + beforeNoonUp + ", beforeNoonDown=" + beforeNoonDown
				+ ", fupImg=" + fupImg + ", fdownImg=" + fdownImg + "]";
	}
	
	

}
