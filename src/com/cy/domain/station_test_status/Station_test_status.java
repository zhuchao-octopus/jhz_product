package com.cy.domain.station_test_status;

import java.io.Serializable;

import com.cy.domain.line.WorkStationCode;
import com.cy.domain.list.ProductCode;


//工作站状态的中间表
public class Station_test_status implements Serializable {


	private static final long serialVersionUID = 1L;
	
//	  `station_sn_product_id` int(100) NOT NULL COMMENT '工作站结果统计',
//	  `product_name` varchar(100) DEFAULT NULL COMMENT '产品id',
//	  `productCode_sn1` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT 'sn1',
//	  `workstaion_id` int(100) DEFAULT NULL COMMENT '工作站主键',
//	  `workstaion_status` int(100) DEFAULT NULL COMMENT '工作站测试状态  0-ok   1-not ok',
	
	private Integer station_sn_product_id;
	private String product_name;
	
	private String productCode_sn1;
	private boolean state;//是否已录
	private WorkStationCode workstationCode;
	private Integer workstation_status;
	public Integer getStation_sn_product_id() {
		return station_sn_product_id;
	}
	public void setStation_sn_product_id(Integer station_sn_product_id) {
		this.station_sn_product_id = station_sn_product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProductCode_sn1() {
		return productCode_sn1;
	}
	public void setProductCode_sn1(String productCode_sn1) {
		this.productCode_sn1 = productCode_sn1;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public WorkStationCode getWorkstationCode() {
		return workstationCode;
	}
	public void setWorkstationCode(WorkStationCode workstationCode) {
		this.workstationCode = workstationCode;
	}
	public Integer getWorkstation_status() {
		return workstation_status;
	}
	public void setWorkstation_status(Integer workstation_status) {
		this.workstation_status = workstation_status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Station_test_status [station_sn_product_id=" + station_sn_product_id + ", product_name=" + product_name
				+ ", productCode_sn1=" + productCode_sn1 + ", state=" + state + ", workstationCode=" + workstationCode
				+ ", workstation_status=" + workstation_status + "]";
	}
	
}
