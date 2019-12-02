package com.cy.domain.produce.tv;

import java.util.List;

public class OrderList {
	private Integer id;
	private Integer mid;
	private String orderNumber;
	private Integer numbers;
	private Integer outNumber;
	private Integer residue;
	private Double price;
	private Double money;
	private Integer stuat;
	private String productName;
	private String pn;
	private String createTime;
	private Integer storeState;
	private String materialName;//品名
	private List<ProductHistory> phList;
	private Integer page;
	
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
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
	private Integer pageSize;
	private Integer limit;
	public List<ProductHistory> getPhList() {
		return phList;
	}
	public void setPhList(List<ProductHistory> phList) {
		this.phList = phList;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getStoreState() {
		return storeState;
	}
	public void setStoreState(Integer storeState) {
		this.storeState = storeState;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Integer getNumbers() {
		return numbers;
	}
	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}
	public Integer getOutNumber() {
		return outNumber;
	}
	public void setOutNumber(Integer outNumber) {
		this.outNumber = outNumber;
	}
	public Integer getResidue() {
		return residue;
	}
	public void setResidue(Integer residue) {
		this.residue = residue;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getStuat() {
		return stuat;
	}
	public void setStuat(Integer stuat) {
		this.stuat = stuat;
	}
	@Override
	public String toString() {
		return "OrderList [id=" + id + ", mid=" + mid + ", orderNumber="
				+ orderNumber + ", numbers=" + numbers + ", outNumber="
				+ outNumber + ", residue=" + residue + ", price=" + price
				+ ", money=" + money + ", stuat=" + stuat + ", productName="
				+ productName + ", pn=" + pn + ", createTime=" + createTime
				+ ", storeState=" + storeState + ", materialName="
				+ materialName + ", phList=" + phList + ", page=" + page
				+ ", pageSize=" + pageSize + ", limit=" + limit + "]";
	}
	

}
