package com.cy.controller.check;

import java.text.ParseException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.domain.line.ProductDetails;
import com.cy.domain.line.ProductWorker;
import com.cy.domain.line.WorkLine;
import com.cy.service.check.NewCheckService;
import com.cy.utils.CyResult;

@Controller
@RequestMapping("/newCheck")
public class NewCheckController {
	
	@Resource
	private NewCheckService service;
	
	@RequestMapping("/checkDown.do")
	@ResponseBody
	public CyResult checkDown(ProductWorker worker,WorkLine line,Integer status) throws ParseException{
		CyResult result=service.checkDown(worker,line,status);
		return result;
	}
	
	
	/*
	 * 切换产品
	 */
	@RequestMapping("/upProductByLine.do")
    @ResponseBody
    public CyResult upProductByLine(ProductDetails pd,WorkLine line){
		CyResult result=service.upProductByLine(pd,line);
		return result;
	}
	
	/*
	 * 切换工艺
	 */
	@RequestMapping("/upScstate.do")
    @ResponseBody
    public CyResult upProductByLine(WorkLine line){
		CyResult result=service.upScstate(line);
		return result;
	}
	
	/*
	 * 加载出人员的打卡记录
	 */
	@RequestMapping("/loadNewCheck.do")
	@ResponseBody
	public CyResult loadNewCheckByWid(ProductWorker worker){
		
		CyResult result=service.loadNewCheck(worker);
		return result;
	}
	
	
	/*
	 * 加载出人员的工时列表
	 */
    @RequestMapping("/loadWorkTime.do")
    @ResponseBody
    public CyResult loadWorkTime(ProductWorker worker) throws ParseException{
    	CyResult result=service.loadWorkTime(worker);
    	return result;
    }
    
    
    /*
     * 定时发送请求，修改状态
     */
    @RequestMapping("/updateLocationState.do")
    @ResponseBody
    public CyResult updateLocationState(){
    	CyResult result=service.updateLocationState();
    	return result;
    }
    
    
    
    /*
	 * App加载出人员的打卡记录
	 */
	@RequestMapping("/loadAppNewCheck.do")
	@ResponseBody
	public CyResult loadAppNewCheckByWorkerCode(String workerCode){
		
		CyResult result=service.loadAppNewCheck(workerCode);
		return result;
	}
    
	 /*
     * App端加载人员的详情
     */
	@RequestMapping("/loadAppWorker.do")
	@ResponseBody
	public CyResult loadAppWorkerByCode(String workerCode){
		
		CyResult result=service.appLoadWorkerByCode(workerCode);
		return result;
	}

}

