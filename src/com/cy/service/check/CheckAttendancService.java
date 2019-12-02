package com.cy.service.check;

import com.cy.domain.check.CheckAttendanc;
import com.cy.utils.CyResult;

public interface CheckAttendancService {

	CyResult getAttendanc(CheckAttendanc attendanc);
	
	CyResult insert(CheckAttendanc attendanc);
	
	CyResult update(CheckAttendanc attendanc);
	
	CyResult delete(String id);
	
	CyResult deleteBatch(String id);
}
