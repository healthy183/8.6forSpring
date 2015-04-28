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

	    
	// 查询当前营运月门店的总销售数据
	public List<StoreCount> findThisStoreStoreCount(String operatingMonthId) {
		return storeCountDAO.findThisStoreStoreCount(operatingMonthId);
	}

	    
	// 执行统计所有门店在这个营运月的所有销售总额 allStoreTotalSales
	public List<StoreCount> saveAllStoreTotalSales(OperatingMonth thisOperatingMonth) {

		List<Branch> branchList = branchDAO.findAll();
		List<StoreCount> storeCountList = new ArrayList<StoreCount>();
		
		Double saleCount = 0.0;
		for (Branch branch : branchList) {
			//System.out.println("a");
			//新增门店营运月期间销售总额表
			StoreCount storeCount = new StoreCount();
			
			storeCount.setStoreCountType(0);//0店长提成
			
			//设置营运月一对多
			storeCount.setOperatingMonth(thisOperatingMonth);
			//thisOperatingMonth.getStoreCounts().add(storeCount);
			
			//设置门店中间表一对多/**/
			storeCount.setBranch(branch);
			//branch.getStoreCounts().add(storeCount);
	
			//查询当前门店 当前营运月销售记录
			List<SaleDailyYymm> saleList = saleDailyYymmDAO.
					findthisMonththisBrandSaleList(thisOperatingMonth,branch);
			
			saleCount = 0.0; //清0 
			if(saleList.size()>0){
				 //迭代算出 当前门店 当前营运月销售总金额 
				System.out.println("开始计数");
				for(SaleDailyYymm SaleDailyYymm : saleList){  
					saleCount =	Arith.add(saleCount, SaleDailyYymm.getSaleAmt());//加销售金额
					saleCount =	Arith.sub(saleCount, SaleDailyYymm.getPcashPayAmt());//减去现金券金额
				}
			}
			//设置实际销售金额                         
			storeCount.setSaleCount(saleCount);
			
			//类型门店 默认 小区 
			String storeType  = "0";
			//根据佳讯门店获取中间表数据 设置一对多
			List<Corresponding> correspondingList =	correspondingDAO.findByBra(branch.getBraId());
			
			if(correspondingList.size() > 0){
				Corresponding corresponding = correspondingList.get(0);
				//corresponding.getStoreCounts().add(storeCount);
				storeCount.setCorresponding(corresponding);
				//hr门店一对多
				OrgstdStruct orgstdStruct = corresponding.getOrgstdStruct();
				//orgstdStruct.getStoreCounts().add(storeCount);
				storeCount.setOrgstdStruct(orgstdStruct);
			 //类型门店	
			 storeType = corresponding.getStoreType();
			}
			
			String thisMonthId = thisOperatingMonth.getOperatingMonthId();
			//查询当前门店 当前营运月计划销售金额
			List<PlanMoney> thisMounththisBrandPlanMoney =
				planMoneyDao.getThisMounththisBrandPlanMoney
					(thisMonthId,branch.getBraId());
			
			//如果有任务安排的话
			if(thisMounththisBrandPlanMoney.size()>0){
				
				PlanMoney planMoney = thisMounththisBrandPlanMoney.get(0);
				planMoney.getStoreCounts().add(storeCount);
				storeCount.setPlanMoney(planMoney);
				
				Double complatePercentVar = Arith.div
						(saleCount,planMoney.getPlanMoneyCount());
					
				storeCount.setComplatePercent(complatePercentVar);
				storeCount.setPercentStr(Arith.mul(Arith.round(complatePercentVar,3),100)+"%");
			
				//根据当前营运月,当前预计销售金额，以及完成额，查询奖金(门店1)
				Double grundbonusMoney = 
					grundbonusDao.findWagesByThisMonthPlanMoneyComplatePercent
						(thisMonthId,planMoney.getPlanMoneyCount(),complatePercentVar,1);

				//设置奖金
				if(grundbonusMoney != null){ //一星级店长
					storeCount.setOneStarManagergrundbonusMoney(grundbonusMoney);
					storeCount.setPositiveManagergrundbonusMoney(Arith.mul(grundbonusMoney,0.8));//正店长
					storeCount.setDeputyManagergrundbonusMoney(Arith.mul(grundbonusMoney,0.6));//副店长
				}else{
					storeCount.setOneStarManagergrundbonusMoney(0.0);
				}
				
				//根据当前营运月,完成额，查询门店总提(总提 type是2)
				Double rewardPercent = 
						grundbonusDao.findWagesPercentByThisMonthPlanMoneyComplatePercent
							(thisMonthId,complatePercentVar,storeType,2);
				
				System.out.println(rewardPercent == null);
				//设置总提
				storeCount.setStoreAllWages(Arith.round(Arith.mul(rewardPercent,saleCount),2));
				
				
			}else{
				//没有任务 就默认完成为100%
				storeCount.setComplatePercent(1.0);
				storeCount.setPercentStr("100%");
				storeCount.setPlanMoneyCount(0.0);//计划金额0
				
				//然后拿实际计划销售金额,100%去查询奖金
				//再根据当前营运月,当前预计销售金额，以及完成额，查询奖金(门店类型为1)
				Double grundbonusMoney = 
					grundbonusDao.findWagesByThisMonthPlanMoneyComplatePercent
						(thisMonthId,saleCount,1.0,1);
				
				//设置奖金
				if(grundbonusMoney != null){ //一星级店长
					storeCount.setOneStarManagergrundbonusMoney(grundbonusMoney);
					storeCount.setPositiveManagergrundbonusMoney(Arith.mul(grundbonusMoney,0.8));//正店长
					storeCount.setDeputyManagergrundbonusMoney(Arith.mul(grundbonusMoney,0.6));//副店长
				}else{
					storeCount.setOneStarManagergrundbonusMoney(0.0);
				}
				
				//然后拿实际计划销售金额,100%去查询奖金
				//再根据当前营运月,当前预计销售金额，以及完成额，查询奖金(门店总提为2)
				Double rewardPercent = 
						grundbonusDao.findWagesPercentByThisMonthPlanMoneyComplatePercent
							(thisMonthId,1.0,storeType,2);
				//设置总提
				storeCount.setStoreAllWages(Arith.round(Arith.mul(rewardPercent,saleCount),2));
			}
			
			storeCountList.add(storeCount);
			System.out.println("一个门店执行完!");
		}

		//批量保存 storeCount
		storeCountDAO.saveOrupdateAll(storeCountList);
		
		return storeCountList;
	}

	      //查询上个营运月的片区提成StoreCount
	public List<StoreCount> findThisMonthFilmAreaWages(String operatingMonthId,int i) {
		return storeCountDAO.findThisMonthFilmAreaWages(operatingMonthId,i);
	}

	    
	public List<StoreCount> saveStoreCount(String operatingMonthId, int i) {
		
		List<StoreCount> filmAreastoreCountList = new ArrayList<StoreCount>();
		
		//查询所有片区
		List<OrgstdStruct> filmAreaList = orgstdStructDao.findAreaByLabelLength(20);
		
		for(OrgstdStruct orgstdStruct : filmAreaList){
			
			//康10 12修改
			Employee employee  = null;
			
			//康  09-19 添加
			Psnaccount p = orgstdStruct.getPsnaccount();//获得负责人
			if(p != null){
				//根据hr员工persionId来查询佳讯员工
				List<Employee> employeeList = employeeDAO.findByPersonalId(p.getEmployeeid());
				if(employeeList != null &&  employeeList.size()>0){
					 employee =	employeeList.get(0);
				}
			}
			
			
			//根据片区unitCode 查找所有门店的unitId
			List<String> unitIdStoreList = orgstdStructDao.findStoreByFilmAreaUnitCode(orgstdStruct.getUnitcode(),24);
			//根据所有门店的unitId从对应表中查询的braId
			//List<String> BraId = synchronizationStoresSerivce.findBraIdBynitIdList(unitIdStoreList);
			
			List<StoreCount> storeCountList = null;
			
			if(unitIdStoreList.size()>0){
				//根据所有门店的unitId 从表StoreCount查询出 上个营运与   当前片区下所有门店实际和预计销售金额
				 storeCountList = correspondingDAO.findstoreCountByunitIdList(unitIdStoreList,operatingMonthId);
			}else{
				storeCountList = new ArrayList<StoreCount>();
			}
			
			//片区长提成数据
			StoreCount filmAreastoreCount = new StoreCount();
			//20 12改
			filmAreastoreCount.setEmployee(employee);
			
			//查询营运月 设置一对多
			OperatingMonth operatingMonth = operatingMonthDAO.findOperatingMonthId(operatingMonthId);
			//operatingMonth.getStoreCounts().add(filmAreastoreCount);
			filmAreastoreCount.setOperatingMonth(operatingMonth);
			
			//一对多
			//orgstdStruct.getStoreCounts().add(filmAreastoreCount);
			filmAreastoreCount.setOrgstdStruct(orgstdStruct);
			
			Double planMoneyCount = 0.0; //片区计划销售金额
			Double saleCount = 0.0;  //片区实际销售金额
			
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
				complatePercentVar = Arith.div(saleCount, planMoneyCount, 3);//完成额
			}
			
			filmAreastoreCount.setComplatePercent(complatePercentVar);
		    filmAreastoreCount.setPercentStr(Arith.round(Arith.mul(complatePercentVar,100),3)+"%");//完成额%
			
			//根据完成额百分 完成总额 查询奖金
		    Double grundbonusMoney = grundbonusDao.findWagesByThisMonthPlanMoneyComplatePercent
		    	(operatingMonthId,planMoneyCount,complatePercentVar,0);
			
		    if(grundbonusMoney != null){
		    	filmAreastoreCount.setOneStarManagergrundbonusMoney(grundbonusMoney);
		    }else{//哎，为什么设置数据库部默认为0.0!
		    	filmAreastoreCount.setOneStarManagergrundbonusMoney(0.0);
		    }
		    
			filmAreastoreCount.setStoreCountType(1); //片区
			filmAreastoreCountList.add(filmAreastoreCount);
			
		}
		storeCountDAO.saveOrupdateAll(filmAreastoreCountList);
		return filmAreastoreCountList;
	}

	       //保存大区提成StoreCount
	public List<StoreCount> saveBigStoreCount(String operatingMonthId, int i) {
		
		List<StoreCount> bigAreaStoreCount  = new ArrayList<StoreCount>(); 
		
		//查询所有大区
		List<OrgstdStruct> BigAreaList = orgstdStructDao.findAreaByLabelLength(16);
		
		
		for(OrgstdStruct orgstdStruct : BigAreaList){
			
			StoreCount bigAreaStore = new StoreCount();
			//大区一对多
			bigAreaStore.setOrgstdStruct(orgstdStruct);
			//orgstdStruct.getStoreCounts().add(bigAreaStore);
			
			//营运月一对多
			OperatingMonth operatingMonth = operatingMonthDAO.findOperatingMonthId(operatingMonthId);
			//operatingMonth.getStoreCounts().add(bigAreaStore);
			bigAreaStore.setOperatingMonth(operatingMonth);
		
			Double planMoneyCount = 0.0; //大区计划销售金额
			Double saleCount = 0.0;  //大区实际销售金额
			
			//根据大区unitCode 查找所有片区的unitId
			List<String> unitIdStoreList = orgstdStructDao.findStoreByFilmAreaUnitCode(orgstdStruct.getUnitcode(),20);
		    //从 StoreCount表中 查询所有片区的实际以及计划销售情况
			List<StoreCount> filmAreaStoreCount = storeCountDAO.findFilmAreaStoreCount(operatingMonthId,unitIdStoreList,1);
		
			for(StoreCount storeCount : filmAreaStoreCount){
				planMoneyCount = Arith.add(planMoneyCount,storeCount.getPlanMoneyCount());
				saleCount = Arith.add(saleCount,storeCount.getSaleCount());
			}
			
			bigAreaStore.setSaleCount(saleCount);//设置大区实际销售金额
			bigAreaStore.setPlanMoneyCount(planMoneyCount);//设置大区计划销售金额
			
			Double complatePercentVar = 0.0;
			if(planMoneyCount != 0.0){
				complatePercentVar = Arith.div(saleCount,planMoneyCount,3);
				
			}
			bigAreaStore.setComplatePercent(complatePercentVar); //完成百分比
			bigAreaStore.setPercentStr(Arith.round(Arith.mul(complatePercentVar,100),3)+"%");//完成额%
		
			//根据完成额百分 完成总额 查询奖金
		    Double grundbonusMoney = grundbonusDao.findWagesByThisMonthPlanMoneyComplatePercent
		    	(operatingMonthId,planMoneyCount,complatePercentVar,0);
			
		    if(grundbonusMoney != null){
		    	bigAreaStore.setOneStarManagergrundbonusMoney(grundbonusMoney);
		    }else{//哎，为什么不设置数据库部默认为0.0!
		    	bigAreaStore.setOneStarManagergrundbonusMoney(0.0);
		    }
		    
		    bigAreaStore.setPositiveManagergrundbonusMoney(Arith.mul(grundbonusMoney,0.6));
		    
		    bigAreaStore.setStoreCountType(2);  //大区2
		    bigAreaStoreCount.add(bigAreaStore);
		}
		
		storeCountDAO.saveOrupdateAll(bigAreaStoreCount);
		return bigAreaStoreCount;
	}

}
