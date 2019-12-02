package com.cy.domain.line;

import java.io.Serializable;
import java.util.List;

import com.cy.domain.check.Check;
import com.cy.domain.list.ProductScheduling;
/*
 * 产线
 */
public class WorkLine implements Serializable {

	private static final long serialVersionUID = 1L;
    private Integer lid;
    private Integer countValue;
    private Integer status;
    private Integer number;
    private String lineCode;
    private Integer pid;
    private String pname;
    private String price;
    private String creatime;
    private List<WorkLocation> list;
    private Double rentApport;//厂租分摊
    private Double pinput;//生产投入
    private Double poutput;//生产产出
    private Integer profitAndLoss;//盈亏 盈:1  亏:0
    private Check check;
    private Integer checkNum;
    private String scstate;//工艺
    private String timeScope1;//时间组1
    private String timeScope2;//时间组2
    private String flushTime;
    private Integer page;
    private Integer pageSize;
    
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	private Integer limit;
	private ProductDetails product;

    
	

	public ProductDetails getProduct() {
		return product;
	}
	public void setProduct(ProductDetails product) {
		this.product = product;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
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
	public String getTimeScope1() {
		return timeScope1;
	}
	public void setTimeScope1(String timeScope1) {
		this.timeScope1 = timeScope1;
	}
	public String getTimeScope2() {
		return timeScope2;
	}
	public void setTimeScope2(String timeScope2) {
		this.timeScope2 = timeScope2;
	}
	public Integer getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(Integer checkNum) {
		this.checkNum = checkNum;
	}
	
	public Integer getLid() {
		return lid;
	}
	public void setLid(Integer lid) {
		this.lid = lid;
	}
	
	public Integer getCountValue() {
		return countValue;
	}
	public void setCountValue(Integer countValue) {
		this.countValue = countValue;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getLineCode() {
		return lineCode;
	}
	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}
	
	public List<WorkLocation> getList() {
		return list;
	}
	public void setList(List<WorkLocation> list) {
		this.list = list;
	}
	public String getCreatime() {
		return creatime;
	}
	public void setCreatime(String creatime) {
		this.creatime = creatime;
	}
	
	
	public Check getCheck() {
		return check;
	}
	public void setCheck(Check check) {
		this.check = check;
	}
	
	public Double getRentApport() {
		return rentApport;
	}
	public void setRentApport(Double rentApport) {
		this.rentApport = rentApport;
	}
	public Double getPinput() {
		return pinput;
	}
	public void setPinput(Double pinput) {
		this.pinput = pinput;
	}
	public Double getPoutput() {
		return poutput;
	}
	public void setPoutput(Double poutput) {
		this.poutput = poutput;
	}
	public Integer getProfitAndLoss() {
		return profitAndLoss;
	}
	public void setProfitAndLoss(Integer profitAndLoss) {
		this.profitAndLoss = profitAndLoss;
	}
	
	
	public String getScstate() {
		return scstate;
	}
	public void setScstate(String scstate) {
		this.scstate = scstate;
	}

	public String getFlushTime() {
		return flushTime;
	}
	public void setFlushTime(String flushTime) {
		this.flushTime = flushTime;
	}
	@Override
	public String toString() {
		return "WorkLine [lid=" + lid + ", countValue=" + countValue
				+ ", status=" + status + ", number=" + number + ", lineCode="
				+ lineCode + ", pid=" + pid + ", pname=" + pname + ", price="
				+ price + ", creatime=" + creatime + ", list=" + list
				+ ", rentApport=" + rentApport + ", pinput=" + pinput
				+ ", poutput=" + poutput + ", profitAndLoss=" + profitAndLoss
				+ ", check=" + check + ", checkNum=" + checkNum + ", scstate="
				+ scstate + ", timeScope1=" + timeScope1 + ", timeScope2="
				+ timeScope2 + ", flushTime=" + flushTime + ", page=" + page
				+ ", pageSize=" + pageSize + ", limit=" + limit + ", product="
				+ product + "]";
	}
	
	
    
}
