package com.gialen.countProductProject.service;

import java.util.List;

import com.gialen.model.OperatingMonth;
import com.gialen.model.PsnaccountMonthProjectDetailsLink;
import com.gialen.model.SaleDailyYymm;
import com.gialen.model.vo.SaleDailyProductPeopleSumVo;

public interface CountProductProjectService {

	void saveThisMonthPsnaccountMonthSaleCountLink(
			OperatingMonth thisOperatingMonth,String unitcode);

	List<PsnaccountMonthProjectDetailsLink> findLastMonthPsnaccountMonthSaleCountLink(
			String operatingMonthId);

	List<PsnaccountMonthProjectDetailsLink> findLastMonthPsnaccountMonthSaleCountLink(
			String operatingMonthId, String unitcode);

	List<SaleDailyProductPeopleSumVo> findThisMonthPeopleWages(
			OperatingMonth thisOperatingMonth);

	void delMiddleTable();

	void getSaleToSaleDailyProduct(OperatingMonth thisOperatingMonth);

	void saveCountPubId(OperatingMonth thisOperatingMonth);

	void saveCountAll(String operatingMonthId);

	//void saveCountEveryBoby(OperatingMonth thisOperatingMonth);

	

}
