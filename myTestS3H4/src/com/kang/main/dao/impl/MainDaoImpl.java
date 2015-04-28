package com.kang.main.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kang.base.dao.ext.BaseHibernateDao;
import com.kang.main.dao.ImainDao;
import com.kang.model.SysUsers;
import com.kang.tool.Page;

@Repository("mainDaoImpl")
public class MainDaoImpl extends BaseHibernateDao<SysUsers, Integer> 
		implements ImainDao {
	
	
	@Override //执行前请确定userName和userPwd是唯一的,数据库1最好加唯一约束
	public SysUsers lgn(String usrNameVal, String usrPwdVal) {
		String hql ="from SysUsers as s where s.usrname = ? and s.usrpwd = ?";
		//List<SysUsers> userList = getSession().createQuery(hql)
		//		.setString("usrNameVal",usrNameVal).setString("usrPwdVal",usrPwdVal).list();
		List<SysUsers> userList = queryHql(hql,usrNameVal,usrPwdVal);
		if(userList.size() > 0){
			return userList.get(0);
		}
		return null;
	}
	
	
}
