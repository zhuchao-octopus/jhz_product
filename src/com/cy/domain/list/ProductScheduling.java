package com.cy.domain.list;

import java.io.Serializable;

import com.cy.domain.line.ProductDetails;

public class ProductScheduling implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer pid;
	private String snumber;
	private String codeName;
	private Integer codeLength;
	private String pname;//产品名
	private Integer serialLength;
	private String serialSystem;
	private String keyLocatlOne;
	private String keyLocatlTwo;
	private String keyCharOne;
	private String keyCharTwo;
	private String pclass;//工艺
	private Double pcost;
	private ProductDetails pd;
	private Integer c_pid;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getC_pid() {
		return c_pid;
	}
	public void setC_pid(Integer c_pid) {
		this.c_pid = c_pid;
	}
	public ProductDetails getPd() {
		return pd;
	}
	public void setPd(ProductDetails pd) {
		this.pd = pd;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getSnumber() {
		return snumber;
	}
	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public Integer getCodeLength() {
		return codeLength;
	}
	public void setCodeLength(Integer codeLength) {
		this.codeLength = codeLength;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getSerialLength() {
		return serialLength;
	}
	public void setSerialLength(Integer serialLength) {
		this.serialLength = serialLength;
	}
	public String getSerialSystem() {
		return serialSystem;
	}
	public void setSerialSystem(String serialSystem) {
		this.serialSystem = serialSystem;
	}
	public String getKeyLocatlOne() {
		return keyLocatlOne;
	}
	public void setKeyLocatlOne(String keyLocatlOne) {
		this.keyLocatlOne = keyLocatlOne;
	}
	public String getKeyLocatlTwo() {
		return keyLocatlTwo;
	}
	public void setKeyLocatlTwo(String keyLocatlTwo) {
		this.keyLocatlTwo = keyLocatlTwo;
	}
	public String getKeyCharOne() {
		return keyCharOne;
	}
	public void setKeyCharOne(String keyCharOne) {
		this.keyCharOne = keyCharOne;
	}
	public String getKeyCharTwo() {
		return keyCharTwo;
	}
	public void setKeyCharTwo(String keyCharTwo) {
		this.keyCharTwo = keyCharTwo;
	}
	public String getPclass() {
		return pclass;
	}
	public void setPclass(String pclass) {
		this.pclass = pclass;
	}
	public Double getPcost() {
		return pcost;
	}
	public void setPcost(Double pcost) {
		this.pcost = pcost;
	}
	@Override
	public String toString() {
		return "ProductScheduling [pid=" + pid + ", snumber=" + snumber + ", codeName=" + codeName + ", codeLength="
				+ codeLength + ", pname=" + pname + ", serialLength=" + serialLength + ", serialSystem=" + serialSystem
				+ ", keyLocatlOne=" + keyLocatlOne + ", keyLocatlTwo=" + keyLocatlTwo + ", keyCharOne=" + keyCharOne
				+ ", keyCharTwo=" + keyCharTwo + ", pclass=" + pclass + ", pcost=" + pcost + ", pd=" + pd + ", c_pid="
				+ c_pid + ", pageSize=" + pageSize + ", limit=" + limit + "]";
	}
	
}