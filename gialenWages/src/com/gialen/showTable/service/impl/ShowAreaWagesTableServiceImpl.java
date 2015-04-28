package com.gialen.showTable.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.gialen.dao.LastCountTableDAO;
import com.gialen.dao.SaleDailyProductPeopleSumDAO;
import com.gialen.dao.StoreCountDAO;
import com.gialen.model.LastCountTable;
import com.gialen.model.OrgstdStruct;
import com.gialen.model.StoreCount;
import com.gialen.model.vo.SaleDailyProductPeopleSumVo;
import com.gialen.model.vo.StoreCountVo;
import com.gialen.showTable.service.ShowAreaWagesTableService;

public class ShowAreaWagesTableServiceImpl implements ShowAreaWagesTableService {
	
	private StoreCountDAO storeCountDAO;
	private SaleDailyProductPeopleSumDAO saleDailyProductPeopleSumDAO;
	private LastCountTableDAO lastCountTableDAO;

	
	

	public SaleDailyProductPeopleSumDAO getSaleDailyProductPeopleSumDAO() {
		return saleDailyProductPeopleSumDAO;
	}

	public void setSaleDailyProductPeopleSumDAO(
			SaleDailyProductPeopleSumDAO saleDailyProductPeopleSumDAO) {
		this.saleDailyProductPeopleSumDAO = saleDailyProductPeopleSumDAO;
	}

	public LastCountTableDAO getLastCountTableDAO() {
		return lastCountTableDAO;
	}

	public void setLastCountTableDAO(LastCountTableDAO lastCountTableDAO) {
		this.lastCountTableDAO = lastCountTableDAO;
	}

	public StoreCountDAO getStoreCountDAO() {
		return storeCountDAO;
	}

	public void setStoreCountDAO(StoreCountDAO storeCountDAO) {
		this.storeCountDAO = storeCountDAO;
	}

	//��ѯ�ϸ�Ӫ���µ�Ƭ�� �������StoreCount
	public List<StoreCount> findThisMonthAreaWages
		(String operatingMonthId, int i) {
		
		return storeCountDAO.findThisMonthAreaWages(operatingMonthId,i);
		/*List<StoreCount> storeCountList = storeCountDAO.findThisMonthAreaWages(operatingMonthId,i);
		return parseVoList(storeCountList);*/
	}
	
	//��ʽ��vo
	public List<StoreCountVo> parseVoList(List<StoreCount> storeCountList){
		
		List<StoreCountVo> voList = new ArrayList<StoreCountVo>();
		for(StoreCount storeCount :storeCountList){
			voList.add(StoreCountVo.parse(storeCount));
		}
		return voList;
	}
	
	
	public List<StoreCountVo> findThisMonthAreaWagesvo(String operatingMonthId,int i) {
		List<StoreCount> storeCountList = storeCountDAO.findThisMonthAreaWages(operatingMonthId,i);
		return parseVoList(storeCountList);/**/
	}

	

	public List<SaleDailyProductPeopleSumVo> showUsrWages(
			String operatingMonthId, List<String> braIdList) {
		return saleDailyProductPeopleSumDAO.showUsrWagesNew
				(operatingMonthId,braIdList);
	}

	
	
	public List<StoreCountVo> showStoreWages(String operatingMonthId) {	
		
		List<StoreCountVo> voList = new ArrayList<StoreCountVo>();
		//��ѯ��ǰӪ�����ŵ�����������
		List<StoreCount> storeCountList = 
			storeCountDAO.findThisStoreStoreCount(operatingMonthId);
	
	/*if(storeCountList.size() == 0){
		saleDailyProductPeopleSumDAO.showUsrWages(operatingMonthId);
	}
	//��ѯ��ǰӪ�����ŵ�����������
	storeCountList = 
		storeCountDAO.findThisStoreStoreCount(operatingMonthId);*/
	String unitid = null;
		
	for(StoreCount vo : storeCountList){
		
		List<Double> sumList = new ArrayList<Double>();
		sumList.add(0.0);
		sumList.add(0.0);
		sumList.add(0.0);
		
		//String braId = vo.getBranch().getBraId();
		
		OrgstdStruct hrStore = vo.getOrgstdStruct();
		
		if(hrStore != null){
			/*
			 * List<Double> sumList = saleDailyProductPeopleSumDAO
					.getAllAndPubSum(operatingMonthId,braId);
			  */
			unitid = hrStore.getUnitid();
			//��ѯԱ�� �͹����˺ŵ��ܽ���
			sumList = saleDailyProductPeopleSumDAO
					.getAllAndPubSumNew(operatingMonthId,unitid);
		}
		
		//��ʽ��vo
		StoreCountVo storeCountVo =	StoreCountVo.parseByShowStoreWages(vo,sumList);
		voList.add(storeCountVo);
		
		
	}
	
	return voList;
	
	}

	public List<SaleDailyProductPeopleSumVo> showUsrWages(
			String operatingMonthId) {
		
		/*List<LastCountTable> lastCountTableList = null;
				
		lastCountTableList  = lastCountTableDAO.showUsrWages(operatingMonthId);*/
		
		List<SaleDailyProductPeopleSumVo>  saleSumVo =null;
		saleSumVo = saleDailyProductPeopleSumDAO.showUsrWages(operatingMonthId);
		
		if(saleSumVo.size() == 0){
			 saleDailyProductPeopleSumDAO.showUsrWagesNews(operatingMonthId);
		}else{
			return saleSumVo;
		}
		
		return  saleDailyProductPeopleSumDAO.showUsrWages(operatingMonthId);
		
	}

	public SaleDailyProductPeopleSumVo showUsrWagesCount(String operatingMonthId) {
		return saleDailyProductPeopleSumDAO.showUsrWagesCount(operatingMonthId);
	}
	
	/**/
	
	public List<StoreCountVo> showStoreWages(String operatingMonthId,
			List<String> strList) {

		List<StoreCountVo> voList = new ArrayList<StoreCountVo>();

		// ��ѯ��ǰӪ�����ŵ�����������
		List<StoreCount> storeCountList = storeCountDAO
				.findThisStoreStoreCount(operatingMonthId, strList);

		for (StoreCount s : storeCountList) {

			String braId = s.getBranch().getBraId();
			// ��ѯԱ�� �͹����˺ŵ��ܽ���
			List<Double> sumList = saleDailyProductPeopleSumDAO
					.getAllAndPubSumNew(operatingMonthId, braId);

			// ��ʽ��vo
			StoreCountVo storeCountVo = StoreCountVo.parseByShowStoreWages(s,
					sumList);
			voList.add(storeCountVo);
		}

		return voList;

	}
	
	//���� �ϸ�������Ա���Ľ���
	public List<SaleDailyProductPeopleSumVo> collectUsrWages(
			String operatingMonthId) {
		
		List<SaleDailyProductPeopleSumVo> voList 
			= saleDailyProductPeopleSumDAO.selectcollectUsrWages(operatingMonthId);
		
		if(voList.size() > 0){
			return voList;
		}else{
			//ִ�в���
			saleDailyProductPeopleSumDAO.collectUsrWages(operatingMonthId);
			//�ٲ�ѯһ��
			voList = saleDailyProductPeopleSumDAO.selectcollectUsrWages(operatingMonthId);
		}
		return  voList;
	}

	
	

}
