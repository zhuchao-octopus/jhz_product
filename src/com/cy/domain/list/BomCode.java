package com.cy.domain.list;

public class BomCode {
	private String mid;
	private String PNAME;
	private String PN;
	private Double useNumer;
	private String bitNumber;
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getPNAME() {
		return PNAME;
	}
	public void setPNAME(String pNAME) {
		PNAME = pNAME;
	}
	public String getPN() {
		return PN;
	}
	public void setPN(String pN) {
		PN = pN;
	}
	public Double getUseNumer() {
		return useNumer;
	}
	public void setUseNumer(Double useNumer) {
		this.useNumer = useNumer;
	}
	public String getBitNumber() {
		return bitNumber;
	}
	public void setBitNumber(String bitNumber) {
		this.bitNumber = bitNumber;
	}
	@Override
	public String toString() {
		return "BomCode [mid=" + mid + ", PNAME=" + PNAME + ", PN=" + PN
				+ ", useNumer=" + useNumer + ", bitNumber=" + bitNumber + "]";
	}
	
}
