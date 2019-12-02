package com.cy.domain.daynote;

public class LineRecord {
	
	private String lineCode;
	private Integer count;
	private String workTimes;
	private String iutput;//投入
	private String Output;//产出
	private String income;
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getLineCode() {
		return lineCode;
	}
	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getWorkTimes() {
		return workTimes;
	}
	public void setWorkTimes(String workTimes) {
		this.workTimes = workTimes;
	}
	public String getIutput() {
		return iutput;
	}
	public void setIutput(String iutput) {
		this.iutput = iutput;
	}
	public String getOutput() {
		return Output;
	}
	public void setOutput(String output) {
		Output = output;
	}
	@Override
	public String toString() {
		return "LineRecord [lineCode=" + lineCode + ", count=" + count + ", workTimes=" + workTimes + ", iutput="
				+ iutput + ", Output=" + Output + "]";
	}
    
	
	
}
