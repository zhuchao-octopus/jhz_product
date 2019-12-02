package com.cy.domain.line;

import java.io.Serializable;
/*
 * 工位
 */
public class WorkLocation implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer sid;
	private String sjob;
	private String locationCode;
	private ProductWorker worker;
	private WorkLine line;
	private WorkStationCode wsCode;
	private Integer locationState;
	private String nowTime;
	private Integer pageSize;
	private Integer limit;
	private String lineCode;
	private Integer crossStatus;//是否可以过站
	public Integer getCrossStatus() {
		return crossStatus;
	}
	public void setCrossStatus(Integer crossStatus) {
		this.crossStatus = crossStatus;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getSjob() {
		return sjob;
	}
	public void setSjob(String sjob) {
		this.sjob = sjob;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public ProductWorker getWorker() {
		return worker;
	}
	public void setWorker(ProductWorker worker) {
		this.worker = worker;
	}
	public WorkLine getLine() {
		return line;
	}
	public void setLine(WorkLine line) {
		this.line = line;
	}
	public WorkStationCode getWsCode() {
		return wsCode;
	}
	public void setWsCode(WorkStationCode wsCode) {
		this.wsCode = wsCode;
	}
	public Integer getLocationState() {
		return locationState;
	}
	public void setLocationState(Integer locationState) {
		this.locationState = locationState;
	}
	public String getNowTime() {
		return nowTime;
	}
	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
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
	public String getLineCode() {
		return lineCode;
	}
	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "WorkLocation [sid=" + sid + ", sjob=" + sjob
				+ ", locationCode=" + locationCode + ", worker=" + worker
				+ ", line=" + line + ", wsCode=" + wsCode + ", locationState="
				+ locationState + ", nowTime=" + nowTime + ", pageSize="
				+ pageSize + ", limit=" + limit + ", lineCode=" + lineCode
				+ ", crossStatus=" + crossStatus + "]";
	}
	
	
	
	
	

}
