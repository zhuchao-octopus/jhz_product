package com.cy.domain.daynote;
//产品的投入与产出
public class ProductRecord {
	
	private String pname;
	private String workTime;
	private String assemblyInput;//组装投入
	private String assemblyOutput;//组装产出
	private String packagInput;//包装投入
	private String PackageOutput;//包装产出
	private String income;
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public String getAssemblyInput() {
		return assemblyInput;
	}
	public void setAssemblyInput(String assemblyInput) {
		this.assemblyInput = assemblyInput;
	}
	public String getAssemblyOutput() {
		return assemblyOutput;
	}
	public void setAssemblyOutput(String assemblyOutput) {
		this.assemblyOutput = assemblyOutput;
	}
	public String getPackagInput() {
		return packagInput;
	}
	public void setPackagInput(String packagInput) {
		this.packagInput = packagInput;
	}
	public String getPackageOutput() {
		return PackageOutput;
	}
	public void setPackageOutput(String packageOutput) {
		PackageOutput = packageOutput;
	}
	@Override
	public String toString() {
		return "ProductRecord [pname=" + pname + ", workTime=" + workTime + ", assemblyInput=" + assemblyInput
				+ ", assemblyOutput=" + assemblyOutput + ", packagInput=" + packagInput + ", PackageOutput="
				+ PackageOutput + ", income=" + income + "]";
	}
	
}
