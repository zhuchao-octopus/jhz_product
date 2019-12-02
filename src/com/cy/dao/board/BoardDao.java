package com.cy.dao.board;

import java.util.List;
import java.util.Map;

import com.cy.board.Batch;
import com.cy.board.GroupList;
import com.cy.board.Integral;
import com.cy.board.Jgroup;
import com.cy.board.PlanOrder;
import com.cy.board.ProductTest;
import com.cy.board.Project;
import com.cy.board.Repair;
import com.cy.board.Tasks;
import com.cy.domain.line.WorkLine;
import com.cy.domain.line.WorkStationMark;
import com.cy.domain.list.ProductCode;
import com.cy.domain.produce.tv.OrderList;
import com.cy.domain.produce.tv.ReportPrice;
import com.cy.domain.user.ErpUser;

public interface BoardDao {

	List<WorkStationMark> loadMarkTime(String time);

	List<WorkLine> loadAllBoard();



	String searchClientName(String cid);

	int searchOrderNumber(String porder);

	int countWorkCode(String lineCode);

	int trCount(Map<String, Object> trMap);

	int ccCount(Map<String, Object> trMap);

	int ljtrCount(Map<String, Object> trMap);

	int ljccCount(Map<String, Object> trMap);

	List<ProductCode> searchCid(String porder);

	List<String> diquRemarks(String porder);

	int countLoadAllRepair(Repair rep);

	List<Repair> loadAllRepair(Repair rep);

	OrderList findOrderListByOid(Integer oid);

	void addplan(PlanOrder planOrder);

	List<PlanOrder> loadPlanOrder(PlanOrder planOrder);

	int findWorkStationMark(PlanOrder od);

	void updatePlanorder(PlanOrder planOrder);

	void deletePlanOrder(Integer id);

	void updateStuat(Integer id);

	void addProject(Project project);

	List<Project> loadProject(Project project);

	void updateProject(Project project);

	void deleteProject(Project project);

	void addTasks(Tasks tasks);

	List<Tasks> loadTasks(Tasks tasks);

	void updateTasks(Tasks tasks);

	void deleteTasks(Tasks tasks);

	List<Jgroup> loadGroup(Jgroup group);

	void addGroup(Jgroup group);

	void updateGroup(Jgroup group);

	void deleteGroup(Integer zid);

	void addGroupList(GroupList groupList);

	List<GroupList> loadGroupList(Integer zid);

	void deleteGroupList(Integer uid);

	List<ErpUser> loadUserGroupBoss();

	void acceptProject(Project project);

	void abandonProject(Project project);

	void completeProject(Project project);

	void submissionTasks(Tasks tasks);

	List<Tasks> findTasks(Integer xid);

	void addIntegral(Integral integral);

	List<Integral> loadIntegral(Integral integral);

	List<Integral> findIntegralByUserId(Integer userId);

	List<Integral> loadMyIntegra(Integer userId);

	List<Project> loadWJProject(Project project);

	void addWJProject(ProductTest productTest);

	List<ProductTest> loadProductTest(ProductTest productTest);

	void deleteProducTest(Integer cid);

	int countloadProductTest(ProductTest productTest);

	int countloadWJProject(Project project);

	int countloadIntegral(Integral integral);

	int countloadGroup(Jgroup group);

	int countloadTasks(Tasks tasks);

	int countloadProject(Project project);

	void addBatch(Batch batch);

	List<Batch> loadBatch(Batch batch);

	int countloadBatch(Batch batch);

	void deleteBatch(Integer id);

	void completeBatch(Integer id);


}
