package com.cy.service.line;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.dao.check.NewCheckDao;
import com.cy.dao.line.ProductWorkerDao;
import com.cy.dao.line.WorkLineDao;
import com.cy.dao.line.WorkStationMarkDao;
import com.cy.dao.list.ProductListDao;
import com.cy.domain.check.Check;
import com.cy.domain.check.NewCheck;
import com.cy.domain.line.DashBoard;
import com.cy.domain.line.DashBoardCount;
import com.cy.domain.line.ProductDetails;
import com.cy.domain.line.ProductWorker;
import com.cy.domain.line.WorkLine;
import com.cy.domain.line.WorkLineOne;
import com.cy.domain.line.WorkLocation;
import com.cy.domain.line.WorkStationCode;
import com.cy.domain.line.WorkStationMark;
import com.cy.domain.line.daily_production_list;
import com.cy.domain.list.ProductScheduling;
import com.cy.utils.CyResult;
import com.cy.utils.CyUtil;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.google.gson.Gson;

@Service("workLineService")
@Transactional(rollbackFor = Exception.class)
public class WorkLineServiceImpl implements WorkLineService {
	@Resource
	private WorkStationMarkDao markDao;
	@Resource
	private WorkLineDao dao;
	@Resource
	private ProductWorkerDao workerDao;
	@Resource
	private NewCheckDao newDao;
	@Resource
	private ProductListDao pdao;

	/*
	 * 加載生產綫列表(non-Javadoc)
	 * 
	 * @see com.cy.service.line.WorkLineService#findAllline()
	 */
	@Override
	public CyResult findAllline(Integer page , Integer limit) throws ParseException {
		CyResult result = new CyResult();
		if(page==null){
			page=1;
		}
		int pageSize = (page-1)*limit;
		WorkLine wl = new WorkLine();
		wl.setLimit(limit);
		wl.setPageSize(pageSize);
		List<WorkLine> list = dao.loadAllLine(wl);
		if (list == null || list.size() < 1) {
			result.setMsg("生产线无数据!");
			result.setState(0);
			return result;
		}
		for (WorkLine w : list) {
			List<ProductWorker> bmList = dao.finNuber(w.getLineCode());
			w.setNumber(bmList.size());
			System.out.println(bmList.size());
			}
		System.out.println(list);
		int count = dao.loadCountLine();
		result.setCount(count);
		result.setMsg("加载生产线表成功");
		result.setState(1);
		result.setData(list);
		return result;

	}

	@Override
	public CyResult loadLineById(WorkLine line) {
		CyResult result = new CyResult();
		if (line == null || line.getLid() == null) {
			result.setMsg("请选中一条产线!");
			result.setState(0);
			return result;
		}
		List<WorkLocation> list = dao.loadline(line);
		System.out.println(list);
		List<WorkLocation> wlist = new ArrayList<WorkLocation>();
		WorkLine wline = null;
		for (WorkLocation location : list) {
			wline = location.getLine();

		}
		wline.setList(wlist);
		return null;
	}

	@Override
	public CyResult loadSelectOption(String lineCode,String state,Integer pid,String pname) {

		CyResult result = new CyResult();
		List<ProductDetails> list1 = dao.findProduct();
		List<ProductWorker> list2 = dao.findWorker();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("pid", pid);
		map.put("pname", pname);
		List<WorkStationCode> stationList = dao.findWorkStation(map);

		List<String> pclassList = dao.findClass();
        
		if(state==null||!"app".equals(state)){
		   result.setData1(list1);
		   result.setData2(list2);
		}
		result.setData3((new Gson()).toJson(list1));
		
		if (lineCode != null) {
			WorkLine ln = new WorkLine();
			ln.setLineCode(lineCode);
			List<WorkLine> wlist = dao.findLineByCode(ln);
			if (wlist == null || wlist.size() < 1) {
				result.setMsg("该产线不存在！");
				result.setState(2);
				return result;
			}
			ProductDetails pd = dao.findProductByLine(ln);
			List<ProductDetails> pList = dao.findProductById(pd);
			if (pList == null || pList.size() < 1) {
				result.setData5("");

			}
			if (pList != null && pList.size() > 0) {
				result.setData5(pList.get(0).getPname());
			}
			if (wlist.get(0).getScstate() != null) {
				result.setData8(wlist.get(0).getScstate());
			} else {
				result.setData8("");
			}

		}
		if(state==null||!"app".equals(state)){
			result.setData4(stationList);
			result.setData7(pclassList);
		}
		
		result.setData6((new Gson()).toJson(pclassList));
		
		System.out.println(stationList);
		result.setMsg("数据加载成功");
		result.setState(1);
		return result;
	}

