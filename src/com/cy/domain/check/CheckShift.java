package com.cy.domain.check;

import java.io.Serializable;

public class CheckShift implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String checkDate;
	private String beforeNoonDown;
	private String afterNoonUp;
	private String afterNoonDown;
	private String overWorkUp;
	private String overWorkDown;
	private String shiftType;
	private Integer status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public String getBeforeNoonDown() {
		return beforeNoonDown;
	}
	public void setBeforeNoonDown(String beforeNoonDown) {
		this.beforeNoonDown = beforeNoonDown;
	}
	public String getAfterNoonUp() {
		return afterNoonUp;
	}
	public void setAfterNoonUp(String afterNoonUp) {
		this.afterNoonUp = afterNoonUp;
	}
	
	public String getAfterNoonDown() {
		return afterNoonDown;
	}
	public void setAfterNoonDown(String afterNoonDown) {
		this.afterNoonDown = afterNoonDown;
	}
	public String getOverWorkUp() {
		return overWorkUp;
	}
	public void setOverWorkUp(String overWorkUp) {
		this.overWorkUp = overWorkUp;
	}
	public String getOverWorkDown() {
		return overWorkDown;
	}
	public void setOverWorkDown(String overWorkDown) {
		this.overWorkDown = overWorkDown;
	}
	public String getShiftType() {
		return shiftType;
	}
	public void setShiftType(String shiftType) {
		this.shiftType = shiftType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CheckShift [id=" + id + ", checkDate=" + checkDate + ", beforeNoonDown=" + beforeNoonDown
				+ ", afterNoonUp=" + afterNoonUp + ", afterNoonDown=" + afterNoonDown + ", overWorkUp=" + overWorkUp
				+ ", overWorkDown=" + overWorkDown + ", shiftType=" + shiftType + ", status=" + status + "]";
	}
	
	
}
