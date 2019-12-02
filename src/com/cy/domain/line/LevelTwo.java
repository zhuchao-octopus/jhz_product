package com.cy.domain.line;

import java.util.List;

public class LevelTwo {
	private int LevelTwoId;
	private String name;
	private String value;
	private List<LevelThree> list;
	
	private Boolean checked;
	public Boolean getChecked() {
		return checked;
	}
	@Override
	public String toString() {
		return "LevelTwo [LevelTwoId=" + LevelTwoId + ", name=" + name + ", value=" + value + ", list=" + list
				+ ", checked=" + checked + "]";
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
	public int getLevelTwoId() {
		return LevelTwoId;
	}
	public void setLevelTwoId(int levelTwoId) {
		LevelTwoId = levelTwoId;
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
	public List<LevelThree> getList() {
		return list;
	}
	public void setList(List<LevelThree> list) {
		this.list = list;
	}
	
	
	
   
}
