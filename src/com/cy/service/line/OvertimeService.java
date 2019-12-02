package com.cy.service.line;

import java.util.List;

import com.cy.domain.line.OverTime;
import com.cy.domain.line.ProductWorker;
import com.cy.domain.line.WorkLine;
import com.cy.utils.CyResult;

public interface OvertimeService {
	public CyResult loadOvertime(OverTime ovt);

	public CyResult loadWorkerByLine(WorkLine line);

	public CyResult addOverTime(OverTime ovt, String workerStr);


	public CyResult editOvertimeById(OverTime ovt, String codeAndName);

	public CyResult deleteById(OverTime ovt);
}
