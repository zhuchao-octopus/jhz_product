package com.cy.domain.check;
import java.io.Serializable;

public class CheckAfternoon implements Serializable {
	private static final long serialVersionUID = 1L;
	private String workName;//员工姓名
	private String workerCode;//员工工号
	private String checkDate;//日期
	private String week;//星期
	private String afterNoonUp;//下午上班时间
	private String afterNoonDown;//下午下班时间
	private String aupImg;//下午上班打卡照片
	private String adownImg;//下午下班打卡照片
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CheckAfternoon [workName=" + workName + ", workerCode=" + workerCode + ", checkDate=" + checkDate
				+ ", week=" + week + ", afterNoonUp=" + afterNoonUp + ", afterNoonDown=" + afterNoonDown + ", aupImg="
				+ aupImg + ", adownImg=" + adownImg + "]";
	}
	
	
}
