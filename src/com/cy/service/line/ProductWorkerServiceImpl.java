package com.cy.service.line;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cy.dao.check.CheckDao;
import com.cy.dao.line.ProductWorkerDao;
import com.cy.dao.line.WorkLineDao;
import com.cy.domain.check.CheckModel;
import com.cy.domain.line.CheckRes;
import com.cy.domain.line.JhzRole;
import com.cy.domain.line.ProductWorker;
import com.cy.domain.line.WorkStationMark;
import com.cy.domain.list.WorkerDomain;
import com.cy.utils.CodeImportByExcel;
import com.cy.utils.CyResult;
import com.cy.utils.CyUtil;
import com.cy.utils.UploadUtil;
import com.google.gson.Gson;

@Service
@Transactional
public class ProductWorkerServiceImpl implements ProductWorkerService {
    
	@Resource
	private ProductWorkerDao dao;
	@Resource
	private WorkLineDao ldao;
	@Resource
	private CheckDao checkDao;
	
	
	
	@Override
	public CyResult addWorker(ProductWorker worker) {
		CyResult result=new CyResult();
		
		if(worker.getWorkerCode()==null||worker.getWorkerCode().trim().isEmpty()||worker.getWorkerName()==null||worker.getWorkerName().trim().isEmpty()){
			result.setMsg("必填项不能为空");
			result.setState(0);
			return result;
		}
		ProductWorker workerOne=dao.findWorkerByCode(worker.getWorkerCode());

		if(workerOne!=null){
			result.setMsg("工号已存在,不可重复录入！");
			result.setState(2);
			return result;
		}
		worker.setEstate(0);
		
		if(worker.getWorkerPrice()!=null){
		   worker.setWorkerOvertimePay(worker.getWorkerPrice()*1.5);
		   worker.setWorkerWeekenPay(worker.getWorkerPrice()*2);
		   worker.setWorkerHolidayPay(worker.getWorkerPrice()*3);
		   
		}else{
		   worker.setWorkerOvertimePay(null);
		   worker.setWorkerWeekenPay(null);
		   worker.setWorkerHolidayPay(null);
		}
		//worker.setFromValue(optionValue);
		
		worker.setStateTwo(1);//设置人员为在职状态
		int n=dao.addFinger(worker);
		
		if(n!=1){
	      throw new RuntimeException("录入新员工失败");
		}
		result.setMsg("录入成功!");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult findWorker(ProductWorker worker,String obj) {
		//先计算出需要分页
	    CyResult result=new CyResult();
	    List<ProductWorker> list=null;
	    if(worker.getWorkerCode()==null){
	       ProductWorker workerOne=new ProductWorker();
	       list=dao.findWorker(workerOne);
	       if(list==null||list.size()<1){
	    	   result.setMsg("查无数据!");
			   result.setState(0);
		       return result;
	       }
	       result.setData4(list.size());
	       if(worker.getPageNumber()==null){
	    	   worker.setPageNumber(0);
		   }else{
			  worker.setPageNumber((worker.getPageNumber()-1)*17);
		   }
	       list=dao.findWorker(worker);
		   if(list==null||list.size()<1){
		      result.setMsg("查无数据!");
		      result.setState(0);
	          return result;
		    }
		   result.setData(list);
		   result.setMsg("数据加载成功!");
		   result.setState(1);
	       return result;
	    }
	    //如果没有分页
		if((obj==null||!obj.equals("1"))&&worker.getPageNumber()==null){
			list=dao.findWorker(worker);
			if(list==null||list.size()<1){
				result.setMsg("查无数据！");
				result.setState(0);
				return result;
			}
			System.out.println(list);
			result.setData(list);
			result.setMsg("数据加载成功!");
			result.setState(1);
			return result;
		}
		if(obj.equals("1")&&worker.getPageNumber()==null){
		    Gson gs=new Gson();
		    list=dao.findWorker(worker);
		    if(list==null||list.size()<1){
		    	result.setMsg("查无数据!");
		        result.setState(1);
	            return result;
		    }
		    result.setData1(gs.toJson(list));
		    List<String> nameList=new ArrayList<String>();
		    for(ProductWorker wk:list){
			   if(wk.getWorkerName()!=null||!"".equals(wk.getWorkerName())){
				  nameList.add(wk.getWorkerName());
			   }
		    }  
		    if(nameList!=null&&nameList.size()>0){
			   result.setData3(nameList.get(0));
		    }
	        result.setMsg("数据加载成功!");
	        result.setState(1);
            return result;
        }
		result.setMsg("查询失败!");
        result.setState(0);
        return result;
	}    

	@Override
	public CyResult deleteWorker(ProductWorker worker) {
		
		CyResult result = new CyResult();
		
		if (worker.getWid()==null) {
			result.setMsg("ID无效");
			result.setState(0);
			return result;
		}
		System.out.println(worker);
		List<Integer> list = ldao.findWorkerLocation(worker);
		for(int abc : list){
			ldao.updateWorkerLocation(abc);
		}
		int n=dao.deleteWorker(worker);
		if(n!=1){
			throw new RuntimeException("刪除失敗");
		}
		result.setMsg("刪除成功");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult updateWorker(ProductWorker worker) {
		CyResult result = new CyResult();
		if (worker.getWid()==null) {
			result.setMsg("ID无效");
			result.setState(0);
			return result;
		}
		if(worker.getWorkerPrice()!=null){
		   worker.setWorkerOvertimePay(worker.getWorkerPrice()*1.5);
		   worker.setWorkerWeekenPay(worker.getWorkerPrice()*2);
		   worker.setWorkerHolidayPay(worker.getWorkerPrice()*3);
		}else{
			worker.setWorkerOvertimePay(null);
			worker.setWorkerWeekenPay(null);
			worker.setWorkerHolidayPay(null);	
		}
		//worker.setFromValue(optionValue);
		int n=dao.updateWorker(worker);
		if(n!=1){
			throw new RuntimeException("修改失败");
		}
		result.setMsg("修改成功");
		result.setState(1);
		return result;
	}
    
	/*
	 * 加載出每個員工的工時(non-Javadoc)
	 * @see com.cy.service.line.ProductWorkerService#loadWorkerHour(com.cy.domain.line.ProductWorker)
	 */
	@Override
	public CyResult loadWorkerHour(ProductWorker worker) throws ParseException {
		CyResult result=new CyResult();
		if(worker.getWid()==null){
			result.setMsg("人員ID不能爲空!");
			result.setState(0);
			return result;
		}
		ProductWorker workerOne=dao.findWorkerById(worker);
		if(workerOne==null){
			result.setMsg("未能查找相關人員,該人員或已刪除，請嘗試刷新!");
			result.setState(3);
			return result;
		}
		List<CheckModel> list=dao.findCheckByWid(worker);
		if(list==null||list.size()<1){
			result.setState(2);
			result.setMsg("未能查找到相關數據!");
			return result;
		}
		List<String> strl = new ArrayList<String>(); 
		List<Long> strlist=new ArrayList<Long>();
		for(CheckModel model:list){
			if(model.getUpTime1()!=null&&model.getDownTime1()!=null){
				Long lonTime=CyUtil.parseTime(model.getDownTime1(),model.getUpTime1());
				Long hour=lonTime/1000/60/60;
				Long minutes=lonTime/1000/60-(60*hour);
				Long second=lonTime/1000-(60*minutes+3600*hour);
				model.setTimeDifference1(hour+"小时"+minutes+"分钟"+second+"秒");
				strlist.add(lonTime);
			}
			if(model.getUpTime2()!=null&&model.getDownTime2()!=null){
				Long lonTime=CyUtil.parseTime(model.getDownTime2(),model.getUpTime2());
				Long hour=lonTime/1000/60/60;
				Long minutes=lonTime/1000/60-(60*hour);
				Long second=lonTime/1000-(60*minutes+3600*hour);
				model.setTimeDifference2(hour+"小时"+minutes+"分钟"+second+"秒");
				strlist.add(lonTime);
			}
			if(model.getUpTime3()!=null&&model.getDownTime3()!=null){
				Long lonTime=CyUtil.parseTime(model.getDownTime3(),model.getUpTime3());
				Long hour=lonTime/1000/60/60;
				Long minutes=lonTime/1000/60-(60*hour);
				Long second=lonTime/1000-(60*minutes+3600*hour);
				model.setTimeDifference3(hour+"小时"+minutes+"分钟"+second+"秒");
				strlist.add(lonTime);
			}
			if(model.getUpTime4()!=null&&model.getDownTime4()!=null){
				Long lonTime=CyUtil.parseTime(model.getDownTime4(),model.getUpTime4());
				Long hour=lonTime/1000/60/60;
				Long minutes=lonTime/1000/60-(60*hour);
				Long second=lonTime/1000-(60*minutes+3600*hour);
				model.setTimeDifference4(hour+"小时"+minutes+"分钟"+second+"秒");
				strlist.add(lonTime);
			}
			if(model.getUpTime5()!=null&&model.getDownTime5()!=null){
				Long lonTime=CyUtil.parseTime(model.getDownTime5(),model.getUpTime5());
				Long hour=lonTime/1000/60/60;
				Long minutes=lonTime/1000/60-(60*hour);
				Long second=lonTime/1000-(60*minutes+3600*hour);
				model.setTimeDifference5(hour+"小时"+minutes+"分钟"+second+"秒");
				strlist.add(lonTime);
			}
			if(model.getUpTime6()!=null&&model.getDownTime6()!=null){
				Long lonTime=CyUtil.parseTime(model.getDownTime6(),model.getUpTime6());
				Long hour=lonTime/1000/60/60;
				Long minutes=lonTime/1000/60-(60*hour);
				Long second=lonTime/1000-(60*minutes+3600*hour);
				model.setTimeDifference6(hour+"小时"+minutes+"分钟"+second+"秒");
				strlist.add(lonTime);
			}
			if(model.getUpTime7()!=null&&model.getDownTime7()!=null){
				Long lonTime=CyUtil.parseTime(model.getDownTime7(),model.getUpTime7());
				Long hour=lonTime/1000/60/60;
				Long minutes=lonTime/1000/60-(60*hour);
				Long second=lonTime/1000-(60*minutes+3600*hour);
				model.setTimeDifference7(hour+"小时"+minutes+"分钟"+second+"秒");
				strlist.add(lonTime);
			}
			if(model.getUpTime8()!=null&&model.getDownTime8()!=null){
				Long lonTime=CyUtil.parseTime(model.getDownTime8(),model.getUpTime8());
				Long hour=lonTime/1000/60/60;
				Long minutes=lonTime/1000/60-(60*hour);
				Long second=lonTime/1000-(60*minutes+3600*hour);
				model.setTimeDifference8(hour+"小时"+minutes+"分钟"+second+"秒");
				strlist.add(lonTime);
			}
			if(model.getUpTime9()!=null&&model.getDownTime9()!=null){
				Long lonTime=CyUtil.parseTime(model.getDownTime9(),model.getUpTime9());
				Long hour=lonTime/1000/60/60;
				Long minutes=lonTime/1000/60-(60*hour);
				Long second=lonTime/1000-(60*minutes+3600*hour);
				model.setTimeDifference9(hour+"小时"+minutes+"分钟"+second+"秒");
				strlist.add(lonTime);
			}
			if(model.getUpTime10()!=null&&model.getDownTime10()!=null){
				Long lonTime=CyUtil.parseTime(model.getDownTime10(),model.getUpTime10());
				Long hour=lonTime/1000/60/60;
				Long minutes=lonTime/1000/60-(60*hour);
				Long second=lonTime/1000-(60*minutes+3600*hour);
				model.setTimeDifference10(hour+"小时"+minutes+"分钟"+second+"秒");
				strlist.add(lonTime);
			}
			Long time=0L;
			for(int i=0;i<strlist.size();i++){
				time+=strlist.get(i);				
			}
			
			Long hour=time/1000/60/60;
			Long minutes=time/1000/60-(60*hour);
			Long second=time/1000-(60*minutes+3600*hour);
			model.setWorktime(hour+"小时"+minutes+"分钟"+second+"秒");
			
		}
	    result.setData(list);
	    result.setData1(strl);
	    result.setMsg("數據加載成功");
	    result.setState(1);
		return result;
	}

	@Override
	public CyResult findNewWorker(ProductWorker worker, Integer page, Integer limit) {
		 System.out.println("worker:"+worker);
		CyResult result = new CyResult();
		if (page == null) {
			page = 1;
		}
		int pageSize = (page - 1) * limit;
		// ProductWorker pw = new ProductWorker();
		worker.setLimit(limit);
		worker.setPageSize(pageSize);
		List<ProductWorker> list = dao.findAllWorkerByDemo(worker);
		int count = dao.findAllWorkerByDemoCount(worker);
		//int count = pwList.size();*/
		//System.out.println("list-->"+list);
		//int count =list.size();
		result.setCount(count);
		result.setData(list);
		result.setMsg("SUCCESSFUL");
		result.setState(0);
		return result;
		
	}
	@Override
	public CyResult findNewbdWorker(ProductWorker worker, Integer page, Integer limit) {
		 System.out.println("worker:"+worker);
		CyResult result = new CyResult();
		if (page == null) {
			page = 1;
		}
		int pageSize = (page - 1) * limit;
		// ProductWorker pw = new ProductWorker();
		worker.setLimit(limit);
		worker.setPageSize(pageSize);
		List<ProductWorker> list = dao.findbdWorker(worker);
        int count=dao.findbdWorkerCount(worker);
		result.setCount(count);
		result.setData(list);
		result.setMsg("SUCCESSFUL");
		result.setState(0);
		return result;
		
	}

	@Override
	public CyResult deleteBatchWorker(String workerStr) {
		CyResult result = new CyResult();
		String[] worker = workerStr.split(",");
		for(int i=0; i<worker.length; i++){
			dao.deleteBatchWorker(worker[i]);
		}
		result.setMsg("SUCCESSFUL");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult loadAllRole(Integer page, Integer limit) {
		CyResult result = new CyResult();
		if(page==null){
			page=1;
		}
		int pageSize = (page-1)*limit;
		JhzRole role = new JhzRole();
		role.setLimit(limit);
		role.setPageSize(pageSize);
		List<JhzRole> roleList = dao.loadAllRole(role);
		int count = dao.countAllRole();
		result.setCount(count);
		result.setData(roleList);
		result.setMsg("SUCCESSFUL");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult insertRole(JhzRole role) {
		CyResult result = new CyResult();
		if(role!=null){
			int row = dao.insertRole(role);
			if(row==1){
				result.setMsg("SUCCESSFUL");
				result.setState(1);
				return result;
			}
			result.setMsg("fail");
			result.setState(2);
			return result;
		}
		return result;
	}

	@Override
	public CyResult workerMoreAdd(MultipartFile file, ProductWorker worker, HttpServletRequest request) throws Exception {
		CyResult result = new CyResult();
		// 文件夹名字
		String folderName = "mac";
		// 接收上传结果
		Map<String, Object> map = null;
		//System.out.println("productCode:"+productCode);
		if (file != null&&!file.isEmpty()) {
			map = UploadUtil.upload(file, request, folderName);
			if (!map.get("msg").toString().equals("保存成功")) {
				result.setState(0);
				result.setMsg(map.get("msg") + ",请重新传送!!");
				return result;
			} else {
				String localAppAdd = map.get("path").toString() + File.separator + map.get("filename").toString();
				List<Map<String, String>> list = null;
		       //String colums[]={"SN","GD","RJMac","STBID","WIFIMac","YSTSN","VN","TN","YPRO","PN"};
		       list=CodeImportByExcel.ImportExcelUtils(localAppAdd);
		       List<ProductWorker> workerList=new ArrayList<ProductWorker>();
		       for (Map<String,String> mapone : list) {
		    	   ProductWorker pworker=new ProductWorker();
		    	   WorkerDomain workerDomian = new WorkerDomain();
		           //BeanUtils.populate(workerDomian, mapone);
		    	   BeanUtils.populate(workerDomian, mapone);
		           System.out.println("workerDomian:"+workerDomian);
		           pworker.setWorkerName(workerDomian.get姓名().replace(" ", ""));
		           pworker.setWorkerCode(workerDomian.get工号().replace(" ", ""));
		           
		           if(workerDomian.get时间()!=null&&workerDomian.get时间()!=""){
		        	   pworker.setHiredate(workerDomian.get时间().replace(" ", ""));
		           }
		           if(workerDomian.get工价()!=null){
		        	   pworker.setWorkerPrice(workerDomian.get工价());
		           }
		           if(workerDomian.get电话()!=null&&workerDomian.get电话()!=""){
		        	   pworker.setTel(workerDomian.get电话().replace(" ", ""));
		           }
		           if(workerDomian.get职务()!=null&&workerDomian.get职务()!=""){
		        	   pworker.setWorkPost(workerDomian.get职务().replace(" ", ""));
		           }
		           if(workerDomian.get技能()!=null&&workerDomian.get技能()!=""){
		        	   pworker.setAbi(workerDomian.get技能().replace(" ", ""));
		           }
		           pworker.setStateTwo(1);
		           if(pworker.getWorkerCode()!=null&&!"".equals(pworker.getWorkerCode())){
		        	 ProductWorker workerOne=dao.findWorkerByCode(pworker.getWorkerCode());
		        	 System.out.println("workerOne:"+workerOne);
		   			 if(workerOne!=null){
		   				 workerList.add(pworker);
		   				 continue;
		   			}
		   		   }
		          int n= dao.addFinger(pworker);
		           //workerList.add(pworker);
		       }
		       if(workerList.size()>0){
		    	   	result.setData(workerList);
		    	    result.setState(0);
					result.setMsg("录入成功，但文件工号有重复，重复见date");
					return result;
		       }
			   /* try {

			    	for(ProductWorker pworker:workerList){
			    		System.err.println("pworker："+pworker);
			    	}
			    	 int n = dao.addMoreWorker(workerList);
			    	 result.setState(1);
					 result.setMsg("录入成功!");
					 return result;
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("批量录入失败!");
				}*/
		       	
		       	result.setState(1);
				result.setMsg("录入成功!");
				return result;
			}
		}

		result.setState(2);
		result.setMsg("录入失败!");
		return result;
		
	}

	@Override
	public CyResult checkResource(CheckRes checkRes, Integer page, Integer limit) {
		System.out.println("worker:"+checkRes);
		CyResult result = new CyResult();
		if (page == null) {
			page = 1;
		}
		int pageSize = (page - 1) * limit;
		// ProductWorker pw = new ProductWorker();
		checkRes.setLimit(limit);
		checkRes.setPageSize(pageSize);
		List<CheckRes> list = dao.checkResource(checkRes);
		result.setData(list);
		result.setMsg("SUCCESSFUL");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult addCheckWorker(CheckRes checkRes) {
		CyResult result=new CyResult();
		System.out.println("checkRes-->"+checkRes);
		if(checkRes.getWorkerCode()==null||checkRes.getWorkerCode().trim().isEmpty()||checkRes.getWorkName()==null||checkRes.getWorkName().trim().isEmpty()){
			result.setMsg("姓名和工号不可为空！");
			result.setState(0);
			return result;
		}
		int n=dao.addCheckRes(checkRes);
		if(n!=1){
			throw new RuntimeException("录入失败");
		}
		result.setMsg("录入成功!");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult delCheckResource(CheckRes checkRes) {
		CyResult result = new CyResult();
		if (checkRes.getRid()==null) {
			result.setMsg("id无效");
			result.setState(0);
			return result;
		}
		System.out.println(checkRes);
		int n=dao.delCheckRes(checkRes);
		if(n!=1){
			throw new RuntimeException("刪除失敗");
		}
		result.setMsg("刪除成功");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult delCheckResByRids(CheckRes checkRes) {
		CyResult result = new CyResult();
		result.setMsg("刪除成功");
		result.setState(1);
		return result;
	}

	@Override
	public CyResult workStationMark(WorkStationMark workStationMark,
			Integer page, Integer limit) {
		System.out.println("worker:"+workStationMark);
		CyResult result = new CyResult();
		if (page == null) {
			page = 1;
		}
		int pageSize = (page - 1) * limit;
		// ProductWorker pw = new ProductWorker();
		workStationMark.setLimit(limit);
		workStationMark.setPageSize(pageSize);
		List<WorkStationMark> list = dao.loadWorkStationMark(workStationMark);
		int count=dao.countLoadWorkStationMark(workStationMark);
		result.setData(list);
		result.setCount(count);
		result.setMsg("SUCCESSFUL");
		result.setCode(0);
		return result;
	}
	
}
	
