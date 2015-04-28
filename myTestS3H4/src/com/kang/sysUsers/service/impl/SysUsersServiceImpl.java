package com.kang.sysUsers.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;



import com.kang.base.dao.IbaseDao;
import com.kang.base.service.ext.BaseHibernateService;
import com.kang.model.SysRoles;
import com.kang.model.SysUsers;
import com.kang.model.UsrRoleLink;
import com.kang.model.vo.SysUsersVo;
import com.kang.sysRoles.dao.IsysRolesDao;
import com.kang.sysUsers.dao.IsysUsersDao;
import com.kang.sysUsers.service.IsysUsersService;
import com.kang.tool.Page;

@Scope("prototype")
@Service("sysUsersServiceImpl")
public  class SysUsersServiceImpl extends BaseHibernateService<SysUsers, Integer> 
	implements IsysUsersService {
	
	@Autowired
	@Qualifier("sysRolesDaoImpl")
	private IsysRolesDao sysRolesDaoImpl;
	
	private IsysUsersDao sysUsersDaoImpl;
	@Autowired
    @Qualifier("sysUsersDaoImpl")
	@Override //重写通用父接口的setBaseDao()方法 
	public void setBaseDao(IbaseDao<SysUsers, Integer> baseDao) {
		 this.baseDao = baseDao; //将@UserDao的类注入到abstract BaseService(抽象通用service)的baseDao
	     this.sysUsersDaoImpl = (IsysUsersDao) baseDao; //将@UserDao的类注入到全局变量userDao
	}

	@Override
	public List<SysUsers> findAllLead() {
		return sysUsersDaoImpl.findAllLead();
	}

	@Override  //保存新增用户,角色,中间表
	public void saveUserAndRole(SysUsers newUser, SysRoles newRole,
			String[] roleIdCheck,String leadId) {
		
		if(leadId != null  && !leadId.equals("") ){//设置领导
			SysUsers leader = sysUsersDaoImpl.get(Integer.valueOf(leadId));
			newUser.setSysUsers(leader);
		}
		
		List<UsrRoleLink> usrRoleLinkList  = new ArrayList<UsrRoleLink>();
		
		//新增用户角色中间表
		if(newRole.getRoleName() != null){
			
			UsrRoleLink usrRoleLink = new UsrRoleLink();
			usrRoleLink.setSysUsers(newUser);//设置一对多
			newUser.getUsrRoleLinks().add(usrRoleLink);
			
			usrRoleLink.setSysRoles(newRole);//设置一对多
			newRole.getUsrRoleLinks().add(usrRoleLink);
			usrRoleLinkList.add(usrRoleLink);
		}
		
		
		
		if(roleIdCheck != null && roleIdCheck.length > 0){ //如果有勾上的角色
			List<Integer> roleIntgerList = new ArrayList<Integer>();
			List<String> roleIdList = Arrays.asList(roleIdCheck);
			//如何将String转换成List<Integer>???
			for(String s :roleIdList){
				Integer roleId = Integer.valueOf(s);
				roleIntgerList.add(roleId);
			}
			//根据角色id查找角色
			List<SysRoles> rolesList = findRolesByRoleIdList(roleIdList);
			if(rolesList.size()>0){
				for(SysRoles role : rolesList){
					
					UsrRoleLink usrRoleLinkInfor = new UsrRoleLink();
					
					usrRoleLinkInfor.setSysUsers(newUser);
					newUser.getUsrRoleLinks().add(usrRoleLinkInfor);
					
					usrRoleLinkInfor.setSysRoles(role);
					//role.getUsrRoleLinks().add(usrRoleLinkInfor);
					
					usrRoleLinkList.add(usrRoleLinkInfor);
				}
			}
		}
		//保存新增用户,角色,中间表
		saveNewUsrAndRole(newUser,newRole,usrRoleLinkList);
	}

	private void saveNewUsrAndRole(SysUsers newUser, SysRoles newRole,
			List<UsrRoleLink> usrRoleLinkList) {
		sysUsersDaoImpl.saveNewUsrAndRole(newUser,newRole,usrRoleLinkList);
	}

	private List<SysRoles> findRolesByRoleIdList(List<String> roleIdList) {
		return	sysRolesDaoImpl.findRolesByRoleIdList(roleIdList);
	}

	
	@Override //查询所有用户 除自己
	public List<SysUsersVo> findAllButMe(Integer usrid) {
		
		List<SysUsers> usrList = sysRolesDaoImpl.findAllButMe(usrid);
		
		List<SysUsersVo> voList = new ArrayList<SysUsersVo>();
		
		for(SysUsers usr : usrList){
			voList.add(SysUsersVo.parse(usr));
		}
		
		return voList;
	}

	@Override //删除用户
	public void delUsr(String usrid) {
		delete(Integer.valueOf(usrid));
	}

	@Override //查询所有下属
	public List<SysUsersVo> findMyUsr(Integer usrid) {
		
		List<SysUsers> usrList = sysUsersDaoImpl.findMyUsr(usrid);
		
		 List<SysUsersVo> voList = new ArrayList<SysUsersVo>();
		for(SysUsers usr : usrList){
			voList.add(SysUsersVo.parse(usr));
		}
		return voList;
	}

	@Override
	public void updtUsrSuccess(String usrid, String usrname, String leadId) {
		
		SysUsers leader = null;
		SysUsers user = user = sysUsersDaoImpl.load(Integer.valueOf(usrid));
		
		if(!leadId.equals("0")){
			leader = sysUsersDaoImpl.load(Integer.valueOf(leadId));
		}
		
		user.setSysUsers(leader);
		user.setUsrname(usrname);
		sysUsersDaoImpl.merge(user);
		
	}

	@Override //查询自己所有用户
	public List<SysUsersVo> findMyUser(Integer usrid) {
		
		List<SysUsers> usrList = sysUsersDaoImpl.findMyUsr(usrid);
		
		 List<SysUsersVo> voList = new ArrayList<SysUsersVo>();
		for(SysUsers usr : usrList){
			voList.add(SysUsersVo.parse(usr));
		}
		return voList;
	}

	@Override	//查询用户
	public Page findAllUsr(int pageNo, int pageSize) {
		return sysUsersDaoImpl.findAllUsr(pageNo,pageSize);
	}

	@Override
	public Page findMyUsrForPage(Integer usrid, Integer pageNo, int pageSize) {
		return sysUsersDaoImpl.findMyUsrForPage(usrid,pageNo,pageSize);
	}
	
	
	
	
}
