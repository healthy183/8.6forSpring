package com.gialen.arrangeBrand.service;

import java.util.List;

import com.gialen.model.PlanBrand;
import com.gialen.model.PlanMoney;
import com.gialen.model.ProProjectRelationTable;
import com.gialen.model.ProductBrand;
import com.gialen.model.ProductClass;
import com.gialen.model.ProductProject;

public interface AddBrandService {

	List<PlanBrand> findThisMonthArrangeBrand(String operatingMonthId);

	List<ProductClass> findAllProductClass();

	List<ProductBrand> findAllBigBrand();

	List<ProductClass> findAllBigClass();

	List<ProductProject> findthisMonthProjectBytype(String operatingMonthId,
			int projectType);

	List<ProProjectRelationTable> findProProjectRelationTableByProductProjectId(
			String productProjectId);

	void invalidProject(String productProjectId);

	List<ProductProject> findthisMonthProjectBytypeForpage(
			String operatingMonthId, int projectType, int pagenum, int pageSize);

	long findthisMonthProjectBytypeRownums(String operatingMonthId,
			int projectType);

	ProductProject findProductProjectById(String productProjectId);

	long findthisMonthInvalidProjectRownums(String operatingMonthId,
			int projectType);

	List<ProductProject> findthisMonthInvalidProjectForpage(
			String operatingMonthId, int projectType, int pagenum, int pageSize);

	void updtRestoreProject(String productProjectId);

	List<ProProjectRelationTable> findRepeatProProject(String operatingMonthId);

	void delRepeatProProject(String[] proStr);

	
////////////alpha 2012-8-14添加门店定额
	
//void addPlanMoney(Integer productProjectId, List<PlanMoney> pm_list);

void addPlanMoney(Integer productProjectId, String[] branchid,
	Double[] money,Integer planMoneyType,String operatingMonthId);

List<PlanBrand> findPlanBrandByProductProjectId_planBrandType(String productProjectId,int planBrandType);
//void addPlanMoney(Integer productProjectId, List<PlanMoney> pm_list);



//alpha 2012-8-17通过 (planMoneyType planMoneyType)查询门店定额
List<PlanMoney> findPlanMoney(Integer planMoneyType,String operatingMonthId,String productProjectId);

 


List<Object[]> showplanmonyes(String operatingMonthId);




List<ProductProject> findthisMonthProjectByProjectType_ProductProjectType(String operatingMonthId,
		int projectType,int productProjectType);

}
