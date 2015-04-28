package com.gialen.main.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.gialen.dao.CorrespondingDAO;
import com.gialen.dao.PsnaccountDAO;
import com.gialen.main.service.MainService;
import com.gialen.model.Corresponding;
import com.gialen.model.Psnaccount;
import com.gialen.model.vo.LgnUsrVo;

public class MainServiceImpl implements MainService {

	private PsnaccountDAO psnaccountDAO;
	private CorrespondingDAO correspondingDAO;
	
	
	    
	public CorrespondingDAO getCorrespondingDAO() {
		return correspondingDAO;
	}


	public void setCorrespondingDAO(CorrespondingDAO correspondingDAO) {
		this.correspondingDAO = correspondingDAO;
	}


	public Psnaccount findPSNByPersonid(String personid) {
		return psnaccountDAO.findById(personid);
	}


	public PsnaccountDAO getPsnaccountDAO() {
		return psnaccountDAO;
	}


	public void setPsnaccountDAO(PsnaccountDAO psnaccountDAO) {
		this.psnaccountDAO = psnaccountDAO;
	}

	public LgnUsrVo lgnByOA(String loginCode) {
		
		System.out.println(loginCode);
		
		List<Corresponding> correspondingList = correspondingDAO.lgnByOA(loginCode);
		
		if(correspondingList != null && correspondingList.size()>0){
			
			LgnUsrVo lgnUsrVo = new LgnUsrVo();
			
			List<String> braIdList = new ArrayList<String>();
			List<String> unitidList = new ArrayList<String>();
			List<String> unitCodeList = new ArrayList<String>();
			
				for(Corresponding corresponding : correspondingList){
					
					lgnUsrVo.setUsrType(corresponding.getCorrespondingType());
					
					if(corresponding.getCorrespondingType() == 0){//如果是店长 获得hr 佳讯  门店id
						braIdList.add(corresponding.getBranch().getBraId());
						unitidList.add(corresponding.getOrgstdStruct().getUnitid());
						unitidList.add(corresponding.getOrgstdStruct().getUnitcode());
					}else if(corresponding.getCorrespondingType() == 1){ // == 1的时候就是片区 大区 
						//获取所在 片区 大区  unitCode
						String unitcode = corresponding.getOrgstdStruct().getUnitcode();
						//从中间表Corresponding  获取  unitcode braId
						List<Object[]> objList = correspondingDAO.findUnitcodeAndbraId(unitcode);
						for(Object[] obj : objList){
							
							if(obj != null){
								
								if(obj[0] != null){
									braIdList.add(obj[0].toString());
								}
								unitidList.add(obj[1].toString());
								unitCodeList.add(obj[2].toString());
							}
						}
					}
				}
				lgnUsrVo.setBraIdList(braIdList);
				lgnUsrVo.setUnitidList(unitidList);
				lgnUsrVo.setUnitCodeList(unitCodeList);
				
				return lgnUsrVo;
		}
		
		return null;
	}
	
	
	 
}
