package com.gialen.arrangeBrand.service.impl;

import java.util.Arrays;
import java.util.List;

import com.gialen.arrangeBrand.service.AddBrandService;
import com.gialen.dao.BranchDAO;
import com.gialen.dao.OperatingMonthDAO;
import com.gialen.dao.PlanBrandDAO;
import com.gialen.dao.PlanMoneyDAO;
import com.gialen.dao.ProProjectRelationTableDAO;
import com.gialen.dao.ProductClassDAO;
import com.gialen.dao.ProductProjectDAO;
import com.gialen.model.Branch;
import com.gialen.model.OperatingMonth;
import com.gialen.model.PlanBrand;
import com.gialen.model.PlanMoney;
import com.gialen.model.ProProjectRelationTable;
import com.gialen.model.ProductBrand;
import com.gialen.model.ProductClass;
import com.gialen.model.ProductProject;

public class AddBrandServiceImpl implements AddBrandService {

	private	PlanBrandDAO planBrandDAO;
	private ProductClassDAO productClassDAO;
	private ProductProjectDAO productProjectDAO;
	private ProProjectRelationTableDAO proProjectRelationTableDAO;
	// /alpha 2012-8-15
		private PlanMoneyDAO planMoneyDAO;
		private BranchDAO branchDAO;

		private OperatingMonthDAO operatingMonthDAO;	
	public PlanBrandDAO getPlanBrandDAO() {
		return planBrandDAO;
	}

	public void setPlanBrandDAO(PlanBrandDAO planBrandDAO) {
		this.planBrandDAO = planBrandDAO;
	}

	public ProductClassDAO getProductClassDAO() {
		return productClassDAO;
	}

	public void setProductClassDAO(ProductClassDAO productClassDAO) {
		this.productClassDAO = productClassDAO;
	}
	
	public ProductProjectDAO getProductProjectDAO() {
		return productProjectDAO;
	}

	public void setProductProjectDAO(ProductProjectDAO productProjectDAO) {
		this.productProjectDAO = productProjectDAO;
	}

	public ProProjectRelationTableDAO getProProjectRelationTableDAO() {
		return proProjectRelationTableDAO;
	}

	public void setProProjectRelationTableDAO(
			ProProjectRelationTableDAO proProjectRelationTableDAO) {
		this.proProjectRelationTableDAO = proProjectRelationTableDAO;
	}

	    
	public List<PlanBrand> findThisMonthArrangeBrand(String operatingMonthId) {
		return planBrandDAO.findThisMonthArrangeBrand(operatingMonthId);
	}

	    
	public List<ProductClass> findAllProductClass() {
		return productClassDAO.findAllProductClass();
	}	
	
	      //查找所有大品牌
	public List<ProductBrand> findAllBigBrand() {
		return planBrandDAO.findAllBigBrand();
	}
	
	     //查找所有大类别
	public List<ProductClass> findAllBigClass() {
		return productClassDAO.findAllBigClass();
	}

	      		//根据当前类型查找单品,类型，品牌方案
	public List<ProductProject> findthisMonthProjectBytype(
			String operatingMonthId, int projectType) {
		//operatingMonthId.split(",");
		
		return productProjectDAO.findthisMonthProjectBytype
				(operatingMonthId,projectType);
	}

	      //根据项目id查找 该项目明细
	public List<ProProjectRelationTable> findProProjectRelationTableByProductProjectId(
			String productProjectId) {
		return proProjectRelationTableDAO
				.findProProjectRelationTableByProductProjectId(productProjectId);
	}

	      //作废单品项目 
	public void invalidProject(String productProjectId) {
		ProductProject	productProject = productProjectDAO.findProductProjectById(Integer.valueOf(productProjectId));
		productProject.setProjectStatus(1); //作废
		productProjectDAO.merge(productProject);
	}

	     //恢复单品项目 
	public void updtRestoreProject(String productProjectId) {
		ProductProject	productProject = productProjectDAO.findProductProjectById(Integer.valueOf(productProjectId));
		productProject.setProjectStatus(0); //恢复
		productProjectDAO.merge(productProject);
	}
	
	
	       //根据当前营运月 和类型 0单品方案 1品牌方案 2类别方案   查找品牌方案(分页)
	public List<ProductProject> findthisMonthProjectBytypeForpage(
			String operatingMonthId, int projectType, int pageNum, int pageSize) {
		return productProjectDAO.findthisMonthProjectBytypeForpage(operatingMonthId,projectType,pageNum,pageSize);
	}

	      //根据当前营运月 和类型 0单品方案 1品牌方案 2类别方案   查找品牌方案 总行数
	public long findthisMonthProjectBytypeRownums(String operatingMonthId,
			int projectType) {
		return productProjectDAO.findthisMonthProjectBytypeRownums(operatingMonthId,projectType);
	}

	           //根据id查找ProductProject
	public ProductProject findProductProjectById(String productProjectId) {
		return productProjectDAO.findProductProjectById(Integer.valueOf(productProjectId));
	}

	      //查找指定月的总作废项目行数
	public long findthisMonthInvalidProjectRownums(String operatingMonthId,
			int projectType) {
		return productProjectDAO.findthisMonthInvalidProjectRownums(operatingMonthId,projectType);
	}

	     //分页查找指定月的作废项目
	public List<ProductProject> findthisMonthInvalidProjectForpage(
			String operatingMonthId, int projectType, int pagenum, int pageSize) {
		return productProjectDAO.findthisMonthInvalidProjectForpage(operatingMonthId,projectType,pagenum,pageSize);
	}

