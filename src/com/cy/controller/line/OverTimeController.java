package com.cy.controller.line;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.domain.line.OverTime;
import com.cy.domain.line.ProductWorker;
import com.cy.domain.line.WorkLine;
import com.cy.service.line.OvertimeService;
import com.cy.utils.CyResult;

@Controller
@RequestMapping("/overtime")
public class OverTimeController {
	
	@Resource
	private OvertimeService service;
	
	@RequestMapping("/loadOvertime.do")
	@ResponseBody
	public CyResult loadOvertime(OverTime ovt){
		CyResult result=service.loadOvertime(ovt);
		return result;
	}
	
	
	//change事件下拉选
	@RequestMapping("/loadWorkerByLine.do")
	@ResponseBody
    public CyResult loadWorkerByLine(WorkLine line){
		CyResult result=service.loadWorkerByLine(line);
		return result;
	}
	
	//增加申请
	@RequestMapping("/addOverTime.do")
	@ResponseBody
	public CyResult addOverTime(OverTime ovt,String workerStr){
		CyResult result=service.addOverTime(ovt,workerStr);
		return result;
	}
	
	//编辑申请
	@RequestMapping("/editOvertimeById.do")
	@ResponseBody
	public CyResult editOvertimeById(OverTime ovt,String codeAndName){
		CyResult result=service.editOvertimeById(ovt,codeAndName);
		return result;
		
	}
	
	//删除
	@RequestMapping("/deleteById.do")
    @ResponseBody
    public  CyResult deleteById(OverTime ovt){
		CyResult result=service.deleteById(ovt);
		return result;
	}
}
