package com.kang.sysRoles.service;

import java.util.List;

import com.kang.base.service.IbaseService;
import com.kang.model.SysRoles;

public interface IsysRolesService extends IbaseService<SysRoles, Integer> {

	List<SysRoles> findAllRoles();

	List<SysRoles> findMyRole(Integer usrid);

}
