package com.kang.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.kang.base.dao.IbaseDao;
import com.kang.base.service.ext.BaseHibernateService;
import com.kang.main.dao.ImainDao;
import com.kang.main.service.ImainService;
import com.kang.model.SysUsers;
import com.kang.tool.Page;

@Scope("prototype")
@Service("mainServiceImpl")
public class MainServiceImpl  extends BaseHibernateService<SysUsers, Integer>
	implements ImainService {
	
	private ImainDao mainDaoImpl;
	
	@Override
	@Autowired
	@Qualifier("mainDaoImpl")
	public void setBaseDao(IbaseDao<SysUsers, Integer> baseDao) {
		this.baseDao  =  baseDao;
		this.mainDaoImpl = (ImainDao)baseDao;
	}

	@Override
	public SysUsers lgn(SysUsers user) {
		return  mainDaoImpl.lgn(user.getUsrname(),user.getUsrpwd());
	}
	
}
