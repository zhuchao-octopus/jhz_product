package com.cy.domain.line;

import java.io.Serializable;
import java.util.List;

public class daily_production_list implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer lid;//产线id
	private String lineCode;//产线名称
	private Integer pid;//产品id
	private String pname;//产品名
	private String orderNumber;//订单号
	private String explain;//说明
	private Integer targetProduction;//生产目标
	private Integer actualProduction;//实际生产
	private String time;//生产时间
	private Integer yield;//今日产量
	private Integer cumulativeOutput;//累计数量
	private String abnormal;//异常
	private String impositionDate;//拼版周期
	private List<daily_production_list> list;
	private Integer page;
	private Integer pageSize;
	private Integer limit;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImpositionDate() {
		return impositionDate;
	}
	public void setImpositionDate(String impositionDate) {
		this.impositionDate = impositionDate;
	}
	public List<daily_production_list> getList() {
		return list;
	}
	public void setList(List<daily_production_list> list) {
		this.list = list;
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
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getLid() {
		return lid;
	}
	public void setLid(Integer lid) {
		this.lid = lid;
	}
	public String getLineCode() {
		return lineCode;
	}
	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public Integer getTargetProduction() {
		return targetProduction;
	}
	public void setTargetProduction(Integer targetProduction) {
		this.targetProduction = targetProduction;
	}
	public Integer getActualProduction() {
		return actualProduction;
	}
	public void setActualProduction(Integer actualProduction) {
		this.actualProduction = actualProduction;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getYield() {
		return yield;
	}
	public void setYield(Integer yield) {
		this.yield = yield;
	}
	public Integer getCumulativeOutput() {
		return cumulativeOutput;
	}
	public void setCumulativeOutput(Integer cumulativeOutput) {
		this.cumulativeOutput = cumulativeOutput;
	}
	public String getAbnormal() {
		return abnormal;
	}
	public void setAbnormal(String abnormal) {
		this.abnormal = abnormal;
	}
	@Override
	public String toString() {
		return "daily_production_list [lid=" + lid + ", lineCode=" + lineCode + ", pid=" + pid + ", pname=" + pname
				+ ", orderNumber=" + orderNumber + ", explain=" + explain + ", targetProduction=" + targetProduction
				+ ", actualProduction=" + actualProduction + ", time=" + time + ", yield=" + yield
				+ ", cumulativeOutput=" + cumulativeOutput + ", abnormal=" + abnormal + ", page=" + page + ", pageSize="
				+ pageSize + ", limit=" + limit + "]";
	}
	
	
}
