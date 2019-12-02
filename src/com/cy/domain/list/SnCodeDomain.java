package com.cy.domain.list;
public class SnCodeDomain {
	
	/*YSTSN 号	 SN 号	 广电号	WIFI Mac地址	RJ Mac地址	 STBID	 PN 	VN	TN	YPRO*/
    private String YSTSN号;
    private String SN号;
    private String 广电号;
    private String WIFIMac地址;
    private String RJMac地址;
    private String STBID;
    private String PN;
    private String VN;
    private String TN;
    private String YPRO;
    public String getYSTSN号() {
		return YSTSN号;
	}
	public void setYSTSN号(String ySTSN号) {
		YSTSN号 = ySTSN号;
	}
	
	public String getSN号() {
		return SN号;
	}
	public void setSN号(String sN号) {
		SN号 = sN号;
	}
	public String get广电号() {
		return 广电号;
	}
	public void set广电号(String 广电号) {
		this.广电号 = 广电号;
	}
	public String getWIFIMac地址() {
		return WIFIMac地址;
	}
	public void setWIFIMac地址(String wIFIMac地址) {
		WIFIMac地址 = wIFIMac地址;
	}
	public String getRJMac地址() {
		return RJMac地址;
	}
	public void setRJMac地址(String rJMac地址) {
		RJMac地址 = rJMac地址;
	}
	public String getSTBID() {
		return STBID;
	}
	public void setSTBID(String sTBID) {
		STBID = sTBID;
	}
	public String getPN() {
		return PN;
	}
	public void setPN(String pN) {
		PN = pN;
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
	public String getYPRO() {
		return YPRO;
	}
	public void setYPRO(String yPRO) {
		YPRO = yPRO;
	}
	
    
    @Override
	public String toString() {
		return "SnCodeDomain [YSTSN号=" + YSTSN号 + ", SN号=" + SN号 + ", 广电号=" + 广电号 + ", WIFIMac地址=" + WIFIMac地址
				+ ", RJMac地址=" + RJMac地址 + ", STBID=" + STBID + ", PN=" + PN + ", VN=" + VN + ", TN=" + TN + ", YPRO="
				+ YPRO + "]";
	}

}
