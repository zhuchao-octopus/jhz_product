package com.cy.board;

public class Repair {
	private Integer rid;
	private String sn;//订单批次
	private String wxTime;//日期
	private String model;//产品类型
	private String unhealthyxx;//不良现象
	private String unhealthyy;//不良原因
	private String wxUser;//维修员
	private String category;//类别
	private String wxNumber;//维修次数
	private String remarks;//备注
	private Integer page;
	private Integer limit;
	private Integer pageSize;
	private Integer xId;
	private String blPhenom;
	private Integer brid;
	private String pname;
	private Integer id;//产品类型id
	private Integer pid;//产品id
	private String cy_model;//产品类型
	
	public String getCy_model() {
		return cy_model;
	}
	public void setCy_model(String cy_model) {
		this.cy_model = cy_model;
	}
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
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getBrid() {
		return brid;
	}
	public void setBrid(Integer brid) {
		this.brid = brid;
	}
	public Integer getxId() {
		return xId;
	}
	public void setxId(Integer xId) {
		this.xId = xId;
	}
	public String getBlPhenom() {
		return blPhenom;
	}
	public void setBlPhenom(String blPhenom) {
		this.blPhenom = blPhenom;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getWxTime() {
		return wxTime;
	}
	public void setWxTime(String wxTime) {
		this.wxTime = wxTime;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getUnhealthyxx() {
		return unhealthyxx;
	}
	public void setUnhealthyxx(String unhealthyxx) {
		this.unhealthyxx = unhealthyxx;
	}
	public String getUnhealthyy() {
		return unhealthyy;
	}
	public void setUnhealthyy(String unhealthyy) {
		this.unhealthyy = unhealthyy;
	}
	public String getWxUser() {
		return wxUser;
	}
	public void setWxUser(String wxUser) {
		this.wxUser = wxUser;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getWxNumber() {
		return wxNumber;
	}
	public void setWxNumber(String wxNumber) {
		this.wxNumber = wxNumber;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
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
	@Override
	public String toString() {
		return "Repair [rid=" + rid + ", sn=" + sn + ", wxTime=" + wxTime
				+ ", model=" + model + ", unhealthyxx=" + unhealthyxx
				+ ", unhealthyy=" + unhealthyy + ", wxUser=" + wxUser
				+ ", category=" + category + ", wxNumber=" + wxNumber
				+ ", remarks=" + remarks + ", page=" + page + ", limit="
				+ limit + ", pageSize=" + pageSize + ", xId=" + xId
				+ ", blPhenom=" + blPhenom + ", brid=" + brid + ", pname="
				+ pname + ", id=" + id + ", pid=" + pid + ", cy_model="
				+ cy_model + "]";
	}

}
