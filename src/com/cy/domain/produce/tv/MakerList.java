package com.cy.domain.produce.tv;

public class MakerList {
	private Integer id;
	private String makerNo;
	private String makerName;
	private String linkman;
	private String telphone;
	private String email;
	private String creamTime;
	private String makerAddress;
	private String openbank;
	private String ratepayingNumber;
	private String papers;
	private String accountNumber;
	private String introduce;
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
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMakerNo() {
		return makerNo;
	}
	public void setMakerNo(String makerNo) {
		this.makerNo = makerNo;
	}
	public String getMakerName() {
		return makerName;
	}
	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
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
	public String getMakerAddress() {
		return makerAddress;
	}
	public void setMakerAddress(String makerAddress) {
		this.makerAddress = makerAddress;
	}
	public String getOpenbank() {
		return openbank;
	}
	public void setOpenbank(String openbank) {
		this.openbank = openbank;
	}
	public String getRatepayingNumber() {
		return ratepayingNumber;
	}
	public void setRatepayingNumber(String ratepayingNumber) {
		this.ratepayingNumber = ratepayingNumber;
	}
	public String getPapers() {
		return papers;
	}
	public void setPapers(String papers) {
		this.papers = papers;
	}
	@Override
	public String toString() {
		return "MakerList [id=" + id + ", makerNo=" + makerNo + ", makerName=" + makerName + ", linkman=" + linkman
				+ ", telphone=" + telphone + ", email=" + email + ", creamTime=" + creamTime + ", makerAddress="
				+ makerAddress + ", openbank=" + openbank + ", ratepayingNumber=" + ratepayingNumber + ", papers="
				+ papers + ", accountNumber=" + accountNumber + ", introduce=" + introduce + ", pageSize=" + pageSize
				+ ", limit=" + limit + ", page=" + page + "]";
	}

}
