package com.cy.service.line;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

import com.cy.domain.line.DashBoard;
import com.cy.domain.line.ProductDetails;
import com.cy.domain.line.ProductWorker;
import com.cy.domain.line.WorkLine;
import com.cy.domain.line.WorkLocation;
import com.cy.domain.line.WorkStationCode;
import com.cy.domain.line.daily_production_list;
import com.cy.utils.CyResult;

public interface WorkLineService {
	public CyResult findAllline(Integer page , Integer limit) throws ParseException;
	/*
	 * 增加产线
	 */
	public CyResult addWorkLine(String lineCode, String pid,String rentApport, String scstate, String timeScope1, String timeScope2, String flushTime);
	/*
	 * 加载出一条产线的详情
	 */
	public CyResult loadLineById(WorkLine line);
	
	/*
	 * 数据字典
	 */
	public CyResult loadSelectOption(String lineCode,String state,Integer pid, String pname);
	
	/*
	 * 加载出一条产线下面的工位详情
	 */
    public CyResult loadLocation(WorkLine line);
    
    /*
     * 在当前产线增加工位
     */
	public CyResult addLocation(WorkLocation location) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	
	/*
	 * 修改产线和工位
	 */
	public CyResult saveLine(WorkLocation location, ProductWorker worker, WorkLine line,WorkStationCode station) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
    
	/*
	 * 删除工位
	 */
	public CyResult deleteLocation(WorkLocation location);
	
	/*
	 * 删除产线
	 */
	public CyResult deleteLine(WorkLine line);
	
	/*
	 * 员工工时列表查看
	 */
	public CyResult hourList(ProductWorker worker);
	
	
	
	/*
	 * 加载出所有的看板
	 */
	public CyResult loadBorad(DashBoard board, Integer page, Integer limit);
	
	/*
	 *增加一快看版
	 */
	public CyResult addBoard(DashBoard board,Integer lid) throws ParseException;
	/*
	 * 删除看板
	 */
	public CyResult deleteBoard(DashBoard db);
	//增加产品大类
	public CyResult addProduct(ProductDetails pd);
	//编辑产品大类
	public CyResult editPd(ProductDetails pd);
	//删除产品大类
	public CyResult deleteById(Integer pid);
	public CyResult setLineByLine(WorkLine line, Integer pid);
	
	
	
	//app端看板 展示生产数据
	public CyResult loadDashBoradData(WorkLine line) throws ParseException;
	public CyResult loadProductDetails(Integer page, Integer limit);
	public CyResult loadWorkLocation(Integer page, Integer limit, WorkLocation workLocation);
	public CyResult loadAllworkLine();
	public CyResult insertWorkLine(WorkLine workLine);
	public CyResult updatBoard(DashBoard db, WorkLine line);
	public CyResult loadWorkerByLineCode(WorkLine line);

	public CyResult updateWorkerLineByCode(String lineCode, String newArrString);
	public CyResult deletePersonnel(Integer wid);

	
}
