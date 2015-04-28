package com.kang.sysRoles.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.kang.base.dao.IbaseDao;
import com.kang.base.service.ext.BaseHibernateService;
import com.kang.model.SysRoles;
import com.kang.model.SysUsers;
import com.kang.sysRoles.dao.IsysRolesDao;
import com.kang.sysRoles.service.IsysRolesService;


@Scope("prototype")
@Service("sysRolesServiceImpl")
public class SysRolesServiceImpl
	extends BaseHibernateService<SysRoles, Integer> 
		implements IsysRolesService{

	
	private IsysRolesDao sysRolesDaoImpl;
	
	@Override
	@Autowired
	@Qualifier("sysRolesDaoImpl")
	public void setBaseDao(IbaseDao<SysRoles, Integer> baseDao) {
		this.baseDao =  baseDao;
		this.sysRolesDaoImpl = (IsysRolesDao) baseDao;
	}

	@Override
	public List<SysRoles> findAllRoles() {
		return findAll();
	}

	@Override //查询自己的角色
	public List<SysRoles> findMyRole(Integer usrid) {
		return sysRolesDaoImpl.findMyRole(usrid);
	}

	

}
