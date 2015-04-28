package com.gialen.thisMonthWages.service;

import java.util.List;

import com.gialen.model.ComplatePercent;
import com.gialen.model.Grundbonus;
import com.gialen.model.OperatingMonthPlanMoney;

public interface ThisMonthWagesService {

	List<Grundbonus> findThisMonthGrundbonus(String string);

	void saveGrundbonus(List<Grundbonus> grundbonusList);

	List<OperatingMonthPlanMoney> findOperatingMonthPlanMoney(String string);

	List<ComplatePercent> findComplatePercent(String string);

	void deleteThisMonthGrundbonus(String string);

	void saveComplatePercent(List<ComplatePercent> complatePercentList);

	void saveOneComplatePercent(ComplatePercent complatePercent);

	void savaOnemonthPlanMoney(OperatingMonthPlanMoney monthPlanMoney);

	void saveOneGrundbonus(Grundbonus grundbonus);

	List<Grundbonus> findThisMonthGrundbonusByMonthId(String operatingMonthId);

	List<String> showAreaError(String fileName);

	List<String> showStoreError(String fileName);

}
