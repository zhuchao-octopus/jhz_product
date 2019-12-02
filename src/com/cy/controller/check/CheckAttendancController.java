package com.cy.controller.check;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.domain.check.CheckAttendanc;
import com.cy.service.check.CheckAttendancService;
import com.cy.utils.CyResult;

@Controller
public class CheckAttendancController {

	@Resource
	private CheckAttendancService service;
	
	@RequestMapping("/getAttendanc.do")
	@ResponseBody
	public CyResult getAttendanc(CheckAttendanc attendanc){
		CyResult result = service.getAttendanc(attendanc);
		return result;
	}
	
	
	@RequestMapping("/deleteAttendanc.do")
	@ResponseBody
	public CyResult delete(String id){
		CyResult result = service.delete(id);
		return result;
	}
	
	@RequestMapping("/deleteBatchAttendanc.do")
	@ResponseBody
	public CyResult deleteBatch(String id){
		CyResult result = service.deleteBatch(id);
		return result;
	}
	
	@RequestMapping("/updateAttendanc.do")
	@ResponseBody
	public CyResult updateBatch(CheckAttendanc attendanc){
		CyResult result = service.update(attendanc);
		return result;
	}
	
}
