package com.gialen.accountingArea.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.gialen.accountingArea.service.AccountingAreaService;
import com.gialen.dao.BranchDAO;
import com.gialen.dao.CorrespondingDAO;
import com.gialen.dao.EmployeeDAO;
import com.gialen.dao.GrundbonusDAO;
import com.gialen.dao.OperatingMonthDAO;
import com.gialen.dao.OrgstdStructDAO;
import com.gialen.dao.PlanMoneyDAO;
import com.gialen.dao.SaleDailyYymmDAO;
import com.gialen.dao.StoreCountDAO;
import com.gialen.model.Branch;
import com.gialen.model.Corresponding;
import com.gialen.model.Employee;
import com.gialen.model.OperatingMonth;
import com.gialen.model.OrgstdStruct;
import com.gialen.model.PlanMoney;
import com.gialen.model.Psnaccount;
import com.gialen.model.SaleDailyYymm;
import com.gialen.model.StoreCount;
import com.gialen.tools.Arith;

public class AccountingAreaServiceImpl implements AccountingAreaService {

	private StoreCountDAO storeCountDAO;
	private BranchDAO branchDAO;
	private SaleDailyYymmDAO saleDailyYymmDAO;
	private PlanMoneyDAO planMoneyDao;
	private GrundbonusDAO grundbonusDao;
	private CorrespondingDAO correspondingDAO;
	private OrgstdStructDAO orgstdStructDao;
	private OperatingMonthDAO operatingMonthDAO;
	private EmployeeDAO employeeDAO; 
	
	
	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	public OperatingMonthDAO getOperatingMonthDAO() {
		return operatingMonthDAO;
	}

	public void setOperatingMonthDAO(OperatingMonthDAO operatingMonthDAO) {
		this.operatingMonthDAO = operatingMonthDAO;
	}

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

	public GrundbonusDAO getGrundbonusDao() {
		return grundbonusDao;
	}

	public void setGrundbonusDao(GrundbonusDAO grundbonusDao) {
		this.grundbonusDao = grundbonusDao;
	}

	public PlanMoneyDAO getPlanMoneyDao() {
		return planMoneyDao;
	}

	public void setPlanMoneyDao(PlanMoneyDAO planMoneyDao) {
		this.planMoneyDao = planMoneyDao;
	}

	public com.gialen.dao.StoreCountDAO getStoreCountDAO() {
		return storeCountDAO;
	}

	public void setStoreCountDAO(com.gialen.dao.StoreCountDAO storeCountDAO) {
		this.storeCountDAO = storeCountDAO;
	}

	public BranchDAO getBranchDAO() {
		return branchDAO;
	}

	public void setBranchDAO(BranchDAO branchDAO) {
		this.branchDAO = branchDAO;
	}

	public SaleDailyYymmDAO getSaleDailyYymmDAO() {
		return saleDailyYymmDAO;
	}

