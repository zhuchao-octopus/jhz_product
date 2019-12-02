package com.cy.service.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.dao.user.UserDao;
import com.cy.domain.user.User;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserDao userDao;

	@Override
	public User login(String username) {
		User user=new User();
		user.setUsername(username);
		User user1=userDao.findByUser(user);
		return user1;
	}
	
	
}
