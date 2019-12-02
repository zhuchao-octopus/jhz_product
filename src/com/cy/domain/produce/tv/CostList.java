package com.cy.domain.produce.tv;

public class CostList {
	private Integer id;
	private Integer bid;
	private Double demandNumber;
	private String pname;
	private String pn;
	private String costOrders;
	private String operator;
	private Integer materialNumber;
	private Double totalMoney;
	private Integer stuat;
	private Integer orderStuat;
	private String creamTime;
	private Integer pageSize;
	private Integer limit;
	private Integer page;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCostOrders() {
		return costOrders;
	}
	public void setCostOrders(String costOrders) {
		this.costOrders = costOrders;
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
	@Override
	public String toString() {
		return "CostList [id=" + id + ", bid=" + bid + ", demandNumber=" + demandNumber + ", pname=" + pname + ", pn="
				+ pn + ", costOrders=" + costOrders + ", operator=" + operator + ", materialNumber=" + materialNumber
				+ ", totalMoney=" + totalMoney + ", stuat=" + stuat + ", orderStuat=" + orderStuat + ", creamTime="
				+ creamTime + ", pageSize=" + pageSize + ", limit=" + limit + ", page=" + page + "]";
	}

}
