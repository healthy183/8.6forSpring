package com.test;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

@Controller
public class UsrAct {
	
	@Resource
	private UsrBiz usrBiz;
	
	
	public void find(){
		usrBiz.find();
	}

	public UsrBiz getUsrBiz() {
		return usrBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}
	
	

}
