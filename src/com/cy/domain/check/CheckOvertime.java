package com.cy.domain.check;

import java.io.Serializable;

public class CheckOvertime implements Serializable {
	private static final long serialVersionUID = 1L;
	private String workName;//员工姓名
	private String workerCode;//员工工号
	private String checkDate;//日期
	private String week;//星期
	private String overWorkUp;//加班签到时间
	private String overWorkDown;//加班签退时间
	private String oupImg;//加班上班打卡照片
	private String odownImg;//加班下班打卡照片
	
	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
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
		return "CheckOvertime [workName=" + workName + ", workerCode=" + workerCode + ", checkDate=" + checkDate
				+ ", week=" + week + ", overWorkUp=" + overWorkUp + ", overWorkDown=" + overWorkDown + ", oupImg="
				+ oupImg + ", odownImg=" + odownImg + "]";
	}
	

}
