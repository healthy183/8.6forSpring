package com.gialen.synchronizationStores.service;

import com.gialen.model.Branch;
import com.gialen.model.OrgstdStruct;

public interface DwrSynchronizationStoresService {

	
	//String braNameVar,String unitNameVar,
	public	void updtCorresponding(String correspondingIdVar, String braIdVar,
			 String unitidVar, String storeTypeNameVar);

	public void delCorresponding(String correspondingIdVar);

	public String addCorresponding(String braIdVar, 
			String unitidVar,  String storeTypeNameVar);

	public String ishadBrand(String lastbraIdvar);

	public String ishadOrgstdStruct(String lastunitnamevar);

	public String ishadBrand(String correspondingIdVar, String braIdVar);

	public String ishadOrgstdStruct(String correspondingIdVar, String unitidVar);

	public Branch findBrandById(String braId);

	public OrgstdStruct findOrgstdStructByCode(String unitid);

}
