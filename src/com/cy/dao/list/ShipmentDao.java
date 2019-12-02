package com.cy.dao.list;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cy.domain.list.Shipment;

public interface ShipmentDao {
	List<Shipment> getShipmentList(Shipment shipment);
	List<Shipment> getShipmentBySn1(@Param(value="sn1")String sn1);
	int count(Shipment shipment);
	Shipment getShipment(@Param(value="sn1")String sn1);
	int insertShipment(Shipment shipment);
	int xlsAddShipment(@Param(value="list")List<Shipment> list);
	void deleteShipment(@Param(value="id")Integer id);
	int updateShipment(Shipment shipment);
	







}
