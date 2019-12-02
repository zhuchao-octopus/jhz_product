package com.cy.domain.produce.tv;

public class MaterialManage {
	private Integer id;
	private String materialName;
	private String pn;
	private String mexplain;
	private String weight;
	private String storeState;
	private String softwareAddress;
	private String downPassword;
	private String specification;
	private String productName;
	private String supplier;
	private String inDate;
	private String batch;
	private String incomingQuantity;
	private String bindingQuantity;
	private String residue;
	
	
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSoftwareAddress() {
		return softwareAddress;
	}
	public void setSoftwareAddress(String softwareAddress) {
		this.softwareAddress = softwareAddress;
	}
	public String getDownPassword() {
		return downPassword;
	}
	public void setDownPassword(String downPassword) {
		this.downPassword = downPassword;
	}
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	public String getStoreState() {
		return storeState;
	}
	public void setStoreState(String storeState) {
		this.storeState = storeState;
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
	public String getResidue() {
		return residue;
	}
	public void setResidue(String residue) {
		this.residue = residue;
	}
	@Override
	public String toString() {
		return "MaterialManage [id=" + id + ", materialName=" + materialName + ", pn=" + pn + ", mexplain=" + mexplain
				+ ", weight=" + weight + ", storeState=" + storeState + ", softwareAddress=" + softwareAddress
				+ ", downPassword=" + downPassword + ", specification=" + specification + ", productName=" + productName
				+ ", supplier=" + supplier + ", inDate=" + inDate + ", batch=" + batch + ", incomingQuantity="
				+ incomingQuantity + ", bindingQuantity=" + bindingQuantity + ", residue=" + residue + ", pageSize="
				+ pageSize + ", limit=" + limit + "]";
	}
	
	
}
