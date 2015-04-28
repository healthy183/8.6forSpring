package com.kang.sysRoles.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.kang.base.dao.IbaseDao;
import com.kang.base.dao.ext.BaseHibernateDao;
import com.kang.base.service.ext.BaseHibernateService;
import com.kang.model.SysRoles;
import com.kang.model.SysUsers;
import com.kang.sysRoles.dao.IsysRolesDao;

@Scope("prototype")
@Repository("sysRolesDaoImpl")
public class SysRolesDaoImpl 
	extends BaseHibernateDao<SysRoles, Integer>
		implements IsysRolesDao {
	
	@Override //根据roleId集合查找所有role
	public List<SysRoles> findRolesByRoleIdList(List<String> roleIdList) {
		String hql ="from SysRoles as s where s.roleId in (:roleIdList)";
		/*return getSession().createQuery(hql)
			.setParameterList("roleIdList",roleIdList).list();*/
		Map<String, Collection<?>> roleIdMap = new HashMap<String,Collection<?>>();
		
		List<Integer> intList = new ArrayList<Integer>();
		for(String str : roleIdList){
			intList.add(Integer.valueOf(str));
		}
		
		roleIdMap.put("roleIdList",intList);
		return super.queryHqlWithIn(hql, roleIdMap);
	}

	@Override  //查询所有用户 除自己
	public List<SysUsers> findAllButMe(Integer usrid) {
		String hql = "from SysUsers as s where s.usrid != ?";
		return super.queryHql(hql, usrid);
	}

	@Override
	public List<SysRoles> findMyRole(Integer usrid) {
		String hql ="select distinct k.sysRoles from UsrRoleLink as k where k.sysUsers.usrid = ?";
		return queryHql(hql,usrid);
	}

	
	
	

	
	
}
