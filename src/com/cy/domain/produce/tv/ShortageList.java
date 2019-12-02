package com.cy.domain.produce.tv;

public class ShortageList {
	private Integer id;
	private Integer sid;//欠料表主键
	private String materialName;
	private String pn;//料号
	private Integer pageSize;
	private Integer limit;
	private Integer page;
	private Integer produceNumber;//生产需数
	private Integer stockNumber;//库存数
	private Integer shortageNumber;//欠数
	private String stime;
	private String pname;
	private String pcode;
	
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getPn() {
		return pn;
	}
	public void setPn(String pn) {
		this.pn = pn;
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
	public Integer getProduceNumber() {
		return produceNumber;
	}
	public void setProduceNumber(Integer produceNumber) {
		this.produceNumber = produceNumber;
	}
	public Integer getStockNumber() {
		return stockNumber;
	}
	public void setStockNumber(Integer stockNumber) {
		this.stockNumber = stockNumber;
	}
	public Integer getShortageNumber() {
		return shortageNumber;
	}
	public void setShortageNumber(Integer shortageNumber) {
		this.shortageNumber = shortageNumber;
	}
	@Override
	public String toString() {
		return "ShortageList [id=" + id + ", sid=" + sid + ", materialName="
				+ materialName + ", pn=" + pn + ", pageSize=" + pageSize
				+ ", limit=" + limit + ", page=" + page + ", produceNumber="
				+ produceNumber + ", stockNumber=" + stockNumber
				+ ", shortageNumber=" + shortageNumber + ", stime=" + stime
				+ ", pname=" + pname + ", pcode=" + pcode + "]";
	}
}
