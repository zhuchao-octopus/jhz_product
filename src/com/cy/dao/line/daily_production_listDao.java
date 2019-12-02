package com.cy.dao.line;

import java.util.List;
import java.util.Map;

import com.cy.domain.line.daily_production_list;

public interface daily_production_listDao {
	/**
	 * 查询全部
	 * @param dpl
	 * @return
	 */
	List<daily_production_list> loadAllDaily(daily_production_list dpls);	
	int CountLine();
	
	/**
	 * 添加日报信息
	 * @param dpl
	 * @return
	 */
	int addDailyProduction(daily_production_list dpls);
	
	/**
	 * 删除
	 * @return
	 */
	int delDailyProduction(daily_production_list dpl);
	
	/**
	 * 修改
	 * @param dpl
	 * @return
	 */
	int updateDailyProduction(daily_production_list dpl);
	
}
