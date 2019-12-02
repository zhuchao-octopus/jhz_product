package com.cy.domain.list;

import java.io.Serializable;

public class WorkStationCode implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	Integer mid;
	private String wsNumber;//工作站编号
	private String wsFunction;//功能
	private String pname;//产品名
	private String boxNumCount;//装箱数
	private String sn1;//操作码1
	private String sn2;//操作码2
	private String sn3;//操作码3
	private String sn4;//操作码4
	private String sn5;//操作码5
	private String sn6;//操作码6
	private String sn7;//操作码7
	private String data1;//操作码8
	private String data2;//操作码9
	private String missStation;//是否过站
	private Integer pageSize;
	private Integer limit;
	private Integer pid;
	private String snName1;
	private String snName2;
	private String snName3;
	private String snName4;
	private String snName5;
	private String snName6;
	private String snName7;
	private String dataName1;
	private String dataName2;
	private String inputStation;
	private String outputStation;
	private String employ;
	public String getEmploy() {
		return employ;
	}
	public void setEmploy(String employ) {
		this.employ = employ;
	}
	public String getInputStation() {
		return inputStation;
	}
	public void setInputStation(String inputStation) {
		this.inputStation = inputStation;
	}
	public String getOutputStation() {
		return outputStation;
	}
	public void setOutputStation(String outputStation) {
		this.outputStation = outputStation;
	}
	public String getSnName1() {
		return snName1;
	}
	public void setSnName1(String snName1) {
		this.snName1 = snName1;
	}
	public String getSnName2() {
		return snName2;
	}
	public void setSnName2(String snName2) {
		this.snName2 = snName2;
	}
	public String getSnName3() {
		return snName3;
	}
	public void setSnName3(String snName3) {
		this.snName3 = snName3;
	}
	public String getSnName4() {
		return snName4;
	}
	public void setSnName4(String snName4) {
		this.snName4 = snName4;
	}
	public String getSnName5() {
		return snName5;
	}
	public void setSnName5(String snName5) {
		this.snName5 = snName5;
	}
	public String getSnName6() {
		return snName6;
	}
	public void setSnName6(String snName6) {
		this.snName6 = snName6;
	}
	public String getSnName7() {
		return snName7;
	}
	public void setSnName7(String snName7) {
		this.snName7 = snName7;
	}
	public String getDataName1() {
		return dataName1;
	}
	public void setDataName1(String dataName1) {
		this.dataName1 = dataName1;
	}
	public String getDataName2() {
		return dataName2;
	}
	public void setDataName2(String dataName2) {
		this.dataName2 = dataName2;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
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
	public String getMissStation() {
		return missStation;
	}
	public void setMissStation(String missStation) {
		this.missStation = missStation;
	}
	
	public String getWsNumber() {
		return wsNumber;
	}
	public void setWsNumber(String wsNumber) {
		this.wsNumber = wsNumber;
	}
	public String getWsFunction() {
		return wsFunction;
	}
	public void setWsFunction(String wsFunction) {
		this.wsFunction = wsFunction;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getBoxNumCount() {
		return boxNumCount;
	}
	public void setBoxNumCount(String boxNumCount) {
		this.boxNumCount = boxNumCount;
	}
	public String getSn1() {
		return sn1;
	}
	public void setSn1(String sn1) {
		this.sn1 = sn1;
	}
	public String getSn2() {
		return sn2;
	}
	public void setSn2(String sn2) {
		this.sn2 = sn2;
	}
	public String getSn3() {
		return sn3;
	}
	public void setSn3(String sn3) {
		this.sn3 = sn3;
	}
	public String getSn4() {
		return sn4;
	}
	public void setSn4(String sn4) {
		this.sn4 = sn4;
	}
	public String getSn5() {
		return sn5;
	}
	public void setSn5(String sn5) {
		this.sn5 = sn5;
	}
	public String getSn6() {
		return sn6;
	}
	public void setSn6(String sn6) {
		this.sn6 = sn6;
	}
	public String getSn7() {
		return sn7;
	}
	public void setSn7(String sn7) {
		this.sn7 = sn7;
	}
	public String getData1() {
		return data1;
	}
	public void setData1(String data1) {
		this.data1 = data1;
	}
	public String getData2() {
		return data2;
	}
	public void setData2(String data2) {
		this.data2 = data2;
	}
	@Override
	public String toString() {
		return "WorkStationCode [mid=" + mid + ", wsNumber=" + wsNumber + ", wsFunction=" + wsFunction + ", pname="
				+ pname + ", boxNumCount=" + boxNumCount + ", sn1=" + sn1 + ", sn2=" + sn2 + ", sn3=" + sn3 + ", sn4="
				+ sn4 + ", sn5=" + sn5 + ", sn6=" + sn6 + ", sn7=" + sn7 + ", data1=" + data1 + ", data2=" + data2
				+ ", missStation=" + missStation + ", pageSize=" + pageSize + ", limit=" + limit + ", pid=" + pid
				+ ", snName1=" + snName1 + ", snName2=" + snName2 + ", snName3=" + snName3 + ", snName4=" + snName4
				+ ", snName5=" + snName5 + ", snName6=" + snName6 + ", snName7=" + snName7 + ", dataName1=" + dataName1
				+ ", dataName2=" + dataName2 + ", inputStation=" + inputStation + ", outputStation=" + outputStation
				+ ", employ=" + employ + "]";
	}
	
}
