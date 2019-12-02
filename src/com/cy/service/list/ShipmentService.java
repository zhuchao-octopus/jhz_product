package com.cy.service.list;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.cy.domain.list.ProductCode;
import com.cy.domain.list.Shipment;
import com.cy.utils.CyResult;

public interface ShipmentService {
	CyResult getShipmentList(Shipment shipment);
	CyResult insertShipment(Shipment shipment);
	CyResult elsInsertShipment(MultipartFile file, Shipment shipment, HttpServletRequest request)throws Exception;
	CyResult deleteShipment(Integer id);
	CyResult deleteShipments(String idStr);
	CyResult updateShipment(Shipment shipment);
}
