package com.cy.controller.check;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cy.domain.check.CheckEquipment;
import com.cy.service.check.CheckEquipmentService;
import com.cy.utils.CyResult;
                      
@Controller
public class CheckEquipmentController {

	@Resource
	private CheckEquipmentService equipmentService;
	
	//后台
	@RequestMapping("/checkEquipment.do")
	@ResponseBody
	public CyResult getEquipment(CheckEquipment equipment){
		CyResult result = equipmentService.getEquipment(equipment);
		return result;
	}
	@RequestMapping("/checkEquipmentList.do")
	@ResponseBody
	public List<CheckEquipment> getEquipmentList(CheckEquipment equipment){
		List<CheckEquipment> list = equipmentService.getEquipmentList(equipment);
		return list;
	}
	
	@RequestMapping("/insertEquipment.do")
	@ResponseBody
	public CyResult insertEquipment(CheckEquipment equipment){
		CyResult result = equipmentService.insertEquipment(equipment);
		return result;
	}
	
	@RequestMapping("/insertEquipmentList.do")
	@ResponseBody
	public CyResult insertEquipmentList(@RequestParam(value = "file", required = false) MultipartFile file, CheckEquipment equipment, HttpServletRequest request) throws Exception{
		CyResult result = equipmentService.insertEquipmentList(file, equipment, request);
		return result;
	}
	
	@RequestMapping("/deleteEquipment.do")
	@ResponseBody
	public CyResult deleteEquipment(String id){
		CyResult result = equipmentService.deleteEquipment(id);
		return result;
	}
	
	@RequestMapping("/deleteBatchEquipment.do")
	@ResponseBody
	public CyResult deleteBatchEquipment(String	strId){
		CyResult result = equipmentService.deleteBatchEquipment(strId);
		return result;
	}
	
	@RequestMapping("/updateBatchEquipment.do")
	@ResponseBody
	public CyResult updateBatchEquipment(String	strId,String status){
		CyResult result = equipmentService.updateBatchEquipment(strId, status);
		return result;
	}
}
