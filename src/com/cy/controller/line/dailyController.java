package com.cy.controller.line;


import java.text.ParseException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.domain.line.daily_production_list;
import com.cy.service.line.daily_production_listServier;
import com.cy.utils.CyResult;

@RequestMapping("daily")
@Controller
public class dailyController {

	@Resource
	private daily_production_listServier dplService;

	@RequestMapping("/loaddaily_production.do")
	@ResponseBody
	public CyResult loadlineAll(Integer page , Integer limit) throws ParseException{
		daily_production_list dpl = new daily_production_list();
		CyResult result=dplService.findAllDaily(page, limit);
		return result;
	}

	@RequestMapping("/addDailyProductionAll.do")
	@ResponseBody
	public CyResult addDailyProductionAll(daily_production_list daily) {
		CyResult result=dplService.addDailyProduction(daily);
		return result;
	}
	
	@RequestMapping("/delDailyProduction.do")
	@ResponseBody
	public CyResult delDailyProductionAll(daily_production_list dpl) {
		CyResult result=dplService.delDailyProduction(dpl);
		return result;
	}
	
	@RequestMapping("/updateDailyProduction.do")
	@ResponseBody
	public CyResult updateDailyProductionAll(daily_production_list dpl) {
		CyResult result=dplService.updateDailyProduction(dpl);
		return result;
	}

}
