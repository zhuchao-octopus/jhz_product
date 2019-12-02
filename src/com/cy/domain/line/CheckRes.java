package com.cy.domain.line;

public class CheckRes {
	private static final long serialVersionUID = 1L;
	private Integer rid;
	private String workerCode;// 工号
	private String workName;// 姓名
	private String checkDate;// 日期
	private String beforeNoonUp;// 上午上班时间
	private String beforeNoonDown;// 上午下班时间
	private String afterNoonUp;// 下午上班时间
	private String afterNoonDown;// 下午下班时间
	private String overWorkUp;// 加班上班时间
	private String overWorkDown;// 加班下班时间
	private String writetime;// 记录时间
	private String bufferTime;// 缓冲时间
	private String workTime;// 工作工时
	private String overWorkTime;// 加班工时
	private String allWorkTime;// 总工时
	private Integer pageSize;
	private Integer limit;
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getWorkerCode() {
		return workerCode;
	}
	public void setWorkerCode(String workerCode) {
		this.workerCode = workerCode;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
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
	public String getWritetime() {
		return writetime;
	}
	public void setWritetime(String writetime) {
		this.writetime = writetime;
	}
	public String getBufferTime() {
		return bufferTime;
	}
	public void setBufferTime(String bufferTime) {
		this.bufferTime = bufferTime;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public String getOverWorkTime() {
		return overWorkTime;
	}
	public void setOverWorkTime(String overWorkTime) {
		this.overWorkTime = overWorkTime;
	}
	public String getAllWorkTime() {
		return allWorkTime;
	}
	public void setAllWorkTime(String allWorkTime) {
		this.allWorkTime = allWorkTime;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CheckRes [rid=" + rid + ", workerCode=" + workerCode
				+ ", workName=" + workName + ", checkDate=" + checkDate
				+ ", beforeNoonUp=" + beforeNoonUp + ", beforeNoonDown="
				+ beforeNoonDown + ", afterNoonUp=" + afterNoonUp
				+ ", afterNoonDown=" + afterNoonDown + ", overWorkUp="
				+ overWorkUp + ", overWorkDown=" + overWorkDown
				+ ", writetime=" + writetime + ", bufferTime=" + bufferTime
				+ ", workTime=" + workTime + ", overWorkTime=" + overWorkTime
				+ ", allWorkTime=" + allWorkTime + ", pageSize=" + pageSize
				+ ", limit=" + limit + "]";
	}
	
}
