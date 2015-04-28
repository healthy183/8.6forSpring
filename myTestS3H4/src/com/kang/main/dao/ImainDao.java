package com.kang.main.dao;

import com.kang.base.dao.IbaseDao;
import com.kang.model.SysUsers;

public interface ImainDao extends IbaseDao<SysUsers, Integer> {

	SysUsers lgn(String usrNameVal, String usrPwdVal);

	
	
}
