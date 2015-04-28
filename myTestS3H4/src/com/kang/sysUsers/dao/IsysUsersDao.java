package com.kang.sysUsers.dao;

import java.util.List;

import com.kang.base.dao.IbaseDao;
import com.kang.model.SysRoles;
import com.kang.model.SysUsers;
import com.kang.model.UsrRoleLink;
import com.kang.tool.Page;

public interface IsysUsersDao extends IbaseDao<SysUsers, Integer> {

	List<SysUsers> findAllLead();

	void saveNewUsrAndRole(SysUsers newUser, SysRoles newRole,
			List<UsrRoleLink> usrRoleLinkList);

	List<SysUsers> findMyUsr(Integer usrid);

	Page findAllUsr(int pageNo, int pageSize);

	Page findMyUsrForPage(Integer usrid, Integer pageNo, int pageSize);


}
