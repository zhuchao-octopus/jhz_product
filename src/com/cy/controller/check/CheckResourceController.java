package com.cy.controller.check;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.service.check.CheckResourceService;
import com.cy.utils.CyResult;

@Controller
public class CheckResourceController {
	@Resource
	private CheckResourceService service;
	
	@RequestMapping("/getResource.do")
	@ResponseBody
	public CyResult getResourceService(String workerCode,String attendanceStartDate,String attendanceEndDate){
		CyResult result = service.getCheckByCodeAndDate(workerCode, attendanceStartDate, attendanceEndDate);
		return result;
	}
}
