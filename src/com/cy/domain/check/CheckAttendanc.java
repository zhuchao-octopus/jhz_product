package com.cy.domain.check;

import java.io.Serializable;

public class CheckAttendanc implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String workerCode;
	private String workerName;
	private String attendanceStartDate;
	private String attendanceEndDate;
	private Integer absenteeism;
	private Integer leave;
	private Integer travel;
	private Integer goToWork;
	private Integer late;
	private Integer leaveEarly;
	private Double normalOvertime;
	private Double specialOvertime;
	private String date;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getAttendanceStartDate() {
		return attendanceStartDate;
	}
	public void setAttendanceStartDate(String attendanceStartDate) {
		this.attendanceStartDate = attendanceStartDate;
	}
	public String getAttendanceEndDate() {
		return attendanceEndDate;
	}
	public void setAttendanceEndDate(String attendanceEndDate) {
		this.attendanceEndDate = attendanceEndDate;
	}
	public Integer getAbsenteeism() {
		return absenteeism;
	}
	public void setAbsenteeism(Integer absenteeism) {
		this.absenteeism = absenteeism;
	}
	public Integer getLeave() {
		return leave;
	}
	public void setLeave(Integer leave) {
		this.leave = leave;
	}
	public Integer getTravel() {
		return travel;
	}
	public void setTravel(Integer travel) {
		this.travel = travel;
	}
	public Integer getGoToWork() {
		return goToWork;
	}
	public void setGoToWork(Integer goToWork) {
		this.goToWork = goToWork;
	}
	public Integer getLate() {
		return late;
	}
	public void setLate(Integer late) {
		this.late = late;
	}
	public Integer getLeaveEarly() {
		return leaveEarly;
	}
	public void setLeaveEarly(Integer leaveEarly) {
		this.leaveEarly = leaveEarly;
	}
	public Double getNormalOvertime() {
		return normalOvertime;
	}
	public void setNormalOvertime(Double normalOvertime) {
		this.normalOvertime = normalOvertime;
	}
	public Double getSpecialOvertime() {
		return specialOvertime;
	}
	public void setSpecialOvertime(Double specialOvertime) {
		this.specialOvertime = specialOvertime;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CheckAttendanc [id=" + id + ", workerCode=" + workerCode + ", workerName=" + workerName
				+ ", attendanceStartDate=" + attendanceStartDate + ", attendanceEndDate=" + attendanceEndDate
				+ ", absenteeism=" + absenteeism + ", leave=" + leave + ", travel=" + travel + ", goToWork=" + goToWork
				+ ", late=" + late + ", leaveEarly=" + leaveEarly + ", normalOvertime=" + normalOvertime
				+ ", specialOvertime=" + specialOvertime + ", date=" + date + "]";
	}


}
