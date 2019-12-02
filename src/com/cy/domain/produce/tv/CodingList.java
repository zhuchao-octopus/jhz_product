package com.cy.domain.produce.tv;

public class CodingList {
	private Integer id;
	private String number;
	private String pname;
	private String mexplain;
	private String creamTime;
	private Integer page;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getMexplain() {
		return mexplain;
	}
	public void setMexplain(String mexplain) {
		this.mexplain = mexplain;
	}
	public String getCreamTime() {
		return creamTime;
	}
	public void setCreamTime(String creamTime) {
		this.creamTime = creamTime;
	}
	@Override
	public String toString() {
		return "CodingList [id=" + id + ", number=" + number + ", pname=" + pname + ", mexplain=" + mexplain
				+ ", creamTime=" + creamTime + ", page=" + page + ", pageSize=" + pageSize + ", limit=" + limit + "]";
	}

}
