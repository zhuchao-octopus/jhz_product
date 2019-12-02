package com.cy.domain.check;

import java.io.Serializable;

public class WorkTime implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private String workerCode;
	private String workerName;
	private String lineCode;
	private String wtime;
	private String pname;
	private String nowDate;
	public String getWtime() {
		return wtime;
	}
	public void setWtime(String wtime) {
		this.wtime = wtime;
	}
	
	public String getNowDate() {
		return nowDate;
	}
	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}
	public String getWorkerCode() {
		return workerCode;
	}
	public void setWorkerCode(String workerCode) {
		this.workerCode = workerCode;
	}
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public String getLineCode() {
		return lineCode;
	}
	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lineCode == null) ? 0 : lineCode.hashCode());
		result = prime * result + ((nowDate == null) ? 0 : nowDate.hashCode());
		result = prime * result + ((pname == null) ? 0 : pname.hashCode());
		result = prime * result + ((workerCode == null) ? 0 : workerCode.hashCode());
		result = prime * result + ((workerName == null) ? 0 : workerName.hashCode());
		result = prime * result + ((wtime == null) ? 0 : wtime.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkTime other = (WorkTime) obj;
		if (lineCode == null) {
			if (other.lineCode != null)
				return false;
		} else if (!lineCode.equals(other.lineCode))
			return false;
		if (nowDate == null) {
			if (other.nowDate != null)
				return false;
		} else if (!nowDate.equals(other.nowDate))
			return false;
		if (pname == null) {
			if (other.pname != null)
				return false;
		} else if (!pname.equals(other.pname))
			return false;
		if (workerCode == null) {
			if (other.workerCode != null)
				return false;
		} else if (!workerCode.equals(other.workerCode))
			return false;
		if (workerName == null) {
			if (other.workerName != null)
				return false;
		} else if (!workerName.equals(other.workerName))
			return false;
		if (wtime == null) {
			if (other.wtime != null)
				return false;
		} else if (!wtime.equals(other.wtime))
			return false;
		return true;
	}
	

}
