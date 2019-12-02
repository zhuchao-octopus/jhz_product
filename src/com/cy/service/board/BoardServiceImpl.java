package com.cy.service.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cy.board.Batch;
import com.cy.board.GroupList;
import com.cy.board.Integral;
import com.cy.board.Jgroup;
import com.cy.board.LineBoard;
import com.cy.board.PlanOrder;
import com.cy.board.ProductBoard;
import com.cy.board.ProductTest;
import com.cy.board.Project;
import com.cy.board.Repair;
import com.cy.board.Tasks;
import com.cy.dao.board.BoardDao;
import com.cy.domain.line.WorkLine;
import com.cy.domain.line.WorkStationMark;
import com.cy.domain.list.ProductCode;
import com.cy.domain.produce.tv.BomList;
import com.cy.domain.produce.tv.MaterialManage;
import com.cy.domain.produce.tv.OrderList;
import com.cy.domain.produce.tv.ReportPrice;
import com.cy.domain.user.ErpUser;
import com.cy.utils.CyResult;
import com.cy.utils.CyUtil;

@Service
public class BoardServiceImpl implements BoardService{
	@Resource
	private BoardDao boardDao;
	@Override
	public CyResult loadBoard() {
		CyResult result = new CyResult();
		List<WorkStationMark> wmList = boardDao.loadMarkTime(CyUtil.getTime2());//
		System.out.println("wmList"+wmList);
		List<WorkLine> lineList = boardDao.loadAllBoard();
		//List<LineBoard> lbList = new ArrayList<LineBoard>();
		List<ProductBoard> lbList = new ArrayList<ProductBoard>();
		for(WorkStationMark wm : wmList){
			for(WorkLine wl : lineList){
				if(wm.getLineCode().equals(wl.getLineCode())){
					
					//LineBoard lineBoard = new LineBoard();
					ProductBoard board = new ProductBoard();
					board.setOrderid(wm.getPorder());//订单号
					System.out.println("Porder-->"+wm.getPorder());
					List<ProductCode> pcList = boardDao.searchCid(wm.getPorder());
				    List<String> regionList=boardDao.diquRemarks(wm.getPorder());
				    for(String region:regionList){
				    	System.out.println(region);
				    }
				    System.out.println("aaa:"+regionList);
					if(regionList!=null&&regionList.size()>0){
						System.err.println(regionList.get(0));
						board.setRemarks(regionList.get(0));
					}
                     
					String clientName = boardDao.searchClientName(pcList.get(0).getCid());
					
					board.setCustomername(clientName);//客户名称
					board.setModel(pcList.get(0).getModelCode());//机型
					board.setMotherbtype(pcList.get(0).getMainboardModel());//主板型号
					int number = boardDao.searchOrderNumber(wm.getPorder());
					board.setOrdernumber(number);//订单数
					int workCount = boardDao.countWorkCode(wm.getLineCode());
					board.setProdnumber(String.valueOf(workCount));//生产人数
					Map<String, Object> trMap = new HashMap<String, Object>();
					trMap.put("time", CyUtil.getTime2());
					trMap.put("lineCode", wm.getLineCode());
					trMap.put("porder", wm.getPorder());
					int trCount = boardDao.trCount(trMap);
					board.setProdnumber_in(String.valueOf(trCount));//生产投入数
					int ccCount = boardDao.ccCount(trMap);
					board.setProd_Prodnumber(String.valueOf(ccCount));//生产产出数
					int ljtrCount = boardDao.ljtrCount(trMap);
					board.setCumulat_number(ljtrCount);//累计投入数
					int ljccCount = boardDao.ljccCount(trMap);
					board.setCumulat_Prodnumber(ljccCount);//累计产出数
					board.setOrder_shortage(number-ljccCount);//订单欠数
					board.setLine(wm.getLineCode());
					lbList.add(board);
/*					
					lineBoard.setLine();
					lineBoard.setProductBoards(boards);
					lbList.add(lineBoard);*/
				}
			}
		}
	
		System.out.println("-->"+lbList);
		//result.setDate(lbList);
		//result.setDate1(CyUtil.getTime()+"&"+CyUtil.getTime2());
		result.setData(lbList);
		result.setData1(CyUtil.getTime2()+"&"+CyUtil.getTime1());
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}
	@Override
	public CyResult loadRepair(Repair rep) {
		CyResult result = new CyResult();
		if (rep.getPage() == null) {
			rep.setPage(1);
		}
		rep.setPageSize((rep.getPage() - 1) * rep.getLimit());
		int count = boardDao.countLoadAllRepair(rep);
		List<Repair> RepairList = boardDao.loadAllRepair(rep);
		
		result.setData(RepairList);
		result.setCount(count);
		result.setMsg("加载成功");
		result.setState(0);
		return result;
	}
	@Override
	public CyResult addplan(PlanOrder planOrder) {
		System.out.println("planOrder"+planOrder);
		CyResult result=new CyResult();
		OrderList order=boardDao.findOrderListByOid(planOrder.getOid());
		planOrder.setPn(order.getPn());
		planOrder.setPname(order.getMaterialName());
		planOrder.setJhz_time(CyUtil.getTime());
		planOrder.setMid(order.getMid());
		String batch="PD"+CyUtil.getTime4();
		planOrder.setBatch(batch);
		System.out.println("planOrder后---》"+planOrder);
		boardDao.addplan(planOrder);
		result.setState(0);
		result.setMsg("添加成功");
		return result;
	}
	@Override
	public CyResult loadPlanOrder(PlanOrder planOrder) {
		CyResult result = new CyResult();
		if (planOrder.getPage() == null) {
			planOrder.setPage(1);
			planOrder.setLimit(10);
		}
		planOrder.setPageSize((planOrder.getPage() - 1) * planOrder.getLimit());
		List<PlanOrder> orderList=boardDao.loadPlanOrder(planOrder);
		if(orderList!=null){
			for(PlanOrder od:orderList){
				System.out.println("od"+od);
				int completeNumber=boardDao.findWorkStationMark(od);
				System.out.println("完成数量："+completeNumber);
				if(od.getStuat()==0&&completeNumber>=od.getJhz_jhNumber()){
					od.setCompleteNumber(od.getJhz_jhNumber());
					od.setNotCompleteNumber(0);
					boardDao.updateStuat(od.getId());
				}else if(od.getStuat()==1){
					od.setCompleteNumber(od.getJhz_jhNumber());
					od.setNotCompleteNumber(0);
				}else{
					od.setCompleteNumber(completeNumber);//已完成数
				
					od.setNotCompleteNumber(od.getJhz_jhNumber()-completeNumber);//未完成数
				}
			}
		}
		System.out.println("orderList"+orderList);
		int count=0;
		if(orderList!=null){
			count=count+orderList.size();
		}
		result.setState(0);
		result.setData(orderList);
		result.setCount(count);
		result.setMsg("加载成功");
		return result;
	}
	@Override
	public CyResult updatePlanOrder(PlanOrder planOrder) {
		System.out.println("planOrder"+planOrder);
		CyResult result=new CyResult();
		if(planOrder.getStuat()==1){
			result.setState(1);
			result.setMsg("已完成不能编辑");
			return result;
		}
		OrderList order=boardDao.findOrderListByOid(planOrder.getOid());
		planOrder.setPn(order.getPn());
		planOrder.setPname(order.getMaterialName());
		//planOrder.setJhz_time(CyUtil.getTime());
		planOrder.setMid(order.getMid());
		/*String batch="PD"+CyUtil.getTime4();
		planOrder.setBatch(batch);*/
		System.out.println("planOrder后---》"+planOrder);
		boardDao.updatePlanorder(planOrder);
		result.setState(0);
		result.setMsg("编辑成功");
		return result;
	}
	@Override
	public CyResult deletePlanOrder(PlanOrder planOrder) {
		System.out.println("planOrder"+planOrder);
		CyResult result=new CyResult();
		if(planOrder.getStuat()!=0){
			result.setState(1);
			result.setMsg("已完成不能删除");
			return result;
		}
		boardDao.deletePlanOrder(planOrder.getId());
		result.setState(0);
		result.setMsg("删除成功");
		return result;
	}
	@Override
	public CyResult addProject(Project project) {
		CyResult result=new CyResult();
		System.out.println(project);
		project.setJhz_startTime(CyUtil.getTime());
		boardDao.addProject(project);
		result.setState(0);
		result.setMsg("添加成功");
		return result;
	}
	@Override
	public CyResult loadProject(Project project) {
		CyResult result=new CyResult();
		System.out.println("project-->"+project);
		if (project.getPage() == null) {
			project.setPage(1);
			project.setLimit(10);
		}
		project.setPageSize((project.getPage() - 1) * project.getLimit());
		List<Project> prolist=boardDao.loadProject(project);
		int count=boardDao.countloadProject(project);
		for(Project pj:prolist){
			List<Tasks> rws=boardDao.findTasks(pj.getXid());//查询该项目下任务
			if(rws!=null){
			int wc=0;
			for(Tasks tas:rws){
				if(tas.getTasks_state()==1){
					wc=wc+1;
				}
			}
			int wcd=0;
			if(wc!=0){
				wcd=(wc*100)/rws.size();
			}
			String jhz_schedule=wcd+"%";
			pj.setJhz_schedule(jhz_schedule);
		}
		}
		
		/*if(prolist==null){
			result.setCount(0);
		}else{
			result.setCount(prolist.size());
		}*/
		result.setCount(count);
		result.setState(0);
		result.setMsg("成功");
		result.setData(prolist);
		return result;
	}
	@Override
	public CyResult updateProject(Project project) {
		CyResult result=new CyResult();
		System.out.println(project);
		//project.setJhz_startTime(CyUtil.getTime());
		boardDao.updateProject(project);
		result.setState(0);
		result.setMsg("编辑成功");
		return result;
	}
	@Override
	public CyResult deleteProject(Project project) {
		CyResult result=new CyResult();
		boardDao.deleteProject(project);
		result.setState(0);
		result.setMsg("删除成功");
		return result;
	}
	@Override
	public CyResult loadTasks(Tasks tasks) {
		CyResult result=new CyResult();
		if (tasks.getPage() == null) {
			tasks.setPage(1);
			tasks.setLimit(10);
		}
		tasks.setPageSize((tasks.getPage() - 1) * tasks.getLimit());
		List<Tasks> tasklist=boardDao.loadTasks(tasks);
		int count=boardDao.countloadTasks(tasks);
		/*if(tasklist!=null){
			result.setCount(tasklist.size());
		}else{
			result.setCount(0);
		}*/
		result.setCount(count);
		result.setData(tasklist);
		result.setState(0);
		result.setMsg("成功");
		return result;
	}
	@Override
	public CyResult addTasks(Tasks tasks) {
		CyResult result=new CyResult();
		System.out.println("tasks-->"+tasks);
		boardDao.addTasks(tasks);
		result.setState(0);
		result.setMsg("添加成功");
		return result;
	}
	@Override
	public CyResult updateTasks(Tasks tasks) {
		CyResult result=new CyResult();
		System.out.println(tasks);
		boardDao.updateTasks(tasks);
		result.setState(0);
		result.setMsg("修改成功");
		return result;
	}
	@Override
	public CyResult deleteTasks(Tasks tasks) {
		CyResult result=new CyResult();
		boardDao.deleteTasks(tasks);
		result.setState(0);
		result.setMsg("删除成功");
		return result;
	}
	@Override
	public CyResult loadGroup(Jgroup group) {
		CyResult result=new CyResult();
		if (group.getPage() == null) {
			group.setPage(1);
			group.setLimit(10);
		}
		group.setPageSize((group.getPage() - 1) * group.getLimit());
		List<Jgroup> gList=boardDao.loadGroup(group);
		int count=boardDao.countloadGroup(group);
		//System.out.println("gList"+gList);
		if(gList!=null){
			for(Jgroup list:gList){
				//System.out.println("group.getZid()-->"+list.getZid());
				List<GroupList> manlist= boardDao.loadGroupList(list.getZid());
				String RealName="";
				//System.out.println("manlist"+manlist);
				int i=0;
				for(GroupList ml:manlist){
					
					//System.out.println(manlist.size());
					if(i==manlist.size()-1){
						RealName=RealName+ml.getRealName();
					}else{
						RealName=RealName+ml.getRealName()+",";
					}
					i++;
				}
				list.setJhz_peopleName(RealName);
				//System.out.println("RealName"+RealName);
			}
		}
		result.setCount(count);
		result.setData(gList);
		result.setState(0);
		result.setMsg("成功");
		return result;
	}
	@Override
	public CyResult addGroup(Jgroup group) {
		CyResult result=new CyResult();
		System.out.println(group);
		boardDao.addGroup(group);
		result.setState(0);
		result.setMsg("成功");
		return result;
	}
	@Override
	public CyResult updateGroup(Jgroup group) {
		CyResult result=new CyResult();
		System.out.println(group);
		boardDao.updateGroup(group);
		result.setState(0);
		result.setMsg("成功");
		return result;
	}
	@Override
	public CyResult deleteGroup(Jgroup group) {
		CyResult result=new CyResult();
		System.out.println(group);
		if(group!=null||group.getZid()!=null){
			boardDao.deleteGroup(group.getZid());
			result.setState(0);
			result.setMsg("删除成功");
		}
		
		return result;
	}
	@Override
	public CyResult addGroupList(GroupList groupList, String newArrString,
			String nameArrString) {
		CyResult result=new CyResult();
		String[] useridlist=newArrString.split(",");
		String[] namelist=nameArrString.split(",");
		for(int i=0;i<useridlist.length;i++){
			System.out.println();
			groupList.setRealName(namelist[i]);
			groupList.setUserId(Integer.parseInt(useridlist[i]));
			boardDao.addGroupList(groupList);
		}
		result.setState(0);
		result.setMsg("成功");
		return result;
	}
	@Override
	public CyResult loadGroupList(GroupList groupList) {
		CyResult result=new CyResult();
		if (groupList.getPage() == null) {
			groupList.setPage(1);
			groupList.setLimit(10);
		}
		groupList.setPageSize((groupList.getPage() - 1) * groupList.getLimit());
		List<GroupList> list=boardDao.loadGroupList(groupList.getZid());
		//int count=boardDao.countloadGroupList(groupList.getZid());
		if(list==null){
			result.setCount(0);
		}else{
			result.setCount(list.size());
		}
		//result.setCount(count);
		result.setData(list);
		result.setMsg("成功");
		return result;
	}
	@Override
	public CyResult deleteGroupList(GroupList groupList) {
		CyResult result=new CyResult();
		boardDao.deleteGroupList(groupList.getUid());
		result.setState(0);
		result.setMsg("移除成功");
		return result;
	}
	@Override
	public CyResult loadUserGroupBoss(ErpUser erpuser) {
		CyResult result=new CyResult();
		List<ErpUser> list=boardDao.loadUserGroupBoss();
		System.out.println("list"+list);
		result.setData(list);
		result.setState(0);
		result.setMsg("成功");
		return result;
	}
	@Override
	public CyResult acceptProject(Project project) {
		CyResult result=new CyResult();
		System.out.println("project"+project);
		boardDao.acceptProject(project);
		result.setState(0);
		result.setMsg("成功");
		return result;
	}
	@Override
	public CyResult abandonProject(Project project) {
		CyResult result=new CyResult();
		System.out.println("project"+project);
		boardDao.abandonProject(project);
		result.setState(0);
		result.setMsg("成功");
		return result;
	}
	@Override
	public CyResult completeProject(Project project) {
		CyResult result=new CyResult();
		System.out.println("project"+project);
		if(project.getJhz_schedule()=="100%"||"100%".equals(project.getJhz_schedule())){
			List<Tasks> rws=boardDao.findTasks(project.getXid());
			for(Tasks tas:rws){
				Integral integral=new Integral();
				integral.setJhz_integralNumber(tas.getJhz_tasksIntegral());
				integral.setJhz_people(tas.getJhz_peple());
				integral.setJhz_projectName(tas.getJhz_tasksName());
				integral.setJhz_integralTime(CyUtil.getTime());
				integral.setUserId(tas.getUserId());
				integral.setXid(project.getXid());
				List<Integral> list=boardDao.findIntegralByUserId(tas.getUserId());
				int jhz_integral=0;
				for(Integral intg:list){
					jhz_integral=jhz_integral+intg.getJhz_integralNumber();
				}
				integral.setJhz_integral(jhz_integral);
				boardDao.addIntegral(integral);
			}
			boardDao.completeProject(project);
			result.setState(0);
			result.setMsg("成功");
			return result;
		}
		result.setState(1);
		result.setMsg("任务未完成");
		return result;
	}
	
