package com.cy.dao.user;


import java.util.List;

import com.cy.domain.line.JhzRole;





import com.cy.domain.user.ErpUser;

public interface ErpUserDao {


	List<JhzRole> loadAllRole();


	List<ErpUser> loadAllUser(ErpUser user);

	int loadAllCountUser();

	JhzRole loadRole(Integer kid);


	void deleteRole(int kid);


	void editRole(JhzRole role);


	void addUser(ErpUser user);


	List<ErpUser> findUserByName(String userName);


	JhzRole findPermissionsByKid(int kid);

	void deleteUserByUserId(int userId);

}
