package com.cy.service.check;

import java.text.ParseException;

import com.cy.domain.line.ProductDetails;
import com.cy.domain.line.ProductWorker;
import com.cy.domain.line.WorkLine;
import com.cy.utils.CyResult;

public interface NewCheckService {

	public CyResult checkDown(ProductWorker worker, WorkLine line, Integer status) throws ParseException;
   
	/*
	 * 切换产品
	 */
	public CyResult upProductByLine(ProductDetails pd,WorkLine line);

	public CyResult loadNewCheck(ProductWorker worker);//加载出人员的打卡记录

	public CyResult loadWorkTime(ProductWorker worker) throws ParseException;
    
	/*
	 * 切换工艺
	 */
	public CyResult upScstate(WorkLine line);

	public CyResult updateLocationState();

	public CyResult loadAppNewCheck(String workerCode);

	public CyResult appLoadWorkerByCode(String workerCode);

}
