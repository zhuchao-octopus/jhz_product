package com.cy.service.line;

import java.text.ParseException;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.cy.domain.line.CheckRes;
import com.cy.domain.line.JhzRole;
import com.cy.domain.line.ProductWorker;
import com.cy.domain.line.WorkLine;
import com.cy.domain.line.WorkStationMark;
import com.cy.utils.CyResult;

public interface ProductWorkerService {
	
	public CyResult addWorker(ProductWorker worker);

	public CyResult findWorker(ProductWorker worker,String obj);
	
	public CyResult deleteWorker(ProductWorker worker);
	
	public CyResult updateWorker(ProductWorker worker);

	public CyResult loadWorkerHour(ProductWorker worker) throws ParseException;

	public CyResult findNewWorker(ProductWorker worker, Integer page, Integer limit);

	public CyResult deleteBatchWorker(String workerStr);

	public CyResult loadAllRole(Integer page, Integer limit);

	public CyResult insertRole(JhzRole role);

	public CyResult workerMoreAdd(MultipartFile file, ProductWorker worker, HttpServletRequest request) throws Exception;

	public CyResult checkResource(CheckRes checkRes, Integer page, Integer limit);

	public CyResult addCheckWorker(CheckRes checkRes);

	public CyResult delCheckResource(CheckRes checkRes);

	public CyResult delCheckResByRids(CheckRes checkRes);

	public CyResult findNewbdWorker(ProductWorker worker, Integer page,
			Integer limit);

	public CyResult workStationMark(WorkStationMark workStationMark,
			Integer page, Integer limit);

}
