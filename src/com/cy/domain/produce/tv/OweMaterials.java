package com.cy.domain.produce.tv;

public class OweMaterials {
	private String bomName;//bom名
	private String orderNumber;//订单号
	private Integer numbers;//批量
	private Integer notNumber;//未生产数
	private String materialName;//品名
	private String pn;//料号
	private Double useNumer;//用量
	private Integer inventory;//库存
	private Double oweMaterial;//欠料
	public String getBomName() {
		return bomName;
	}
	public void setBomName(String bomName) {
		this.bomName = bomName;
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
	public Integer getNotNumber() {
		return notNumber;
	}
	public void setNotNumber(Integer notNumber) {
		this.notNumber = notNumber;
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
	public Double getUseNumer() {
		return useNumer;
	}
	public void setUseNumer(Double useNumer) {
		this.useNumer = useNumer;
	}
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	public Double getOweMaterial() {
		return oweMaterial;
	}
	public void setOweMaterial(Double oweMaterial) {
		this.oweMaterial = oweMaterial;
	}
	@Override
	public String toString() {
		return "OweMaterials [bomName=" + bomName + ", orderNumber=" + orderNumber + ", numbers=" + numbers
				+ ", notNumber=" + notNumber + ", materialName=" + materialName + ", pn=" + pn + ", useNumer="
				+ useNumer + ", inventory=" + inventory + ", oweMaterial=" + oweMaterial + "]";
	}
	
}
