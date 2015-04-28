package com.gialen.synchronizationStores.service.impl;

import java.util.List;

import com.gialen.dao.CorrespondingDAO;
import com.gialen.dao.OrgstdStructDAO;
import com.gialen.model.Branch;
import com.gialen.model.Corresponding;
import com.gialen.model.OrgstdStruct;
import com.gialen.model.StoreCount;
import com.gialen.synchronizationStores.service.SynchronizationStoresSerivce;

public class SynchronizationStoresSerivceImpl implements
		SynchronizationStoresSerivce {
	
	private CorrespondingDAO correspondingDAO;
	private OrgstdStructDAO orgstdStructDao;
	
	public OrgstdStructDAO getOrgstdStructDao() {
		return orgstdStructDao;
	}

	public void setOrgstdStructDao(OrgstdStructDAO orgstdStructDao) {
		this.orgstdStructDao = orgstdStructDao;
	}

	public CorrespondingDAO getCorrespondingDAO() {
		return correspondingDAO;
	}

	public void setCorrespondingDAO(CorrespondingDAO correspondingDAO) {
		this.correspondingDAO = correspondingDAO;
	}

	    
	public List<Corresponding> findAllCorresponding() {
		return correspondingDAO.findAll();
	}

	    
	public void saveOrUpdtCorresponding(List<Corresponding> correspondingList) {
		correspondingDAO.mergeCorresponding(correspondingList);
	}

	      //查询所有片区
	public List<OrgstdStruct> findAreaByLabelLength(int i) {
		return orgstdStructDao.findAreaByLabelLength(i);
	}

	     //根据片区unitCode 查找所有门店
	public List<String> findStoreByFilmAreaUnitCode(String unitcode, int i) {
		return orgstdStructDao.findStoreByFilmAreaUnitCode(unitcode,i);
	}

	     //根据所有门店的unitId 从表StoreCount查询出当前片区下所有门店实际和预计销售金额
	public List<StoreCount> findstoreCountByunitIdList(
			List<String> unitIdStoreList,String operatingMonth) {
		return correspondingDAO.findstoreCountByunitIdList(unitIdStoreList,operatingMonth);
	}

	/*      //根据所有门店的unitId从对应表中查询的braId
	public List<String> findBraIdBynitIdList(List<String> unitIdStoreList) {
		return correspondingDAO.findBraIdBynitIdList(unitIdStoreList);
	}*/
	
	
	
	
	
}
