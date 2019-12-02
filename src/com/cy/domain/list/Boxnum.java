package com.cy.domain.list;

public class Boxnum {
	private Integer bid;
	private String boxNum;
	private String creatime;
	private String modelCode;
	private String pnCode;
    private String boxState;
	public String getBoxState() {
		return boxState;
	}

	public void setBoxState(String boxState) {
		this.boxState = boxState;
	}

	public String getPorder() {
		return porder;
	}

	public void setPorder(String porder) {
		this.porder = porder;
	}

	private String porder;

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getPnCode() {
		return pnCode;
	}

	public void setPnCode(String pnCode) {
		this.pnCode = pnCode;
	}

	private Integer page;
	private Integer pageSize;
	private Integer limit;

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

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getBoxNum() {
		return boxNum;
	}

	public void setBoxNum(String boxNum) {
		this.boxNum = boxNum;
	}

	public String getCreatime() {
		return creatime;
	}

	public void setCreatime(String creatime) {
		this.creatime = creatime;
	}

	@Override
	public String toString() {
		return "Boxnum [bid=" + bid + ", boxNum=" + boxNum + ", creatime=" + creatime + ", modelCode=" + modelCode
				+ ", pnCode=" + pnCode + ", boxState=" + boxState + ", porder=" + porder + ", page=" + page
				+ ", pageSize=" + pageSize + ", limit=" + limit + "]";
	}

}
