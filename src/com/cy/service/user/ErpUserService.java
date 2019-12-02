package com.cy.service.user;

import java.util.List;

import com.cy.domain.line.JhzRole;
import com.cy.domain.user.ErpUser;
import com.cy.utils.CyResult;

public interface ErpUserService {


	CyResult loadAllUser(Integer limit, Integer page);

	CyResult loadAllRole();

	CyResult deleteRole(int kid);

	CyResult editRole(JhzRole role);

	CyResult addUser(ErpUser user);

	List<ErpUser> login(String userName);

	CyResult findPermissionsByKid(int kid);

	CyResult deleteUserById(int userId);

}
