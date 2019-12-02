package com.cy.dao.line;

import java.util.List;




import com.cy.domain.check.CheckModel;
import com.cy.domain.line.CheckRes;
import com.cy.domain.line.JhzRole;
import com.cy.domain.line.ProductWorker;
import com.cy.domain.line.WorkStationMark;

public interface ProductWorkerDao {
	
	public int addFinger(ProductWorker worker);
	public List<ProductWorker> findWorker(ProductWorker worker);
	
	public int addWorker(ProductWorker worker);
	
	public int deleteWorker(ProductWorker worker);
	
	public int updateWorker(ProductWorker worker);
	
	
	/*
	 * 查詢出一個人員的工時列表
	 */
    public List<CheckModel>	findCheckByWid(ProductWorker worker);
    
    public ProductWorker findWorkerById(ProductWorker worker);
    
    public ProductWorker findWorkerByCode(String workerCode);
	public List<ProductWorker> findCountWorker(ProductWorker worker);
	public void deleteBatchWorker(String string);
	public List<JhzRole> loadAllRole(JhzRole role);
	public int countAllRole();
	public int insertRole(JhzRole role);
	public int addMoreWorker(List<ProductWorker> workerList);
	public List<CheckRes> checkResource(CheckRes checkRes);
	public int addCheckRes(CheckRes checkRes);
	public int delCheckRes(CheckRes checkRes);
	public List<ProductWorker> findbdWorker(ProductWorker worker);
	public int findbdWorkerCount(ProductWorker worker);
	
	public List<ProductWorker> findAllWorkerByDemo(ProductWorker worker);
	int findAllWorkerByDemoCount(ProductWorker worker);
	public List<WorkStationMark> loadWorkStationMark(WorkStationMark workStationMark);
	public int countLoadWorkStationMark(WorkStationMark workStationMark);
	public int findWorkerByWorkerCode(String workerCode);
	
}
