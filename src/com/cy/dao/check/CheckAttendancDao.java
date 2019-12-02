package com.cy.dao.check;

import java.util.List;

import com.cy.domain.check.CheckAttendanc;

public interface CheckAttendancDao {

	List<CheckAttendanc> getCheckAttendanc(CheckAttendanc attendanc);
	
	int getCount(CheckAttendanc attendanc);
	
	int insert(CheckAttendanc attendanc);
	
	int update(CheckAttendanc attendanc);
	
	void delete(Integer id);
	
	void deleteBatch(Integer id);
}
