package com.cy.dao.check;


import java.util.List;

import com.cy.domain.check.CheckModel;
import com.cy.domain.check.CheckUtil;
import com.cy.domain.line.WorkLine;
import com.cy.domain.line.WorkLocation;


public interface CheckDao {
/*    public WorkLine findObjectByLine(WorkLine line);
    
   
    public  int writeCheckTime(CheckResource resource);
    
    
     * 繼續打卡
     s
    public int updateResource(CheckResource resource);
    
   
    public CheckResource findCheckResource(CheckResource resource);*/
    
    /*
     * 每天的第一次打卡
     */
    public int addCheck(CheckModel model);
    /*
     *第二次或者以上 上线或者下线
     */
    public int updateCheck(CheckModel model);
    
    /*
     * 通过人员和日期查询出对应的打卡记录
     */
    public CheckModel findCheckByWid(CheckModel model);
    
    
    
    /*
     *   <!-- 查询出当前产线下所有的工位和人员信息 -->  
     */
    public List<WorkLocation> findWorkerByCode(WorkLine line);
    
    
    /*
     * <!-- 通过人员的信息查询出打卡状态 -->
     */
    public CheckModel findCheckByWorker(CheckModel model);
 //---------------------------------------------------------------------------------------   
    
    
    public CheckUtil findStatusByTimeAndWorker(CheckUtil cu);
    public int addCheckStatus(CheckUtil cu);
    public int updateStatus(CheckUtil cu);
	public WorkLine findObjectByLine(WorkLine line);
    
    
    
    
    
    
}
