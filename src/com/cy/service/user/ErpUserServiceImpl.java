package com.cy.service.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cy.dao.user.ErpUserDao;


import com.cy.domain.line.JhzRole;

import com.cy.domain.line.JhzRole;

import com.cy.domain.user.ErpUser;

import com.cy.utils.CyResult;
import com.cy.utils.CyUtil;
@Service
public class ErpUserServiceImpl implements ErpUserService {
    
	@Resource
	private ErpUserDao erpUserDao;
	
	@Override
	public CyResult loadAllUser(Integer limit,Integer page) {
		CyResult result = new CyResult();
		if(page==null){
			page=1;
		}
		int pageSize = (page-1)*limit;
		ErpUser user = new ErpUser();
		user.setLimit(limit);
		user.setPageSize(pageSize);
		List<ErpUser> userList = erpUserDao.loadAllUser(user);
		List<ErpUser> erpUser = new ArrayList<>();
		for(ErpUser userer : userList){
			JhzRole role = erpUserDao.loadRole(userer.getKid());
			if(role!=null){
				userer.setRoleName(role.getRoleName());
			}
			erpUser.add(userer);
		}
		int count = erpUserDao.loadAllCountUser();
		result.setData(erpUser);
		result.setCount(count);
		result.setMsg("succssful");
		result.setState(0);
		return result;
	}

	@Override
	public CyResult loadAllRole() {
		CyResult result=new CyResult();
		List<JhzRole> list=erpUserDao.loadAllRole();
		result.setData(list);
		result.setCode(0);
		result.setMsg("success!");
		return result;
	}

	@Override
	public CyResult deleteRole(int kid) {
		CyResult result=new CyResult();
		try {
			erpUserDao.deleteRole(kid);
			result.setCode(0);
			result.setState(1);
			result.setMsg("success!");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error!");
		}
	}

	@Override
	public CyResult editRole(JhzRole role) {
		CyResult result=new CyResult();
		try {
			erpUserDao.editRole(role);
			result.setCode(0);
			result.setState(1);
			result.setMsg("success");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error");
		}
	
	}

	@Override
	public CyResult addUser(ErpUser user) {
		CyResult result=new CyResult();
		if(user.getPwd()==null&&"".equals(user.getPwd())){
			result.setCode(0);
			result.setState(0);
			result.setMsg("password can be not null!");
			return result;
		}
		if(user.getUserName()==null&&"".equals(user.getUserName())){
			result.setCode(0);
			result.setState(2);
			result.setMsg("user accounts can be not null!");
			return result;
		}
		List<ErpUser> list=erpUserDao.findUserByName(user.getUserName());
		if(list.size()>0){
			result.setCode(0);
			result.setState(3);
			result.setMsg("user account already exists!");
			return result;
		}
		
		String pwd=user.getPwd();
		pwd=CyUtil.getMD5(pwd);
		user.setPwd(pwd);
		try {
			erpUserDao.addUser(user);
			result.setCode(0);
			result.setState(1);
			result.setMsg("success!");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error"); 
		}
		
		
		
	}
	
	
	

	@Override
	public List<ErpUser> login(String userName) {
		
		return  erpUserDao.findUserByName(userName);
	}

	@Override
	public CyResult findPermissionsByKid(int kid) {
		CyResult result=new CyResult();
		try {
			JhzRole role=erpUserDao.findPermissionsByKid(kid);
			result.setCode(0);
			result.setState(1);
			result.setData(role);
			result.setMsg("success!");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error");
		}
		
	}

	@Override
	public CyResult deleteUserById(int userId) {
		CyResult result=new CyResult();
		try {
			erpUserDao.deleteUserByUserId(userId);
			result.setCode(0);
			result.setMsg("success!");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error");
		}

	}
	
	

}
