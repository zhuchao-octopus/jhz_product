package com.cy.domain.produce.tv;

public class RequestList {
	private Integer id;
	private String serialNumber;
	private String qualityRequest;
	private String deliveryRequest;
	private String clearingform;
	private String taxDemands;
	private String informationChange;
	private String dissResolution;
	private Integer page;
	private Integer pageSize;
	private Integer limit;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getQualityRequest() {
		return qualityRequest;
	}
	public void setQualityRequest(String qualityRequest) {
		this.qualityRequest = qualityRequest;
	}
	public String getDeliveryRequest() {
		return deliveryRequest;
	}
	public void setDeliveryRequest(String deliveryRequest) {
		this.deliveryRequest = deliveryRequest;
	}
	public String getClearingform() {
		return clearingform;
	}
	public void setClearingform(String clearingform) {
		this.clearingform = clearingform;
	}
	public String getTaxDemands() {
		return taxDemands;
	}
	public void setTaxDemands(String taxDemands) {
		this.taxDemands = taxDemands;
	}
	public String getInformationChange() {
		return informationChange;
	}
	public void setInformationChange(String informationChange) {
		this.informationChange = informationChange;
	}
	public String getDissResolution() {
		return dissResolution;
	}
	public void setDissResolution(String dissResolution) {
		this.dissResolution = dissResolution;
	}
	@Override
	public String toString() {
		return "RequestList [id=" + id + ", serialNumber=" + serialNumber + ", qualityRequest=" + qualityRequest
				+ ", deliveryRequest=" + deliveryRequest + ", clearingform=" + clearingform + ", taxDemands="
				+ taxDemands + ", informationChange=" + informationChange + ", dissResolution=" + dissResolution
				+ ", page=" + page + ", pageSize=" + pageSize + ", limit=" + limit + "]";
	}

}
