package com.cy.domain.check;

import java.io.Serializable;
import java.util.List;

import com.cy.domain.line.WorkLine;

public class Check implements Serializable {


	private static final long serialVersionUID = 1L;
	private Integer checkid;
	private String checktime;
	private String restime;
	private String checkinterval;
	private String checktotal;
	private List<WorkLine> workLineList;
	public Integer getCheckid() {
		return checkid;
	}
	public void setCheckid(Integer checkid) {
		this.checkid = checkid;
	}
	public String getChecktime() {
		return checktime;
	}
	public void setChecktime(String checktime) {
		this.checktime = checktime;
	}
	public String getRestime() {
		return restime;
	}
	public void setRestime(String restime) {
		this.restime = restime;
	}
	public String getCheckinterval() {
		return checkinterval;
	}
	public void setCheckinterval(String checkinterval) {
		this.checkinterval = checkinterval;
	}
	public String getChecktotal() {
		return checktotal;
	}
	public void setChecktotal(String checktotal) {
		this.checktotal = checktotal;
	}
	
	public List<WorkLine> getWorkLineList() {
		return workLineList;
	}
	public void setWorkLineList(List<WorkLine> workLineList) {
		this.workLineList = workLineList;
	}
	@Override
	public String toString() {
		return "Check [checkid=" + checkid + ", checktime=" + checktime + ", restime=" + restime + ", checkinterval="
				+ checkinterval + ", checktotal=" + checktotal + ", workLineList=" + workLineList + "]";
	}
	
	
   

}
