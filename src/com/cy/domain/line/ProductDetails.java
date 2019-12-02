package com.cy.domain.line;

import java.io.Serializable;
/*
 * 产品详情列表
 */
public class ProductDetails implements Serializable {
 
	@Override
	public String toString() {
		return "ProductDetails [pid=" + pid + ", pname=" + pname + ", price=" + price + ", pweight=" + pweight
				+ ", perror=" + perror + ", bweight=" + bweight + ", berror=" + berror + ", pageSize=" + pageSize
				+ ", limit=" + limit + ", boxCount=" + boxCount + "]";
	}
	private static final long serialVersionUID = 1L;
	private Integer pid;
    private String pname;
    private Double price;
	private Double pweight;//机重
    private Double perror;//机重误差
    private Double bweight;//箱重
    private Double berror;
    private Integer pageSize;
	private Integer limit;
	private Integer boxCount;
	public Integer getBoxCount() {
		return boxCount;
	}
	public void setBoxCount(Integer boxCount) {
		this.boxCount = boxCount;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Double getPweight() {
		return pweight;
	}
	public void setPweight(Double pweight) {
		this.pweight = pweight;
	}
	
	public Double getBweight() {
		return bweight;
	}
	public void setBweight(Double bweight) {
		this.bweight = bweight;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getPerror() {
		return perror;
	}
	public void setPerror(Double perror) {
		this.perror = perror;
	}
	public Double getBerror() {
		return berror;
	}
	public void setBerror(Double berror) {
		this.berror = berror;
	}
}
