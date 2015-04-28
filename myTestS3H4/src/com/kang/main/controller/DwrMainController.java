package com.kang.main.controller;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.kang.main.service.IdwrService;
import com.kang.sysUsers.service.IsysUsersService;

@RemoteProxy(name="dwrMainController")
public class DwrMainController {

	
	@Autowired
	@Qualifier("dwrServiceImpl")
	private IdwrService dwrServiceImpl;
	
	@Autowired
	@Qualifier("sysUsersServiceImpl")
	private IsysUsersService sysUsersServiceImpl;/**/
	@RemoteMethod
	public String updtUsrSuccess(String usridVar,String usrnameVar,String leadIdVar){
		
		System.out.println(usrnameVar);
		
		sysUsersServiceImpl.updtUsrSuccess(usridVar,usrnameVar,leadIdVar);
		
		return "successful";
	}
	
	
	@RemoteMethod
	public String lgn(String usrNameVal,String usrPwdVal){
		
		System.out.println("ss");
		
		
		return dwrServiceImpl.lgn(usrNameVal,usrPwdVal);
	}
	
	
	
	
	@RemoteMethod
	public String test(){
		return "test!";
	}
	
}
