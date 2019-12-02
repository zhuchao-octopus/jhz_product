package com.cy.dao.check;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cy.domain.check.CheckEquipment;

public interface CheckEquipmentDao {

	/**
	 * 查询设备信息
	 * @param equipment
	 * @return
	 */
	List<CheckEquipment> getEquipmentList(CheckEquipment equipment);
	
	/**
	 * 查询设备数量
	 * @param equipment
	 * @return
	 */
	int count(CheckEquipment equipment);

	
	/**
	 * 根据mac地址查询
	 * @param mac1
	 * @return
	 */
	CheckEquipment findEquipmentByMac1(@Param(value="mac1") String mac1);
	
	/**
	 * 单个设备新增
	 * @param equipment
	 * @return
	 */
	int insertEquipment(CheckEquipment equipment);
	
	/**
	 * 批量设备新增
	 * @param equipement
	 * @return
	 */
	int insertEquipmentLists(List<CheckEquipment> equipement);
	
	void deleteEquipment(Integer id);
	
	void deleteBatchEquipment(Integer strId);
	
	void updateEquipment(CheckEquipment equipment);
	
	void updateBatchEquipment(CheckEquipment equipment);
	
	
}