	      	//查询这个营运月单品项目,重复的单品
	public List<ProProjectRelationTable> findRepeatProProject(
			String operatingMonthId) {
		return proProjectRelationTableDAO.findRepeatProProject(operatingMonthId);
	}

	      //删除这个营运月单品项目,重复的单品
	public void delRepeatProProject(String[] proStr) {
		
		List<String> delProlist = Arrays.asList(proStr);
		proProjectRelationTableDAO.delRepeatProProject(delProlist);
	}

	public PlanMoneyDAO getPlanMoneyDAO() {
		return planMoneyDAO;
	}

	public void setPlanMoneyDAO(PlanMoneyDAO planMoneyDAO) {
		this.planMoneyDAO = planMoneyDAO;
	}

	

	

	

	public void addPlanMoney(Integer productProjectId, String[] branchid,
			Double[] money, Integer planMoneyType, String operatingMonthId) {
		String OMonthId = operatingMonthId;
		for (int i = 0; i < branchid.length; i++) {
			PlanMoney pm = new PlanMoney();
			ProductProject Pp = null;
			Branch branch = branchDAO.findById(branchid[i]);       //根据branchid查找门店
			pm.setBranch(branch);
			if (productProjectId == 1) {           //1为公司预计完成定额   
				Pp = productProjectDAO.findById(productProjectId);
				OperatingMonth op = operatingMonthDAO
						.findById(operatingMonthId);
				pm.setOperatingMonth(op);
			} else {
				Pp = productProjectDAO.findById(productProjectId);
				pm.setOperatingMonth(Pp.getOperatingMonth());
				// Pp.getPlanMoneys().
				OMonthId = Pp.getOperatingMonth().getOperatingMonthId();
				// OMonthId=Pp.getPlanMoneys().

				// OMonthId=pm.setOperatingMonth(Pp.getOperatingMonth());
			}
			pm.setProductProject(Pp);
			pm.setPlanMoneyType(planMoneyType);     //定额类型
			pm.setPlanMoneyCount(money[i]);            //定额
			pm.setProductProjectName(Pp.getProductProjectName());        //方案名称
 	//	System.out.println("ProjectName " + Pp.getProductProjectName());
//			System.out.println("branchid[i] " + branchid[i]);
//			System.out.println("operatingMonthId " + OMonthId);

			// productProjectDAO.f
			List<PlanMoney> exist = planMoneyDAO.findByExist(OMonthId,
					branchid[i], productProjectId);

		//	System.out.println("exist size: " + exist.size());

			if (exist.size() > 0) {                     ///如果已经存在定额显示

				for (PlanMoney py : exist) {
					//System.out.println("exist");

					py.setPlanMoneyCount(money[i]);
					py.setProductProjectName(Pp.getProductProjectName());
					//System.out.println("ProjectName " + Pp.getProductProjectName());
					// planMoneyDAO
					planMoneyDAO.merge(py);
				}
				// planMoneyDAO.save(pm);
			}

			else
				planMoneyDAO.save(pm);

		}

	}

	 

	 

	// alpha 2012-8-16通过ProjectId planBrandType 查询品牌方案
	    
	public List<PlanBrand> findPlanBrandByProductProjectId_planBrandType(
			String productProjectId, int planBrandType) {
		return planBrandDAO.findPlanBrandByProductProjectId_planBrandType(
				productProjectId, planBrandType);
	}

	//findPlanMoney(Integer.valueOf(planMoneyType), operatingMonthId,productProjectId);
	
	
	    
	public List<PlanMoney> findPlanMoney(Integer planMoneyType,
			String operatingMonthId, String productProjectId) {
		// TODO Auto-generated method stub

		
//		System.out.println( "operatingMonthId4 "+operatingMonthId);
//		
//		System.out.println( "productProjectId4 "+productProjectId);
//		
//		System.out.println( "planMoneyType4 "+planMoneyType);
		
		
		
		///通过营运月ID与 项目ID 查询公司定额
		if (productProjectId != null) {
			return planMoneyDAO.findByProductProjectId( Integer.valueOf(productProjectId),operatingMonthId);
		}

		return planMoneyDAO.findByPlanMoneyType_OperatingMonthId(planMoneyType,
				operatingMonthId);

	}

	
	///2012-8-30显示营运月对应门店定额
	
	    
	public List<Object[]> showplanmonyes(String operatingMonthId) {
		// TODO Auto-generated method stub		
	return planMoneyDAO.ShowPlanMoneys(operatingMonthId);
	}

	public BranchDAO getBranchDAO() {
		return branchDAO;
	}

	public void setBranchDAO(BranchDAO branchDAO) {
		this.branchDAO = branchDAO;
	}

	public OperatingMonthDAO getOperatingMonthDAO() {
		return operatingMonthDAO;
	}

	public void setOperatingMonthDAO(OperatingMonthDAO operatingMonthDAO) {
		this.operatingMonthDAO = operatingMonthDAO;
	}

	    
	public List<ProductProject> findthisMonthProjectByProjectType_ProductProjectType(
			String operatingMonthId, int projectType, int productProjectType) {
		// TODO Auto-generated method stub
		
		
		
		return productProjectDAO.findthisMonthProjectByProjectType_ProductProjectType(operatingMonthId,   1,   1);
		   
	}
	
	
	
	
	

}
