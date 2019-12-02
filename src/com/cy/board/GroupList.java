package com.cy.board;

public class GroupList {
	private Integer uid;
	private Integer xid;
	private Integer zid;
	private Integer userId;
	private String realName;//用户名
	private Integer khid;
	private Integer page;
	private Integer limit;
	private Integer pageSize;
	private String jhz_skill;
	private String jhz_groupName;//项目组名
	
	public String getJhz_groupName() {
		return jhz_groupName;
	}
	public void setJhz_groupName(String jhz_groupName) {
		this.jhz_groupName = jhz_groupName;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getXid() {
		return xid;
	}
	public void setXid(Integer xid) {
		this.xid = xid;
	}
	public Integer getZid() {
		return zid;
	}
	public void setZid(Integer zid) {
		this.zid = zid;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Integer getKhid() {
		return khid;
	}
	public void setKhid(Integer khid) {
		this.khid = khid;
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
	public String getJhz_skill() {
		return jhz_skill;
	}
	public void setJhz_skill(String jhz_skill) {
		this.jhz_skill = jhz_skill;
	}
	@Override
	public String toString() {
		return "GroupList [uid=" + uid + ", xid=" + xid + ", zid=" + zid
				+ ", userId=" + userId + ", realName=" + realName + ", khid="
				+ khid + ", page=" + page + ", limit=" + limit + ", pageSize="
				+ pageSize + ", jhz_skill=" + jhz_skill + ", jhz_groupName="
				+ jhz_groupName + "]";
	}

}
