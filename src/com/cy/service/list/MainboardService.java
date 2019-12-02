package com.cy.service.list;

import com.cy.domain.list.Mainboard;
import com.cy.utils.CyResult;

public interface MainboardService {
	/**
	 * 显示主板列表
	 * @param mainboard
	 * @return
	 */
	CyResult selectMainboardList(Integer page , Integer limit,Mainboard mainboard);
	
	/**
	 * 删除主板
	 * @param mainboard
	 * @return
	 */
	CyResult MainboardDel(Mainboard mainboard);
	
	/**
	 * 添加主板
	 * @param mainboard
	 * @return
	 */
	CyResult insertMainboard(Mainboard mainboard);
	
	/**
	 * 修改
	 * @param mainboard
	 * @return
	 */
	CyResult updateMainboard(Mainboard mainboard);

}
