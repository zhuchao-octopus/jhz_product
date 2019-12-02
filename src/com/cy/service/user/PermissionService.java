package com.cy.service.user;

import com.cy.utils.CyResult;

public interface PermissionService {

	public  CyResult loadAllPermission();

	public CyResult loadPermissionById(int kid);

}
