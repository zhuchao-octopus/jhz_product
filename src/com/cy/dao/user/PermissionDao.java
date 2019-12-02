package com.cy.dao.user;

import java.util.List;

import com.cy.domain.line.JhzRole;
import com.cy.domain.line.LevelOne;
import com.cy.domain.line.LevelThree;

public interface PermissionDao {
	  
	public List<LevelOne> loadAllPermission();

	public JhzRole findPermissionByKid(int kid);
	
	public int addLevelThree(LevelThree level);

}
