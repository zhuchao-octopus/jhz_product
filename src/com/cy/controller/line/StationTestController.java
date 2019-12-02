package com.cy.controller.line;
import java.text.ParseException;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.cy.service.line.StationTestService;

import com.cy.utils.CyResult;
@RequestMapping("/test")
@Controller
public class StationTestController {
	
	@Resource
	private StationTestService service;
	
	@RequestMapping("/loadTestStatus.do")
	@ResponseBody
	public CyResult loadTestStatus() throws ParseException{
		CyResult result=service.loadTestStatus();
		return result;
	}
	
	
	
	
	
	
}