	@Override
	public CyResult submissionTasks(Tasks tasks) {
		CyResult result=new CyResult();
		System.out.println("tasks-->"+tasks);
		boardDao.submissionTasks(tasks);
		result.setState(0);
		result.setMsg("提交成功");
		return result;
	}
	@Override
	public CyResult loadIntegral(Integral integral) {
		CyResult result=new CyResult();
		if (integral.getPage() == null) {
			integral.setPage(1);
			integral.setLimit(10);
		}
		integral.setPageSize((integral.getPage() - 1) * integral.getLimit());
		List<Integral> list=boardDao.loadIntegral(integral);
		int count =boardDao.countloadIntegral(integral);
		/*if(list==null){
			result.setCount(0);
		}else{
			result.setCount(list.size());
		}*/
		result.setCount(count);
		result.setData(list);
		result.setMsg("成功");
		return result;
	}
	@Override
	public CyResult loadMyIntegra(Integral integral) {
		CyResult result=new CyResult();
		List<Integral> list=boardDao.loadMyIntegra(integral.getUserId());
		result.setData(list);
		result.setMsg("成功");
		return result;
	}
	@Override
	public CyResult loadWJProject(Project project) {
		CyResult result=new CyResult();
		System.out.println("project-->"+project);
		if (project.getPage() == null) {
			project.setPage(1);
			project.setLimit(10);
		}
		project.setPageSize((project.getPage() - 1) * project.getLimit());
		List<Project> prolist=boardDao.loadWJProject(project);
		int count =boardDao.countloadWJProject(project);
		/*if(prolist==null){
			result.setCount(0);
		}else{
			result.setCount(prolist.size());
		}*/
		result.setCount(count);
		result.setState(0);
		result.setMsg("成功");
		result.setData(prolist);
		return result;
	
	}
	@Override
	public CyResult loadProductTest(ProductTest productTest) {
		CyResult result=new CyResult();
		System.out.println("project-->"+productTest);
		if (productTest.getPage() == null) {
			productTest.setPage(1);
			productTest.setLimit(10);
		}
		productTest.setPageSize((productTest.getPage() - 1) * productTest.getLimit());
		List<ProductTest> prolist=boardDao.loadProductTest(productTest);
		int count=boardDao.countloadProductTest(productTest);
		/*if(prolist==null){
			result.setCount(0);
		}else{
			result.setCount(prolist.size());
		}*/
		result.setCount(count);
		result.setState(0);
		result.setMsg("成功");
		result.setData(prolist);
		return result;
	}
	@Override
	public CyResult addProductTest(ProductTest productTest) {
		CyResult result=new CyResult();
		System.out.println(productTest);
		productTest.setTime(CyUtil.getTime());
		boardDao.addWJProject(productTest);
		result.setData(productTest);
		result.setState(0);
		result.setMsg("成功");
		return result;
	}
	@Override
	public CyResult deleteProducTest(ProductTest productTest) {
		CyResult result=new CyResult();
		System.out.println(productTest);
		boardDao.deleteProducTest(productTest.getCid());
		result.setState(0);
		result.setMsg("删除成功");
		return result;
	}
	@Override
	public CyResult addProductTest1(String brand, String cpuid,
			String deviceName, String etherMac, String firmwareClient,
			String launcherPackageName, String launcherPackageVersion,
			String model, String os, String fingerprint,String wifiMac) {
		CyResult result=new CyResult();
		ProductTest productTest=new ProductTest();
		productTest.setBrand(brand);
		productTest.setCpuid(cpuid);
		productTest.setDeviceName(deviceName);
		productTest.setEtherMac(etherMac);
		productTest.setFingerprint(fingerprint);
		productTest.setFirmwareClient(firmwareClient);
		productTest.setLauncherPackageName(launcherPackageName);
		productTest.setLauncherPackageVersion(launcherPackageVersion);
		productTest.setModel(model);
		productTest.setWifiMac(wifiMac);
		productTest.setOs(os);
		System.out.println(productTest);
		boardDao.addWJProject(productTest);
		result.setData(productTest);
		result.setState(0);
		result.setMsg("成功");
		return result;
	}
	@Override
	public CyResult addBatch(Batch batch) {
		CyResult result=new CyResult();
		System.out.println(batch);
		boardDao.addBatch(batch);
		result.setState(0);
		result.setMsg("成功");
		return result;
	}
	@Override
	public CyResult loadBatch(Batch batch) {
		CyResult result=new CyResult();
		System.out.println("batch-->"+batch);
		if (batch.getPage() == null) {
			batch.setPage(1);
			batch.setLimit(10);
		}
		batch.setPageSize((batch.getPage() - 1) * batch.getLimit());
		batch.setTime(CyUtil.getTime());
		List<Batch> batchlist=boardDao.loadBatch(batch);
		System.out.println(batch.getPid());
		int count=boardDao.countloadBatch(batch);
		/*if(prolist==null){
			result.setCount(0);
		}else{
			result.setCount(prolist.size());
		}*/
		result.setCount(count);
		result.setState(0);
		result.setMsg("成功");
		result.setData(batchlist);
		return result;
	}
	@Override
	public CyResult deleteBatch(Batch batch) {
		CyResult result=new CyResult();
		System.out.println(batch);
		boardDao.deleteBatch(batch.getId());
		result.setState(0);
		result.setMsg("删除成功");
		return result;
	}
	@Override
	public CyResult completeBatch(Batch batch) {
		CyResult result=new CyResult();
		boardDao.completeBatch(batch.getId());
		result.setState(0);
		result.setMsg("成功完结");
		return result;
	}
}
