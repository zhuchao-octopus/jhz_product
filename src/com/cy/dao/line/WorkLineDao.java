package com.cy.dao.line;
import java.util.List;
import java.util.Map;

import com.cy.domain.line.DashBoard;
import com.cy.domain.line.ProductDetails;
import com.cy.domain.line.ProductWorker;
import com.cy.domain.line.WorkLine;
import com.cy.domain.line.WorkLocation;
import com.cy.domain.line.WorkStationCode;
import com.cy.domain.line.daily_production_list;

public interface WorkLineDao {
	/**
	 * 查询全部
	 * @param wl
	 * @return
	 */
     public List<WorkLine> loadAllLine(WorkLine wl);
     public int addWorkLine(Map<String, Object> lineMap);
     public int addWorkLocation(Map<String, Object> locationMap);
     public List<WorkLocation> loadline(WorkLine line);
     
     /*
 	 * 数据字典
 	 */
 	public List<ProductWorker> findWorker();
 	public List<ProductDetails> findProduct();
 	public List<WorkStationCode> findWorkStation(Map map);
 	
 	/*
 	 * 通过产线编码查询出一条产线的数据
 	 */
 	public List<WorkLine> findLineByCode(WorkLine line);
 	/*
 	 * 查询出当前产线下所有的工位
 	 */
 	public List<WorkLocation> findLocationBylineCode(WorkLine line);
 	/*
 	 * 修改工位信息
 	 * 
 	 */
	public int updateLocation(Map<String, Object> map);
	/*
	 * 查詢员工对应工位信息
	 */
	public List<Integer> findWorkerLocation(ProductWorker worker);
	/*
	 * 清除员工对应工位信息
	 * 
	 */
	public int updateWorkerLocation(int abc);
	/*
	 * 修改产线信息
	 */
	public int updateLine(Map<String, Object> map);
	
	/*
	 * 查詢出一個工位
	 */
	public WorkLocation findLocationByCode(WorkLocation location);
	/*
	 * 删除一个工位
	 * 
	 */
	public int deleteLocation(WorkLocation location);
	/*
	 * 计算出当前产线下有多少工位
	 */
	public int findCountValue(WorkLine line);
	
	/*
	 * 删除一条产线
	 * 
	 */
	public int deleteLine(WorkLine line);
	
	/*
	 * 通过员工和产线编码反查生产线
	 */
	public List<WorkLine> findLineByCodeAndWorker(Map<String, Object> map);

	
	public int countLocation(WorkLine line);
    //通过pid查询出与产品相关联的产线
	public List<WorkLine> findLineByPid(Integer pid);
	
	public ProductWorker findWorkerBySid(WorkLocation location);

	 //單獨修改工作站
	public  int updateWorkStation(Map map);
	
	//通过产线编码查询出对应的产品
	public ProductDetails findProductByLine(WorkLine line);
	
	 //查询产品 -->
	List<ProductDetails> findProductById(ProductDetails pd);
	//<!-- 加载工作站 -->
	public WorkStationCode loadAllWSC(WorkStationCode wcode);
	//加载工艺
	public List<String> findClass();	
	/*
	 * 看板
	 */
	public List<DashBoard> findAllDashBoard(DashBoard db);//加载出所有的看板
	public int addDashBoard(DashBoard db);//增加一块看板
	public int deleteBoardById(DashBoard db);//删除看板
	
	
	//新增产品大类
	public int addProduct(ProductDetails pd);
	//修改产品大类
	public int editPd(ProductDetails pd);
	//删除产品大类
	public void deletePdById(Integer pid);
	
	public  int updateLocationState();

	//设置产线信息
	public int setLine(WorkLine line);
	public List<ProductDetails> loadPd(ProductDetails pd);
	public int countPd();
	public void updateWSC(ProductDetails pd);
	//查询出所有工位的数据
	public List<WorkLocation> loadWorkLocation(WorkLocation location);
	public int loadCountLine();
	public List<WorkLocation> loadWl(WorkLocation wl);
	public int loadWlCount(WorkLocation wl);
	public List<WorkLine> loadAllworkLine();
	public int insertWorkLine(WorkLine workLine);
	public int findAllDashBoardCount(DashBoard board);
	
	public int addWorkLocationByBean(WorkLocation location);
	public int updateLocationByBean(WorkLocation location);
	public int updateBoard(DashBoard db);
	public List<WorkLocation> loadLocationByCode(String locationCode);
	public List<ProductWorker> loadWorkerByWorker(WorkLine line);
	
	public void updateWorkerLineByCode(ProductWorker worker);
	
	int loadWorkerByWorkerCount(WorkLine line);
	public List<ProductWorker> finNuber(String lineCode);
	public int deletePersonnel(ProductWorker pd);
	

}
