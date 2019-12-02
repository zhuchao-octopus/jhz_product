package com.cy.dao.check;

import java.util.List;
import java.util.Map;

import com.cy.domain.check.NewCheck;
import com.cy.domain.line.ProductDetails;
import com.cy.domain.line.WorkLocation;

public interface NewCheckDao {
	 //查询出当日,当前产线下所有员工的工时信息 
	public List<WorkLocation> findCheckByLineCode(Map map);
	//查询出该人当日当前产线当前产品下的打卡信息 -->
	public List<NewCheck> findNewCheckByWorkerCode(NewCheck check);
	//增加一条打卡记录 
	public int addNewCheck(NewCheck check);
    //修改一条打卡记录
	public int updateNewCheck(NewCheck check);
	
	public List<String> findAllLineCode();
	
	public List<ProductDetails> findAllPname();
}
