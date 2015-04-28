package com.kang.TReim.dao.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.kang.TReim.dao.ItreimDao;
import com.kang.base.dao.ext.BaseHibernateDao;
import com.kang.model.TReim;
import com.kang.tool.Page;

@Scope("prototype")
@Repository("treimDaoImpl")
public class TreimDaoImpl
	extends BaseHibernateDao<TReim, Integer> implements ItreimDao {

	@Override
	public List<TReim> findMyTreim(Integer usrid, String rmdesc) {
		String hql ="from TReim as t where t.sysUsers.usrid = ?  order by t.rmdesc";
		return queryHql(hql,usrid);
	}

	@Override
	public List<TReim> findTreimTome(Integer usrid) {
		String hql ="from TReim as t where t.sysUsers.sysUsers.usrid = ? and " +
			"t.rmdesc = '0' order by t.sysUsers.usrid";
		return queryHql(hql,usrid);
	}
	
	
	
	
	
	
}
