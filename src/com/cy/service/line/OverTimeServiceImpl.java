package com.cy.service.line;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cy.dao.line.OverTimeDao;
import com.cy.dao.line.WorkLineDao;
import com.cy.domain.line.OverTime;
import com.cy.domain.line.ProductWorker;
import com.cy.domain.line.WorkLine;
import com.cy.domain.line.WorkLocation;
import com.cy.utils.CyResult;
@Service("overTimeService")
public class OverTimeServiceImpl implements OvertimeService {
	 
	@Resource
	private OverTimeDao timeDao;
	@Resource
	private WorkLineDao lineDao;
	
	

	@Override
	public CyResult loadOvertime(OverTime ovt) {
		CyResult result=new CyResult();
		List<OverTime> list=timeDao.findOverTime(ovt);
		result.setState(1);
		result.setData(list);
		result.setMsg("加载申请列表成功!");
		return result;
	}




	@Override
	public CyResult loadWorkerByLine(WorkLine line) {
		CyResult result=new CyResult();
		if(line.getLid()==null){
			result.setMsg("产线id不能为空!");
			result.setState(0);
			return result;
		}
		List<WorkLocation> list=lineDao.findLocationBylineCode(line);
		List<ProductWorker> plist=new ArrayList<ProductWorker>();
		for(WorkLocation w:list){
			if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
				plist.add(w.getWorker());
			}
		}
		result.setMsg("数据加载成功!");
		result.setState(1);
		result.setData(plist);
		return result;
	}




	@Override
	public CyResult addOverTime(OverTime ovt, String workerStr) {
		CyResult result=new CyResult();
		
		if(ovt.getLine().getLineCode()==null||ovt.getStartime()==null||ovt.getEndtime()==null||ovt.getCreatime()==null||"".equals(ovt.getCreatime())||ovt.getWorkPay()==null){
			result.setMsg("所有选项均需填写!");
			result.setState(0);
			return result;
		}
		if(workerStr==null||"".equals(workerStr)){
			result.setMsg("人员不能为空");
			result.setState(2);
			return result;
		}
		
		String[] wstr=workerStr.split(",");
		for(String str:wstr){
			ProductWorker worker=new ProductWorker();
			worker.setWorkerCode(str.trim().split(":")[0]);
			worker.setWorkerName(str.trim().split(":")[1]);
			List<OverTime> list=timeDao.findOverTime(null);
			List<Integer> numberList=new ArrayList<Integer>();
			for(OverTime ov:list){
				numberList.add(Integer.parseInt(ov.getOvtnumber()));
			}
			Collections.sort(numberList);
			
				
			
		   if(numberList.size()!=0){
			  String ovtnumber=String.format("%06d",numberList.get(numberList.size()-1)+1);
			  ovt.setWorker(worker);
			  ovt.setOvtnumber(ovtnumber);
		   }else{
			  String ovtnumber="000001";
			  ovt.setWorker(worker);
		      ovt.setOvtnumber(ovtnumber);
		   }
			try {
				timeDao.addOverTime(ovt);
			} catch (Exception e) {
				result.setMsg("异常,请检查网络!");
				result.setState(3);
				return result;
			}
			
		}
		result.setMsg("新增成功!");
		result.setState(1);
		return result;
	}




	@Override
	public CyResult editOvertimeById(OverTime ovt, String codeAndName) {
		CyResult result=new CyResult();
		String workerCode=codeAndName.substring(codeAndName.indexOf("(")+1, codeAndName.length()-1);
		
		String workerName=codeAndName.substring(0,codeAndName.indexOf("("));
		
		ProductWorker worker=new ProductWorker();
		worker.setWorkerCode(workerCode);
		worker.setWorkerName(workerName);
		ovt.setWorker(worker);
		try {
			int n=timeDao.editOvertimeById(ovt);
			System.out.println(n);
			result.setMsg("编辑成功!");
			result.setState(1);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("异常,请检查网络!");
			result.setState(3);
			return result;
		}

	}




	@Override
	public CyResult deleteById(OverTime ovt){
		CyResult result=new CyResult();
		List<OverTime> list=timeDao.findOverTime(ovt);
		if(list==null||list.size()<1){
			result.setMsg("数据已不存在,请尝试刷新!");
			result.setState(0);
			return result;
		}
		try {
			int n=timeDao.deleteById(ovt);
		} catch (Exception e) {
			result.setMsg("删除失败!");
			result.setState(2);
			return result;
		}
		result.setMsg("删除成功!");
		result.setState(1);
		return result;
	}




	
    
	
}
