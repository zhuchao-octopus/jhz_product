package com.cy.domain.produce.tv;

public class BomOrderList {
	private Integer oid;
	private Integer bid;
	private Integer mid;
	private String pname;
	private String pn;
	private String mexplain;
	private String weight;
	private Integer dosage;
	private String orderNumber;
	private Integer numbers;
	private Integer outNumber;
	private Integer residue;
	private Double price;
	private Double money;
	private Integer storeState;
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public Integer getStoreState() {
		return storeState;
	}
	public void setStoreState(Integer storeState) {
		this.storeState = storeState;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPn() {
		return pn;
	}
	public void setPn(String pn) {
		this.pn = pn;
	}
	public String getMexplain() {
		return mexplain;
	}
	public void setMexplain(String mexplain) {
		this.mexplain = mexplain;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public Integer getDosage() {
		return dosage;
	}
	public void setDosage(Integer dosage) {
		this.dosage = dosage;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Integer getNumbers() {
		return numbers;
	}
	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}
	public Integer getOutNumber() {
		return outNumber;
	}
	public void setOutNumber(Integer outNumber) {
		this.outNumber = outNumber;
	}
	public Integer getResidue() {
		return residue;
	}
	public void setResidue(Integer residue) {
		this.residue = residue;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "BomOrderList [oid=" + oid + ", bid=" + bid + ", mid=" + mid + ", pname=" + pname + ", pn=" + pn
				+ ", mexplain=" + mexplain + ", weight=" + weight + ", dosage=" + dosage + ", orderNumber="
				+ orderNumber + ", numbers=" + numbers + ", outNumber=" + outNumber + ", residue=" + residue
				+ ", price=" + price + ", money=" + money + ", storeState=" + storeState + "]";
	}
}