	public void setSaleDailyYymmDAO(SaleDailyYymmDAO saleDailyYymmDAO) {
		this.saleDailyYymmDAO = saleDailyYymmDAO;
	}

	    
	// ��ѯ��ǰӪ�����ŵ������������
	public List<StoreCount> findThisStoreStoreCount(String operatingMonthId) {
		return storeCountDAO.findThisStoreStoreCount(operatingMonthId);
	}

	    
	// ִ��ͳ�������ŵ������Ӫ���µ����������ܶ� allStoreTotalSales
	public List<StoreCount> saveAllStoreTotalSales(OperatingMonth thisOperatingMonth) {

		List<Branch> branchList = branchDAO.findAll();
		List<StoreCount> storeCountList = new ArrayList<StoreCount>();
		
		Double saleCount = 0.0;
		for (Branch branch : branchList) {
			//System.out.println("a");
			//�����ŵ�Ӫ�����ڼ������ܶ��
			StoreCount storeCount = new StoreCount();
			
			storeCount.setStoreCountType(0);//0�곤���
			
			//����Ӫ����һ�Զ�
			storeCount.setOperatingMonth(thisOperatingMonth);
			//thisOperatingMonth.getStoreCounts().add(storeCount);
			
			//�����ŵ��м��һ�Զ�/**/
			storeCount.setBranch(branch);
			//branch.getStoreCounts().add(storeCount);
	
			//��ѯ��ǰ�ŵ� ��ǰӪ�������ۼ�¼
			List<SaleDailyYymm> saleList = saleDailyYymmDAO.
					findthisMonththisBrandSaleList(thisOperatingMonth,branch);
			
			saleCount = 0.0; //��0 
			if(saleList.size()>0){
				 //������� ��ǰ�ŵ� ��ǰӪ���������ܽ�� 
				System.out.println("��ʼ����");
				for(SaleDailyYymm SaleDailyYymm : saleList){  
					saleCount =	Arith.add(saleCount, SaleDailyYymm.getSaleAmt());//�����۽��
					saleCount =	Arith.sub(saleCount, SaleDailyYymm.getPcashPayAmt());//��ȥ�ֽ�ȯ���
				}
			}
			//����ʵ�����۽��                         
			storeCount.setSaleCount(saleCount);
			
			//�����ŵ� Ĭ�� С�� 
			String storeType  = "0";
			//���ݼ�Ѷ�ŵ��ȡ�м������ ����һ�Զ�
			List<Corresponding> correspondingList =	correspondingDAO.findByBra(branch.getBraId());
			
			if(correspondingList.size() > 0){
				Corresponding corresponding = correspondingList.get(0);
				//corresponding.getStoreCounts().add(storeCount);
				storeCount.setCorresponding(corresponding);
				//hr�ŵ�һ�Զ�
				OrgstdStruct orgstdStruct = corresponding.getOrgstdStruct();
				//orgstdStruct.getStoreCounts().add(storeCount);
				storeCount.setOrgstdStruct(orgstdStruct);
			 //�����ŵ�	
			 storeType = corresponding.getStoreType();
			}
			
			String thisMonthId = thisOperatingMonth.getOperatingMonthId();
			//��ѯ��ǰ�ŵ� ��ǰӪ���¼ƻ����۽��
			List<PlanMoney> thisMounththisBrandPlanMoney =
				planMoneyDao.getThisMounththisBrandPlanMoney
					(thisMonthId,branch.getBraId());
			
			//����������ŵĻ�
			if(thisMounththisBrandPlanMoney.size()>0){
				
				PlanMoney planMoney = thisMounththisBrandPlanMoney.get(0);
				planMoney.getStoreCounts().add(storeCount);
				storeCount.setPlanMoney(planMoney);
				
				Double complatePercentVar = Arith.div
						(saleCount,planMoney.getPlanMoneyCount());
					
				storeCount.setComplatePercent(complatePercentVar);
				storeCount.setPercentStr(Arith.mul(Arith.round(complatePercentVar,3),100)+"%");
			
				//���ݵ�ǰӪ����,��ǰԤ�����۽��Լ���ɶ��ѯ����(�ŵ�1)
				Double grundbonusMoney = 
					grundbonusDao.findWagesByThisMonthPlanMoneyComplatePercent
						(thisMonthId,planMoney.getPlanMoneyCount(),complatePercentVar,1);

				//���ý���
				if(grundbonusMoney != null){ //һ�Ǽ��곤
					storeCount.setOneStarManagergrundbonusMoney(grundbonusMoney);
					storeCount.setPositiveManagergrundbonusMoney(Arith.mul(grundbonusMoney,0.8));//���곤
					storeCount.setDeputyManagergrundbonusMoney(Arith.mul(grundbonusMoney,0.6));//���곤
				}else{
					storeCount.setOneStarManagergrundbonusMoney(0.0);
				}
				
				//���ݵ�ǰӪ����,��ɶ��ѯ�ŵ�����(���� type��2)
				Double rewardPercent = 
						grundbonusDao.findWagesPercentByThisMonthPlanMoneyComplatePercent
							(thisMonthId,complatePercentVar,storeType,2);
				
				System.out.println(rewardPercent == null);
				//��������
				storeCount.setStoreAllWages(Arith.round(Arith.mul(rewardPercent,saleCount),2));
				
				
			}else{
				//û������ ��Ĭ�����Ϊ100%
				storeCount.setComplatePercent(1.0);
				storeCount.setPercentStr("100%");
				storeCount.setPlanMoneyCount(0.0);//�ƻ����0
				
				//Ȼ����ʵ�ʼƻ����۽��,100%ȥ��ѯ����
				//�ٸ��ݵ�ǰӪ����,��ǰԤ�����۽��Լ���ɶ��ѯ����(�ŵ�����Ϊ1)
				Double grundbonusMoney = 
					grundbonusDao.findWagesByThisMonthPlanMoneyComplatePercent
						(thisMonthId,saleCount,1.0,1);
				
				//���ý���
				if(grundbonusMoney != null){ //һ�Ǽ��곤
					storeCount.setOneStarManagergrundbonusMoney(grundbonusMoney);
					storeCount.setPositiveManagergrundbonusMoney(Arith.mul(grundbonusMoney,0.8));//���곤
					storeCount.setDeputyManagergrundbonusMoney(Arith.mul(grundbonusMoney,0.6));//���곤
				}else{
					storeCount.setOneStarManagergrundbonusMoney(0.0);
				}
				
				//Ȼ����ʵ�ʼƻ����۽��,100%ȥ��ѯ����
				//�ٸ��ݵ�ǰӪ����,��ǰԤ�����۽��Լ���ɶ��ѯ����(�ŵ�����Ϊ2)
				Double rewardPercent = 
						grundbonusDao.findWagesPercentByThisMonthPlanMoneyComplatePercent
							(thisMonthId,1.0,storeType,2);
				//��������
				storeCount.setStoreAllWages(Arith.round(Arith.mul(rewardPercent,saleCount),2));
			}
			
			storeCountList.add(storeCount);
			System.out.println("һ���ŵ�ִ����!");
		}

		//�������� storeCount
		storeCountDAO.saveOrupdateAll(storeCountList);
		
		return storeCountList;
	}

