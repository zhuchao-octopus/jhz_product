package com.cy.domain.produce.tv;

public class DemandList {
	private Integer id;
	private Integer bid;
	private Integer mid;
	private String pname;
	private String pn;
	private Double demandNumber;
	private Double jprice;
	private Double dmoney;
	private String purchaseOrders;
	private String creamTime;
	private String mexplain;
	private String deliveryTime;
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
	public String getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getMexplain() {
		return mexplain;
	}
	public void setMexplain(String mexplain) {
		this.mexplain = mexplain;
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
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public Double getDemandNumber() {
		return demandNumber;
	}
	public void setDemandNumber(Double demandNumber) {
		this.demandNumber = demandNumber;
	}
	public Double getJprice() {
		return jprice;
	}
	public void setJprice(Double jprice) {
		this.jprice = jprice;
	}
	public Double getDmoney() {
		return dmoney;
	}
	public void setDmoney(Double dmoney) {
		this.dmoney = dmoney;
	}
	public String getPurchaseOrders() {
		return purchaseOrders;
	}
	public void setPurchaseOrders(String purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}
	public String getCreamTime() {
		return creamTime;
	}
	public void setCreamTime(String creamTime) {
		this.creamTime = creamTime;
	}
	@Override
	public String toString() {
		return "DemandList [id=" + id + ", bid=" + bid + ", mid=" + mid + ", pname=" + pname + ", pn=" + pn
				+ ", demandNumber=" + demandNumber + ", jprice=" + jprice + ", dmoney=" + dmoney + ", purchaseOrders="
				+ purchaseOrders + ", creamTime=" + creamTime + ", mexplain=" + mexplain + ", deliveryTime="
				+ deliveryTime + ", pageSize=" + pageSize + ", limit=" + limit + ", page=" + page + "]";
	}

}
