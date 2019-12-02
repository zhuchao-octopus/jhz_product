package com.cy.domain.list;

public class SnCode {
	private String SN;
	private String GD;
	private String RJMac;
	private String STBID;
	private String WIFIMac;
	private String YSTSN;
	private String VN;
	private String YPRO;//产品类型
	private String PN;//生产批次
	public String getSN() {
		return SN;
	}
	public String getYPRO() {
		return YPRO;
	}
	public void setYPRO(String yPRO) {
		YPRO = yPRO;
	}
	public String getPN() {
		return PN;
	}
	public void setPN(String pN) {
		PN = pN;
	}
	public void setSN(String sN) {
		SN = sN;
	}
	public String getGD() {
		return GD;
	}
	public void setGD(String gD) {
		GD = gD;
	}
	public String getRJMac() {
		return RJMac;
	}
	public void setRJMac(String rJMac) {
		RJMac = rJMac;
	}
	public String getSTBID() {
		return STBID;
	}
	public void setSTBID(String sTBID) {
		STBID = sTBID;
	}
	public String getWIFIMac() {
		return WIFIMac;
	}
	public void setWIFIMac(String wIFIMac) {
		WIFIMac = wIFIMac;
	}
	public String getYSTSN() {
		return YSTSN;
	}
	public void setYSTSN(String ySTSN) {
		YSTSN = ySTSN;
	}
	public String getVN() {
		return VN;
	}
	public void setVN(String vN) {
		VN = vN;
	}
	public String getTN() {
		return TN;
	}
	public void setTN(String tN) {
		TN = tN;
	}
	private String TN;
	@Override
	public String toString() {
		return "SnCode [SN=" + SN + ", GD=" + GD + ", RJMac=" + RJMac + ", STBID=" + STBID + ", WIFIMac=" + WIFIMac
				+ ", YSTSN=" + YSTSN + ", VN=" + VN + ", YPRO=" + YPRO + ", PN=" + PN + ", TN=" + TN + "]";
	}
}
