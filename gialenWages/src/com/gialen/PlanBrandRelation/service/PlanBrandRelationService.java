package com.gialen.PlanBrandRelation.service;

import java.util.List;

import com.gialen.model.OperatingMonth;
import com.gialen.model.Psnaccount;
import com.gialen.model.RewardBrand;
import com.gialen.model.RewardQverQuota;
import com.gialen.model.SaleDailyMan;

public interface PlanBrandRelationService {

	void findCheck_Brand_Product(String operatingMonthId);

	// void Separate_SaleDaily(String operatingMonthId,String sql);

	void Branch_Product_Collect(String operatingMonthId, Integer planBrandType);

	void IsAddBrandWages_in(String operatingMonthId);

	void IsAddBrandWages_not_in(String operatingMonthId);

	void Collect_BrandWages(String operatingMonthId);
	
	List<Object[]> Show_BrandWages(String operatingMonthId);
	
	void deletetable();
	void Count_QverQuota(String operatingMonthId);
	
	List<Object[]> ShowQverQuota(String operatingMonthId,String BraId);
	
	List<Object[]> SelectSaleDailyMan(String operatingMonthId,String BraId);
	
	void addRewardQverQuota(String SqlRewardQverQuota, String BraId,
			String operatingMonthId);
	
	List<RewardQverQuota> Show_RewardQverQuota(String operatingMonthId,String BraId);
	
	
	void updteRewardQverQuota(String RewardQverQuota);
	
	
	

}
