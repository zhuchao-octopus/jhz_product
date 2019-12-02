package com.cy.domain.line;

import java.util.List;

public class LevelOne {
	 private int levelOneId;
	 private String name;
	 private Boolean checked;
	 private String value;
	
	private List<LevelTwo>  list;
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public int getLevelOneId() {
		return levelOneId;
	}
	public void setLevelOneId(int levelOneId) {
		this.levelOneId = levelOneId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<LevelTwo> getList() {
		return list;
	}
	public void setList(List<LevelTwo> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "LevelOne [levelOneId=" + levelOneId + ", name=" + name
				+ ", checked=" + checked + ", value=" + value + ", list="
				+ list + "]";
	}

}
