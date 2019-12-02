package com.cy.dao.check;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cy.domain.check.CheckShift;

public interface CheckShiftDao {
	
	CheckShift getShiftByType(@Param(value="shiftType")String shiftType);
	
	List<CheckShift> getShift(CheckShift shift );
	
	int count(CheckShift shift);
	
	int update(CheckShift shift);
	
	int insert(CheckShift shift);
	
	void deleteShift(Integer id);
	void deleteBatchShift(Integer id);

}
