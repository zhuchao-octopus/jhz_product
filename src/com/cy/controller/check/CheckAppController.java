package com.cy.controller.check;

import java.text.ParseException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.dao.line.WorkLineDao;
import com.cy.domain.line.ProductDetails;
import com.cy.domain.line.WorkLine;
import com.cy.service.check.CheckAppService;
import com.cy.utils.CyResult;

@Controller
@RequestMapping("/app")
public class CheckAppController {
    
	@Resource
	private CheckAppService appService;
	
	@RequestMapping("/loadAppObj.do")
	@ResponseBody
	public CyResult loadAppProducts(ProductDetails pd) throws ParseException{
		CyResult result=appService.loadAppProducts(pd);
		return result;
	}
	
	
	@RequestMapping("/loadAppline.do")
	@ResponseBody
	public  CyResult loadAppline(WorkLine line) throws ParseException{
		CyResult result=appService.loadAppline(line);
		return result;
	}
	
	@RequestMapping("/loadApplineAndPname.do")
	@ResponseBody
	public CyResult loadApplineAndPname(){
		CyResult result=appService.loadApplineAndPname();
		return result;
	}
}
