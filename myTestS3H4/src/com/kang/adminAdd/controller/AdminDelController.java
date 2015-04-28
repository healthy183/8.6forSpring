package com.kang.adminAdd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kang.model.vo.SysUsersVo;
import com.kang.sysUsers.service.IsysUsersService;

@Scope("prototype")
@Controller
@RequestMapping("/adminDel")
public class AdminDelController {

	@Autowired
	@Qualifier("sysUsersServiceImpl")
	private IsysUsersService sysUsersServiceImpl;
	
	@RequestMapping("/delUsr")
	public String delUsr(HttpServletRequest request){
		
		SysUsersVo vo =	(SysUsersVo)request.getSession().getAttribute("lgnUser");
		//查询所有用户除了自己 
		List<SysUsersVo> usrVoList = sysUsersServiceImpl.findAllButMe(vo.getUsrid());
		
		request.setAttribute("usrVoList",usrVoList);
		return "adminDel/delUsr";
	}
	
	@RequestMapping("/delUsrSuccess")
	public String delUsrSuccess(HttpServletRequest request,String usrid){
		
		sysUsersServiceImpl.delUsr(usrid);
		
		SysUsersVo vo =	(SysUsersVo)request.getSession().getAttribute("lgnUser");
		//查询所有用户除了自己 
		List<SysUsersVo> usrVoList = sysUsersServiceImpl.findAllButMe(vo.getUsrid());
		
		request.setAttribute("usrVoList",usrVoList);
		return "adminDel/delUsr";
	}
	
	@RequestMapping("/delMyUsr")
	public String delMyUsr(HttpServletRequest request){
		
		SysUsersVo vo =	(SysUsersVo)request.getSession().getAttribute("lgnUser");
		//查询所有下属
		List<SysUsersVo> usrVoList = sysUsersServiceImpl.findMyUsr(vo.getUsrid());
		
		request.setAttribute("usrVoList",usrVoList);
		return "adminDel/delMyUsr";
	}
	
	
	@RequestMapping("/delMyUsrSuccess")
	public String delMyUsrSuccess(HttpServletRequest request,String usrid){
		
		sysUsersServiceImpl.delUsr(usrid);
		
		SysUsersVo vo =	(SysUsersVo)request.getSession().getAttribute("lgnUser");
		//查询所有下属
		List<SysUsersVo> usrVoList = sysUsersServiceImpl.findMyUsr(vo.getUsrid());
		
		request.setAttribute("usrVoList",usrVoList);
		return "adminDel/delMyUsr";
	}
}
