package com.kang.adminAdd.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kang.TReim.service.ItreimService;
import com.kang.model.SysUsers;
import com.kang.model.TReim;
import com.kang.model.TReimitm;
import com.kang.model.vo.SysUsersVo;

@Scope("prototype")
@Controller
@RequestMapping("/adminTreim")
public class AdminTreimController {
	
	@Autowired
	@Qualifier("treimServiceImpl")
	private ItreimService treimServiceImpl;
	
	@RequestMapping("/findTreim")
	public String findTreim(HttpServletRequest request){
		
		int i = 1/0;
		
		SysUsersVo user =(SysUsersVo)request
			.getSession().getAttribute("lgnUser");
		
		List<TReim>	treimList = treimServiceImpl.findMyTreim(user.getUsrid());
		request.setAttribute("treimList",treimList);
		
		return "adminTreim/findTreim";
	}
	
	@RequestMapping("/applyTreim")
	public String applyTreim(){
		return "adminTreim/applyTreim";
	}
	
	@RequestMapping(value="/applyTreimSuccess",method=RequestMethod.POST)
	public String applyTreimSuccess(HttpServletRequest request){
		
		SysUsersVo sysusers = (SysUsersVo)request.getSession().getAttribute("lgnUser");
		
		String rmname =	request.getParameter("rmname");
		String[] rmitmname = request.getParameterValues("rmitmname");
		String[] rmitmcost = request.getParameterValues("rmitmcost");
		
		treimServiceImpl.saveTReim(sysusers,rmname,rmitmname,rmitmcost);
		
		return "adminTreim/applyTreimSuccess";
	}
	
	@RequestMapping("/examineTreim")
	public String examineTreim(HttpServletRequest request){
		
		SysUsersVo sysusers = (SysUsersVo)request.getSession().getAttribute("lgnUser");
		
		List<TReim>	treimList = treimServiceImpl.findTreimTome(sysusers.getUsrid());
		
		request.setAttribute("treimList",treimList);
		
		return "adminTreim/examineTreim";
	}
	
	@RequestMapping(value="/examineTreimSuccessful",method=RequestMethod.POST)
	public String examineTreimSuccessful(
		@RequestParam(value="rmid",required=true) String rmid,
			@RequestParam(value="rmdesc",required=true) String rmdesc){
		
		 treimServiceImpl.updtExamineTreimSuccessful(rmid,rmdesc);
		return "success";
	}
	
	
}
