package com.cy.domain.produce.tv;

public class PriceList {
	private Integer id;
	private Integer mid;
	private String pname;
	private String pn;
	private String mexplain;
	private Double jprice;
	private String supplier;
	private String telphone;
	private String email;
	private String creamTime;
	private Double minNumbers;
	private Double processCost;
	private Double sampleCost;
	private Integer limit;
	private Integer pageSize;
	private Integer page;

	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Double getProcessCost() {
		return processCost;
	}
	public void setProcessCost(Double processCost) {
		this.processCost = processCost;
	}
	public Double getSampleCost() {
		return sampleCost;
	}
	public void setSampleCost(Double sampleCost) {
		this.sampleCost = sampleCost;
	}
	public Double getMinNumbers() {
		return minNumbers;
	}
	public void setMinNumbers(Double minNumbers) {
		this.minNumbers = minNumbers;
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
	public String getMexplain() {
		return mexplain;
	}
	public void setMexplain(String mexplain) {
		this.mexplain = mexplain;
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
	public Double getJprice() {
		return jprice;
	}
	public void setJprice(Double jprice) {
		this.jprice = jprice;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreamTime() {
		return creamTime;
	}
	public void setCreamTime(String creamTime) {
		this.creamTime = creamTime;
	}
	@Override
	public String toString() {
		return "PriceList [id=" + id + ", mid=" + mid + ", pname=" + pname + ", pn=" + pn + ", mexplain=" + mexplain
				+ ", jprice=" + jprice + ", supplier=" + supplier + ", telphone=" + telphone + ", email=" + email
				+ ", creamTime=" + creamTime + ", minNumbers=" + minNumbers + ", processCost=" + processCost
				+ ", sampleCost=" + sampleCost + ", limit=" + limit + ", pageSize=" + pageSize + ", page=" + page + "]";
	}

}
