package com.cy.board;

public class Project {
	private Integer xid;
	private Integer jhz_integral;//积分
	private Integer jhz_state;//是否完成
	private Integer khid;//客户id
	private Integer pageSize;
	private Integer limit;
	private Integer page;
	private Integer jhz_executivePower;//递减参数
	private String jhz_projectNumber;//项目编号
	private String jhz_projectName;//项目名
	private String jhz_startName;//发布人
	private String jhz_startTime;//发布时间
	private String jhz_expectTime;//预期完成时间
	private String jhz_respoName;//项目负责人
	private String jhz_schedule;//完成进度
	private String jhz_finishTime;//完成时间
	private String jhz_describe;//任务描述
	private String jhz_projectBook;//项目文档
	private Integer userId;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getXid() {
		return xid;
	}
	public void setXid(Integer xid) {
		this.xid = xid;
	}
	public Integer getJhz_integral() {
		return jhz_integral;
	}
	public void setJhz_integral(Integer jhz_integral) {
		this.jhz_integral = jhz_integral;
	}
	public Integer getJhz_state() {
		return jhz_state;
	}
	public void setJhz_state(Integer jhz_state) {
		this.jhz_state = jhz_state;
	}
	public Integer getKhid() {
		return khid;
	}
	public void setKhid(Integer khid) {
		this.khid = khid;
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
	public Integer getJhz_executivePower() {
		return jhz_executivePower;
	}
	public void setJhz_executivePower(Integer jhz_executivePower) {
		this.jhz_executivePower = jhz_executivePower;
	}
	public String getJhz_projectNumber() {
		return jhz_projectNumber;
	}
	public void setJhz_projectNumber(String jhz_projectNumber) {
		this.jhz_projectNumber = jhz_projectNumber;
	}
	public String getJhz_projectName() {
		return jhz_projectName;
	}
	public void setJhz_projectName(String jhz_projectName) {
		this.jhz_projectName = jhz_projectName;
	}
	public String getJhz_startName() {
		return jhz_startName;
	}
	public void setJhz_startName(String jhz_startName) {
		this.jhz_startName = jhz_startName;
	}
	public String getJhz_startTime() {
		return jhz_startTime;
	}
	public void setJhz_startTime(String jhz_startTime) {
		this.jhz_startTime = jhz_startTime;
	}
	public String getJhz_expectTime() {
		return jhz_expectTime;
	}
	public void setJhz_expectTime(String jhz_expectTime) {
		this.jhz_expectTime = jhz_expectTime;
	}
	public String getJhz_respoName() {
		return jhz_respoName;
	}
	public void setJhz_respoName(String jhz_respoName) {
		this.jhz_respoName = jhz_respoName;
	}
	public String getJhz_schedule() {
		return jhz_schedule;
	}
	public void setJhz_schedule(String jhz_schedule) {
		this.jhz_schedule = jhz_schedule;
	}
	public String getJhz_finishTime() {
		return jhz_finishTime;
	}
	public void setJhz_finishTime(String jhz_finishTime) {
		this.jhz_finishTime = jhz_finishTime;
	}
	public String getJhz_describe() {
		return jhz_describe;
	}
	public void setJhz_describe(String jhz_describe) {
		this.jhz_describe = jhz_describe;
	}
	public String getJhz_projectBook() {
		return jhz_projectBook;
	}
	public void setJhz_projectBook(String jhz_projectBook) {
		this.jhz_projectBook = jhz_projectBook;
	}
	@Override
	public String toString() {
		return "Project [xid=" + xid + ", jhz_integral=" + jhz_integral
				+ ", jhz_state=" + jhz_state + ", khid=" + khid + ", pageSize="
				+ pageSize + ", limit=" + limit + ", page=" + page
				+ ", jhz_executivePower=" + jhz_executivePower
				+ ", jhz_projectNumber=" + jhz_projectNumber
				+ ", jhz_projectName=" + jhz_projectName + ", jhz_startName="
				+ jhz_startName + ", jhz_startTime=" + jhz_startTime
				+ ", jhz_expectTime=" + jhz_expectTime + ", jhz_respoName="
				+ jhz_respoName + ", jhz_schedule=" + jhz_schedule
				+ ", jhz_finishTime=" + jhz_finishTime + ", jhz_describe="
				+ jhz_describe + ", jhz_projectBook=" + jhz_projectBook
				+ ", userId=" + userId + "]";
	}
	
}
