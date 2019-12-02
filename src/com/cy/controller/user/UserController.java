package com.cy.controller.user;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.domain.line.JhzRole;
import com.cy.domain.user.ErpUser;
import com.cy.domain.user.User;
import com.cy.service.user.ErpUserService;
import com.cy.service.user.PermissionService;
import com.cy.service.user.UserService;
import com.cy.utils.CyResult;
import com.cy.utils.CyUtil;

@RequestMapping("/user")
@Controller
public class UserController {

		@Resource
		private UserService service;
		@Resource
		private ErpUserService erpUserService;
		@Resource
		private PermissionService permissionService;
		
		@RequestMapping("/loadAllUser.do")
		@ResponseBody
		public CyResult loadAllUser(Integer limit,Integer page){
			CyResult result=erpUserService.loadAllUser(limit,page);
			return result;	
		}
		
		@RequestMapping("/deleteUserById.do")
		@ResponseBody
		public CyResult deleteUserById(int userId){
			CyResult result=erpUserService.deleteUserById(userId);
			return result;
		}
		
		@RequestMapping("/addUser.do")
		@ResponseBody
		public CyResult addUser(ErpUser user){
			CyResult result=erpUserService.addUser(user);
			return result;
		}
		
		
		
		
		@RequestMapping("/login.do")
		@ResponseBody
		public CyResult login(HttpServletRequest request){
			CyResult result = new CyResult();
			String userName = request.getParameter("userName");
			String pwd = request.getParameter("pwd");
			if(userName==null||userName.trim().isEmpty()||pwd==null||pwd.trim().isEmpty()){
				result.setMsg("用户名和密码不能为空！");
				result.setState(0);
				return result;
			}
			List<ErpUser> list = erpUserService.login(userName);
			System.out.println(list);
			if(list.size()<1){
				result.setMsg("用户名不存在!");
				result.setState(2);
				return result;
			}
		    pwd=CyUtil.getMD5(pwd);
			if (!pwd.equals(list.get(0).getPwd())) {
				result.setMsg("密码输入有误！");
				result.setState(3);
				return result;
			}
			result.setMsg("登录成功！");
			result.setData(list.get(0));
			result.setState(1);
			return result;
		}
		
		
		@RequestMapping("/loadPermission.do")
		@ResponseBody
		public CyResult loadPermission(){
			CyResult result = permissionService.loadAllPermission();
			return result;
		}
		
		
		@RequestMapping("/loadPermissionById.do")
		@ResponseBody
		public CyResult loadPermissionById(int kid){
			CyResult result = permissionService.loadPermissionById(kid);
			return result;
		}
		
		@RequestMapping("/loadAllRole.do")
        @ResponseBody
        public CyResult loadAllRole(){
			CyResult result=erpUserService.loadAllRole();
			return result;
		}
		
		@RequestMapping("/deleteRole.do")
		@ResponseBody
		public CyResult deleteRole(int kid){
			CyResult result=erpUserService.deleteRole(kid);
			return result;
		}
		
		@RequestMapping("/editRole.do")
		@ResponseBody
		public CyResult updateRole(JhzRole role){
			CyResult result=erpUserService.editRole(role);
			return result;
		}
		
		@RequestMapping("/findPermissionsByKid.do")
		@ResponseBody
		public CyResult findPermissionsByKid(int kid){
			CyResult result=erpUserService.findPermissionsByKid(kid);
			return result;
		}
		
		
		
		
	
}
