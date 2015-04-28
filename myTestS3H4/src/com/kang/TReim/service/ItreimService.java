package com.kang.TReim.service;

import java.util.List;

import com.kang.base.service.IbaseService;
import com.kang.model.TReim;
import com.kang.model.vo.SysUsersVo;

public interface ItreimService 
	extends IbaseService<TReim,Integer> {

	void saveTReim(SysUsersVo sysusers, String rmname, String[] rmitmname,
			String[] rmitmcost);

	List<TReim> findMyTreim(Integer usrid);

	List<TReim> findTreimTome(Integer usrid);

	void updtExamineTreimSuccessful(String rmid, String rmdesc);


}
