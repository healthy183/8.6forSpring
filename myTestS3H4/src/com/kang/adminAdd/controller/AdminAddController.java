package com.kang.adminAdd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kang.model.SysRoles;
import com.kang.model.SysUsers;
import com.kang.sysRoles.service.IsysRolesService;
import com.kang.sysUsers.service.IsysUsersService;

@Scope("prototype")
@Controller
@RequestMapping("/adminAdd")
public class AdminAddController {
	
	@Autowired
	@Qualifier("sysUsersServiceImpl")
	private IsysUsersService sysUsersServiceImpl;
	
	@Autowired
	@Qualifier("sysRolesServiceImpl")
	private IsysRolesService sysRolesServiceImpl;
	
	//新增用户同时新增角色(测试两个一对多,即多对多)
	@RequestMapping("/addUserAndRole")
	public String addUserAndRole(HttpServletRequest request){
		//查询所有员工类型为0的员工即领导
		List<SysUsers> leadList = sysUsersServiceImpl.findAllLead();
		request.setAttribute("leadList",leadList);
		
		//查询所有角色
		List<SysRoles> rolesList = sysRolesServiceImpl.findAllRoles();
		request.setAttribute("rolesList",rolesList);
		return "adminAdd/addUserAndRole";
	}
	
	@RequestMapping("/addUserAndRoleSuccess")
	public String addUserAndRoleSuccess(SysUsers newUser,SysRoles newRole,HttpServletRequest request){
		String leadId =	request.getParameter("leadId");
		String[] roleIdCheck = request.getParameterValues("roleIdCheck");
		sysUsersServiceImpl.saveUserAndRole(newUser,newRole,roleIdCheck,leadId);
		return "adminAdd/addUserAndRoleSuccess";
	}
	@RequestMapping("/addUsr")
	public String addUsr(HttpServletRequest request){
		
		//查询所有员工类型为0的员工即领导
		List<SysUsers> leadList = sysUsersServiceImpl.findAllLead();
		request.setAttribute("leadList",leadList);
		
		//查询所有角色
		List<SysRoles> rolesList = sysRolesServiceImpl.findAllRoles();
		request.setAttribute("rolesList",rolesList);
		return "adminAdd/addUsr";
	}

	@RequestMapping("/addMyUsr")
	public String addMyUsr(HttpServletRequest request){
		//查询所有角色
		List<SysRoles> rolesList = sysRolesServiceImpl.findAllRoles();
		request.setAttribute("rolesList",rolesList);
		return "adminAdd/addMyUsr";
	}
}
