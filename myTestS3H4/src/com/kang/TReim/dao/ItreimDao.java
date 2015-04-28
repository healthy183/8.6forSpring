package com.kang.TReim.dao;

import java.util.List;

import com.kang.base.dao.IbaseDao;
import com.kang.model.TReim;

public interface ItreimDao extends IbaseDao<TReim,Integer> {

	List<TReim> findMyTreim(Integer usrid, String string);

	List<TReim> findTreimTome(Integer usrid);


	
	
	
}
