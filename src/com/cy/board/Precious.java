package com.cy.board;

public class Precious {
	private Integer id ;
	private Integer mid;
	private Integer khid;
	private Integer stuat;//状态
	private Integer page;
	private Integer limit;
	private Integer pageSize;
	private String time;//时间
	private String materialName;//品名
	private String pn;
	private String mac;
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
	public Integer getKhid() {
		return khid;
	}
	public void setKhid(Integer khid) {
		this.khid = khid;
	}
	public Integer getStuat() {
		return stuat;
	}
	public void setStuat(Integer stuat) {
		this.stuat = stuat;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	@Override
	public String toString() {
		return "Precious [id=" + id + ", mid=" + mid + ", khid=" + khid
				+ ", stuat=" + stuat + ", page=" + page + ", limit=" + limit
				+ ", pageSize=" + pageSize + ", time=" + time
				+ ", materialName=" + materialName + ", pn=" + pn + ", mac="
				+ mac + "]";
	}
	
}
