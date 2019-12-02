package com.cy.board;

public class ProductTest {
	private Integer cid;
	private String brand;
	private String model;
	private String deviceName;
	private String os;
	private String fingerprint;
	private String firmwareClient;
	private String launcherPackageName;//Launch名
	private String launcherPackageVersion;//版本号
	private String wifiMac;
	private String etherMac;
	private String cpuid;//Cpu系列号
	private Integer page;
	private Integer limit;
	private Integer pageSize;
	private String time;
	private String serialno;//序列号
	
	
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getFingerprint() {
		return fingerprint;
	}
	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}
	public String getFirmwareClient() {
		return firmwareClient;
	}
	public void setFirmwareClient(String firmwareClient) {
		this.firmwareClient = firmwareClient;
	}
	public String getLauncherPackageName() {
		return launcherPackageName;
	}
	public void setLauncherPackageName(String launcherPackageName) {
		this.launcherPackageName = launcherPackageName;
	}
	public String getLauncherPackageVersion() {
		return launcherPackageVersion;
	}
	public void setLauncherPackageVersion(String launcherPackageVersion) {
		this.launcherPackageVersion = launcherPackageVersion;
	}
	public String getWifiMac() {
		return wifiMac;
	}
	public void setWifiMac(String wifiMac) {
		this.wifiMac = wifiMac;
	}
	public String getEtherMac() {
		return etherMac;
	}
	public void setEtherMac(String etherMac) {
		this.etherMac = etherMac;
	}
	public String getCpuid() {
		return cpuid;
	}
	public void setCpuid(String cpuid) {
		this.cpuid = cpuid;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	@Override
	public String toString() {
		return "ProductTest [cid=" + cid + ", brand=" + brand + ", model="
				+ model + ", deviceName=" + deviceName + ", os=" + os
				+ ", fingerprint=" + fingerprint + ", firmwareClient="
				+ firmwareClient + ", launcherPackageName="
				+ launcherPackageName + ", launcherPackageVersion="
				+ launcherPackageVersion + ", wifiMac=" + wifiMac
				+ ", etherMac=" + etherMac + ", cpuid=" + cpuid + ", page="
				+ page + ", limit=" + limit + ", pageSize=" + pageSize
				+ ", time=" + time + ", serialno=" + serialno+"]";
	}
	
}
