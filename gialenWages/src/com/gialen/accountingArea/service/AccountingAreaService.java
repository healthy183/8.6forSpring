package com.gialen.accountingArea.service;

import java.util.List;

import com.gialen.model.OperatingMonth;
import com.gialen.model.StoreCount;

public interface AccountingAreaService {

	List<StoreCount> findThisStoreStoreCount(String operatingMonthId);

	List<StoreCount> saveAllStoreTotalSales(OperatingMonth thisOperatingMonth);



	List<StoreCount> findThisMonthFilmAreaWages(String operatingMonthId, int i);

	List<StoreCount> saveStoreCount(String operatingMonthId, int i);

	List<StoreCount> saveBigStoreCount(String operatingMonthId, int i);



	

}
