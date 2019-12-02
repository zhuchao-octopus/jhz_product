package com.cy.domain.produce.tv;

public class ReportPrice {//报价
	 
	private Integer id;
	private Integer bid;
	private Double demandNumber;
	private String pname;
	private String pn;
	private String purchaseOrders;
	private String operator;
	private Integer materialNumber;
	private Double totalMoney;
	private Integer stuat;
	private Integer orderStuat;
	private String creamTime;
	private String site;
	private String deliveryTime;
	private Integer sort;
	private Integer technology;
	private Integer pageSize;
	private Integer limit;
	private Integer page;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Double getDemandNumber() {
		return demandNumber;
	}
	public void setDemandNumber(Double demandNumber) {
		this.demandNumber = demandNumber;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPn() {
		return pn;
	}
	public void setPn(String pn) {
		this.pn = pn;
	}
	public String getPurchaseOrders() {
		return purchaseOrders;
	}
	public void setPurchaseOrders(String purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Integer getMaterialNumber() {
		return materialNumber;
	}
	public void setMaterialNumber(Integer materialNumber) {
		this.materialNumber = materialNumber;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Integer getStuat() {
		return stuat;
	}
	public void setStuat(Integer stuat) {
		this.stuat = stuat;
	}
	public Integer getOrderStuat() {
		return orderStuat;
	}
	public void setOrderStuat(Integer orderStuat) {
		this.orderStuat = orderStuat;
	}
	public String getCreamTime() {
		return creamTime;
	}
	public void setCreamTime(String creamTime) {
		this.creamTime = creamTime;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getTechnology() {
		return technology;
	}
	public void setTechnology(Integer technology) {
		this.technology = technology;
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
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "ReportPrice [id=" + id + ", bid=" + bid + ", demandNumber=" + demandNumber + ", pname=" + pname
				+ ", pn=" + pn + ", purchaseOrders=" + purchaseOrders + ", operator=" + operator + ", materialNumber="
				+ materialNumber + ", totalMoney=" + totalMoney + ", stuat=" + stuat + ", orderStuat=" + orderStuat
				+ ", creamTime=" + creamTime + ", site=" + site + ", deliveryTime=" + deliveryTime + ", sort=" + sort
				+ ", technology=" + technology + ", pageSize=" + pageSize + ", limit=" + limit + ", page=" + page + "]";
	}
	
	
	

}
