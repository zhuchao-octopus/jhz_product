package com.cy.domain.line;

public class JhzRole {
	private Integer kid;
	private String roleName;
	private Integer uid;
	private Integer khid;
	private String permissionStr;
	
	public Integer getKhid() {
		return khid;
	}
	public void setKhid(Integer khid) {
		this.khid = khid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getPermissionStr() {
		return permissionStr;
	}
	public void setPermissionStr(String permissionStr) {
		this.permissionStr = permissionStr;
	}
	private String introduce;
	private Integer pageSize;
	private Integer limit;
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
	
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	@Override
	public String toString() {
		return "JhzRole [kid=" + kid + ", roleName=" + roleName + ", uid="
				+ uid + ", khid=" + khid + ", permissionStr=" + permissionStr
				+ ", introduce=" + introduce + ", pageSize=" + pageSize
				+ ", limit=" + limit + "]";
	}
	
}
