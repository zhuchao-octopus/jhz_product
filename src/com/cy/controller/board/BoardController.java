package com.cy.controller.board;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.cy.service.board.BoardService;
import com.cy.utils.CyResult;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Resource
	private BoardService boardService;
	
	@RequestMapping("/loadBoard.do")
	@ResponseBody
	public CyResult loadBoard(){
		CyResult result = boardService.loadBoard();
		return result;
	}
	@RequestMapping("/loadRepair.do")
	@ResponseBody
	public CyResult loadRepair(Repair rep){
		CyResult result = boardService.loadRepair(rep);
		return result;
	}
	@RequestMapping("/addplan.do")
	@ResponseBody
	public CyResult addplan(PlanOrder planOrder){
		CyResult result = boardService.addplan(planOrder);
		return result;
	}
	@RequestMapping("/loadPlanOrder.do")
	@ResponseBody
	public CyResult loadPlanOrder(PlanOrder planOrder){
		CyResult result = boardService.loadPlanOrder(planOrder);
		return result;
	}
	@RequestMapping("/updatePlanOrder.do")
	@ResponseBody
	public CyResult updatePlanOrder(PlanOrder planOrder){
		CyResult result = boardService.updatePlanOrder(planOrder);
		return result;
	}
	@RequestMapping("/deletePlanOrder.do")
	@ResponseBody
	public CyResult deleteBomById(PlanOrder planOrder){
		CyResult result = boardService.deletePlanOrder(planOrder);
		return result;
	}
	@RequestMapping("/addProject.do")
	@ResponseBody
	public CyResult addProject(Project project){
		CyResult result = boardService.addProject(project);
		return result;
	}
	@RequestMapping("/loadProject.do")
	@ResponseBody
	public CyResult loadProject(Project project){
		CyResult result = boardService.loadProject(project);
		return result;
	}
	@RequestMapping("/updateProject.do")
	@ResponseBody
	public CyResult updateProject(Project project){
		CyResult result = boardService.updateProject(project);
		return result;
	}
	@RequestMapping("/deleteProject.do")
	@ResponseBody
	public CyResult deleteProject(Project project){
		CyResult result = boardService.deleteProject(project);
		return result;
	}
	@RequestMapping("/loadTasks.do")
	@ResponseBody
	public CyResult loadTasks(Tasks tasks){
		CyResult result = boardService.loadTasks(tasks);
		return result;
	}
	@RequestMapping("/addTasks.do")
	@ResponseBody
	public CyResult addTasks(Tasks tasks){
		CyResult result = boardService.addTasks(tasks);
		return result;
	}
	@RequestMapping("/updateTasks.do")
	@ResponseBody
	public CyResult updateTasks(Tasks tasks){
		CyResult result = boardService.updateTasks(tasks);
		return result;
	}
	@RequestMapping("/deleteTasks.do")
	@ResponseBody
	public CyResult deleteTasks(Tasks tasks){
		CyResult result = boardService.deleteTasks(tasks);
		return result;
	}
	@RequestMapping("/loadGroup.do")
	@ResponseBody
	public CyResult loadGroup(Jgroup group){
		CyResult result = boardService.loadGroup(group);
		return result;
	}
	@RequestMapping("/addGroup.do")
	@ResponseBody
	public CyResult addGroup(Jgroup group){
		CyResult result = boardService.addGroup(group);
		return result;
	}
	@RequestMapping("/updateGroup.do")
	@ResponseBody
	public CyResult updateGroup(Jgroup group){
		CyResult result = boardService.updateGroup(group);
		return result;
	}
	@RequestMapping("/deleteGroup.do")
	@ResponseBody
	public CyResult deleteGroup(Jgroup group){
		CyResult result = boardService.deleteGroup(group);
		return result;
	}
	@RequestMapping("/addGroupList.do")
	@ResponseBody
	public CyResult addGroupList(GroupList groupList,String newArrString,String nameArrString){
		CyResult result = boardService.addGroupList(groupList,newArrString,nameArrString);
		return result;
	}
	@RequestMapping("/loadGroupList.do")
	@ResponseBody
	public CyResult loadGroupList(GroupList groupList){
		CyResult result = boardService.loadGroupList(groupList);
		return result;
	}
	@RequestMapping("/deleteGroupList.do")
	@ResponseBody
	public CyResult deleteGroupList(GroupList groupList){
		CyResult result = boardService.deleteGroupList(groupList);
		return result;
	}
	@RequestMapping("/loadUserGroupBoss.do")
	@ResponseBody
	public CyResult loadUserGroupBoss(ErpUser erpuser){
		CyResult result = boardService.loadUserGroupBoss(erpuser);
		return result;
	}
	@RequestMapping("/acceptProject.do")
	@ResponseBody
	public CyResult acceptProject(Project project){
		CyResult result = boardService.acceptProject(project);
		return result;
	}
	@RequestMapping("/abandonProject.do")
	@ResponseBody
	public CyResult abandonProject(Project project){
		CyResult result = boardService.abandonProject(project);
		return result;
	}
	@RequestMapping("/completeProject.do")
	@ResponseBody
	public CyResult completeProject(Project project){
		CyResult result = boardService.completeProject(project);
		return result;
	}
	@RequestMapping("/loadIntegral.do")
	@ResponseBody
	public CyResult loadIntegral(Integral integral){
		CyResult result = boardService.loadIntegral(integral);
		return result;
	}
	@RequestMapping("/submissionTasks.do")
	@ResponseBody
	public CyResult submissionTasks(Tasks tasks){
		CyResult result = boardService.submissionTasks(tasks);
		return result;
	}
	@RequestMapping("/loadMyIntegra.do")
	@ResponseBody
	public CyResult loadMyIntegra(Integral integral){
		CyResult result = boardService.loadMyIntegra(integral);
		return result;
	}
	@RequestMapping("/loadWJProject.do")
	@ResponseBody
	public CyResult loadWJProject(Project project){
		CyResult result = boardService.loadWJProject(project);
		return result;
	}
	@RequestMapping("/loadProductTest.do")
	@ResponseBody
	public CyResult loadProductTest(ProductTest productTest){
		CyResult result = boardService.loadProductTest(productTest);
		return result;
	}
	@RequestMapping("/addProductTest.do")
	@ResponseBody
	public CyResult addProductTest(ProductTest productTest){
		CyResult result = boardService.addProductTest(productTest);
		return result;
	}
	@RequestMapping("deleteProducTest.do")
	@ResponseBody
	public CyResult deleteProducTest(ProductTest productTest){
		CyResult result = boardService.deleteProducTest(productTest);
		return result;
	}
	@RequestMapping("/addProductTest1.do")
	@ResponseBody
	public CyResult addProductTest1(String brand ,String cpuid ,String deviceName ,
			String etherMac ,String firmwareClient ,String launcherPackageName ,
			String launcherPackageVersion ,String model ,String os ,String fingerprint,String wifiMac){
		CyResult result = boardService.addProductTest1(brand, cpuid, deviceName, etherMac, firmwareClient, launcherPackageName, launcherPackageVersion, model, os, fingerprint, wifiMac);
		return result;
	}
	@RequestMapping("/addBatch.do")
	@ResponseBody
	public CyResult addBatch(Batch batch){
		CyResult result = boardService.addBatch(batch);
		System.err.println(batch.getStuat());
		result.getData();
		return result;
	}
	@RequestMapping("/loadBatch.do")
	@ResponseBody
	public CyResult loadBatch(Batch batch){
		CyResult result = boardService.loadBatch(batch);
		return result;
	}
	@RequestMapping("/deleteBatch.do")
	@ResponseBody
	public CyResult deleteBatch(Batch batch){
		CyResult result = boardService.deleteBatch(batch);
		return result;
	}
	@RequestMapping("/completeBatch.do")
	@ResponseBody
	public CyResult completeBatch(Batch batch){
		CyResult result = boardService.completeBatch(batch);
		return result;
	}
	
}
