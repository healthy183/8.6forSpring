package com.kang.adminAdd.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kang.model.vo.SysUsersVo;
import com.kang.sysUsers.service.IsysUsersService;
import com.kang.tool.Page;


@Scope("prototype")
@Controller
@RequestMapping("/adminFind")
public class AdminFindController {

	@Autowired
	@Qualifier("sysUsersServiceImpl")
	private IsysUsersService sysUsersServiceImpl;
	
	@RequestMapping("/findUsr")
	public ModelAndView findUsr(@RequestParam(value = "pageNo", required = false) Integer pageNo){
		
		pageNo = pageNo==null?1:pageNo;
		int pageSize = 3;  

		Page page = sysUsersServiceImpl.findAllUsr(pageNo,pageSize);
		//request.setAttribute("page",page);
		ModelAndView view =new ModelAndView();
		view.addObject("pagedTopic",page);
		view.setViewName("adminFind/findUsr");
		return view;
	}
	
	@RequestMapping("/findMyUsr/{usrid}")
	public ModelAndView findMyUsr(HttpServletRequest request,
		@PathVariable(value="usrid") Integer usrid,
			@RequestParam(value = "pageNo", required = false) Integer pageNo){
		
		pageNo = pageNo==null?1:pageNo;
		int pageSize = 3; 
		
		if(usrid == null){
			SysUsersVo user = (SysUsersVo)request
					.getSession().getAttribute("lgnUser");
			usrid = user.getUsrid();
		}
		
		Page page =	sysUsersServiceImpl.findMyUsrForPage(usrid,pageNo,pageSize);
		
		ModelAndView view = new ModelAndView();
		
		view.addObject("page",page);
		view.addObject("usrid",usrid);
		view.setViewName("adminFind/findMyUsr");
		
		return view;
	}
	
}
