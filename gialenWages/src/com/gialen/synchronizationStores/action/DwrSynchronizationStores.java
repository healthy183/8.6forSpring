package com.gialen.synchronizationStores.action;

import com.gialen.synchronizationStores.service.DwrSynchronizationStoresService;

public class DwrSynchronizationStores {

	
	private	DwrSynchronizationStoresService dwrSynchronizationStoresService;
	
	//�����м�� String braNameVar, String unitNameVar,unitNameVar,,braNameVar
	public void updtCorresponding(String correspondingIdVar,String braIdVar,
			String unitidVar,String storeTypeNameVar){
		dwrSynchronizationStoresService.updtCorresponding
			(correspondingIdVar,braIdVar,unitidVar,storeTypeNameVar);
	}
	
	//����idɾ��hr��Ѷ�ŵ��Ӧ��
	public void delCorresponding(String correspondingIdVar){
		dwrSynchronizationStoresService.delCorresponding(correspondingIdVar);
	}
	
	//����id�жϼ�Ѷ�ŵ��Ƿ����
	public String ishadBrand(String lastbraIdvar){
		return dwrSynchronizationStoresService.ishadBrand(lastbraIdvar);
	}
	
	//��Ѷ���Ƿ��Ѿ����ڸ��ŵ�   �Լ��м�������Ѿ���Ӧ����
	public String ishadBrand(String correspondingIdVar,String braIdVar){
		return dwrSynchronizationStoresService.ishadBrand(correspondingIdVar,braIdVar);
	}
	
	/**///����id�ж�hr�ŵ��Ƿ����
	public String ishadOrgstdStruct(String lastunitidvar){
		return dwrSynchronizationStoresService.ishadOrgstdStruct(lastunitidvar);
	}
	
	//hr���Ƿ��Ѿ����ڸ��ŵ�   �Լ��м�������Ѿ���Ӧ����
	public String ishadOrgstdStruct(String correspondingIdVar,String unitidVar){
		return dwrSynchronizationStoresService.ishadOrgstdStruct(correspondingIdVar,unitidVar);
	}
	
	//����м������
	public String addCorresponding(String braIdVar,
			String unitidVar,String storeTypeNameVar){
		return dwrSynchronizationStoresService.addCorresponding
				(braIdVar,unitidVar,storeTypeNameVar);
	}
	
	//���ݼ�Ѷ�ŵ��Ż�ȡ�ŵ�����
	public String getBrandName(String braIdVar){
		return dwrSynchronizationStoresService.findBrandById(braIdVar).getBraName();
	}
	
	//����HR�ŵ��Ż�ȡ�ŵ�����
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
