package com.cy.domain.produce.tv;

public class WarehouseHistory {
	private Integer id;
	private Integer mid;
	private String materialName;
	private String mexplain;
	private String pn;
	private Integer pnumber;
	private String ptime;
	private String state;
	private String orderNumber;
	private Integer oid;
	private Integer numbers;//批量
	private Integer outNumber;//累计出货
	private Integer residue;//余数
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
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public Integer getPnumber() {
		return pnumber;
	}
	public void setPnumber(Integer pnumber) {
		this.pnumber = pnumber;
	}
	public String getPtime() {
		return ptime;
	}
	public void setPtime(String ptime) {
		this.ptime = ptime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
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
	@Override
	public String toString() {
		return "WarehouseHistory [id=" + id + ", mid=" + mid + ", materialName=" + materialName + ", mexplain="
				+ mexplain + ", pn=" + pn + ", pnumber=" + pnumber + ", ptime=" + ptime + ", state=" + state
				+ ", orderNumber=" + orderNumber + ", oid=" + oid + ", numbers=" + numbers + ", outNumber=" + outNumber
				+ ", residue=" + residue + "]";
	}

}
