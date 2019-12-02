package com.cy.domain.produce.tv;

public class Warehouse {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSoftwareAddress() {
		return softwareAddress;
	}
	public void setSoftwareAddress(String softwareAddress) {
		this.softwareAddress = softwareAddress;
	}
	public String getSoftwarePasssword() {
		return softwarePasssword;
	}
	public void setSoftwarePasssword(String softwarePasssword) {
		this.softwarePasssword = softwarePasssword;
	}

	private String productName;
	private String pn;
	private String weight;
	private Integer putNumber;
	private Integer outNumber;
	private Integer residue;
	private String specification;
	private Integer pageSize;
	private Integer limit;
	private Integer page;
	private String storeState;
	private String softwareAddress;
	private String softwarePasssword;
	private String supplier;
	private String inDate;
	private String batch;
	private String incomingQuantity;
	private String bindingQuantity;
	public String getStoreState() {
		return storeState;
	}
	public void setStoreState(String storeState) {
		this.storeState = storeState;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPn() {
		return pn;
	}
	public void setPn(String pn) {
		this.pn = pn;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public Integer getPutNumber() {
		return putNumber;
	}
	public void setPutNumber(Integer putNumber) {
		this.putNumber = putNumber;
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
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getInDate() {
		return inDate;
	}
	public void setInDate(String inDate) {
		this.inDate = inDate;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getIncomingQuantity() {
		return incomingQuantity;
	}
	public void setIncomingQuantity(String incomingQuantity) {
		this.incomingQuantity = incomingQuantity;
	}
	public String getBindingQuantity() {
		return bindingQuantity;
	}
	public void setBindingQuantity(String bindingQuantity) {
		this.bindingQuantity = bindingQuantity;
	}
	@Override
	public String toString() {
		return "Warehouse [id=" + id + ", productName=" + productName + ", pn=" + pn + ", weight=" + weight
				+ ", putNumber=" + putNumber + ", outNumber=" + outNumber + ", residue=" + residue + ", specification="
				+ specification + ", pageSize=" + pageSize + ", limit=" + limit + ", page=" + page + ", storeState="
				+ storeState + ", softwareAddress=" + softwareAddress + ", softwarePasssword=" + softwarePasssword
				+ ", supplier=" + supplier + ", inDate=" + inDate + ", batch=" + batch + ", incomingQuantity="
				+ incomingQuantity + ", bindingQuantity=" + bindingQuantity + "]";
	}

}
