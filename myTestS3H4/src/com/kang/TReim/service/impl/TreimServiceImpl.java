package com.kang.TReim.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.kang.TReim.dao.ItreimDao;
import com.kang.TReim.service.ItreimService;
import com.kang.base.dao.IbaseDao;
import com.kang.base.service.ext.BaseHibernateService;
import com.kang.model.SysUsers;
import com.kang.model.TReim;
import com.kang.model.TReimitm;
import com.kang.model.vo.SysUsersVo;
import com.kang.sysUsers.dao.IsysUsersDao;
import com.kang.tool.Page;

@Scope("prototype")
@Service("treimServiceImpl")
public class TreimServiceImpl 
		extends BaseHibernateService<TReim,Integer> 
			implements ItreimService {
	
	@Autowired
	@Qualifier("sysUsersDaoImpl")
	private IsysUsersDao sysUsersDaoImpl;
	
	private ItreimDao treimDaoImpl;
	
	@Override
	@Autowired
	@Qualifier("treimDaoImpl")
	public void setBaseDao(IbaseDao<TReim, Integer> baseDao) {
		this.baseDao = baseDao;
		this.treimDaoImpl = (ItreimDao)baseDao;
	}

	@Override
	public void saveTReim(SysUsersVo sysusers, String rmname,
			String[] rmitmname, String[] rmitmcost) {
		
		SysUsers user = sysUsersDaoImpl.load(sysusers.getUsrid());
		
		TReim treim  = new TReim();
		treim.setSysUsers(user);
		treim.setRmname(rmname);
		treim.setRmdate(new Date());
		treim.setRmdesc("0");
		
		for(int i = 0;i<rmitmname.length;i++){
			
			TReimitm treimitm = new TReimitm();
			treimitm.setRmitmname(rmitmname[i]);
			treimitm.setRmitmcost(Double.valueOf(rmitmcost[i]));
			treimitm.setTReim(treim);
			treim.getTReimitms().add(treimitm);
		}
		
		treimDaoImpl.save(treim);
		for(TReimitm t : treim.getTReimitms()){
			treimDaoImpl.save(t);
		}
	}

	@Override
	public List<TReim> findMyTreim(Integer usrid) {
		//查询待审批的申请单 状态0(这里不分啦,没心情,dao直接order by rmdesc)
		return treimDaoImpl.findMyTreim(usrid,"0");
	}

	@Override //查询需要自己审批的申请单
	public List<TReim> findTreimTome(Integer usrid) {
		return treimDaoImpl.findTreimTome(usrid);
	}

	@Override
	public void updtExamineTreimSuccessful(String rmid, String rmdesc) {
		TReim treim = treimDaoImpl.get(Integer.valueOf(rmid));
		treim.setRmdesc(rmdesc);
		treimDaoImpl.merge(treim);
	}

}
