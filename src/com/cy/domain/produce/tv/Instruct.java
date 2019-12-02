package com.cy.domain.produce.tv;

public class Instruct {
	private Integer instruct_id;
	private Integer instruct_mid;
	private String instruct_state;// 状态
	private Integer instruct_number;// 个数
	private String instruct_pname;// 品民
	private String instruct_pcode;// 料号
	private String instruct_order;// 订单号
	private Integer instruct_oid;
	private Integer storeState;
	private Integer instruct_pzState;
	private Integer instructPermision;
	private String qsid;
	
	public String getQsid() {
		return qsid;
	}

	public void setQsid(String qsid) {
		this.qsid = qsid;
	}

	public Integer getInstruct_pzState() {
		return instruct_pzState;
	}

	public void setInstruct_pzState(Integer instruct_pzState) {
		this.instruct_pzState = instruct_pzState;
	}

	public Integer getInstructPermision() {
		return instructPermision;
	}

	public void setInstructPermision(Integer instructPermision) {
		this.instructPermision = instructPermision;
	}

	public Integer getStoreState() {
		return storeState;
	}

	public void setStoreState(Integer storeState) {
		this.storeState = storeState;
	}

	public Integer getInstruct_oid() {
		return instruct_oid;
	}

	public void setInstruct_oid(Integer instruct_oid) {
		this.instruct_oid = instruct_oid;
	}

	private String instruct_time;// 时间
	private String instruct_user;
	private Integer instruct_userId;

	public Integer getInstruct_userId() {
		return instruct_userId;
	}

	public void setInstruct_userId(Integer instruct_userId) {
		this.instruct_userId = instruct_userId;
	}

	public String getInstruct_user() {
		return instruct_user;
	}

	public void setInstruct_user(String instruct_user) {
		this.instruct_user = instruct_user;
	}

	private Integer page;
	private Integer pageSize;
	private Integer limit;

	public Integer getInstruct_id() {
		return instruct_id;
	}

	public void setInstruct_id(Integer instruct_id) {
		this.instruct_id = instruct_id;
	}

	public Integer getInstruct_mid() {
		return instruct_mid;
	}

	public void setInstruct_mid(Integer instruct_mid) {
		this.instruct_mid = instruct_mid;
	}

	public String getInstruct_state() {
		return instruct_state;
	}

	public void setInstruct_state(String instruct_state) {
		this.instruct_state = instruct_state;
	}

	public Integer getInstruct_number() {
		return instruct_number;
	}

	public void setInstruct_number(Integer instruct_number) {
		this.instruct_number = instruct_number;
	}

	public String getInstruct_pname() {
		return instruct_pname;
	}

	public void setInstruct_pname(String instruct_pname) {
		this.instruct_pname = instruct_pname;
	}

	public String getInstruct_pcode() {
		return instruct_pcode;
	}

	public void setInstruct_pcode(String instruct_pcode) {
		this.instruct_pcode = instruct_pcode;
	}

	public String getInstruct_order() {
		return instruct_order;
	}

	public void setInstruct_order(String instruct_order) {
		this.instruct_order = instruct_order;
	}

	public String getInstruct_time() {
		return instruct_time;
	}

	public void setInstruct_time(String instruct_time) {
		this.instruct_time = instruct_time;
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
		return "Instruct [instruct_id=" + instruct_id + ", instruct_mid="
				+ instruct_mid + ", instruct_state=" + instruct_state
				+ ", instruct_number=" + instruct_number + ", instruct_pname="
				+ instruct_pname + ", instruct_pcode=" + instruct_pcode
				+ ", instruct_order=" + instruct_order + ", instruct_oid="
				+ instruct_oid + ", storeState=" + storeState
				+ ", instruct_pzState=" + instruct_pzState
				+ ", instructPermision=" + instructPermision + ", qsid=" + qsid
				+ ", instruct_time=" + instruct_time + ", instruct_user="
				+ instruct_user + ", instruct_userId=" + instruct_userId
				+ ", page=" + page + ", pageSize=" + pageSize + ", limit="
				+ limit + "]";
	}

	

}
