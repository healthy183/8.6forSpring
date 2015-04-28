package com.kang.main.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kang.main.service.IdwrService;
import com.kang.main.service.ImainService;
import com.kang.model.SysRoles;
import com.kang.model.SysUsers;
import com.kang.model.vo.SysUsersVo;
import com.kang.sysRoles.service.IsysRolesService;
import com.kang.sysUsers.service.IsysUsersService;



@Scope("prototype")
@Controller
@RequestMapping("/main")
public class MainController {

	@Autowired
	@Qualifier("sysRolesServiceImpl")
	private IsysRolesService sysRolesServiceImpl;
	
	@Autowired
	@Qualifier("dwrServiceImpl")
	private IdwrService dwrServiceImpl;
	
	@Autowired
	@Qualifier("mainServiceImpl")
	private ImainService mainServiceImpl;
	
	@RequestMapping(value="/ajaxLgn",method=RequestMethod.POST)
	public @ResponseBody  String ajaxLgn
		(@RequestParam(value="usrName",required = true) String usrName,
			@RequestParam(value="usrPwd",required = true) String usrPwd){
		return dwrServiceImpl.lgn(usrName,usrPwd);
	}
	
	@RequestMapping(value="/lgn",method=RequestMethod.POST)
	public String lgn(SysUsers user,HttpServletRequest request){
		
		SysUsers lgnUser =	mainServiceImpl.lgn(user);
		if(lgnUser != null){
			HttpSession session = request.getSession();
			
			session.setAttribute("lgnUser",SysUsersVo.parse(lgnUser));
			//查询自己的角色
			List<SysRoles> sysRolesList = 
				sysRolesServiceImpl.findMyRole(lgnUser.getUsrid());
			
			session.setAttribute("sysRolesList",sysRolesList);
			
			return "forward:/welcome/welcome.jsp";
		}
		return "forward:/index.jsp";
	}
	
	@RequestMapping(value="/lgnOut")
	public String lgnOut(HttpServletRequest request){
		request.getSession().invalidate();
		return "forward:/index.jsp";
	}
	
	
	
}
