package com.cy.board;

public class Tasks {
	private Integer tid;
	private Integer xid;//项目表主键
	private Integer khid;
	private Integer jhz_tasksIntegral;//任务积分
	private Integer tasks_state;
	private Integer page;
	private Integer limit;
	private Integer pageSize;
	private String jhz_tasksName;//任务名
	private String jhz_innovate;//技术创新
	private String jhz_projectName;//项目名
	private String jhz_tasksTime;//完成时间
	private String jhz_peple;//责任人
	private String jhz_efficiency;//效评
	private String jhz_expectTime;
	private Integer lid;
	private Integer userId;
	private String jhz_tasksDescribe;
	private Integer jhz_submission;//是否提交
	private String jhz_submissionName;//提交人
	private String jhz_toexamineName;//审核人
	private Integer jhz_tasksToexamine;//是否测试审核
	
	public Integer getJhz_submission() {
		return jhz_submission;
	}
	public void setJhz_submission(Integer jhz_submission) {
		this.jhz_submission = jhz_submission;
	}
	public String getJhz_submissionName() {
		return jhz_submissionName;
	}
	public void setJhz_submissionName(String jhz_submissionName) {
		this.jhz_submissionName = jhz_submissionName;
	}
	public String getJhz_toexamineName() {
		return jhz_toexamineName;
	}
	public void setJhz_toexamineName(String jhz_toexamineName) {
		this.jhz_toexamineName = jhz_toexamineName;
	}
	public Integer getJhz_tasksToexamine() {
		return jhz_tasksToexamine;
	}
	public void setJhz_tasksToexamine(Integer jhz_tasksToexamine) {
		this.jhz_tasksToexamine = jhz_tasksToexamine;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getJhz_tasksDescribe() {
		return jhz_tasksDescribe;
	}
	public void setJhz_tasksDescribe(String jhz_tasksDescribe) {
		this.jhz_tasksDescribe = jhz_tasksDescribe;
	}
	public Integer getLid() {
		return lid;
	}
	public void setLid(Integer lid) {
		this.lid = lid;
	}
	public Integer getKhid() {
		return khid;
	}
	public void setKhid(Integer khid) {
		this.khid = khid;
	}
	public String getJhz_expectTime() {
		return jhz_expectTime;
	}
	public void setJhz_expectTime(String jhz_expectTime) {
		this.jhz_expectTime = jhz_expectTime;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getXid() {
		return xid;
	}
	public void setXid(Integer xid) {
		this.xid = xid;
	}
	public Integer getJhz_tasksIntegral() {
		return jhz_tasksIntegral;
	}
	public void setJhz_tasksIntegral(Integer jhz_tasksIntegral) {
		this.jhz_tasksIntegral = jhz_tasksIntegral;
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
	public String getJhz_tasksName() {
		return jhz_tasksName;
	}
	public void setJhz_tasksName(String jhz_tasksName) {
		this.jhz_tasksName = jhz_tasksName;
	}
	public String getJhz_innovate() {
		return jhz_innovate;
	}
	public void setJhz_innovate(String jhz_innovate) {
		this.jhz_innovate = jhz_innovate;
	}
	public String getJhz_projectName() {
		return jhz_projectName;
	}
	public void setJhz_projectName(String jhz_projectName) {
		this.jhz_projectName = jhz_projectName;
	}
	public String getJhz_tasksTime() {
		return jhz_tasksTime;
	}
	public void setJhz_tasksTime(String jhz_tasksTime) {
		this.jhz_tasksTime = jhz_tasksTime;
	}
	public String getJhz_peple() {
		return jhz_peple;
	}
	public void setJhz_peple(String jhz_peple) {
		this.jhz_peple = jhz_peple;
	}
	public String getJhz_efficiency() {
		return jhz_efficiency;
	}
	public void setJhz_efficiency(String jhz_efficiency) {
		this.jhz_efficiency = jhz_efficiency;
	}
	@Override
	public String toString() {
		return "Tasks [tid=" + tid + ", xid=" + xid + ", khid=" + khid
				+ ", jhz_tasksIntegral=" + jhz_tasksIntegral + ", tasks_state="
				+ tasks_state + ", page=" + page + ", limit=" + limit
				+ ", pageSize=" + pageSize + ", jhz_tasksName=" + jhz_tasksName
				+ ", jhz_innovate=" + jhz_innovate + ", jhz_projectName="
				+ jhz_projectName + ", jhz_tasksTime=" + jhz_tasksTime
				+ ", jhz_peple=" + jhz_peple + ", jhz_efficiency="
				+ jhz_efficiency + ", jhz_expectTime=" + jhz_expectTime
				+ ", lid=" + lid + ", userId=" + userId
				+ ", jhz_tasksDescribe=" + jhz_tasksDescribe
				+ ", jhz_submission=" + jhz_submission
				+ ", jhz_submissionName=" + jhz_submissionName
				+ ", jhz_toexamineName=" + jhz_toexamineName
				+ ", jhz_tasksToexamine=" + jhz_tasksToexamine + "]";
	}
	
}
