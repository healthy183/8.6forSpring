package com.kang.sysUsers.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.kang.base.dao.ext.BaseHibernateDao;
import com.kang.model.SysRoles;
import com.kang.model.SysUsers;
import com.kang.model.UsrRoleLink;
import com.kang.model.vo.SysUsersVo;
import com.kang.sysUsers.dao.IsysUsersDao;
import com.kang.tool.Page;

@Scope("prototype")
@Repository("sysUsersDaoImpl")
public class SysUsersDaoImpl extends BaseHibernateDao<SysUsers, Integer>
		implements IsysUsersDao {

	@Override //查询所有领导
	public List<SysUsers> findAllLead() {
		String hql ="from SysUsers as s where s.usrtype = 0 order by s.usrid";
		return queryHql(hql);
	}

	@Override //保存新增用户,角色,中间表
	public void saveNewUsrAndRole(SysUsers newUser, SysRoles newRole,
			List<UsrRoleLink> usrRoleLinkList) {
		
		save(newUser);
		
		if(newRole.getRoleName() != null){
		save(newRole);
		}
		
		for(UsrRoleLink UsrRoleLink : usrRoleLinkList){
			save(UsrRoleLink);
		}
	}
 
	@Override //查询所有下属
	public List<SysUsers> findMyUsr(Integer usrid) {
		String hql ="from SysUsers as s where s.sysUsers.usrid = ?";
		return queryHql(hql, usrid);
	}

	@Override //分页查询对象
	public Page findAllUsr(int pageNo,int pageSize) {
		String hql = "from SysUsers";
		
		Page page =	pageQuery(hql,pageNo,pageSize);
		
		List<SysUsersVo> usrVoList = new ArrayList<SysUsersVo>();
		
		List<SysUsers> usrList = page.getResult();
		
		if(usrList != null){
			for(SysUsers usr : usrList){
				usrVoList.add(SysUsersVo.parse(usr));
			}
			page.setData(usrVoList);
		}
		
		return page;
	}

	@Override
	public Page findMyUsrForPage(Integer usrid, Integer pageNo, int pageSize) {
		String hql ="from SysUsers as s where s.sysUsers.usrid = ?";
		
		Page page =	pageQuery(hql, pageNo, pageSize, usrid);
		List<SysUsers> userList = page.getResult();
		
		List<SysUsersVo> userVoList = new ArrayList<SysUsersVo>();
		
		for(SysUsers user : userList){
			userVoList.add(SysUsersVo.parse(user));
		}
		
		page.setData(userVoList);
		
		return page;
	}

	
	
	
	
}
