package com.cy.controller.list;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cy.domain.list.ProductCode;
import com.cy.domain.list.Shipment;
import com.cy.service.list.ShipmentService;
import com.cy.utils.CyResult;

@Controller
@RequestMapping("shipment")
public class ShimentController {
	@Resource
	private ShipmentService service;
	
	@ResponseBody
	@RequestMapping("/getShipmentList")
	public CyResult getShipmentList(Shipment shipment){
		CyResult result = service.getShipmentList(shipment); 
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping("/insertShipment.do")
	public CyResult insertShipment(Shipment shipment){
		CyResult result = service.insertShipment(shipment);
		return result;
	}
	
	@RequestMapping("/xlsAddShipment.do")
	@ResponseBody
	public CyResult xlsAddShipment(@RequestParam("file") MultipartFile file, Shipment shipment, HttpServletRequest request) throws Exception{
		CyResult result = service.elsInsertShipment(file, shipment, request);
		return result;
	}
	@RequestMapping("/updateShipment.do")
	@ResponseBody
	public CyResult updateShipment( Shipment shipment){
		CyResult result = service.updateShipment(shipment);
		return result;
	}
	@RequestMapping("/deleteShipment.do")
	@ResponseBody
	public CyResult deleteShipment(String pid){
		CyResult result = service.deleteShipment(Integer.valueOf(pid));
		return result;
	}
	@RequestMapping("/deleteBathShipment.do")
	@ResponseBody
	public CyResult deleteBathShipment(String pidString){
		CyResult result = service.deleteShipments(pidString);
		return result;
	}
}