	      //��ѯ�ϸ�Ӫ���µ�Ƭ�����StoreCount
	public List<StoreCount> findThisMonthFilmAreaWages(String operatingMonthId,int i) {
		return storeCountDAO.findThisMonthFilmAreaWages(operatingMonthId,i);
	}

	    
	public List<StoreCount> saveStoreCount(String operatingMonthId, int i) {
		
		List<StoreCount> filmAreastoreCountList = new ArrayList<StoreCount>();
		
		//��ѯ����Ƭ��
		List<OrgstdStruct> filmAreaList = orgstdStructDao.findAreaByLabelLength(20);
		
		for(OrgstdStruct orgstdStruct : filmAreaList){
			
			//��10 12�޸�
			Employee employee  = null;
			
			//��  09-19 ���
			Psnaccount p = orgstdStruct.getPsnaccount();//��ø�����
			if(p != null){
				//����hrԱ��persionId����ѯ��ѶԱ��
				List<Employee> employeeList = employeeDAO.findByPersonalId(p.getEmployeeid());
				if(employeeList != null &&  employeeList.size()>0){
					 employee =	employeeList.get(0);
				}
			}
			
			
			//����Ƭ��unitCode ���������ŵ��unitId
			List<String> unitIdStoreList = orgstdStructDao.findStoreByFilmAreaUnitCode(orgstdStruct.getUnitcode(),24);
			//���������ŵ��unitId�Ӷ�Ӧ���в�ѯ��braId
			//List<String> BraId = synchronizationStoresSerivce.findBraIdBynitIdList(unitIdStoreList);
			
			List<StoreCount> storeCountList = null;
			
			if(unitIdStoreList.size()>0){
				//���������ŵ��unitId �ӱ�StoreCount��ѯ�� �ϸ�Ӫ����   ��ǰƬ���������ŵ�ʵ�ʺ�Ԥ�����۽��
				 storeCountList = correspondingDAO.findstoreCountByunitIdList(unitIdStoreList,operatingMonthId);
			}else{
				storeCountList = new ArrayList<StoreCount>();
			}
			
			//Ƭ�����������
			StoreCount filmAreastoreCount = new StoreCount();
			//20 12��
			filmAreastoreCount.setEmployee(employee);
			
			//��ѯӪ���� ����һ�Զ�
			OperatingMonth operatingMonth = operatingMonthDAO.findOperatingMonthId(operatingMonthId);
			//operatingMonth.getStoreCounts().add(filmAreastoreCount);
			filmAreastoreCount.setOperatingMonth(operatingMonth);
			
			//һ�Զ�
			//orgstdStruct.getStoreCounts().add(filmAreastoreCount);
			filmAreastoreCount.setOrgstdStruct(orgstdStruct);
			
			Double planMoneyCount = 0.0; //Ƭ���ƻ����۽��
			Double saleCount = 0.0;  //Ƭ��ʵ�����۽��
			
			for(StoreCount storeCount : storeCountList){
			 if(storeCount.getPlanMoney() != null){
				 if(storeCount.getPlanMoney().getPlanMoneyCount() != null){
					 planMoneyCount = Arith.add(storeCount.getPlanMoney().getPlanMoneyCount(),planMoneyCount);
				} 
			 }	
				 saleCount = Arith.add(storeCount.getSaleCount(),saleCount);
			}
			
			filmAreastoreCount.setPlanMoneyCount(planMoneyCount);
			filmAreastoreCount.setSaleCount(saleCount);
			
			Double complatePercentVar = 0.0;
			if(planMoneyCount != 0.0){
				complatePercentVar = Arith.div(saleCount, planMoneyCount, 3);//��ɶ�
			}
			
			filmAreastoreCount.setComplatePercent(complatePercentVar);
		    filmAreastoreCount.setPercentStr(Arith.round(Arith.mul(complatePercentVar,100),3)+"%");//��ɶ�%
			
			//������ɶ�ٷ� ����ܶ� ��ѯ����
		    Double grundbonusMoney = grundbonusDao.findWagesByThisMonthPlanMoneyComplatePercent
		    	(operatingMonthId,planMoneyCount,complatePercentVar,0);
			
		    if(grundbonusMoney != null){
		    	filmAreastoreCount.setOneStarManagergrundbonusMoney(grundbonusMoney);
		    }else{//����Ϊʲô�������ݿⲿĬ��Ϊ0.0!
		    	filmAreastoreCount.setOneStarManagergrundbonusMoney(0.0);
		    }
		    
			filmAreastoreCount.setStoreCountType(1); //Ƭ��
			filmAreastoreCountList.add(filmAreastoreCount);
			
		}
		storeCountDAO.saveOrupdateAll(filmAreastoreCountList);
		return filmAreastoreCountList;
	}