	/*
	 * 新增生产线(non-Javadoc)
	 * 
	 * @see com.cy.service.line.WorkLineService#addWorkLine(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public CyResult addWorkLine(String lineCode, String pid, String rentApport, String scstate, String timeScope1,
			String timeScope2, String flushTime) {

		System.out.println("缓冲时间:" + flushTime);
		System.out.println("工艺:" + scstate);
		CyResult result = new CyResult();
		if (lineCode == null || lineCode.trim().isEmpty()) {
			result.setMsg("产线编码不能为空!");
			result.setState(0);
			return result;
		}
		WorkLine line = new WorkLine();
		line.setLineCode(lineCode);
		List<WorkLine> list = dao.findLineByCode(line);
		if (list != null && list.size() > 0) {
			result.setMsg("该产线编码已存在,请重新填写!");
			result.setState(2);
			return result;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("countValue", 0);
		map.put("lineCode", lineCode);
		map.put("scstate", scstate);

		try {
			map.put("rentApport", Double.parseDouble(rentApport));
		} catch (Exception e) {
			if ("".equals(rentApport) || "".equals(pid)) {
				map.put("rentApport", null);
			} else {
				result.setMsg("场租分摊无法解析,请检查数据格式!");
				result.setState(0);
				return result;
			}
		}

		try {
			map.put("pid", Integer.parseInt(pid));
		} catch (Exception e) {
			map.put("pid", null);
		}

		map.put("status", 0);
		map.put("creatime", CyUtil.getTime());
		map.put("timeScope1", timeScope1);
		map.put("timeScope2", timeScope2);
		map.put("flushTime", flushTime);
		System.out.println("时间组1" + map.get("timeScope1"));
		int n = dao.addWorkLine(map);
		if (n != 1) {
			throw new RuntimeException("新增产线失败!");
		}
		result.setMsg("新增产线成功!");
		result.setState(1);
		return result;
	}

	/*
	 * 加载出当前产线下所有的工位(non-Javadoc)
	 * 
	 * @see com.cy.service.line.WorkLineService#loadLocation(com.cy.domain.line.
	 * WorkLine)
	 */
	@Override
	public CyResult loadLocation(WorkLine line) {
		CyResult result = new CyResult();
		System.out.println(line);
		if (line == null || line.getLid() == null) {
			result.setMsg("请选定一条产线!");
			result.setState(0);
			return result;
		}
		List<WorkLocation> list = dao.findLocationBylineCode(line);
		for (WorkLocation w : list) {
			System.out.println(w);
		}
		// System.out.println(list);
		result.setMsg("加载工位成功!");
		result.setState(1);
		result.setData(list);
		return result;
	}

