package com.kang.sysRoles.dao;

import java.util.List;

import com.kang.base.dao.IbaseDao;
import com.kang.model.SysRoles;
import com.kang.model.SysUsers;

public interface IsysRolesDao extends IbaseDao<SysRoles,Integer>{

	List<SysRoles> findRolesByRoleIdList(List<String> roleIdList);

	List<SysUsers> findAllButMe(Integer usrid);

	List<SysRoles> findMyRole(Integer usrid);

}
