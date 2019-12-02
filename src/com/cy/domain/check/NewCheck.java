package com.cy.domain.check;

import java.io.Serializable;

import com.cy.domain.line.ProductDetails;
import com.cy.domain.line.ProductWorker;
import com.cy.domain.line.WorkLine;

public class NewCheck implements Serializable{
    /*
     * ccid` int(200) NOT NULL,
  `upLine` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '上线时间',
  `downLine` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '下线时间',
  `nowTime` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '当前时间',
  `wid` int(200) DEFAULT NULL COMMENT '对应的人员',
  `nowDate` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '当前日期',
     */

	private static final long serialVersionUID = 1L;
	private Integer ccid;
	private String upLine;//上线时间
	private String downLine;//下线时间
	private Double nowTm;//时间差
	private ProductWorker worker;
	private String nowDate;
	private WorkLine line;
	private ProductDetails product;
	private String workerName;
	private String pname;
	private String scstate;//工艺
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getCcid() {
		return ccid;
	}
	public WorkLine getLine() {
		return line;
	}
	public void setLine(WorkLine line) {
		this.line = line;
	}
	public ProductDetails getProduct() {
		return product;
	}
	public void setProduct(ProductDetails product) {
		this.product = product;
	}
	public void setCcid(Integer ccid) {
		this.ccid = ccid;
	}
	public String getUpLine() {
		return upLine;
	}
	public void setUpLine(String upLine) {
		this.upLine = upLine;
	}
	public String getDownLine() {
		return downLine;
	}
	public void setDownLine(String downLine) {
		this.downLine = downLine;
	}
	
	public ProductWorker getWorker() {
		return worker;
	}
	public void setWorker(ProductWorker worker) {
		this.worker = worker;
	}
	public String getNowDate() {
		return nowDate;
	}
	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}
	
	public Double getNowTm() {
		return nowTm;
	}
	public void setNowTm(Double nowTm) {
		this.nowTm = nowTm;
	}
	@Override
	public String toString() {
		return "NewCheck [ccid=" + ccid + ", upLine=" + upLine + ", downLine=" + downLine + ", nowTm=" + nowTm
				+ ", worker=" + worker + ", nowDate=" + nowDate + ", line=" + line + ", product=" + product
				+ ", workerName=" + workerName + ", pname=" + pname + ", scstate=" + scstate + "]";
	}
	public String getScstate() {
		return scstate;
	}
	public void setScstate(String scstate) {
		this.scstate = scstate;
	}
	

	
}
