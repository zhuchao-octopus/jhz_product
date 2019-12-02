package com.cy.domain.list;

import java.io.Serializable;

public class Binding_code implements Serializable{
    /*
     * REATE TABLE `binding_Code` (
  `bindingCodeId` int(100) NOT NULL AUTO_INCREMENT,
  `modelCode` varchar(100) DEFAULT NULL COMMENT '型号',
  `PNCode` varchar(100) DEFAULT NULL COMMENT '料号',
  `PCBA_SN` varchar(100) DEFAULT NULL COMMENT 'pcba_sn',
  `plt_SN` varchar(100) DEFAULT NULL COMMENT '小板sn',
  `disk_SN` varchar(100) DEFAULT NULL COMMENT '盘芯sn',
  `bxk_SN` varchar(100) DEFAULT NULL COMMENT '保修卡sn',
  `ch_SN` varchar(100) DEFAULT NULL COMMENT '彩盒sn',
     */

	private static final long serialVersionUID = 1L;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private Integer bindingCodeId;
	private String modelCode;
	private String pNCode;
	private String pCBA_SN;
	private String plt_SN;
	private String disk_SN;
	private String bxk_SN;
	private String ch_SN;
	private String creatime;
	private String boxNum;
	private String boardNum;
	private String batch;
	private String statusOne;
//	private TechList techlist;
	public Integer getBindingCodeId() {
		return bindingCodeId;
	}
	public void setBindingCodeId(Integer bindingCodeId) {
		this.bindingCodeId = bindingCodeId;
	}
	public String getModelCode() {
		return modelCode;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	public String getPNCode() {
		return pNCode;
	}
	public void setPNCode(String pNCode) {
		this.pNCode = pNCode;
	}
	public String getPCBA_SN() {
		return pCBA_SN;
	}
	public void setPCBA_SN(String pCBA_SN) {
		this.pCBA_SN = pCBA_SN;
	}
	public String getPlt_SN() {
		return plt_SN;
	}
	public void setPlt_SN(String plt_SN) {
		this.plt_SN = plt_SN;
	}
	public String getDisk_SN() {
		return disk_SN;
	}
	public void setDisk_SN(String disk_SN) {
		this.disk_SN = disk_SN;
	}
	public String getBxk_SN() {
		return bxk_SN;
	}
	public void setBxk_SN(String bxk_SN) {
		this.bxk_SN = bxk_SN;
	}
	public String getCh_SN() {
		return ch_SN;
	}
	public void setCh_SN(String ch_SN) {
		this.ch_SN = ch_SN;
	}
	public String getCreatime() {
		return creatime;
	}
	public void setCreatime(String creatime) {
		this.creatime = creatime;
	}
	public String getBoxNum() {
		return boxNum;
	}
	public void setBoxNum(String boxNum) {
		this.boxNum = boxNum;
	}
	public String getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(String boardNum) {
		this.boardNum = boardNum;
	}
//	public TechList getTechlist() {
//		return techlist;
//	}
//	public void setTechlist(TechList techlist) {
//		this.techlist = techlist;
//	}
	
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	
	public String getStatusOne() {
		return statusOne;
	}
	public void setStatusOne(String statusOne) {
		this.statusOne = statusOne;
	}
//	@Override
//	public String toString() {
//		return "Binding_code [bindingCodeId=" + bindingCodeId + ", modelCode=" + modelCode + ", pNCode=" + pNCode
//				+ ", pCBA_SN=" + pCBA_SN + ", plt_SN=" + plt_SN + ", disk_SN=" + disk_SN + ", bxk_SN=" + bxk_SN
//				+ ", ch_SN=" + ch_SN + ", creatime=" + creatime + ", boxNum=" + boxNum + ", boardNum=" + boardNum
//				+ ", batch=" + batch + ", statusOne=" + statusOne + ", techlist=" + techlist + "]";
//	}
	
	
	@Override
	public String toString() {
		return "Binding_code [bindingCodeId=" + bindingCodeId + ", modelCode=" + modelCode + ", pNCode=" + pNCode
				+ ", pCBA_SN=" + pCBA_SN + ", plt_SN=" + plt_SN + ", disk_SN=" + disk_SN + ", bxk_SN=" + bxk_SN
				+ ", ch_SN=" + ch_SN + ", creatime=" + creatime + ", boxNum=" + boxNum + ", boardNum=" + boardNum
				+ ", batch=" + batch + ", statusOne=" + statusOne + "]";
	}
	
	
	
	
	
	

}
