package com.cy.domain.check;

import java.io.Serializable;

public class CheckEquipment implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String information;
	private String brand;
	private String region;
	private String note;
	private String sn1;//盒子sn号
	private String mac1;//盒子mac地址
	private String ip1;//盒子ip
	private String sn2;//手机sn号
	private String mac2;//手机mac地址
	private String ip2;//手机ip
	private Integer way;
	private Integer status;
	
	private Integer page;
	private Integer pageSize;
	private Integer limit;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getSn1() {
		return sn1;
	}
	public void setSn1(String sn1) {
		this.sn1 = sn1;
	}
	public String getMac1() {
		return mac1;
	}
	public void setMac1(String mac1) {
		this.mac1 = mac1;
	}
	public String getIp1() {
		return ip1;
	}
	public void setIp1(String ip1) {
		this.ip1 = ip1;
	}
	public String getSn2() {
		return sn2;
	}
	public void setSn2(String sn2) {
		this.sn2 = sn2;
	}
	public String getMac2() {
		return mac2;
	}
	public void setMac2(String mac2) {
		this.mac2 = mac2;
	}
	public String getIp2() {
		return ip2;
	}
	public void setIp2(String ip2) {
		this.ip2 = ip2;
	}
	
	public Integer getWay() {
		return way;
	}
	public void setWay(Integer way) {
		this.way = way;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
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
		return "CheckEquipment [id=" + id + ", name=" + name + ", information=" + information + ", brand=" + brand
				+ ", region=" + region + ", note=" + note + ", sn1=" + sn1 + ", mac1=" + mac1 + ", ip1=" + ip1
				+ ", sn2=" + sn2 + ", mac2=" + mac2 + ", ip2=" + ip2 + ", way=" + way + ", status=" + status + ", page="
				+ page + ", pageSize=" + pageSize + ", limit=" + limit + "]";
	}
	
	
}
