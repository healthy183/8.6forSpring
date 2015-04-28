package com.gialen.synchronizationStores.service;

import java.util.List;

import com.gialen.model.Branch;
import com.gialen.model.Corresponding;
import com.gialen.model.OrgstdStruct;
import com.gialen.model.StoreCount;

public interface SynchronizationStoresSerivce {

	List<Corresponding> findAllCorresponding();
	
	void saveOrUpdtCorresponding(List<Corresponding> correspondingList);


	List<OrgstdStruct> findAreaByLabelLength(int i);

	List<String> findStoreByFilmAreaUnitCode(
			String unitcode, int i);

	List<StoreCount> findstoreCountByunitIdList(List<String> unitIdStoreList,
			String operatingMonthId);

	

	//List<String> findBraIdBynitIdList(List<String> unitIdStoreList);

}
