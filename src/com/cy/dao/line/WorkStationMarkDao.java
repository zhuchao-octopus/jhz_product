package com.cy.dao.line;

import java.util.List;
import java.util.Map;

import com.cy.domain.line.DashBoard;
import com.cy.domain.line.WorkLine;

public interface WorkStationMarkDao {
	
	WorkLine findEscateByCode(WorkLine line);//查询当前产线所对应的工艺
	List<DashBoard> findDashBoardByCode(WorkLine line);//查询当前产线下所有的时间组
	int findZuInputByCode(Map map);//计算组装投入 
	int findZuOutputByCode(Map map);//计算组装产出
	int findBaoOutputByCode(Map map);//计算包装产出
	int findZuBaoInputByCode(Map map);//计算组包投入
	int findZuBaoOutputByCode(Map map);//计算组包产出
    
	
	
	int findLeijiZuInputByCode(Map map);//计算组装累计投入 
	int findLeijiZuOutputByCode(Map map);//计算组装累计产出
	int findLeijiBaoOutputByCode(Map map);//计算包装累计产出
	int findLeijiZuBaoInputByCode(Map map);//计算组包累计投入
	int findLeijiZuBaoOutputByCode(Map map);//计算组包累计产出
}
