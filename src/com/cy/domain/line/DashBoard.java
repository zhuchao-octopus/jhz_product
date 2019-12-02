package com.cy.domain.line;

import java.io.Serializable;

public class DashBoard implements Serializable{


	private static final long serialVersionUID = 1L;
	private Integer tid;
	private String startime;
	private String endtime;
	private WorkLine line;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getStartime() {
		return startime;
	}
	public void setStartime(String startime) {
		this.startime = startime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public WorkLine getLine() {
		return line;
	}
	public void setLine(WorkLine line) {
		this.line = line;
	}
	@Override
	public String toString() {
		return "DashBoard [tid=" + tid + ", startime=" + startime + ", endtime=" + endtime + ", line=" + line
				+ ", pageSize=" + pageSize + ", limit=" + limit + "]";
	}
    
	
}
