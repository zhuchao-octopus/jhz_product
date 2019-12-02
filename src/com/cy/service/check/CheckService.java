package com.cy.service.check;

import com.cy.domain.line.ProductWorker;
import com.cy.domain.line.WorkLine;
import com.cy.utils.CyResult;


public interface CheckService {
	public CyResult findCheckByLine(WorkLine line);
	
	//public CyResult writeTime(CheckResource resource,ProductWorker worker);

	public CyResult checkDone(WorkLine line, ProductWorker worker, Integer status) throws Exception;

	public CyResult loadTimeByWorker(ProductWorker worker);

	public CyResult lineUp(ProductWorker worker, WorkLine line);

	public CyResult askForLeave(ProductWorker worker, Integer state);

}
