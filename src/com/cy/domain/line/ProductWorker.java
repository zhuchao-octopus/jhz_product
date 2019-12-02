package com.cy.domain.line;

import java.io.Serializable;
import java.util.List;

import com.cy.domain.check.CheckShift;
import com.cy.domain.check.NewCheck;
/*
 * 作业员
 */
public class ProductWorker implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer wid;
	private String workerCode;//工号
	private String workerName;//姓名
	private Double workerPrice;
	private Double workerOvertimePay;
	private Double workerWeekenPay;
	private Double workerHolidayPay;
	private String fingerCode;
	private String workPost;//职务
	private WorkLocation location;//工位(内包含产线)
	private Integer estate;
	private String objThing;
	private String tel;//电话
	private String abi;//技能
	private Integer stateTwo;
	private Integer pageNumber;
	private String fromValue;//工种的value
	private List<NewCheck> newCheckList;
	private Integer pageSize;
	private Integer limit;
	private String lineCode;
	private String workerPhoto;
	private String shiftType;
	private String hiredate;

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public String getLineCode() {
		return lineCode;
	}

	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
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

	public String getWorkPost() {
		return workPost;
	}

	public void setWorkPost(String workPost) {
		this.workPost = workPost;
	}

	
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAbi() {
		return abi;
	}

	public void setAbi(String abi) {
		this.abi = abi;
	}



	public WorkLocation getLocation() {
		return location;
	}

	public void setLocation(WorkLocation location) {
		this.location = location;
	}

	public Integer getWid() {
		return wid;
	}

	public void setWid(Integer wid) {
		this.wid = wid;
	}

	public String getWorkerCode() {
		return workerCode;
	}

	public void setWorkerCode(String workerCode) {
		this.workerCode = workerCode;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public String getFingerCode() {
		return fingerCode;
	}

	public void setFingerCode(String fingerCode) {
		this.fingerCode = fingerCode;
	}

	public Double getWorkerPrice() {
		return workerPrice;
	}

	public void setWorkerPrice(Double workerPrice) {
		this.workerPrice = workerPrice;
	}
	
	public Double getWorkerOvertimePay() {
		return workerOvertimePay;
	}
	
	public void setWorkerOvertimePay(Double workerOvertimePay) {
		this.workerOvertimePay = workerOvertimePay;
	}
	
	public Double getWorkerWeekenPay() {
		return workerWeekenPay;
	}
	
	public void setWorkerWeekenPay(Double workerWeekenPay) {
		this.workerWeekenPay = workerWeekenPay;
	}
	
	public Double getWorkerHolidayPay() {
		return workerHolidayPay;
	}
	
	public void setWorkerHolidayPay(Double workerHolidayPay) {
		this.workerHolidayPay = workerHolidayPay;
	}
	public String getObjThing() {
		return objThing;
	}

	public void setObjThing(String objThing) {
		this.objThing = objThing;
	}
	
	public ProductWorker(Integer wid, String workerCode, String workerName, Double workerPrice, String fingerCode) {
		super();
		this.wid = wid;
		this.workerCode = workerCode;
		this.workerName = workerName;
		this.workerPrice = workerPrice;
		this.fingerCode = fingerCode;
	}

	public ProductWorker(Integer wid, String workerCode, String workerName, Double workerPrice) {
		super();
		this.wid = wid;
		this.workerCode = workerCode;
		this.workerName = workerName;
		this.workerPrice = workerPrice;
	}

	public ProductWorker() {
		super();
	}

	
	public Integer getEstate() {
		return estate;
	}

	public void setEstate(Integer estate) {
		this.estate = estate;
	}

	public String getFromValue() {
		return fromValue;
	}

	public void setFromValue(String fromValue) {
		this.fromValue = fromValue;
	}

	public Integer getStateTwo() {
		return stateTwo;
	}

	public void setStateTwo(Integer stateTwo) {
		this.stateTwo = stateTwo;
	}

	

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}


	public List<NewCheck> getNewCheckList() {
		return newCheckList;
	}

	public void setNewCheckList(List<NewCheck> newCheckList) {
		this.newCheckList = newCheckList;
	}
	


	public String getShiftType() {
		return shiftType;
	}

	public void setShiftType(String shiftType) {
		this.shiftType = shiftType;
	}

	public String getWorkerPhoto() {
		return workerPhoto;
	}

	public void setWorkerPhoto(String workerPhoto) {
		this.workerPhoto = workerPhoto;
	}

	@Override
	public String toString() {
		return "ProductWorker [wid=" + wid + ", workerCode=" + workerCode + ", workerName=" + workerName
				+ ", workerPrice=" + workerPrice + ", workerOvertimePay=" + workerOvertimePay + ", workerWeekenPay="
				+ workerWeekenPay + ", workerHolidayPay=" + workerHolidayPay + ", fingerCode=" + fingerCode
				+ ", workPost=" + workPost + ", location=" + location + ", estate=" + estate + ", objThing=" + objThing
				+ ", tel=" + tel + ", abi=" + abi + ", stateTwo=" + stateTwo + ", pageNumber=" + pageNumber
				+ ", fromValue=" + fromValue + ", newCheckList=" + newCheckList + ", pageSize=" + pageSize + ", limit="
				+ limit + ", lineCode=" + lineCode + ", workerPhoto=" + workerPhoto + ", shiftType=" + shiftType
				+ ", hiredate=" + hiredate + "]";
	}
	
	


	

	



}
