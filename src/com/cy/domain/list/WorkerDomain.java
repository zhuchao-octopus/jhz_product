package com.cy.domain.list;

public class WorkerDomain {
	
	private String 工号;
	private String 姓名;
	private Double 工价;
	private String 技能;
	private String 电话;
	private String 职务;
	private String 时间;
	public String get工号() {
		return 工号;
	}
	public void set工号(String 工号) {
		this.工号 = 工号;
	}
	public String get姓名() {
		return 姓名;
	}
	public void set姓名(String 姓名) {
		this.姓名 = 姓名;
	}
	public Double get工价() {
		return 工价;
	}
	public void set工价(Double 工价) {
		this.工价 = 工价;
	}
	public String get技能() {
		return 技能;
	}
	public void set技能(String 技能) {
		this.技能 = 技能;
	}
	public String get电话() {
		return 电话;
	}
	public void set电话(String 电话) {
		this.电话 = 电话;
	}
	public String get职务() {
		return 职务;
	}
	public void set职务(String 职务) {
		this.职务 = 职务;
	}
	public String get时间() {
		return 时间;
	}
	public void set时间(String 时间) {
		this.时间 = 时间;
	}
	@Override
	public String toString() {
		return "WorkerDomain [工号=" + 工号 + ", 姓名=" + 姓名 + ", 工价=" + 工价 + ", 技能=" + 技能 + ", 电话=" + 电话 + ", 职务=" + 职务
				+ ", 时间=" + 时间 + "]";
	}
	
	

}
