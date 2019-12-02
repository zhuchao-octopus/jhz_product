package com.cy.dao.user;

import com.cy.domain.user.User;
import com.cy.utils.CyResult;

public interface UserDao {
	public User findByUser(User user);
}
