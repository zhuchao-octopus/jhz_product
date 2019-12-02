package com.cy.service.line;

import java.text.ParseException;

import org.apache.ibatis.annotations.Param;

import com.cy.domain.line.daily_production_list;
import com.cy.utils.CyResult;


public interface daily_production_listServier {


	CyResult findAllDaily(Integer page , Integer limit) throws ParseException;

	/**
	 * 添加日报信息
	 * @param dpl
	 * @return
	 */
	CyResult addDailyProduction(daily_production_list dpls);
	/**
	 * 删除日报信息
	 * @param dpl
	 * @return
	 */
	CyResult delDailyProduction(daily_production_list dpl);
	
	/**
	 * 修改
	 * @param dpl
	 * @return
	 */
	CyResult updateDailyProduction(daily_production_list dpl);
	
}
