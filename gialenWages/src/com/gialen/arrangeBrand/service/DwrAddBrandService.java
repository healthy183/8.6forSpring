package com.gialen.arrangeBrand.service;

import java.util.List;
import java.util.Map;

import com.gialen.model.Branch;
import com.gialen.model.PlanBrand;
import com.gialen.model.PlanMoney;
import com.gialen.model.Product;
import com.gialen.model.ProductBrand;
import com.gialen.model.ProductClass;

public interface DwrAddBrandService {


	Map<String, String> getbrandByLevel(String brandLevel);

	Map<String, String> getClassByLevel(String classLevel);

	
	Map<String, String> findBrandByParentBrand(String bigBrandId, String level);

	Map<String, String> findClassByParentClass(String parentClassId,
			String level);

	List<ProductBrand> findBrandByLevel(String string);

	List<ProductClass> findClassByLevel(String string);

	List<Object[]> findProductReturnMap(String proIdKeywordVar,String barcodeKeywordVar,
			String proNameKeyWordVar, String bigBrandVar,
			String middleBrandVar, String smallBrandVar, String bigClassVar,
			String middleClassVar, String smallClassVar,String ProFlagStr,String ProStatusStr,
			String proIdClassStr);

	String addProductProject(String operatingMonthIdVar,String checkboxVarstr,
			String productProjectNameVal,String productProjectTypeVal,
			String productProjectVal,String isAddBrandWagesVal,String projectTypeVar);

	List<Product> findProductByProject(String productProjectId);

	void updtProductProject(String checkboxVarstr, String productProjectIdVar,
			String productProjectNameVal, String productProjectTypeVal,
			String productProjectVal, String isAddBrandWagesVal);

	void delPro(String productProjectId);


	// /2012-8-9 搜索品牌
		// List<ProductBrand> findSmallProductReturnMap(String bigBrandVar,String
		// middleBrandVar, String smallBrandVar);

		List<ProductBrand> findSmallProductReturnMap(String bigBrandVar,
				String middleBrandVar, String smallBrandVar, String proIdClassStr);

		// /2012-8-12 新增品牌，单品
		String addProduct_Project(String operatingMonthIdVar,
				String checkboxVarstr, String productProjectNameVal,
				String productProjectTypeVal, String productProjectVal,
				String isAddBrandWagesVal, String projectTypeVar);

		
		
		
		
		// /2012-8-1更新品牌，单品
		String updtProduct_Project(String operatingMonthIdVar,
				String checkboxVarstr, String productProjectNameVal,
				String productProjectTypeVal, String productProjectVal,
				String isAddBrandWagesVal, String projectTypeVar);
		
		
		
		
		// /2012-8-15 导入excel查询门店
		public  Branch findbraid( String braid);
		
		
		String addProductProject_Group(String operatingMonthIdVar,String checkboxVarstr,
				String productProjectNameVal,String productProjectTypeVal,
				String productProjectVal,String isAddBrandWagesVal,String projectTypeVar);	
	
}
