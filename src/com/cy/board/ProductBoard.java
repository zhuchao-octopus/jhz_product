package com.cy.board;

public class ProductBoard extends LineBoard{
	private String productxt;
	private String orderid;//订单批次
	private String customername;//客户
	private String model;//产品类型
	private String motherbtype;//主板型号
	private Integer ordernumber;//订单数
	private String prodnumber;//生产人数
	private String prodnumber_in;//当天生产投入数
	private String prod_Prodnumber;//当天生产产出数
	private Integer cumulat_number;//累计投入数
	private Integer cumulat_Prodnumber;//累计产出数
	private Integer order_shortage;//订单欠数
	private String remarks;//地区
	public String getProductxt() {
		return productxt;
	}
	public void setProductxt(String productxt) {
		this.productxt = productxt;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMotherbtype() {
		return motherbtype;
	}
	public void setMotherbtype(String motherbtype) {
		this.motherbtype = motherbtype;
	}
	public Integer getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(Integer ordernumber) {
		this.ordernumber = ordernumber;
	}
	public String getProdnumber() {
		return prodnumber;
	}
	public void setProdnumber(String prodnumber) {
		this.prodnumber = prodnumber;
	}
	public String getProdnumber_in() {
		return prodnumber_in;
	}
	public void setProdnumber_in(String prodnumber_in) {
		this.prodnumber_in = prodnumber_in;
	}
	public String getProd_Prodnumber() {
		return prod_Prodnumber;
	}
	public void setProd_Prodnumber(String prod_Prodnumber) {
		this.prod_Prodnumber = prod_Prodnumber;
	}
	public Integer getCumulat_number() {
		return cumulat_number;
	}
	public void setCumulat_number(Integer cumulat_number) {
		this.cumulat_number = cumulat_number;
	}
	public Integer getCumulat_Prodnumber() {
		return cumulat_Prodnumber;
	}
	public void setCumulat_Prodnumber(Integer cumulat_Prodnumber) {
		this.cumulat_Prodnumber = cumulat_Prodnumber;
	}
	public Integer getOrder_shortage() {
		return order_shortage;
	}
	public void setOrder_shortage(Integer order_shortage) {
		this.order_shortage = order_shortage;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "ProductBoard [productxt=" + productxt + ", orderid=" + orderid
				+ ", customername=" + customername + ", model=" + model
				+ ", motherbtype=" + motherbtype + ", ordernumber="
				+ ordernumber + ", prodnumber=" + prodnumber
				+ ", prodnumber_in=" + prodnumber_in + ", prod_Prodnumber="
				+ prod_Prodnumber + ", cumulat_number=" + cumulat_number
				+ ", cumulat_Prodnumber=" + cumulat_Prodnumber
				+ ", order_shortage=" + order_shortage + ", remarks=" + remarks
				+ "]";
	}
	
}
