package com.cy.domain.list;
public class FileUrl {
	private Integer fileId;
	private String fileName;
	private String fileUrl;
	private String creatime;
	private Integer page;
	private Integer pageSize;
	private Integer limit;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
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
	public String getCreatime() {
		return creatime;
	}
	public void setCreatime(String creatime) {
		this.creatime = creatime;
	}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	@Override
	public String toString() {
		return "FileUrl [fileId=" + fileId + ", fileName=" + fileName + ", fileUrl=" + fileUrl + ", creatime="
				+ creatime + ", page=" + page + ", pageSize=" + pageSize + ", limit=" + limit + "]";
	}
	

}
