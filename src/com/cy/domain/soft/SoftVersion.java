package com.cy.domain.soft;

import java.io.Serializable;

public class SoftVersion implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer soft_id;
	private String soft_name;
	private Double soft_version;
	private String soft_description;
	private String soft_url;
	private Integer pageSize;
	private Integer page;
	private Integer limit;

	public Integer getSoft_id() {
		return soft_id;
	}

	public void setSoft_id(Integer soft_id) {
		this.soft_id = soft_id;
	}

	public String getSoft_name() {
		return soft_name;
	}

	public void setSoft_name(String soft_name) {
		this.soft_name = soft_name;
	}

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getSoft_version() {
		return soft_version;
	}

	public void setSoft_version(Double soft_version) {
		this.soft_version = soft_version;
	}

	public String getSoft_description() {
		return soft_description;
	}

	public void setSoft_description(String soft_description) {
		this.soft_description = soft_description;
	}

	public String getSoft_url() {
		return soft_url;
	}

	public void setSoft_url(String soft_url) {
		this.soft_url = soft_url;
	}

	@Override
	public String toString() {
		return "SoftVersion [soft_id=" + soft_id + ", soft_name=" + soft_name + ", soft_version=" + soft_version
				+ ", soft_description=" + soft_description + ", soft_url=" + soft_url + ", pageSize=" + pageSize
				+ ", page=" + page + ", limit=" + limit + "]";
	}

}