	       //����������StoreCount
	public List<StoreCount> saveBigStoreCount(String operatingMonthId, int i) {
		
		List<StoreCount> bigAreaStoreCount  = new ArrayList<StoreCount>(); 
		
		//��ѯ���д���
		List<OrgstdStruct> BigAreaList = orgstdStructDao.findAreaByLabelLength(16);
		
		
		for(OrgstdStruct orgstdStruct : BigAreaList){
			
			StoreCount bigAreaStore = new StoreCount();
			//����һ�Զ�
			bigAreaStore.setOrgstdStruct(orgstdStruct);
			//orgstdStruct.getStoreCounts().add(bigAreaStore);
			
			//Ӫ����һ�Զ�
			OperatingMonth operatingMonth = operatingMonthDAO.findOperatingMonthId(operatingMonthId);
			//operatingMonth.getStoreCounts().add(bigAreaStore);
			bigAreaStore.setOperatingMonth(operatingMonth);
		
			Double planMoneyCount = 0.0; //�����ƻ����۽��
			Double saleCount = 0.0;  //����ʵ�����۽��
			
			//���ݴ���unitCode ��������Ƭ����unitId
			List<String> unitIdStoreList = orgstdStructDao.findStoreByFilmAreaUnitCode(orgstdStruct.getUnitcode(),20);
		    //�� StoreCount���� ��ѯ����Ƭ����ʵ���Լ��ƻ��������
			List<StoreCount> filmAreaStoreCount = storeCountDAO.findFilmAreaStoreCount(operatingMonthId,unitIdStoreList,1);
		
			for(StoreCount storeCount : filmAreaStoreCount){
				planMoneyCount = Arith.add(planMoneyCount,storeCount.getPlanMoneyCount());
				saleCount = Arith.add(saleCount,storeCount.getSaleCount());
			}
			
			bigAreaStore.setSaleCount(saleCount);//���ô���ʵ�����۽��
			bigAreaStore.setPlanMoneyCount(planMoneyCount);//���ô����ƻ����۽��
			
			Double complatePercentVar = 0.0;
			if(planMoneyCount != 0.0){
				complatePercentVar = Arith.div(saleCount,planMoneyCount,3);
				
			}
			bigAreaStore.setComplatePercent(complatePercentVar); //��ɰٷֱ�
			bigAreaStore.setPercentStr(Arith.round(Arith.mul(complatePercentVar,100),3)+"%");//��ɶ�%
		
			//������ɶ�ٷ� ����ܶ� ��ѯ����
		    Double grundbonusMoney = grundbonusDao.findWagesByThisMonthPlanMoneyComplatePercent
		    	(operatingMonthId,planMoneyCount,complatePercentVar,0);
			
		    if(grundbonusMoney != null){
		    	bigAreaStore.setOneStarManagergrundbonusMoney(grundbonusMoney);
		    }else{//����Ϊʲô���������ݿⲿĬ��Ϊ0.0!
		    	bigAreaStore.setOneStarManagergrundbonusMoney(0.0);
		    }
		    
		    bigAreaStore.setPositiveManagergrundbonusMoney(Arith.mul(grundbonusMoney,0.6));
		    
		    bigAreaStore.setStoreCountType(2);  //����2
		    bigAreaStoreCount.add(bigAreaStore);
		}
		
		storeCountDAO.saveOrupdateAll(bigAreaStoreCount);
		return bigAreaStoreCount;
	}

}
