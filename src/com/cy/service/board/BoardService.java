package com.cy.service.board;

import com.cy.board.Batch;
import com.cy.board.GroupList;
import com.cy.board.Integral;
import com.cy.board.Jgroup;
import com.cy.board.PlanOrder;
import com.cy.board.ProductTest;
import com.cy.board.Project;
import com.cy.board.Repair;
import com.cy.board.Tasks;
import com.cy.domain.user.ErpUser;
import com.cy.utils.CyResult;

public interface BoardService {

	public CyResult loadBoard();

	public CyResult loadRepair(Repair rep);

	public CyResult addplan(PlanOrder planOrder);

	public CyResult loadPlanOrder(PlanOrder planOrder);

	public CyResult updatePlanOrder(PlanOrder planOrder);

	public CyResult deletePlanOrder(PlanOrder planOrder);

	public CyResult addProject(Project project);

	public CyResult loadProject(Project project);

	public CyResult updateProject(Project project);

	public CyResult deleteProject(Project project);

	public CyResult loadTasks(Tasks tasks);

	public CyResult addTasks(Tasks tasks);

	public CyResult updateTasks(Tasks tasks);

	public CyResult deleteTasks(Tasks tasks);

	public CyResult loadGroup(Jgroup group);

	public CyResult addGroup(Jgroup group);

	public CyResult updateGroup(Jgroup group);

	public CyResult deleteGroup(Jgroup group);

	public CyResult addGroupList(GroupList groupList, String newArrString,
			String nameArrString);

	public CyResult loadGroupList(GroupList groupList);

	public CyResult deleteGroupList(GroupList groupList);

	public CyResult loadUserGroupBoss(ErpUser erpuser);

	public CyResult acceptProject(Project project);

	public CyResult abandonProject(Project project);

	public CyResult completeProject(Project project);

	public CyResult submissionTasks(Tasks tasks);

	public CyResult loadIntegral(Integral integral);

	public CyResult loadMyIntegra(Integral integral);

	public CyResult loadWJProject(Project project);

	public CyResult loadProductTest(ProductTest productTest);


	public CyResult addProductTest(ProductTest productTest);

	public CyResult deleteProducTest(ProductTest productTest);

	public CyResult addProductTest1(String brand, String cpuid,
			String deviceName, String etherMac, String firmwareClient,
			String launcherPackageName, String launcherPackageVersion,
			String model, String os, String fingerprint, String wifiMac);

	public CyResult addBatch(Batch batch);

	public CyResult loadBatch(Batch batch);

	public CyResult deleteBatch(Batch batch);

	public CyResult completeBatch(Batch batch);


}
