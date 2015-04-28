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

	      //��ѯ����Ƭ��
	public List<OrgstdStruct> findAreaByLabelLength(int i) {
		return orgstdStructDao.findAreaByLabelLength(i);
	}

	     //����Ƭ��unitCode ���������ŵ�
	public List<String> findStoreByFilmAreaUnitCode(String unitcode, int i) {
		return orgstdStructDao.findStoreByFilmAreaUnitCode(unitcode,i);
	}

	     //���������ŵ��unitId �ӱ�StoreCount��ѯ����ǰƬ���������ŵ�ʵ�ʺ�Ԥ�����۽��
	public List<StoreCount> findstoreCountByunitIdList(
			List<String> unitIdStoreList,String operatingMonth) {
		return correspondingDAO.findstoreCountByunitIdList(unitIdStoreList,operatingMonth);
	}

	/*      //���������ŵ��unitId�Ӷ�Ӧ���в�ѯ��braId
	public List<String> findBraIdBynitIdList(List<String> unitIdStoreList) {
		return correspondingDAO.findBraIdBynitIdList(unitIdStoreList);
	}*/
	
	
	
	
	
}
