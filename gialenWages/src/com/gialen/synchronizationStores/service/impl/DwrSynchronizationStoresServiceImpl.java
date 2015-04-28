package com.gialen.synchronizationStores.service.impl;

import java.util.List;

import com.gialen.dao.BranchDAO;
import com.gialen.dao.CorrespondingDAO;
import com.gialen.dao.OrgstdStructDAO;
import com.gialen.model.Branch;
import com.gialen.model.Corresponding;
import com.gialen.model.OrgstdStruct;
import com.gialen.synchronizationStores.service.DwrSynchronizationStoresService;

public class DwrSynchronizationStoresServiceImpl 
		implements
			DwrSynchronizationStoresService {

	
	private CorrespondingDAO correspondingDAO;
	private BranchDAO branchDAO;
	private OrgstdStructDAO orgstdStructDao;
	
	
	
	
	      //String braNameVar, String unitNameVar,
	public void updtCorresponding(String correspondingIdVar, String braIdVar,
			 String unitidVar,String storeTypeNameVar) {
		
		Corresponding corresponding = correspondingDAO.findById(Integer.valueOf(correspondingIdVar));
		Branch	thisBranch = branchDAO.findById(braIdVar);
		OrgstdStruct thisOrgstdStruct = (OrgstdStruct) orgstdStructDao.findByUnitcode(unitidVar).get(0);
		
		corresponding.setBranch(thisBranch);
		thisBranch.getCorrespondings().add(corresponding);
		
		corresponding.setOrgstdStruct(thisOrgstdStruct);
		thisOrgstdStruct.getCorrespondings().add(corresponding);
		
		corresponding.setStoreTypeName(storeTypeNameVar);
		if(storeTypeNameVar.equals("小区")){
			corresponding.setStoreType("0");
		}else if(storeTypeNameVar.equals("商业")){
			corresponding.setStoreType("1");
		}
		correspondingDAO.merge(corresponding);
	}
	
	    
	public void delCorresponding(String correspondingIdVar) {
		correspondingDAO.delete(correspondingDAO.findById
				(Integer.valueOf(correspondingIdVar)));	
	}

	    
	public String addCorresponding(String braIdVar,String unitidVar,String storeTypeNameVar) {
		
		Branch branch = branchDAO.findById(braIdVar);
		OrgstdStruct orgstdStruct = (OrgstdStruct)orgstdStructDao.findByUnitcode(unitidVar).get(0);
		
		Corresponding corresponding = new Corresponding();
		
		corresponding.setBranch(branch);
		branch.getCorrespondings().add(corresponding);
		
		corresponding.setOrgstdStruct(orgstdStruct);
		orgstdStruct.getCorrespondings().add(corresponding);
		
		corresponding.setStoreTypeName(storeTypeNameVar);
		if(storeTypeNameVar.equals("小区")){
			corresponding.setStoreType("0");
		}else{
			corresponding.setStoreType("1");
		}
		
		Corresponding mergeCorresponding =  correspondingDAO.merge(corresponding);
		return mergeCorresponding.getCorrespondingId() +"";
	}

	     //佳讯中是否已经存在该门店   以及中间表数据已经对应数据
	public String ishadBrand(String lastbraIdvar) {
		Branch  branch = branchDAO.findById(lastbraIdvar);
		if(branch == null){
			return "nofound";
		}else{
			List<Corresponding> correspondingList 
				= correspondingDAO.findCorrespondingByBrandId(lastbraIdvar);
			if(correspondingList != null && correspondingList.size()>0){
				return correspondingList.get(0).getBranch().getBraName();
			}
			return "ok";
		}
	}
	
	     
	public Branch findBrandById(String braId) {
		return branchDAO.findById(braId);
	}
	
	    
	public String ishadBrand(String correspondingIdVar, String braIdVar) {
		Branch  branch = findBrandById(braIdVar);
		//Branch  branch = branchDAO.findById(braIdVar);
		if(branch == null){
			return "nofound";
		}else{
			List<Corresponding> correspondingList 
				= correspondingDAO.findCorrespondingByBrandId(correspondingIdVar,braIdVar);
			if(correspondingList != null && correspondingList.size()>0){
				return correspondingList.get(0).getBranch().getBraName();
			}
		}
		return "ok";
	}

	
	
	
	
	
	    
	public String ishadOrgstdStruct(String lastunitidvar) {
		List<OrgstdStruct> orgstdStructList = orgstdStructDao.findByUnitcode(lastunitidvar);
		if(orgstdStructList.size() < 1){
			return "nofound";
		}else{
			List<Corresponding> correspondingList 
				= correspondingDAO.findOrgstdStructByBrandId(lastunitidvar);
			if(correspondingList != null && correspondingList.size()>0){
				return correspondingList.get(0).getOrgstdStruct().getUnitname();
			}
		}
		return "ok";
	}
	
	
	      //问题
	public OrgstdStruct findOrgstdStructByCode(String unitid) {
		return (OrgstdStruct) orgstdStructDao.findByUnitcode(unitid).get(0);
	}
	
	    
	public String ishadOrgstdStruct(String correspondingIdVar, String unitidVar) {
		//OrgstdStruct orgstdStruct = findOrgstdStructByCode(unitidVar);
		
		List<Corresponding> thisList = orgstdStructDao.findByUnitcode(unitidVar);
		//OrgstdStruct orgstdStruct = 
		List<Corresponding> correspondingList = null;
		if(thisList.size() == 0){
			return "nofound";
		}else{
		
			correspondingList = correspondingDAO.findOrgstdStructByBrandId(correspondingIdVar,unitidVar);
		
			if(correspondingList != null && correspondingList.size()>0){
				return correspondingList.get(0).getOrgstdStruct().getUnitname();
			}
		}
		return "ok";
	}
	
	
	public BranchDAO getBranchDAO() {
		return branchDAO;
	}

	public void setBranchDAO(BranchDAO branchDAO) {
		this.branchDAO = branchDAO;
	}

	public CorrespondingDAO getCorrespondingDAO() {
		return correspondingDAO;
	}

	public void setCorrespondingDAO(CorrespondingDAO correspondingDAO) {
		this.correspondingDAO = correspondingDAO;
	}

	public OrgstdStructDAO getOrgstdStructDao() {
		return orgstdStructDao;
	}

	public void setOrgstdStructDao(OrgstdStructDAO orgstdStructDao) {
		this.orgstdStructDao = orgstdStructDao;
	}

	
	

	

	

	

	

	
	
	
}
