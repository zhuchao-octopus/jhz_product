package com.cy.domain.produce.tv;

public class ProductHistory {
	private Integer id;
	private Integer mid;
	private String materialName;
	private String pn;
	private String mexplain;
	private Integer pnumber;
	private String ptime;
	private String state;
	private String orderNumber;
	private Integer oid;
	private Integer numbers;//批量
	private Integer outNumber;//累计出货
	private Integer residue;//余数
	private String jobNo;//出货单号
	private Integer totalNumber;//累计出货数量
	private String addMoney;
	private Integer page;
	private Integer limit;
	private Integer pageSize;
    private int instructPermision;//0 没有 1 有
    private String instruct_user;
    private Integer storeState;
    private Integer instruct_userId;
    private String qsid;//欠料表主键
    private String inDate;
    private String supplier;
    private int incomingQuantity;
    private String batch;
	public String getQsid() {
		return qsid;
	}
	public void setQsid(String qsid) {
		this.qsid = qsid;
	}
	public Integer getInstruct_userId() {
		return instruct_userId;
	}
	public void setInstruct_userId(Integer instruct_userId) {
		this.instruct_userId = instruct_userId;
	}
	public Integer getStoreState() {
		return storeState;
	}
	public void setStoreState(Integer storeState) {
		this.storeState = storeState;
	}
	public String getInstruct_user() {
		return instruct_user;
	}
	public void setInstruct_user(String instruct_user) {
		this.instruct_user = instruct_user;
	}
	public int getInstructPermision() {
		return instructPermision;
	}
	public void setInstructPermision(int instructPermision) {
		this.instructPermision = instructPermision;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getAddMoney() {
		return addMoney;
	}
	public void setAddMoney(String addMoney) {
		this.addMoney = addMoney;
	}
	public Integer getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}
	public String getJobNo() {
		return jobNo;
	}
	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
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
	public String getMexplain() {
		return mexplain;
	}
	public void setMexplain(String mexplain) {
		this.mexplain = mexplain;
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
	public String getInDate() {
		return inDate;
	}
	public void setInDate(String inDate) {
		this.inDate = inDate;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public int getIncomingQuantity() {
		return incomingQuantity;
	}
	public void setIncomingQuantity(int incomingQuantity) {
		this.incomingQuantity = incomingQuantity;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	@Override
	public String toString() {
		return "ProductHistory [id=" + id + ", mid=" + mid + ", materialName=" + materialName + ", pn=" + pn
				+ ", mexplain=" + mexplain + ", pnumber=" + pnumber + ", ptime=" + ptime + ", state=" + state
				+ ", orderNumber=" + orderNumber + ", oid=" + oid + ", numbers=" + numbers + ", outNumber=" + outNumber
				+ ", residue=" + residue + ", jobNo=" + jobNo + ", totalNumber=" + totalNumber + ", addMoney="
				+ addMoney + ", page=" + page + ", limit=" + limit + ", pageSize=" + pageSize + ", instructPermision="
				+ instructPermision + ", instruct_user=" + instruct_user + ", storeState=" + storeState
				+ ", instruct_userId=" + instruct_userId + ", qsid=" + qsid + ", inDate=" + inDate + ", supplier="
				+ supplier + ", incomingQuantity=" + incomingQuantity + ", batch=" + batch + "]";
	}
	
}
