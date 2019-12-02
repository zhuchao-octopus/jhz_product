package com.cy.domain.produce.tv;

public class BomMaterials {
	private Integer id;
	private Integer mid;
	private Integer bid;
	private String producePN;
	private String materialName;
	private String pname;
	private String pn;
	private String mexplain;
	private String weight;
	private String bitNumber;
	private Double useNumer;
	private Integer pageSize;
	private Integer limit;
	private Integer page;
	private String storeState;
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getStoreState() {
		return storeState;
	}
	public void setStoreState(String storeState) {
		this.storeState = storeState;
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
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
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
	public String getProducePN() {
		return producePN;
	}
	public void setProducePN(String producePN) {
		this.producePN = producePN;
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
	public String getBitNumber() {
		return bitNumber;
	}
	public void setBitNumber(String bitNumber) {
		this.bitNumber = bitNumber;
	}
	public Double getUseNumer() {
		return useNumer;
	}
	public void setUseNumer(Double useNumer) {
		this.useNumer = useNumer;
	}
	@Override
	public String toString() {
		return "BomMaterials [id=" + id + ", mid=" + mid + ", bid=" + bid
				+ ", producePN=" + producePN + ", materialName=" + materialName
				+ ", pname=" + pname + ", pn=" + pn + ", mexplain=" + mexplain
				+ ", weight=" + weight + ", bitNumber=" + bitNumber
				+ ", useNumer=" + useNumer + ", pageSize=" + pageSize
				+ ", limit=" + limit + ", page=" + page + ", storeState="
				+ storeState + "]";
	}
	

}
