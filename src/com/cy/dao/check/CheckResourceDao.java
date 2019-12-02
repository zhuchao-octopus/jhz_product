package com.cy.dao.check;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cy.domain.check.CheckView;

public interface CheckResourceDao {
	
	/**
	 * 根据员工工号、考勤日期查询员工考勤详细信息
	 * @param workerCode
	 * @param Date
	 * @return
	 */
	List<CheckView> getCheckByCodeAndDate(@Param(value="workerCode")String workerCode,@Param(value="attendanceStartDate")String attendanceStartDate,@Param(value="attendanceEndDate")String attendanceEndDate);
}
