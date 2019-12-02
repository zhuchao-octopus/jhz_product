package com.cy.domain.list;

public class ProductModel {
	private Integer id;
	private String cy_model;
	private String pname;
	private String cy_explain;
	private String cy_modelRemarks;
	private String cy_remarks;
	private String jhz_modelCode;
	private Double jhz_offer;
	private Integer pid;
	private Integer page;
	private Integer pageSize;
	private Integer limit;
	
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCy_model() {
		return cy_model;
	}
	public void setCy_model(String cy_model) {
		this.cy_model = cy_model;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getCy_explain() {
		return cy_explain;
	}
	public void setCy_explain(String cy_explain) {
		this.cy_explain = cy_explain;
	}
	public String getCy_modelRemarks() {
		return cy_modelRemarks;
	}
	public void setCy_modelRemarks(String cy_modelRemarks) {
		this.cy_modelRemarks = cy_modelRemarks;
	}
	public String getCy_remarks() {
		return cy_remarks;
	}
	public void setCy_remarks(String cy_remarks) {
		this.cy_remarks = cy_remarks;
	}
	public String getJhz_modelCode() {
		return jhz_modelCode;
	}
	public void setJhz_modelCode(String jhz_modelCode) {
		this.jhz_modelCode = jhz_modelCode;
	}
	public Double getJhz_offer() {
		return jhz_offer;
	}
	public void setJhz_offer(Double jhz_offer) {
		this.jhz_offer = jhz_offer;
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
	@Override
	public String toString() {
		return "ProductModel [id=" + id + ", cy_model=" + cy_model + ", pname="
				+ pname + ", cy_explain=" + cy_explain + ", cy_modelRemarks="
				+ cy_modelRemarks + ", cy_remarks=" + cy_remarks
				+ ", jhz_modelCode=" + jhz_modelCode + ", jhz_offer="
				+ jhz_offer + ", pid=" + pid + ", page=" + page + ", pageSize="
				+ pageSize + ", limit=" + limit + "]";
	}
	
}
