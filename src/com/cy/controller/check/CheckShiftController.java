package com.cy.controller.check;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.domain.check.CheckShift;
import com.cy.service.check.CheckShiftService;
import com.cy.utils.CyResult;

@Controller
public class CheckShiftController {

	@Resource
	private CheckShiftService service;
	
	
	@RequestMapping("/getShift.do")
	@ResponseBody
	public CyResult getShift(CheckShift shift){
		CyResult result = service.getShift(shift);
		return result;
	}
	
	
	
	@RequestMapping("/insertShift.do")
	@ResponseBody
	public CyResult insert(CheckShift shift){
		CyResult result = service.insert(shift);
		return result;
	}
	
	@RequestMapping("/deleteShift.do")
	@ResponseBody
	public CyResult deleteShift(String id){
		CyResult result = service.deleteShift(id);
		return result;
	}
	
	@RequestMapping("/deleteBatchShift.do")
	@ResponseBody
	public CyResult deleteBatchShift(String strId){
		CyResult result = service.deleteBatchShift(strId);
		return result;
	}
	
	@RequestMapping("/updateShift.do")
	@ResponseBody
	public CyResult update(CheckShift shift){
		CyResult result = service.update(shift);
		return result;
	}
	
}
