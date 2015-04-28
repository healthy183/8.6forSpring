package com.kang.adminAdd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kang.model.SysUsers;
import com.kang.model.vo.SysUsersVo;
import com.kang.sysUsers.service.IsysUsersService;

@Scope("prototype")
@Controller
@RequestMapping("/adminUpdt")
public class AdminUpdtController {

	@Autowired
	@Qualifier("sysUsersServiceImpl")
	private IsysUsersService sysUsersServiceImpl;
	
	@RequestMapping("/updtUsr")
	public String updtUsr(HttpServletRequest request){
		
		//查询所有员工类型为0的员工即领导
		List<SysUsers> leadList = sysUsersServiceImpl.findAllLead();
		
		request.setAttribute("leadList",leadList);
		
		SysUsersVo vo =	(SysUsersVo)request.getSession().getAttribute("lgnUser");
		//查询所有用户除了自己 
		List<SysUsersVo> usrVoList = sysUsersServiceImpl.findAllButMe(vo.getUsrid());
		
		request.setAttribute("usrVoList",usrVoList);
		
		return "adminUpdt/updtUsr";
	} 
	
	@RequestMapping(value="/updtUsrSuccess",method=RequestMethod.POST)
	public  String updtUsrSuccess
		(@RequestParam(value="usrid",required = true) String usrid,
			@RequestParam(value="usrname",required = true) String usrname,
				@RequestParam(value="leadId",required = true) String leadId){
		
		sysUsersServiceImpl.updtUsrSuccess(usrid,usrname,leadId);
		
		return "修改完成!";
	};

	@RequestMapping("/updtMyUsr")
	public String updtMyUsr(HttpServletRequest request){
		//查询所有员工类型为0的员工即领导
		List<SysUsers> leadList = sysUsersServiceImpl.findAllLead();
		
		request.setAttribute("leadList",leadList);
		
		SysUsersVo vo =	(SysUsersVo)request.getSession().getAttribute("lgnUser");
		//查询自己所有所有用户
		List<SysUsersVo> usrVoList = sysUsersServiceImpl.findMyUser(vo.getUsrid());
		request.setAttribute("usrVoList",usrVoList);
		
		return "adminUpdt/updtMyUsr";
	}
	
	
}
