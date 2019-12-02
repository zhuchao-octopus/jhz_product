package com.cy.board;

public class Batch {
	private Integer id ;
	private Integer mid;
	private Integer khid;
	private Integer storeState;//仓库
	private Integer stuat;//状态
	private Integer numbers;//批量
	private Integer outNumber;//出货数
	private Integer residue;//余数
	private Integer pid;//
	private String pname;
	private Integer price;//单价
	private Integer page;
	private Integer limit;
	private Integer pageSize;
	private String batch;//批次
	private String modelCode;//产品类型
	private String time;//时间
	private String materialName;//品名
	private String pn;
	
	
	
	
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
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
	public Integer getKhid() {
		return khid;
	}
	public void setKhid(Integer khid) {
		this.khid = khid;
	}
	public Integer getStoreState() {
		return storeState;
	}
	public void setStoreState(Integer storeState) {
		this.storeState = storeState;
	}
	public Integer getStuat() {
		return stuat;
	}
	public void setStuat(Integer stuat) {
		this.stuat = stuat;
	}
	public Integer getNumbers() {
		return numbers;
	}
	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}
	public Integer getOutNumber() {
		return outNumber;
	}
	public void setOutNumber(Integer outNumber) {
		this.outNumber = outNumber;
	}
	public Integer getResidue() {
		return residue;
	}
	public void setResidue(Integer residue) {
		this.residue = residue;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
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
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getModelCode() {
		return modelCode;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
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
	@Override
	public String toString() {
		return "Batch [id=" + id + ", mid=" + mid + ", khid=" + khid
				+ ", storeState=" + storeState + ", stuat=" + stuat
				+ ", numbers=" + numbers + ", outNumber=" + outNumber
				+ ", residue=" + residue + ", price=" + price + ", page="
				+ page + ", limit=" + limit + ", pageSize=" + pageSize
				+ ", batch=" + batch + ", modelCode=" + modelCode + ", time="
				+ time + ", materialName=" + materialName + ", pn=" + pn + "]";
	}
	
}
