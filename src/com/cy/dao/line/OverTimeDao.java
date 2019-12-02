package com.cy.dao.line;

import java.util.List;

import com.cy.domain.line.OverTime;

public interface OverTimeDao {
	public  List<OverTime> findOverTime(OverTime ovt);
	
	public int addOverTime(OverTime ovt);
	
	public int editOvertimeById(OverTime ovt);
	
    public int deleteById(OverTime ovt);
}
