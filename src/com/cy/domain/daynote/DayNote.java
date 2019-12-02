package com.cy.domain.daynote;

import java.io.Serializable;

public class DayNote implements Serializable{

	private static final long serialVersionUID = 1L;
	private String workDate;
	private String lineCode;
	private String pname;
	private String workTimes;
	private String pcost;
	private Integer output;
	private Double income;
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getLineCode() {
		return lineCode;
	}
	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	public String getWorkTimes() {
		return workTimes;
	}
	public void setWorkTimes(String workTimes) {
		this.workTimes = workTimes;
	}

	public Integer getOutput() {
		return output;
	}
	public void setOutput(Integer output) {
		this.output = output;
	}
	public String getPcost() {
		return pcost;
	}
	public void setPcost(String pcost) {
		this.pcost = pcost;
	}
	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
	}
	@Override
	public String toString() {
		return "DayNote [workDate=" + workDate + ", lineCode=" + lineCode + ", pname=" + pname + ", workTimes="
				+ workTimes + ", pcost=" + pcost + ", output=" + output + ", income=" + income + "]";
	}
	
	
	

}
