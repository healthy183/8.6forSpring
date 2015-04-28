package com.kang.sysUsers.service;

import java.util.List;

import com.kang.base.service.IbaseService;
import com.kang.model.SysRoles;
import com.kang.model.SysUsers;
import com.kang.model.vo.SysUsersVo;
import com.kang.tool.Page;

public interface IsysUsersService extends IbaseService<SysUsers,Integer>{

	List<SysUsers> findAllLead();

	void saveUserAndRole(SysUsers newUser, SysRoles newRole,
			String[] roleIdCheck,String leadId);

	List<SysUsersVo> findAllButMe(Integer usrid);

	void delUsr(String usrid);

	List<SysUsersVo> findMyUsr(Integer usrid);

	void updtUsrSuccess(String usrid, String usrname, String leadId);

	List<SysUsersVo> findMyUser(Integer usrid);

	Page findAllUsr(int pageNo, int pageSize);

	Page findMyUsrForPage(Integer usrid, Integer pageNo, int pageSize);




}
