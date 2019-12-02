package com.cy.service.check;

import com.cy.utils.CyResult;

public interface CheckResourceService {

	CyResult getCheckByCodeAndDate(String workerCode,String attendanceStartDate,String attendanceEndDate);
}
