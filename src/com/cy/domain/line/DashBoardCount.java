package com.cy.domain.line;

public class DashBoardCount {
	
	private String lineCode;//产线
	private String pname;//产品
	private String scesate;//工艺
	private String nowDate;//日期
	private String startime;//开始时间
	private String endtime;//结束时间
	private String input;//当前时间段的投入
	private String output;//当前时间段的产出
	private String  cumulativeInput;//累计投入
	private String cumulativeOutput;//累计产出
	
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
	public String getScesate() {
		return scesate;
	}
	public void setScesate(String scesate) {
		this.scesate = scesate;
	}
	public String getNowDate() {
		return nowDate;
	}
	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
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
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getCumulativeInput() {
		return cumulativeInput;
	}
	public void setCumulativeInput(String cumulativeInput) {
		this.cumulativeInput = cumulativeInput;
	}
	public String getCumulativeOutput() {
		return cumulativeOutput;
	}
	public void setCumulativeOutput(String cumulativeOutput) {
		this.cumulativeOutput = cumulativeOutput;
	}
	@Override
	public String toString() {
		return "DashBoardCount [lineCode=" + lineCode + ", pname=" + pname + ", scesate=" + scesate + ", nowDate="
				+ nowDate + ", startime=" + startime + ", endtime=" + endtime + ", input=" + input + ", output="
				+ output + ", cumulativeInput=" + cumulativeInput + ", cumulativeOutput=" + cumulativeOutput + "]";
	}
	
	
}
