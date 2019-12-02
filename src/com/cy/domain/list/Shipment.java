package com.cy.domain.list;

import java.util.List;

public class Shipment {
	public List<String> getBoxNumsArr() {
		return boxNumsArr;
	}
	public void setBoxNumsArr(List<String> boxNumsArr) {
		this.boxNumsArr = boxNumsArr;
	}
	private Integer pid;
	private String modelCode;//型号
	private String pnCode;//料号
	private String sn1;
	private String sn2;
	private String sn3;
	private String sn4;
	private String sn5;
	private String sn6;
	private String sn7;
	private String pcode;
	private	String color;//颜色
	private String process;//产品名
	private String porder;//订单号
	private String data1;
	private String data2;
	private String weight1;
	private String weight2;
	private Integer page;//页码
	private String mark;
	private Integer pageSize;
	private Integer limit;
	private String creatime;
	private String time;
	private String beginTime;
	private String endTime;
	private String pname;
	private String cid;
	private String hardwareVersion;
	private String softwareVersion;
	private String licenseTag;
	private String mainboardModel;
	private String region;
	private String projectNumber;
	private String batch;
	private String deliverTime;
	private String clientName;
	private String startSn;
	private String endSn;
	private String startBoxnum;
	private String endBoxnum;
	private String boxnumString;
	private List<String> boxNumsArr;
		private String shipmentBatch;
	
	public String getShipmentBatch() {
		return shipmentBatch;
	}
	public void setShipmentBatch(String shipmentBatch) {
		this.shipmentBatch = shipmentBatch;
	}
	public String getBoxnumString() {
		return boxnumString;
	}
	public void setBoxnumString(String boxnumString) {
		this.boxnumString = boxnumString;
	}
	public String getStartSn() {
		return startSn;
	}
	public void setStartSn(String startSn) {
		this.startSn = startSn;
	}
	public String getEndSn() {
		return endSn;
	}
	public void setEndSn(String endSn) {
		this.endSn = endSn;
	}
	public String getStartBoxnum() {
		return startBoxnum;
	}
	public void setStartBoxnum(String startBoxnum) {
		this.startBoxnum = startBoxnum;
	}
	public String getEndBoxnum() {
		return endBoxnum;
	}
	public void setEndBoxnum(String endBoxnum) {
		this.endBoxnum = endBoxnum;
	}
	private String deliver;
	private String deliverStartime;
	private String deliverEndtime;
	public String getDeliverStartime() {
		return deliverStartime;
	}
	public void setDeliverStartime(String deliverStartime) {
		this.deliverStartime = deliverStartime;
	}
	public String getDeliverEndtime() {
		return deliverEndtime;
	}
	public void setDeliverEndtime(String deliverEndtime) {
		this.deliverEndtime = deliverEndtime;
	}
	public String getDeliver() {
		return deliver;
	}
	public void setDeliver(String deliver) {
		this.deliver = deliver;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getModelCode() {
		return modelCode;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	public String getPnCode() {
		return pnCode;
	}
	public void setPnCode(String pnCode) {
		this.pnCode = pnCode;
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
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getPorder() {
		return porder;
	}
	public void setPorder(String porder) {
		this.porder = porder;
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
	public String getWeight1() {
		return weight1;
	}
	public void setWeight1(String weight1) {
		this.weight1 = weight1;
	}
	public String getWeight2() {
		return weight2;
	}
	public void setWeight2(String weight2) {
		this.weight2 = weight2;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
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
	public String getCreatime() {
		return creatime;
	}
	public void setCreatime(String creatime) {
		this.creatime = creatime;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getHardwareVersion() {
		return hardwareVersion;
	}
	public void setHardwareVersion(String hardwareVersion) {
		this.hardwareVersion = hardwareVersion;
	}
	public String getSoftwareVersion() {
		return softwareVersion;
	}
	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}
	public String getLicenseTag() {
		return licenseTag;
	}
	public void setLicenseTag(String licenseTag) {
		this.licenseTag = licenseTag;
	}
	public String getMainboardModel() {
		return mainboardModel;
	}
	public void setMainboardModel(String mainboardModel) {
		this.mainboardModel = mainboardModel;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getProjectNumber() {
		return projectNumber;
	}
	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getDeliverTime() {
		return deliverTime;
	}
	public void setDeliverTime(String deliverTime) {
		this.deliverTime = deliverTime;
	}
	@Override
	public String toString() {
		return "ProductCode [pid=" + pid + ", modelCode=" + modelCode + ", pnCode=" + pnCode + ", sn1=" + sn1 + ", sn2="
				+ sn2 + ", sn3=" + sn3 + ", sn4=" + sn4 + ", sn5=" + sn5 + ", sn6=" + sn6 + ", sn7=" + sn7 + ", pcode="
				+ pcode + ", color=" + color + ", process=" + process + ", porder=" + porder + ", data1=" + data1
				+ ", data2=" + data2 + ", weight1=" + weight1 + ", weight2=" + weight2 + ", page=" + page + ", mark="
				+ mark + ", pageSize=" + pageSize + ", limit=" + limit + ", creatime=" + creatime + ", time=" + time
				+ ", beginTime=" + beginTime + ", endTime=" + endTime + ", pname=" + pname + ", cid=" + cid
				+ ", hardwareVersion=" + hardwareVersion + ", softwareVersion=" + softwareVersion + ", licenseTag="
				+ licenseTag + ", mainboardModel=" + mainboardModel + ", region=" + region + ", projectNumber="
				+ projectNumber + ", batch=" + batch + ", deliverTime=" + deliverTime + ", clientName=" + clientName
				+ ", startSn=" + startSn + ", endSn=" + endSn + ", startBoxnum=" + startBoxnum + ", endBoxnum="
				+ endBoxnum + ", boxnumString=" + boxnumString + ", boxNumsArr=" + boxNumsArr 
				+ ", shipmentBatch=" + shipmentBatch + ", deliver=" + deliver + ", deliverStartime=" + deliverStartime
				+ ", deliverEndtime=" + deliverEndtime + "]";
	}
	

	
}
