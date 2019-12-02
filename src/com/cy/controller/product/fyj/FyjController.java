package com.cy.controller.product.fyj;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.domain.product.fyj.FyjCode;
import com.cy.service.product.FyjCodeService;
import com.cy.utils.CyResult;
@RequestMapping("fyj")
@Controller
public class FyjController {
	
	@Resource
	private FyjCodeService service;
    
	@RequestMapping("/exportByBoxNum.do")
	@ResponseBody
	public CyResult exportByboxNum(String boxNum) throws Exception{
		CyResult result=service.exportByBoxNum(boxNum);
		return result;
	}
	
	@RequestMapping("/exportToBoxNums.do")
	@ResponseBody
	public CyResult exportToBoxNums(String boxNum1,String boxNum2) throws Exception{
		CyResult result=service.exportToBoxNums(boxNum1,boxNum2);
		return result;
	}
	
	@RequestMapping("/loadCode.do")
	@ResponseBody
	public CyResult loadCode(FyjCode code){
		CyResult result=service.loadCode(code);
		return result;
	}
	
	@RequestMapping("/deleteBySn.do")
	@ResponseBody
	public CyResult delete(FyjCode code){
		CyResult result=service.deleteBySn(code);
		return result;
	}
	
	@RequestMapping("/deleteByBoxNum.do")
	@ResponseBody
	public CyResult deleteByBoxNum(FyjCode code){
		CyResult result=service.deleteByBoxNum(code);
		return result;
	}
}
