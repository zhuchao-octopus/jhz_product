package com.cy.board;

public class Jgroup {
	private Integer zid ;
	private Integer tid;
	private Integer khid;
	private Integer userId;
	private Integer page;
	private Integer limit;
	private Integer pageSize;
	private String jhz_groupName;//项目组名
	private String jhz_groupBoss;//项目组长
	private String jhz_peopleName;//项目组人员
	private String jhz_function;//职能
	private String jhz_skill;//主要技能
	private String jhz_evaluate;//能力评价
	private String jhz_contribution;//贡献
	private String jhz_groupSlogan;//口号
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getKhid() {
		return khid;
	}
	public void setKhid(Integer khid) {
		this.khid = khid;
	}
	public Integer getZid() {
		return zid;
	}
	public void setZid(Integer zid) {
		this.zid = zid;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
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
	public String getJhz_groupName() {
		return jhz_groupName;
	}
	public void setJhz_groupName(String jhz_groupName) {
		this.jhz_groupName = jhz_groupName;
	}
	public String getJhz_groupBoss() {
		return jhz_groupBoss;
	}
	public void setJhz_groupBoss(String jhz_groupBoss) {
		this.jhz_groupBoss = jhz_groupBoss;
	}
	public String getJhz_peopleName() {
		return jhz_peopleName;
	}
	public void setJhz_peopleName(String jhz_peopleName) {
		this.jhz_peopleName = jhz_peopleName;
	}
	public String getJhz_function() {
		return jhz_function;
	}
	public void setJhz_function(String jhz_function) {
		this.jhz_function = jhz_function;
	}
	public String getJhz_skill() {
		return jhz_skill;
	}
	public void setJhz_skill(String jhz_skill) {
		this.jhz_skill = jhz_skill;
	}
	public String getJhz_evaluate() {
		return jhz_evaluate;
	}
	public void setJhz_evaluate(String jhz_evaluate) {
		this.jhz_evaluate = jhz_evaluate;
	}
	public String getJhz_contribution() {
		return jhz_contribution;
	}
	public void setJhz_contribution(String jhz_contribution) {
		this.jhz_contribution = jhz_contribution;
	}
	public String getJhz_groupSlogan() {
		return jhz_groupSlogan;
	}
	public void setJhz_groupSlogan(String jhz_groupSlogan) {
		this.jhz_groupSlogan = jhz_groupSlogan;
	}
	@Override
	public String toString() {
		return "Jgroup [zid=" + zid + ", tid=" + tid + ", khid=" + khid
				+ ", userId=" + userId + ", page=" + page + ", limit=" + limit
				+ ", pageSize=" + pageSize + ", jhz_groupName=" + jhz_groupName
				+ ", jhz_groupBoss=" + jhz_groupBoss + ", jhz_peopleName="
				+ jhz_peopleName + ", jhz_function=" + jhz_function
				+ ", jhz_skill=" + jhz_skill + ", jhz_evaluate=" + jhz_evaluate
				+ ", jhz_contribution=" + jhz_contribution
				+ ", jhz_groupSlogan=" + jhz_groupSlogan + "]";
	}
}
