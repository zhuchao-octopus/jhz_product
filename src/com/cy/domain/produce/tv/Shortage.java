package com.cy.domain.produce.tv;

public class Shortage {
	private Integer sid;
	private String shortageCode;//欠料编码
	private String pname;//成品名/bom名
	private Integer pid ;//品名id
	private String pn;//料号
	private String orderNumber;//订单号
	private Integer produceNumber;//生产数
	private Integer order;//是否下单
	private Integer shortageNumber;//欠料数
	private Integer stockNumber;//库存数
	private String remarks;//备注
	private Integer page;
	private Integer pageSize;
	private Integer limit;
	private Integer bid;
	private Integer mid;//bom表主键
	private Integer oid;//订单表主键
	private String qsid;
	
	public String getQsid() {
		return qsid;
	}
	public void setQsid(String qsid) {
		this.qsid = qsid;
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
	public String getPn() {
		return pn;
	}
	public void setPn(String pn) {
		this.pn = pn;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getShortageCode() {
		return shortageCode;
	}
	public void setShortageCode(String shortageCode) {
		this.shortageCode = shortageCode;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Integer getProduceNumber() {
		return produceNumber;
	}
	public void setProduceNumber(Integer produceNumber) {
		this.produceNumber = produceNumber;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Integer getShortageNumber() {
		return shortageNumber;
	}
	public void setShortageNumber(Integer shortageNumber) {
		this.shortageNumber = shortageNumber;
	}
	public Integer getStockNumber() {
		return stockNumber;
	}
	public void setStockNumber(Integer stockNumber) {
		this.stockNumber = stockNumber;
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
		return "Shortage [sid=" + sid + ", shortageCode=" + shortageCode
				+ ", pname=" + pname + ", pid=" + pid + ", pn=" + pn
				+ ", orderNumber=" + orderNumber + ", produceNumber="
				+ produceNumber + ", order=" + order + ", shortageNumber="
				+ shortageNumber + ", stockNumber=" + stockNumber
				+ ", remarks=" + remarks + ", page=" + page + ", pageSize="
				+ pageSize + ", limit=" + limit + ", bid=" + bid + ", mid="
				+ mid + ", oid=" + oid + ", qsid=" + qsid + "]";
	}
}
