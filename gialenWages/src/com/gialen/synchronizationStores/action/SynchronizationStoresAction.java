package com.gialen.synchronizationStores.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.struts2.ServletActionContext;

import com.gialen.model.Branch;
import com.gialen.model.Corresponding;
import com.gialen.model.OrgstdStruct;
import com.gialen.synchronizationStores.service.DwrSynchronizationStoresService;
import com.gialen.synchronizationStores.service.SynchronizationStoresSerivce;
import com.opensymphony.xwork2.ActionSupport;

public class SynchronizationStoresAction extends ActionSupport {

	private String operatingMonthType;
	private String upfiles;
	private SynchronizationStoresSerivce synchronizationStoresSerivce;
	private DwrSynchronizationStoresService dwrSynchronizationStoresService;

	public String writeStoreMessage(){
	
	List<Corresponding> correspondingList = synchronizationStoresSerivce.findAllCorresponding();
		if(correspondingList.size()== 0){
			return "writeStoreMessage";
		}
	
	HttpServletRequest request = ServletActionContext.getRequest();	
	request.setAttribute("correspondingList",correspondingList);
	
	return "showOrUpdtCorresponding";
}

	public String writeStoreMessageSuccess(){
		
		
		List<Branch> branchList = new ArrayList<Branch>(); 
		List<OrgstdStruct> orgstdStructList  = new ArrayList<OrgstdStruct>();
		List<Corresponding> correspondingList = new ArrayList<Corresponding>();
		
		Workbook book;
		
		try {
			book = Workbook.getWorkbook(new File(upfiles));
			Sheet sheet = book.getSheet(0);
			
			for(int i = 0;true;i++){
				
				int j = i+1; //execl中Y轴从 下标为1中开始查询
				String BraId = sheet.getCell(0,j).getContents();
				String BraName = sheet.getCell(1,j).getContents();
				String unitid = sheet.getCell(2,j).getContents();
				String unitName = sheet.getCell(3,j).getContents();
				String storeTypeName = sheet.getCell(4,j).getContents().trim();
				
				if(BraId.equals("") && unitid.equals("")){
					
					String BraIdJ = sheet.getCell(0,(j+1)).getContents();//获取下一行记录 
					String unitidJ = sheet.getCell(2,(j+1)).getContents();
						if(BraIdJ.equals("") && unitidJ.equals("")){
							break;//如果连续两条记录未空,则跳出循环 
						}
				}
				
				Corresponding corresponding = new Corresponding();
				//corresponding.setBraName(BraName);
				//corresponding.setUnitname(unitName);
				
				corresponding.setStoreTypeName(storeTypeName);
				if(storeTypeName.equals("小区")){
					corresponding.setStoreType("0");
				}else{
					corresponding.setStoreType("1");
				}
				
				if(!BraId.equals("")){
					/*Branch branch = new Branch();
					branch.setBraId(BraId);
					branch.setBraName(BraName);
					branch.setBraSname("0");
					branchList.add(branch);*/
					Branch branch =	dwrSynchronizationStoresService.findBrandById(BraId);
					corresponding.setBranch(branch);
					branch.getCorrespondings().add(corresponding);
				}
				
				if(!unitid.equals("")){
					/*OrgstdStruct orgstdStruct = new OrgstdStruct();
					//orgstdStruct.setUnitid(unitid);
					orgstdStruct.setUnitcode(unitid);
					orgstdStruct.setUnitname(unitName);
					orgstdStruct.setCostcenterid(unitid);//
					orgstdStructList.add(orgstdStruct);*/
					OrgstdStruct orgstdStruct = dwrSynchronizationStoresService.findOrgstdStructByCode(unitid);
					
					corresponding.setOrgstdStruct(orgstdStruct);
					orgstdStruct.getCorrespondings().add(corresponding);
				}
				
				correspondingList.add(corresponding);
				
			}
			
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//synchronizationStoresSerivce.mergeBranch(branchList);
		//synchronizationStoresSerivce.mergeOrgstdStruct(orgstdStructList);
		synchronizationStoresSerivce.saveOrUpdtCorresponding(correspondingList);
		
	return "writeStoreMessageSuccess";
}


	public String getOperatingMonthType() {
		return operatingMonthType;
	}

	public void setOperatingMonthType(String operatingMonthType) {
		this.operatingMonthType = operatingMonthType;
	}

	public String getUpfiles() {
		return upfiles;
	}

	public void setUpfiles(String upfiles) {
		this.upfiles = upfiles;
	}

	public SynchronizationStoresSerivce getSynchronizationStoresSerivce() {
	return synchronizationStoresSerivce;
}

	public void setSynchronizationStoresSerivce(
		SynchronizationStoresSerivce synchronizationStoresSerivce) {
	this.synchronizationStoresSerivce = synchronizationStoresSerivce;
}

	public DwrSynchronizationStoresService getDwrSynchronizationStoresService() {
		return dwrSynchronizationStoresService;
	}

	public void setDwrSynchronizationStoresService(
			DwrSynchronizationStoresService dwrSynchronizationStoresService) {
		this.dwrSynchronizationStoresService = dwrSynchronizationStoresService;
	}
	
	

	
	
}
