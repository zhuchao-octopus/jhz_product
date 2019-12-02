package com.cy.domain.list;

/**
 * 产品列表
 * @author ZQT
 *
 */
public class ProductList {
	private Integer lid;
	private String lname;
	private String lmodel;
	private String lmaterial;
	private Integer lplanyield;
	private String lorder;
	private Double lcost;
	
	public Integer getLid() {
		return lid;
	}
	public void setLid(Integer lid) {
		this.lid = lid;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getLmodel() {
		return lmodel;
	}
	public void setLmodel(String lmodel) {
		this.lmodel = lmodel;
	}
	public String getLmaterial() {
		return lmaterial;
	}
	public void setLmaterial(String lmaterial) {
		this.lmaterial = lmaterial;
	}
	public Integer getLplanyield() {
		return lplanyield;
	}
	public void setLplanyield(Integer lplanyield) {
		this.lplanyield = lplanyield;
	}
	public String getLorder() {
		return lorder;
	}
	public void setLorder(String lorder) {
		this.lorder = lorder;
	}
	public Double getLcost() {
		return lcost;
	}
	public void setLcost(Double lcost) {
		this.lcost = lcost;
	}
	@Override
	public String toString() {
		return "ProductList [lid=" + lid + ", lname=" + lname + ", lmodel=" + lmodel + ", lmaterial=" + lmaterial
				+ ", lplanyield=" + lplanyield + ", lorder=" + lorder + ", lcost=" + lcost + "]";
	}
	
	
}
