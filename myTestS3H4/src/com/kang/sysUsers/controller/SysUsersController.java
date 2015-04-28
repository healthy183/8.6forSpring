package com.kang.sysUsers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.kang.sysUsers.service.IsysUsersService;

@Scope("prototype")
@Controller
public class SysUsersController {

 @Autowired
 @Qualifier("sysUsersServiceImpl")
 private IsysUsersService sysUsersServiceImpl;
	
}
