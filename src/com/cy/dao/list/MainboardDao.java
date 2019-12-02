package com.cy.dao.list;

import java.util.List;

import com.cy.domain.list.Mainboard;

public interface MainboardDao {
	/**
	 * 显示主板列表
	 * @param mainboard
	 * @return
	 */
	List<Mainboard> selectMainboardListAll(Mainboard mainboard);
	/**
	 * 统计主板
	 * @param mainboard
	 * @return
	 */
	int count(Mainboard mainboard);
	
	/**
	 * 删除主板
	 * @param mainboard
	 * @return
	 */
	int MainboardDelAll(Mainboard mainboard);
	
	/**
	 * 添加主板
	 * @param mainboard
	 * @return
	 */
	int insertMainboardAll(Mainboard mainboard);
	/**
	 * 修改
	 * @param mainboard
	 * @return
	 */
	int updateMainboardAll(Mainboard mainboard);
	

}
