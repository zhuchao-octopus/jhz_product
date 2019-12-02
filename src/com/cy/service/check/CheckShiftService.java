package com.cy.service.check;

import com.cy.domain.check.CheckShift;
import com.cy.utils.CyResult;

public interface CheckShiftService {

	CyResult getShift(CheckShift shift);
	
	CyResult insert(CheckShift shift);
	
	
	CyResult deleteShift(String id);
	CyResult deleteBatchShift(String strId);
	
	CyResult update(CheckShift shift);
}
