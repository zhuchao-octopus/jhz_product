package com.cy.domain.produce.tv;

public class BomList {
	private Integer id;
	private Integer mid;
	private String pname;
	private String pn;
	private String mexplain;
	private String weight;
	private Integer dosage;
	private String creamTime;
	private Integer pageSize;
	private Integer limit;
	private Integer page;
	private Integer numbers;
	private String uname;//用户名
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public Integer getNumbers() {
		return numbers;
	}
	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
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
	public String getCreamTime() {
		return creamTime;
	}
	public void setCreamTime(String creamTime) {
		this.creamTime = creamTime;
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
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public Integer getDosage() {
		return dosage;
	}
	public void setDosage(Integer dosage) {
		this.dosage = dosage;
	}
	@Override
	public String toString() {
		return "BomList [id=" + id + ", mid=" + mid + ", pname=" + pname
				+ ", pn=" + pn + ", mexplain=" + mexplain + ", weight="
				+ weight + ", dosage=" + dosage + ", creamTime=" + creamTime
				+ ", pageSize=" + pageSize + ", limit=" + limit + ", page="
				+ page + ", numbers=" + numbers + ", uname=" + uname +"]";
	}
	

}
