package com.cy.domain.line;

import java.io.Serializable;

public class OverTime implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer appId;
	private ProductWorker worker;
	private String startime;
	private String endtime;
	private WorkLine line;
	private String creatime;
	private Integer workPay;
	private String ovtnumber;

	public String getOvtnumber() {
		return ovtnumber;
	}

	public void setOvtnumber(String ovtnumber) {
		this.ovtnumber = ovtnumber;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public ProductWorker getWorker() {
		return worker;
	}

	public void setWorker(ProductWorker worker) {
		this.worker = worker;
	}

	public String getStartime() {
		return startime;
	}

	public void setStartime(String startime) {
		this.startime = startime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public WorkLine getLine() {
		return line;
	}

	public void setLine(WorkLine line) {
		this.line = line;
	}

	public String getCreatime() {
		return creatime;
	}

	public void setCreatime(String creatime) {
		this.creatime = creatime;
	}

	public Integer getWorkPay() {
		return workPay;
	}

	public void setWorkPay(Integer workPay) {
		this.workPay = workPay;
	}

	@Override
	public String toString() {
		return "OverTime [appId=" + appId + ", worker=" + worker + ", startime=" + startime + ", endtime=" + endtime
				+ ", line=" + line + ", creatime=" + creatime + ", workPay=" + workPay + ", ovtnumber=" + ovtnumber
				+ "]";
	}
    
	
}
