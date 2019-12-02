package com.cy.domain.user;

public class ErpUser {
	 private int userId;
	 private String realName;
	 private String userName;
	 private String userSex;
	 private String telephone;
	 private String pwd;
	 private String headUrl;
	 private Integer pageSize;
	 private Integer limit;
	 private Integer kid;
	 private String roleName;
	 private Integer khid;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
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
	public Integer getKid() {
		return kid;
	}
	public void setKid(Integer kid) {
		this.kid = kid;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getKhid() {
		return khid;
	}
	public void setKhid(Integer khid) {
		this.khid = khid;
	}
	@Override
	public String toString() {
		return "ErpUser [userId=" + userId + ", realName=" + realName
				+ ", userName=" + userName + ", userSex=" + userSex
				+ ", telephone=" + telephone + ", pwd=" + pwd + ", headUrl="
				+ headUrl + ", pageSize=" + pageSize + ", limit=" + limit
				+ ", kid=" + kid + ", roleName=" + roleName + ", khid=" + khid
				+ "]";
	}
	 
}
