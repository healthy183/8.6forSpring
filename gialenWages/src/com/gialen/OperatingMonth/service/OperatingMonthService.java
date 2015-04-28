package com.gialen.OperatingMonth.service;

import java.util.Collection;
import java.util.List;

import com.gialen.model.OperatingMonth;

public interface OperatingMonthService {

	//List<OperatingMonth> operatingMonthIsWrite(String thisYear);

	void addOperatingMonth(List<OperatingMonth> seasonList);

	List<OperatingMonth> findThisYearsMonth(String string);

	void updtMonthDate(List<OperatingMonth> weekList);

	void addMonth(OperatingMonth operatingMonthSeason);

	List<OperatingMonth> findThisYearsWeek(String string);

	void delMonth(String string);

	List<OperatingMonth> findThisOperatingMonth(String thisType, String thisDate);

	List<OperatingMonth> findThisYearAllOperatingMonth(
			String operatingMonthType, String thisYear);

	OperatingMonth findMonthById(String operatingMonthId);

	List<OperatingMonth> findNextOperatingMonth(String operatingMonthType,
			String thisDate);

	List<OperatingMonth> findPrevOperatingMonth(String operatingMonthType,
			String thisDate);

	List<String> findErrorStr(String u);

	List<String> showMonthError(String u);

	String forStartEndDate(String operatingStartDate, String operatingEndDate);

	

}