	/*
	 * 新增工位(non-Javadoc)
	 * 
	 * @see com.cy.service.line.WorkLineService#addLocation(com.cy.domain.line.
	 * WorkLocation, com.cy.domain.line.ProductWorker,
	 * com.cy.domain.line.WorkLine)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public CyResult addLocation(WorkLocation location) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		CyResult result=new CyResult();
		List<WorkLocation> locationList=dao.loadLocationByCode(location.getLocationCode());
		if(locationList.size()>0){
			result.setState(2);
			result.setCode(1);
			result.setMsg("工位编码重复!");
			return result;
		}
		try {
			/*if(location.getWorker()!=null){
				List<WorkLocation> list=dao.findLocationBylineCode(location.getWorker().getWorkerCode());
				for(WorkLocation wl:list){
					dao.clearWorkerCode(wl.getSid());
				}
			}*/
		  /*  List<WorkLocation> list=dao.findLocationBylineCode(location.getWorker().getWorkerCode());*/
			dao.addWorkLocationByBean(location);
			result.setState(1);
			result.setCode(0);
			result.setMsg("success!");
			return result;
		} catch (Exception e) {
		    e.printStackTrace();
		    throw new RuntimeException("error");
		}
	}

	@Override
	public CyResult saveLine(WorkLocation location, ProductWorker worker, WorkLine line,WorkStationCode station) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		CyResult result = new CyResult();
		location.setLine(line);
		location.setWsCode(station);
		location.setWorker(worker);
	
		
		try {
			dao.updateLocationByBean(location);
			result.setMsg("修改成功!");
			result.setState(1);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error");
		}
    
		
	}

	@Override
	public CyResult deleteLocation(WorkLocation location) {
		CyResult result = new CyResult();
		WorkLocation location1 = dao.findLocationByCode(location);
		if (location1 == null) {
			result.setMsg("当前选定的工位已不存在，请尝试刷新!");
			result.setState(2);
			return result;
		}
		int n = dao.deleteLocation(location);
		if (n != 1) {
			throw new RuntimeException("删除工位失败!");
		}
		result.setMsg("删除成功!");
		result.setState(1);
		return result;
	}

	@Override
	@Transactional
	public CyResult deleteLine(WorkLine line) {
		CyResult result = new CyResult();
		try {
			int n = dao.deleteLine(line);
			result.setMsg("删除成功!");
			result.setState(1);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error");
		}
		
	}

	@Override
	public CyResult hourList(ProductWorker worker) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 加载一快看版(non-Javadoc)
	 * 
	 * @see com.cy.service.line.WorkLineService#loadBorad(com.cy.domain.line.
	 * DashBoard)
	 */
	@Override
	public CyResult loadBorad(DashBoard board, Integer page, Integer limit) {
		CyResult result = new CyResult();
		if(page==null){
			page=1;
		}
		int pageSize = (page-1)*limit;
		board.setLimit(limit);
		board.setPageSize(pageSize);
		List<DashBoard> list = dao.findAllDashBoard(board);
		int listCount = dao.findAllDashBoardCount(board);
		result.setData(list);
		result.setCount(listCount);
		result.setState(1);
		result.setMsg("加载看板成功!");
		return result;
	}
    
	
	
	
	
	
	/*
	 * 增加一快看版(non-Javadoc)
	 * 
	 * @see com.cy.service.line.WorkLineService#addBoard(com.cy.domain.line.
	 * DashBoard)
	 */
	@Override
	public CyResult addBoard(DashBoard board, Integer lid) throws ParseException {
		WorkLine line = new WorkLine();
		line.setLid(lid);
		CyResult result = new CyResult();
		if (board == null || board.getStartime() == null || board.getEndtime() == null) {
			result.setMsg("开始时间或结束时间不能为空!");
			result.setState(0);
			return result;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Long time1 = sdf.parse(board.getStartime()).getTime();
		Long time2 = sdf.parse(board.getEndtime()).getTime();
		if (time2 < time1) {
			result.setMsg("开始时间不得大于结束时间!");
			result.setState(2);
			return result;
		}
		board.setLine(line);
		try {
			int n = dao.addDashBoard(board);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("增加看板失败!");
		}
		result.setState(1);
		result.setMsg("增加看板成功");
		return result;
	}

	@Override
	public CyResult deleteBoard(DashBoard db) {
		CyResult result = new CyResult();
		
		try {
			int n = dao.deleteBoardById(db);
			result.setMsg("删除成功!");
			result.setState(1);
			return result;
		} catch (Exception e) {
			throw new RuntimeException("删除失败!");
		}
		
	}

	@Override
	public CyResult addProduct(ProductDetails pd) {
		CyResult result = new CyResult();
		if (pd.getPname() == null) {
			result.setMsg("产品名不能为空!");
			result.setState(0);
			return result;
		}
		List<ProductDetails> plist = dao.findProductById(pd);
		if (plist.size() > 0) {
			result.setMsg("该产品已存在,请重新填写产品名!");
			result.setState(2);
			return result;
		}
		try {
			int n = dao.addProduct(pd);
		} catch (Exception e) {
			throw new RuntimeException("新增失败!");
		}
		result.setMsg("新增成功!");
		result.setState(1);
		return result;
	}

	/*
	 * 编辑产品大类(non-Javadoc)
	 * 
	 * @see com.cy.service.line.WorkLineService#editPd(com.cy.domain.line.
	 * ProductDetails)
	 */
	@Override
	public CyResult editPd(ProductDetails pd) {
		CyResult result = new CyResult();
		if (pd.getPid() == null || pd.getPname() == null) {
			result.setMsg("id或产品名不能为空!");
			result.setState(0);
			return result;
		}
		ProductDetails pdOne=new ProductDetails();
		pdOne.setPid(pd.getPid());
		List<ProductDetails> plist = dao.findProductById(pdOne);
		if (plist == null || plist.size() < 1) {
			result.setMsg("未能查询到相关产品，请尝试刷新!");
			result.setState(2);
			return result;
		}
		try {
			int n = dao.editPd(pd);
		} catch (Exception e) {
			result.setMsg("修改失败!");
			result.setState(3);
			return result;
		}
		dao.updateWSC(pd);
		result.setMsg("修改成功!");
		result.setState(1);
		return result;

	}

	@Override
	public CyResult deleteById(Integer pid) {
		CyResult result = new CyResult();
		if (pid == null) {
			result.setMsg("id不能为空");
			result.setState(0);
			return result;
		}
		ProductDetails pd = new ProductDetails();
		pd.setPid(pid);
		List<ProductDetails> list = dao.findProductById(pd);

		if (list == null || list.size() < 1) {
			result.setMsg("产品不存在，请尝试刷新！");
			result.setState(2);
			return result;
		}

//		ProductScheduling ps = new ProductScheduling();
//		ps.setPd(list.get(0));
//		System.out.println(list.get(0).getPid());
//		Integer aaa = list.get(0).getPid();
//		List<ProductScheduling> plist = pdao.findSchedulingByPd(aaa);
//		System.out.println("条码规则:" + plist);
//		if (plist != null && plist.size() > 0) {
//			result.setMsg("与该产品关联的条码规则不为空,请先清空条码规则!");
//			result.setState(3);
//			return result;
//		}
//		List<WorkLine> wlist = dao.findLineByPid(aaa);
//		System.out.println(wlist);
//		if (wlist != null && wlist.size() > 0) {
//			result.setMsg("与该产品关联的产线不为空,请先清空产线!");
//			result.setState(3);
//			return result;
//		}
		try {
			dao.deletePdById(pid);
		} catch (Exception e) {
			result.setMsg("删除失败!");
			result.setState(4);
			return result;
		}
		result.setMsg("删除成功!");
		result.setState(1);
		return result;
	}

	/*
	 * 设置产线
	 */
	@Override
	public CyResult setLineByLine(WorkLine line, Integer pid) {
		System.out.println(line.toString());
		CyResult result = new CyResult();
		ProductDetails pd = new ProductDetails();
		pd.setPid(pid);
		line.setPid(pid);
		line.setProduct(pd);
		System.out.println(line);
		try {
			dao.setLine(line);
			result.setMsg("设置成功!");
			result.setState(1);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("设置失败!");
			result.setState(0);
			return result;
		}
	}

	/*
	 * app端加载看板的数据(non-Javadoc)
	 * 
	 * @see
	 * com.cy.service.line.WorkLineService#loadDashBoradData(com.cy.domain.line.
	 * WorkLine)
	 */
	@Override
	public CyResult loadDashBoradData(WorkLine line) throws ParseException {

		Gson gson = new Gson();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sd= new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdm= new SimpleDateFormat("HH:mm:ss");
		// DashBoardCount dc=new DashBoardCount();
		CyResult result = new CyResult();
		if (line.getLineCode() == null) {
			result.setMsg("产线编码不能为空！");
			result.setState(0);
			return result;
		}
		WorkLine lineone = markDao.findEscateByCode(line);
		List<DashBoardCount> listCount = new ArrayList<DashBoardCount>();
		List<DashBoard> dshlist = markDao.findDashBoardByCode(line);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lineCode", line.getLineCode());
		map.put("nowDate", sdf.format(new Date()));

		for (DashBoard db : dshlist) {
			DashBoardCount dc = new DashBoardCount();
			dc.setScesate(lineone.getScstate());
			dc.setNowDate(sdf.format(new Date()));
			
			ProductDetails pd = dao.findProductByLine(line);
			List<ProductDetails> pdList = dao.findProductById(pd);
			if (pdList != null && pdList.size() > 0) {
				dc.setPname(pdList.get(0).getPname());
			} else {
				dc.setPname("");
			}
			dc.setStartime(db.getStartime());
			dc.setEndtime(db.getEndtime());
            System.out.println("开始时间:"+db.getStartime());
            System.out.println("结束时间:"+db.getEndtime());
            System.out.println("开始时间:"+(sd.parse(db.getStartime()).getTime())/1000);
            System.out.println("开始时间:"+sd.parse(db.getStartime()));
            System.out.println("结束时间:"+(sd.parse(db.getEndtime()).getTime())/1000);
            System.out.println("结束时间:"+(sdm.parse("07:30:00").getTime())/1000);
			map.put("start", db.getStartime()+":00");
			map.put("end",db.getEndtime()+":00");
			Integer input = null;
			Integer output = null;
			Integer cumulativeInput = null;
			Integer cumulativeOutput = null;

			if (lineone.getScstate().contains("组装")) {
				input = markDao.findZuInputByCode(map);// 组装投入
				output = markDao.findZuOutputByCode(map);// 组装产出
				cumulativeInput = markDao.findLeijiZuInputByCode(map);
				cumulativeOutput = markDao.findLeijiZuOutputByCode(map);
			}
			if (lineone.getScstate().contains("包装")) {
				output = markDao.findBaoOutputByCode(map);// 包装产出
				cumulativeOutput = markDao.findLeijiBaoOutputByCode(map);
			}
			if (lineone.getScstate().contains("组包")) {
				input = markDao.findZuBaoInputByCode(map);// 组包投入
				output = markDao.findZuBaoOutputByCode(map);// 组包产出
				cumulativeInput = markDao.findLeijiZuBaoInputByCode(map);
				cumulativeOutput = markDao.findLeijiZuBaoOutputByCode(map);
			}
			dc.setInput(input.toString());
			dc.setOutput(output.toString());
			dc.setCumulativeInput(cumulativeInput.toString());
			dc.setCumulativeOutput(cumulativeOutput.toString());
			listCount.add(dc);
		}
		for (DashBoardCount dbc : listCount) {
			if (dbc.getPname() == null) {
				dbc.setPname("");
			}
			if (dbc.getInput() == null) {
				dbc.setInput("");
			}
			if (dbc.getOutput() == null) {
				dbc.setOutput("");
			}
			if (dbc.getCumulativeInput() == null) {
				dbc.setCumulativeInput("");
			}
			if (dbc.getCumulativeOutput() == null) {
				dbc.setCumulativeOutput("");
			}
		}
		result.setData(gson.toJson(listCount));
		result.setData1(line.getLineCode()+","+listCount.get(0).getPname()+","+listCount.get(0).getScesate()+","+listCount.get(0).getNowDate());
		result.setState(1);
		return result;
	}

	@Override
	public CyResult loadProductDetails(Integer page, Integer limit) {
		CyResult result = new CyResult();
		if(page==null){
			page=1;
		}
		int pageSize = (page-1)*limit;
		ProductDetails pd = new ProductDetails();
		pd.setPageSize(pageSize);
		pd.setLimit(limit);
		List<ProductDetails> pdList = dao.loadPd(pd);
		int count = dao.countPd();
		if (pdList != null) {
			result.setCode(0);
			result.setCount(count);
			result.setState(0);
			result.setMsg("获取成功");
			result.setData(pdList);
			return result;
		}
		result.setState(2);
		result.setMsg("获取失败");
		return result;
	}

	@Override
	public CyResult loadWorkLocation(Integer page, Integer limit, WorkLocation workLocation) {
		CyResult result = new CyResult();
		if(page==null){
			page=1;
		}
		int pageSize = (page-1)*limit;
		workLocation.setLimit(limit);
		workLocation.setPageSize(pageSize);
		List<WorkLocation> workList = dao.loadWl(workLocation);
		int count = dao.loadWlCount(workLocation);
		result.setData(workList);
		result.setCount(count);
		result.setMsg("successful");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult loadAllworkLine() {
		CyResult result = new CyResult();
		List<WorkLine> workLines = dao.loadAllworkLine();
		result.setData(workLines);
		result.setMsg("successful");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult insertWorkLine(WorkLine workLine) {
		CyResult result = new CyResult();
		if(workLine!=null){
			int row = dao.insertWorkLine(workLine);
			if(row==1){
				result.setMsg("successful");
			}
		}
		
		return null;
	}

	@Override
	public CyResult updatBoard(DashBoard db,WorkLine line) {
		CyResult result=new CyResult();
		db.setLine(line);
		try {
			dao.updateBoard(db);
			result.setState(1);
			result.setCount(0);
			result.setMsg("success!");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new  RuntimeException("failed");
		}
	}

	@Override
	public CyResult loadWorkerByLineCode(WorkLine line) {
		
		CyResult result=new CyResult();
		line.setPageSize((line.getPage()-1)*line.getLimit());
		List<ProductWorker>  list=dao.loadWorkerByWorker(line);
		int count=dao.loadWorkerByWorkerCount(line);
		result.setData(list);
		result.setCode(0);
		result.setCount(count);
		result.setMsg("success！");
		return result;
	}
	
	/*@Override
	public CyResult updateWorkerLineByCode(ProductWorker worker) {
		CyResult result=new CyResult();
		try {
			dao.updateWorkerLineByCode(worker);
			result.setCode(0);
			result.setMsg("success!");
			return result;
			} catch (Exception e) {
		    e.printStackTrace();
		    throw new RuntimeException("failed");
		}
	}*/

	@Override
	public CyResult updateWorkerLineByCode(String lineCode, String newArrString) {
		CyResult result=new CyResult();
		String[] workerArr=newArrString.split(",");
		List<ProductWorker> list=new ArrayList<>();
		for(String workerCode:workerArr){
			ProductWorker worker=new ProductWorker();
			worker.setWorkerCode(workerCode);
			worker.setLineCode(lineCode);
			System.out.println("worker-->"+worker);
			dao.updateWorkerLineByCode(worker);
		}
		result.setCode(0);
		result.setMsg("success!");
		return result;
	}

	@Override
	public CyResult deletePersonnel(Integer wid) {
		CyResult result = new CyResult();
		if (wid == null) {
			result.setMsg("wid不能为空");
			result.setState(0);
			return result;
		}
		ProductWorker pd = new ProductWorker();
		pd.setWid(wid);
		int a= dao.deletePersonnel(pd);
		if (a==1) {

			result.setState(0);
			result.setMsg("移除成功");
			return result;
		}
		result.setState(2);
		result.setMsg("移除失败");
		return result;
	}

}