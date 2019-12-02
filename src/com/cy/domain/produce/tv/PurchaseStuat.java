package com.cy.domain.produce.tv;

public class PurchaseStuat {
	private Integer id;
	private Integer stuat;
	private Integer orderStuat;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStuat() {
		return stuat;
	}
	public void setStuat(Integer stuat) {
		this.stuat = stuat;
	}
	public Integer getOrderStuat() {
		return orderStuat;
	}
	public void setOrderStuat(Integer orderStuat) {
		this.orderStuat = orderStuat;
	}
	@Override
	public String toString() {
		return "PruchaseStuat [id=" + id + ", stuat=" + stuat + ", orderStuat=" + orderStuat + ", getId()=" + getId()
				+ ", getStuat()=" + getStuat() + ", getOrderStuat()=" + getOrderStuat() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}
