package com.cy.controller.check;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.domain.line.ProductWorker;
import com.cy.domain.line.WorkLine;
import com.cy.service.check.CheckService;
import com.cy.utils.CyResult;

@RequestMapping("/check")
@Controller
public class CheckController {
   
	@Resource
	private CheckService service;
	
	@RequestMapping("/loadCheck.do")
	@ResponseBody
	public CyResult findCheckByLine(WorkLine line){
		CyResult result=service.findCheckByLine(line);
		return result;
	}
	
	@RequestMapping("/checkDone.do")
	@ResponseBody
	public CyResult checkLine(WorkLine line,ProductWorker worker,Integer status) throws Exception{
		System.out.println(line.getLineCode());
		System.out.println(worker.getWorkerCode());
		System.out.println(status);
		System.out.println("******************");
		CyResult result=service.checkDone(line,worker,status);
		return result;
	}
	
	//app加载当人当天的工时
    @RequestMapping("/loadTimeByWorker.do")
    @ResponseBody
    public CyResult loadTimeByWorker(ProductWorker worker){
    	CyResult result=service.loadTimeByWorker(worker);
    	return result;
    }
    
   //app计算调线功能
    @RequestMapping("/lineUp.do")
    @ResponseBody
    public CyResult lineUp(ProductWorker worker,WorkLine line){
    	CyResult result=service.lineUp(worker,line);
    	return result;
    }
    
    //app请假
    @RequestMapping("/askForLeave.do")
     @ResponseBody
     public CyResult askForLeave(ProductWorker worker,Integer state){
    	CyResult result=service.askForLeave(worker,state);
    	return result;
    }
    
    
    
    
    
   
    
    
    
}
