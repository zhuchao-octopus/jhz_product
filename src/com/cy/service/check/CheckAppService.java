package com.cy.service.check;

import java.text.ParseException;

import com.cy.domain.line.ProductDetails;
import com.cy.domain.line.WorkLine;
import com.cy.utils.CyResult;

public interface CheckAppService {

	CyResult loadAppProducts(ProductDetails pd) throws ParseException;

	CyResult loadAppline(WorkLine line) throws ParseException;

	CyResult loadApplineAndPname();

}
