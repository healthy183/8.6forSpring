package com.kang.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kang.base.dao.IbaseDao;
import com.kang.base.service.ext.BaseHibernateService;
import com.kang.main.dao.ImainDao;
import com.kang.main.service.IdwrService;
import com.kang.model.SysUsers;
import com.kang.tool.Page;

@Service("dwrServiceImpl")
public class DwrServiceImpl extends BaseHibernateService<SysUsers, Integer>  
				implements IdwrService {

	private ImainDao mainDaoImpl;
	
	@Override
	@Autowired
	@Qualifier("mainDaoImpl")
	public void setBaseDao(IbaseDao<SysUsers, Integer> mainDaoImpl) {
		this.baseDao = mainDaoImpl;
		this.mainDaoImpl = (ImainDao) mainDaoImpl;
	}

	@Override
	public String lgn(String usrNameVal, String usrPwdVal) {
		SysUsers usr = mainDaoImpl.lgn(usrNameVal,usrPwdVal);
		//String s = "";
		//s.trim();
		if(usr != null){
			return "ok";
		}
		return "no";
	}
	
	
}
