package com.cy.domain.produce.tv;

public class ClientList {
	private Integer id;
	private String clientNo;
	private String clientName;
	private String linkman;
	private String telphone;
	private String email;
	private String creamTime;
	private String clientAddress;
	private String openbank;
	private String ratepayingNumber;
	private String papers;
	private String accountNumber;
	private String introduce;
	private Integer pageSize;
	private Integer limit;
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
	public String getClientNo() {
		return clientNo;
	}
	public void setClientNo(String clientNo) {
		this.clientNo = clientNo;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
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
	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
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
		return "ClientList [id=" + id + ", clientNo=" + clientNo + ", clientName=" + clientName + ", linkman=" + linkman
				+ ", telphone=" + telphone + ", email=" + email + ", creamTime=" + creamTime + ", clientAddress="
				+ clientAddress + ", openbank=" + openbank + ", ratepayingNumber=" + ratepayingNumber + ", papers="
				+ papers + ", accountNumber=" + accountNumber + ", introduce=" + introduce + ", pageSize=" + pageSize
				+ ", limit=" + limit + "]";
	}

}
