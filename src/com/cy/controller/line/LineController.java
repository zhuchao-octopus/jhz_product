package com.cy.controller.line;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.domain.line.DashBoard;
import com.cy.domain.line.ProductDetails;
import com.cy.domain.line.ProductWorker;
import com.cy.domain.line.WorkLine;
import com.cy.domain.line.WorkLocation;
import com.cy.domain.line.WorkStationCode;
import com.cy.service.line.WorkLineService;
import com.cy.utils.CyResult;
@RequestMapping("line")
@Controller
public class LineController {
	
	@Resource
	private WorkLineService service;
	
	@RequestMapping("/loadlines.do")
	@ResponseBody
	public CyResult loadlineAll(Integer page , Integer limit) throws ParseException{
		CyResult result=service.findAllline(page, limit);
		return result;
	}
	
	@RequestMapping("/addLine.do")
	@ResponseBody
	public CyResult addLine(String lineCode,String pid,String rentApport,String scstate,String timeScope1,String timeScope2,String flushTime){
		CyResult result=service.addWorkLine(lineCode,pid,rentApport,scstate,timeScope1,timeScope2,flushTime);
		return result;
	}
    
	
	@RequestMapping("/loadOptions.do")
	@ResponseBody
	public CyResult loadOptions(String lineCode,String state,Integer pid,String pname){
		CyResult result=service.loadSelectOption(lineCode,state,pid, pname);
		return result;
	}
    
	
	/*
	 * 加载出当前产线下所有的工位 
	 */
	@RequestMapping("/loadLocation.do")
	@ResponseBody
	public CyResult loadLocation(WorkLine line){
		CyResult result=service.loadLocation(line);
		return result;
	}
	
	/*
	 * 新增工位
	 */
	@RequestMapping("/addLocation.do")
	@ResponseBody
	public CyResult addLocation(WorkLocation location,ProductWorker  worker,WorkStationCode station,WorkLine line) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		location.setWorker(worker);
		location.setWsCode(station);
		location.setLine(line);
		System.out.println("工位信息:"+location);
		CyResult result=service.addLocation(location);
		return result;
	}
	
	/*
	 * 修改产线和工位
	 */
	@RequestMapping("/saveLine.do")
	@ResponseBody
	public CyResult saveLine(WorkLocation location,ProductWorker worker,WorkLine line,WorkStationCode station) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
	
		CyResult result=service.saveLine(location,worker,line,station);
		return result;
	}
	
	/*
	 * 删除工位
	 */
	@RequestMapping("/deleteLocation.do")
	@ResponseBody
	public CyResult deleteLocation(WorkLocation location){
		CyResult result=service.deleteLocation(location);
		return result;
	}
	
	
	/*
	 * 删除产线
	 */
	@RequestMapping("/deleteLine.do")
	@ResponseBody
	public CyResult deleteLine(WorkLine line){
		CyResult result = service.deleteLine(line);
		return result;
	}
	
	
	/*
	 * 加载看板
	 */
	@RequestMapping("/loadBorad.do")
	@ResponseBody
	public CyResult loadBorad(DashBoard db, Integer page, Integer limit){
		CyResult result=service.loadBorad(db,page,limit);
		return result;
	}
	
	
	/*
	 * 增加一快看板
	 */
	@RequestMapping("/addBoard.do")
	@ResponseBody
	public CyResult addBoard(DashBoard db,Integer lid) throws ParseException{
		CyResult result=service.addBoard(db,lid);
		return result;
	}
	
	/*
	 * 删除看板
	 */
	@RequestMapping("/deleteBoard.do")
	@ResponseBody
	public CyResult deleteBoard(DashBoard db){
		CyResult result=service.deleteBoard(db);
		return result;
	}
	
	
	/*
	 * 删除看板
	 */
	@RequestMapping("/updateBoard.do")
	@ResponseBody
	public CyResult updatBoard(DashBoard db,WorkLine line){
		CyResult result=service.updatBoard(db,line);
		return result;
	}
	
	
	/*
	 * 增加产品大类
	 * 
	 */
	@RequestMapping("/addProduct.do")
	@ResponseBody
	public CyResult addProduct(ProductDetails pd){
		
		CyResult result=service.addProduct(pd);
		return result;
	}
	
	@RequestMapping("/editProduct.do")
	@ResponseBody
	public CyResult editProduct(ProductDetails pd){
		
		CyResult result=service.editPd(pd);
		return result;
	}
	
	/**
	 * 删除产品
	 * @param pid
	 * @return
	 */
	@RequestMapping("/deletePd.do")
	@ResponseBody
	public CyResult deleteById(Integer pid){
	    CyResult result=service.deleteById(pid);
	    return result;
	}
	
	/*
	 * 产线设置
	 */
	@RequestMapping("/setLine.do")
	@ResponseBody
	public CyResult setLine(WorkLine line,Integer pid){
		CyResult result=service.setLineByLine(line,pid);
		return result;
	}
	
	/*
	 * 加载看板数据
	 */
	@RequestMapping("/loadBoardData.do")
	@ResponseBody
	public CyResult loadBoardData(WorkLine line) throws ParseException{
		CyResult result=service.loadDashBoradData(line);
		return result;
	}
	
	/**
	 * 分页加载产品名
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping("/loadProductDetails.do")
	@ResponseBody
	public CyResult loadProductDetails(Integer page , Integer limit){
		CyResult result = service.loadProductDetails(page,limit);
		return result;
	}
	
	/**
	 * 分页加载工位表
	 * @param page
	 * @param limit
	 * @param lineCode
	 * @return
	 */
	@RequestMapping("/loadWorkLocation.do")
	@ResponseBody
	public CyResult loadWorkLocation(Integer page , Integer limit, WorkLocation workLocation,ProductWorker worker){
		workLocation.setWorker(worker);
		CyResult result = service.loadWorkLocation(page,limit,workLocation);
		return result;
	}
	
	@RequestMapping("/loadAllworkLine.do")
	@ResponseBody
	public CyResult loadAllworkLine(){
		CyResult result = service.loadAllworkLine();
		return result;
	}
	
	public CyResult insertWorkLine(WorkLine workLine){
		CyResult result = service.insertWorkLine(workLine);
		return result;
	}
	
	@RequestMapping("/loadWorkerByLineCode.do")
	@ResponseBody
	public CyResult loadWorkerByLineCode(WorkLine line){
		CyResult result = service.loadWorkerByLineCode(line);
		return result;
	}
	

	@RequestMapping("/updateWorkerLineByCode.do")//绑定产线
	@ResponseBody
	public CyResult updateWorkerLineByCode(String lineCode,String newArrString){
		CyResult result = service.updateWorkerLineByCode(lineCode,newArrString);
		return result;
	}
	@RequestMapping("/deletePersonnel.do")
	@ResponseBody
	public CyResult deletePersonnel(Integer wid){
		System.out.println("workline-->"+wid);
		CyResult result = service.deletePersonnel(wid);
		return result;
	}
	
	
	
}
