package com.cy.controller.line;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cy.domain.line.CheckRes;
import com.cy.domain.line.JhzRole;
import com.cy.domain.line.ProductWorker;
import com.cy.domain.line.WorkStationMark;
import com.cy.service.line.ProductWorkerService;
import com.cy.utils.CyResult;

@RequestMapping("/worker")
@Controller
public class ProductWorkerController {

	@Resource
	private ProductWorkerService service;
	
	
	
	
	
	@RequestMapping("/addWorker.do")
	@ResponseBody
	public CyResult addWorker(ProductWorker worker){
		CyResult result=service.addWorker(worker);
		return result;
	}
	
	@RequestMapping("/workerMoreAdd.do")
	@ResponseBody
	public CyResult workerMoreAdd(@RequestParam("file") MultipartFile file, ProductWorker worker, HttpServletRequest request) throws Exception{
		CyResult result=service.workerMoreAdd(file,worker,request);
		return result;
	}
	
	
	@RequestMapping("/loadWorker.do")
	@ResponseBody
	public CyResult loadWorker(ProductWorker worker,String obj){
	     CyResult result=service.findWorker(worker,obj);
	     return result;
	}
	
	@RequestMapping("/deleteWorker.do")
	@ResponseBody
	public CyResult deleteWorker(ProductWorker worker){
		CyResult result = service.deleteWorker(worker);
		return result;
	}
	
	@RequestMapping("/updateWorker.do")
	@ResponseBody
	public CyResult updateWorker(ProductWorker worker,HttpServletRequest request){
		/*System.out.println("人员:"+worker);
		System.out.println(request.getParameter("optionValue"));
		String opv=request.getParameter("optionValue");
		Integer optionValue=null;
		if(opv!=null){
			optionValue =Integer.parseInt(opv);
		}*/
		CyResult result = service.updateWorker(worker);
		return result;
	}
	
	@RequestMapping("/loadWorkHour.do")
	@ResponseBody
	public CyResult loadWorkerHour(ProductWorker worker) throws ParseException{
		CyResult result=service.loadWorkerHour(worker);
		return result;
	}
	
	@RequestMapping("/loadNewWorker.do")
	@ResponseBody
	public CyResult loadNewWorker(ProductWorker worker, Integer page , Integer limit){
	     CyResult result=service.findNewWorker(worker,page,limit);
	     return result;
	}
	@RequestMapping("/loadNewbdWorker.do")
	@ResponseBody
	public CyResult loadNewbdWorker(ProductWorker worker, Integer page , Integer limit){
	     CyResult result=service.findNewbdWorker(worker, page, limit);
	     return result;
	}
	@RequestMapping("/deleteWorkerByIds.do")
	@ResponseBody
	public CyResult deleteBatchWorker(String workerStr){
		CyResult result = service.deleteBatchWorker(workerStr);
		return result;
	}
	
	@RequestMapping("/loadAllRole.do")
	@ResponseBody
	public CyResult loadAllRole(Integer page , Integer limit){
		CyResult result = service.loadAllRole(page,limit);
		return result;
	}
	
	@RequestMapping("/addRole.do")
	@ResponseBody
	public CyResult insertRole(JhzRole role){
		CyResult result = service.insertRole(role);
		return result;
	}
	@RequestMapping("/checkResource.do")
	@ResponseBody
	public CyResult checkResource(CheckRes checkRes,Integer page , Integer limit){
		CyResult result = service.checkResource(checkRes,page,limit);
		return result;
	}
	@RequestMapping("/workStationMark")
	@ResponseBody
	public CyResult workStationMark(WorkStationMark workStationMark,Integer page , Integer limit){
		CyResult result = service.workStationMark(workStationMark,page,limit);
		return result;
	}
	@RequestMapping("/addCheckWorker.do")
	@ResponseBody
	public CyResult addCheckWorker(CheckRes checkRes){
		CyResult result=service.addCheckWorker(checkRes);
		return result;
	}
	@RequestMapping("/delCheckResource.do")
	@ResponseBody
	public CyResult delCheckResource(CheckRes checkRes){
		CyResult result=service.delCheckResource(checkRes);
		return result;
	}
	@RequestMapping("/delCheckResByRids.do")
	@ResponseBody
	public CyResult delCheckResByRids(CheckRes checkRes){
		CyResult result=service.delCheckResByRids(checkRes);
		return result;
	}
	
}
