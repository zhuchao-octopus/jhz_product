package com.cy.utils;

import java.io.Serializable;
import java.util.List;

public class CyResult implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private String msg;
	private Integer state;
	private	Object data1;
	private Object data2;
	private Object data;
	private Object data3;
	
	private int code;
	private int count;
	
	private Object data4;
	private Object data5;
	private Object data6;
	private Object data7;
	private Object data8;
	public Object getData8() {
		return data8;
	}
	public void setData8(Object data8) {
		this.data8 = data8;
	}
	public void setData3(Object data3) {
		this.data3 = data3;
	}
	public Object getData7() {
		return data7;
	}


	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setData7(Object data7) {
		this.data7 = data7;
	}


	public Object getData6() {
		return data6;
	}


	public void setData6(Object data6) {
		this.data6 = data6;
	}


	public Object getData5() {
		return data5;
	}


	public void setData5(Object data5) {
		this.data5 = data5;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}


	public Object getData1() {
		return data1;
	}


	public void setData1(Object data1) {
		this.data1 = data1;
	}


	public Object getData2() {
		return data2;
	}


	public void setData2(Object data2) {
		this.data2 = data2;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}


	@Override
	public String toString() {
		return "CyResult [msg=" + msg + ", state=" + state + ", data1=" + data1 + ", data2=" + data2 + ", data=" + data
				+ "]";
	}


	


	


	public Object getData3() {
		return data3;
	}


	public Object getData4() {
		return data4;
	}


	public void setData4(Object data4) {
		this.data4 = data4;
	}


	
	
}
