package com.cy.domain.list;

import java.io.Serializable;

public class Mainboard implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//型号id
	private String pname;//主板名称
	private Integer pid;//产品id
	private String specifications;//规格
	private Integer price;//价格
	private String explains;//说明
	private String model;//型号
	private Integer pageSize;
	private Integer limit;
	
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getExplains() {
		return explains;
	}
	public void setExplains(String explains) {
		this.explains = explains;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
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
	@Override
	public String toString() {
		return "Mainboard [id=" + id + ", pname=" + pname + ", pid=" + pid + ", specifications=" + specifications
				+ ", price=" + price + ", explains=" + explains + ", model=" + model + ", pageSize=" + pageSize
				+ ", limit=" + limit + "]";
	}
	
}
