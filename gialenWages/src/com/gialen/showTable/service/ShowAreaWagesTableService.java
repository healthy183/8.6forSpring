package com.gialen.showTable.service;

import java.util.List;

import com.gialen.model.StoreCount;
import com.gialen.model.vo.SaleDailyProductPeopleSumVo;
import com.gialen.model.vo.StoreCountVo;

public interface ShowAreaWagesTableService {

	List<StoreCount> findThisMonthAreaWages(String operatingMonthId, int i);

	List<StoreCountVo> findThisMonthAreaWagesvo(String operatingMonthId, int i);
	
	List<StoreCountVo> showStoreWages(String operatingMonthId);
	
	List<StoreCountVo> showStoreWages(String operatingMonthId,
			List<String> strList);
	
	List<SaleDailyProductPeopleSumVo> showUsrWages(String operatingMonthId);

	List<SaleDailyProductPeopleSumVo> showUsrWages(String operatingMonthId,
			List<String> strList);

	List<SaleDailyProductPeopleSumVo> collectUsrWages(String operatingMonthId);

	SaleDailyProductPeopleSumVo showUsrWagesCount(String operatingMonthId);
	
}
