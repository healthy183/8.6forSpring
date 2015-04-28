package com.kang.main.service;

import java.util.List;

import com.kang.base.service.IbaseService;
import com.kang.model.SysUsers;
import com.kang.tool.Page;

public interface IdwrService extends IbaseService<SysUsers,Integer> {

	String lgn(String usrNameVal, String usrPwdVal);
	
	
	
}
