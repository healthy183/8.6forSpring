package com.gialen.synchronizationStores.action;

import com.gialen.synchronizationStores.service.DwrSynchronizationStoresService;

public class DwrSynchronizationStores {

	
	private	DwrSynchronizationStoresService dwrSynchronizationStoresService;
	
	//更新中间表 String braNameVar, String unitNameVar,unitNameVar,,braNameVar
	public void updtCorresponding(String correspondingIdVar,String braIdVar,
			String unitidVar,String storeTypeNameVar){
		dwrSynchronizationStoresService.updtCorresponding
			(correspondingIdVar,braIdVar,unitidVar,storeTypeNameVar);
	}
	
	//根据id删除hr佳讯门店对应表
	public void delCorresponding(String correspondingIdVar){
		dwrSynchronizationStoresService.delCorresponding(correspondingIdVar);
	}
	
	//根据id判断佳讯门店是否存在
	public String ishadBrand(String lastbraIdvar){
		return dwrSynchronizationStoresService.ishadBrand(lastbraIdvar);
	}
	
	//佳讯中是否已经存在该门店   以及中间表数据已经对应数据
	public String ishadBrand(String correspondingIdVar,String braIdVar){
		return dwrSynchronizationStoresService.ishadBrand(correspondingIdVar,braIdVar);
	}
	
	/**///根据id判断hr门店是否存在
	public String ishadOrgstdStruct(String lastunitidvar){
		return dwrSynchronizationStoresService.ishadOrgstdStruct(lastunitidvar);
	}
	
	//hr中是否已经存在该门店   以及中间表数据已经对应数据
	public String ishadOrgstdStruct(String correspondingIdVar,String unitidVar){
		return dwrSynchronizationStoresService.ishadOrgstdStruct(correspondingIdVar,unitidVar);
	}
	
	//添加中间表数据
	public String addCorresponding(String braIdVar,
			String unitidVar,String storeTypeNameVar){
		return dwrSynchronizationStoresService.addCorresponding
				(braIdVar,unitidVar,storeTypeNameVar);
	}
	
	//根据佳讯门店编号获取门店名称
	public String getBrandName(String braIdVar){
		return dwrSynchronizationStoresService.findBrandById(braIdVar).getBraName();
	}
	
	//根据HR门店编号获取门店名称
	public String getHRbrandName(String unitidVar){
		return dwrSynchronizationStoresService.findOrgstdStructByCode(unitidVar).getUnitname();
	}
	
	public DwrSynchronizationStoresService getDwrSynchronizationStoresService() {
		return dwrSynchronizationStoresService;
	}

	public void setDwrSynchronizationStoresService(
			DwrSynchronizationStoresService dwrSynchronizationStoresService) {
		this.dwrSynchronizationStoresService = dwrSynchronizationStoresService;
	}
	
	
	
	
}
