package com.cy.controller.list;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.domain.list.Mainboard;
import com.cy.service.list.MainboardService;
import com.cy.utils.CyResult;

@RequestMapping("mainboard")
@Controller
public class MainboardListController {
	
	@Resource
	private MainboardService service;
	
	@RequestMapping("/loadlist.do")
	@ResponseBody
	public CyResult loadList(Integer page , Integer limit,Mainboard mainboard) {
		CyResult result=service.selectMainboardList(page, limit, mainboard);
		return result;
	}

	@RequestMapping("/delete.do")
	@ResponseBody
	public CyResult mainboardDelete(Mainboard mainboard) {
		CyResult result=service.MainboardDel(mainboard);
		return result;
	}
	
	@RequestMapping("/add.do")
	@ResponseBody
	public CyResult addMainboard(Mainboard mainboard) {
		CyResult result = service.insertMainboard(mainboard);
		return result;
	}
	
	@RequestMapping("/update.do")
	@ResponseBody
	public CyResult updateMainboard(Mainboard mainboard) {
		CyResult result = service.updateMainboard(mainboard);
		return result;
	}
	
}
