package com.cy.board;

public class Integral {
	private Integer jid;
	private Integer xid;//项目表主键
	private Integer khid;
	//private Integer jhz_tasksIntegral;//任务积分
	private Integer tasks_state;
	private Integer page;
	private Integer limit;
	private Integer pageSize;
	private Integer tid;//任务表主键
	private Integer jhz_integral;//总积分
	private Integer jhz_integralNumber;//获得积分
	private String jhz_people;//获得人
	private String jhz_integralTime;//获得时间
	private String jhz_projectName;//积分来源
	private Integer userId;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getJid() {
		return jid;
	}
	public void setJid(Integer jid) {
		this.jid = jid;
	}
	public Integer getXid() {
		return xid;
	}
	public void setXid(Integer xid) {
		this.xid = xid;
	}
	public Integer getKhid() {
		return khid;
	}
	public void setKhid(Integer khid) {
		this.khid = khid;
	}
	public Integer getTasks_state() {
		return tasks_state;
	}
	public void setTasks_state(Integer tasks_state) {
		this.tasks_state = tasks_state;
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
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getJhz_integral() {
		return jhz_integral;
	}
	public void setJhz_integral(Integer jhz_integral) {
		this.jhz_integral = jhz_integral;
	}
	public Integer getJhz_integralNumber() {
		return jhz_integralNumber;
	}
	public void setJhz_integralNumber(Integer jhz_integralNumber) {
		this.jhz_integralNumber = jhz_integralNumber;
	}
	public String getJhz_people() {
		return jhz_people;
	}
	public void setJhz_people(String jhz_people) {
		this.jhz_people = jhz_people;
	}
	public String getJhz_integralTime() {
		return jhz_integralTime;
	}
	public void setJhz_integralTime(String jhz_integralTime) {
		this.jhz_integralTime = jhz_integralTime;
	}
	public String getJhz_projectName() {
		return jhz_projectName;
	}
	public void setJhz_projectName(String jhz_projectName) {
		this.jhz_projectName = jhz_projectName;
	}
	@Override
	public String toString() {
		return "Integral [jid=" + jid + ", xid=" + xid + ", khid=" + khid
				+ ", tasks_state=" + tasks_state + ", page=" + page
				+ ", limit=" + limit + ", pageSize=" + pageSize + ", tid="
				+ tid + ", jhz_integral=" + jhz_integral
				+ ", jhz_integralNumber=" + jhz_integralNumber
				+ ", jhz_people=" + jhz_people + ", jhz_integralTime="
				+ jhz_integralTime + ", jhz_projectName=" + jhz_projectName
				+ ", userId=" + userId + "]";
	}
	
}
