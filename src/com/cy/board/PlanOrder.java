package com.cy.board;

public class PlanOrder {
	private Integer id;
	private Integer jhz_number;//计划人数
	private Integer	oid; //订单id
	private Integer mid; //料号id
	private Integer lid; //产线id
	private Integer stuat;//是否完成
	private Integer	jhz_jhNumber; //计划产量
	private Integer khid; //客户id
	private Integer notCompleteNumber; //未完成数
	private Integer	completeNumber;//完成数量
	private String jhz_planCode; //排单编号
	private String batch;//生产批次
	private String jhz_time; //排单时间
	private String jhz_jhtime; //计划工时
	private String pname;//品名
	private String pn;//料号
	private String orderNumber;//订单号
	private Integer page;
	private Integer limit;
	private Integer pageSize;
	private String lineCode;//产线名称
	private String materialName;
	
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getLineCode() {
		return lineCode;
	}
	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getJhz_number() {
		return jhz_number;
	}
	public void setJhz_number(Integer jhz_number) {
		this.jhz_number = jhz_number;
	}
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public Integer getLid() {
		return lid;
	}
	public void setLid(Integer lid) {
		this.lid = lid;
	}
	public Integer getStuat() {
		return stuat;
	}
	public void setStuat(Integer stuat) {
		this.stuat = stuat;
	}
	public Integer getJhz_jhNumber() {
		return jhz_jhNumber;
	}
	public void setJhz_jhNumber(Integer jhz_jhNumber) {
		this.jhz_jhNumber = jhz_jhNumber;
	}
	public Integer getKhid() {
		return khid;
	}
	public void setKhid(Integer khid) {
		this.khid = khid;
	}
	public Integer getNotCompleteNumber() {
		return notCompleteNumber;
	}
	public void setNotCompleteNumber(Integer notCompleteNumber) {
		this.notCompleteNumber = notCompleteNumber;
	}
	public Integer getCompleteNumber() {
		return completeNumber;
	}
	public void setCompleteNumber(Integer completeNumber) {
		this.completeNumber = completeNumber;
	}
	public String getJhz_planCode() {
		return jhz_planCode;
	}
	public void setJhz_planCode(String jhz_planCode) {
		this.jhz_planCode = jhz_planCode;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getJhz_time() {
		return jhz_time;
	}
	public void setJhz_time(String jhz_time) {
		this.jhz_time = jhz_time;
	}
	public String getJhz_jhtime() {
		return jhz_jhtime;
	}
	public void setJhz_jhtime(String jhz_jhtime) {
		this.jhz_jhtime = jhz_jhtime;
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
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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
		return "PlanOrder [id=" + id + ", jhz_number=" + jhz_number + ", oid="
				+ oid + ", mid=" + mid + ", lid=" + lid + ", stuat=" + stuat
				+ ", jhz_jhNumber=" + jhz_jhNumber + ", khid=" + khid
				+ ", notCompleteNumber=" + notCompleteNumber
				+ ", completeNumber=" + completeNumber + ", jhz_planCode="
				+ jhz_planCode + ", batch=" + batch + ", jhz_time=" + jhz_time
				+ ", jhz_jhtime=" + jhz_jhtime + ", pname=" + pname + ", pn="
				+ pn + ", orderNumber=" + orderNumber + ", page=" + page
				+ ", limit=" + limit + ", pageSize=" + pageSize + ", lineCode="
				+ lineCode + ", materialName=" + materialName + "]";
	}
	
	
}
