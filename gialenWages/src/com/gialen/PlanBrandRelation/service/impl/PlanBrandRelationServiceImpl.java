package com.gialen.PlanBrandRelation.service.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import com.gialen.PlanBrandRelation.service.PlanBrandRelationService;
import com.gialen.dao.PlanBrandDAO;
import com.gialen.dao.PlanBrandRelationDAO;
import com.gialen.dao.PlanMoneyDAO;
import com.gialen.dao.ProProjectRelationTableDAO;
import com.gialen.dao.ProductProjectDAO;
import com.gialen.dao.PsnaccountDAO;
import com.gialen.main.service.MainService;
import com.gialen.model.PlanBrand;
import com.gialen.model.PlanBrandRelationTable;
import com.gialen.model.PlanMoney;
import com.gialen.model.ProProjectRelationTable;
import com.gialen.model.ProProjectRelationTableGroup;
import com.gialen.model.ProductProject;
import com.gialen.model.Psnaccount;
import com.gialen.model.RewardBrand;
import com.gialen.model.RewardQverQuota;
import com.gialen.model.SaleDailyMan;
import com.gialen.model.SaleDailyTemp;
import com.gialen.model.SaleDailyYymmId;
import com.gialen.model.Store_Count;

public class PlanBrandRelationServiceImpl implements PlanBrandRelationService {
	private PlanBrandRelationDAO planBrandRelationTableDAO;
	private ProProjectRelationTableDAO proProjectRelationTableDAO;
	private ProductProjectDAO productProjectDAO;
	private PlanBrandDAO planBrandDAO;
	private PlanMoneyDAO planMoneyDAO;

	public PlanMoneyDAO getPlanMoneyDAO() {
		return planMoneyDAO;
	}

	public void setPlanMoneyDAO(PlanMoneyDAO planMoneyDAO) {
		this.planMoneyDAO = planMoneyDAO;
	}

	// private ProProjectRelationTable proProjectRelationTable;
	public PlanBrandRelationDAO getPlanBrandRelationTableDAO() {
		return planBrandRelationTableDAO;
	}

	public void setPlanBrandRelationTableDAO(
			PlanBrandRelationDAO planBrandRelationTableDAO) {
		this.planBrandRelationTableDAO = planBrandRelationTableDAO;
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

	public PlanBrandDAO getPlanBrandDAO() {
		return planBrandDAO;
	}

	public void setPlanBrandDAO(PlanBrandDAO planBrandDAO) {
		this.planBrandDAO = planBrandDAO;
	}

	// ///取有相关品牌任务的 流水记录

	public void findCheck_Brand_Product(String operatingMonthId) {
		List<ProductProject> Proid_list = productProjectDAO
				.findthisMonthProjectBytype(operatingMonthId, 0); // ////商品集方案
		List<ProductProject> BrandId_list = productProjectDAO
				.findthisMonthProjectBytype(operatingMonthId, 1); // ////品牌方案

		// List<ProductProject> BrandId_list =
		// productProjectDAO.findthisMonthProjectBytype(operatingMonthId); //
		// ////品牌方案
		// System.out.println("Proid_list " + Proid_list.size());
		// System.out.println("BrandId_list " + BrandId_list.size());
		// String[] sql_brandids=new
		// String[Proid_list.size()+BrandId_list.size()];
		// List<String> list = new ArrayList<String>();

		TreeSet list = new TreeSet();
		// TreeSet lists = new TreeSet();//商品集
		// //品牌 检验品牌提成 是否 有与单品提成 有重复
		for (ProductProject pro_proids : Proid_list) {
			List<ProProjectRelationTable> p_proids = proProjectRelationTableDAO
					.findProProjectRelationTableByProductProjectId(pro_proids
							.getProductProjectId().toString());
			// System.out.println("p_proids "+p_proids.size());
			for (ProProjectRelationTable proids : p_proids) { // /方案单品明细
				for (ProductProject pro_brandids : BrandId_list) {
					// /统计方案BrandID总数 商品集,品牌小类
					// List<PlanBrand> p_brandid = planBrandDAO
					// .findByPlanBrandType_Id(
					// pro_brandids.getProductProjectId(), 1);
					List<PlanBrand> p_brandid = planBrandDAO
							.findByPlanBrandType_Id(
									pro_brandids.getProductProjectId(), 1); // /1为品牌代码
					// System.out.println("p_brandid "+p_brandid.size());
					// List<PlanBrand> p_brandid =
					// planBrandDAO.findByPlanBrandType_Id(pro_brandids.getProductProjectId(),
					// 1);
					// System.out.println("p_brandid  " + p_brandid.size());
					// System.out.println("p_brandid  " + p_brandid.size());
					for (PlanBrand brandids : p_brandid) { // 比较是否相同的品牌代码
						list.add(brandids.getProductBrand().getId());
						if (proids.getProductBrand().getId()
								.equals(brandids.getProductBrand().getId())) {
							// list.add(brandids.getProductBrand().getId());
							// System.out.println("list1:"+
							// brandids.getProductBrand().getId());
							PlanBrandRelationTable ptable = new PlanBrandRelationTable();
							ptable.setOperatingMonth(proids.getProductProject()
									.getOperatingMonth());
							ptable.setPlanBrand(brandids);
							ptable.setProduct(proids.getProduct());
							ptable.setProductBrand(proids.getProductBrand());
							ptable.setProProjectRelationTable(proids);

							// /保存重复的记录
							planBrandRelationTableDAO.save(ptable);
						}

						// //2012-9-19 注销 抽流水表时不需要 包含单品方案的 BrandId
						// else {
						// list.add(proids.getProductBrand().getId());
						// }
					}
				}
			}
		}

		// /商品集 检验商品集提成 是否 有与单品提成 有重复
		for (ProductProject pro_proids : Proid_list) {
			// int i = 0;
			List<ProProjectRelationTable> p_proids = proProjectRelationTableDAO
					.findProProjectRelationTableByProductProjectId(pro_proids
							.getProductProjectId().toString());

			for (ProProjectRelationTable proids : p_proids) { // /单品方案明细
				for (ProductProject pro_brandids : BrandId_list) {

					List<PlanBrand> p_brandid = planBrandDAO
							.findByPlanBrandType_Id(
									pro_brandids.getProductProjectId(), 0); // /0为商品集
					for (PlanBrand brandids : p_brandid) {
						// if (proids.getProductBrand().getId()
						// .equals(brandids.getProductBrand().getId())) {

						if (proids.getProduct().getProId() // 比较是否相同的商品代码
								.equals(brandids.getProduct().getProId())) {
							// lists.add(brandids.getProduct().getProId());

							PlanBrandRelationTable ptable = new PlanBrandRelationTable();
							ptable.setOperatingMonth(proids.getProductProject()
									.getOperatingMonth());
							ptable.setPlanBrand(brandids);
							ptable.setProduct(proids.getProduct());
							ptable.setProductBrand(proids.getProductBrand());
							ptable.setProProjectRelationTable(proids);
							// /保存重复的记录
							planBrandRelationTableDAO.save(ptable);
						}

						// //2012-9-19 注销 抽流水表时不需要 包含商品集方案的 BrandId
						// else {
						// list.add(brandids.getProductBrand().getId());
						// }

					}
				}
			}
		}

		// //遍历商品集与品牌方案 不重复的proid
		List<Object[]> p_brandid = planBrandDAO
				.findByOperatingMonthId_proid(operatingMonthId);

		String proid_s = p_brandid.toString();

		String s = proid_s.substring(1, proid_s.length() - 1);
		String sqls = "ProId in (";

		if (s.length() > 0) {
			sqls += s;
		}
		sqls += ")";

		// /记录方案涉及的品牌代码

		Iterator iterator = list.iterator();

		String sql = "BrandId in (";
		while (iterator.hasNext()) {
			sql += iterator.next() + ",";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += ")";

		// /记录方案涉及的品牌代码

		// ////根据品牌代码取有相关品牌任务的 流水记录 保存到 sale_daily

		if (sql.equals("BrandId in )")) { // 单品方案，品牌方案不存在重复数据

 	 	 planBrandRelationTableDAO.Separate_SaleDaily(operatingMonthId); //
			// 营运月所有流水
		}

		if (!sql.equals("BrandId in )") && !sqls.equals("ProId in ()")) {

			sql = sql + " or " + sqls;

			// System.out.println("sql "+sql);
     	 planBrandRelationTableDAO.Separate_SaleDaily(operatingMonthId, sql);
		}
		
		
		
		//findTableGroupByOperatingMonthId_proid
		
		 // /alpha 2012-09-25   记录 在sale_daily不存在 组合的明细 findTableGroupByOperatingMonthId_proid
//		int isAddBrandWages=0;
//		List<Object[]> p_proid = planBrandRelationTableDAO.findTableGroupbyprojectType(operatingMonthId,2,isAddBrandWages);
		
//		String proid_string = p_proid.toString();
//		if (proid_string.length() > 6) {
//			String proid_list = proid_string.substring(1, proid_string.length() - 1);
//			String ProId_sqls = "ProId in (";
//			ProId_sqls += proid_list;
//			ProId_sqls += ")";			
		////把符合组合的商品　抽到 sale_daily表里　Groupflag字段标志为　"Groupflag"
   	//  	planBrandRelationTableDAO.Separate_SaleDaily_Group(operatingMonthId, ProId_sqls); 	  	

	 
//		}		
 		
		
		 
		  
//		 List<ProductProject> BrandGroup_list = productProjectDAO
//		 .findthisMonthProjectBytype(operatingMonthId, 2); // ////组合方案
//		 for (ProductProject pro_proids : BrandGroup_list) {	
//		
// 		 List<Object[]> p_proid_list =proProjectRelationTableDAO.findTableGroup_ProidbyproProjectId2(pro_proids.getProductProjectId());
//			 String proid_string = p_proid_list.toString();			 
//				if (proid_string.length() > 6) {
//					String proid_lis = proid_string.substring(1, proid_string.length() - 1);
//					String[] proids = proid_lis.split(",");
//					 //通过流水saleid  和　单品代码组(  proid in (XXX,XXX) )  查询 sale_daily表   把满足的记录　插入   sale_daily_group
// 			 	    proProjectRelationTableDAO.findGroupSale_daily_byProid(""); 		 
//					}		
//		 }
		
	}
	
	
	
	
	
		 // /记录 在sale_daily不存在 组合的明细
//		List<Object[]> p_proid = planBrandRelationTableDAO
//				.findTableGroupByOperatingMonthId_proid(operatingMonthId);
//		String proid_string = p_proid.toString();
//		if (proid_string.length() > 6) {
//			String proid_list = proid_string.substring(1, proid_string.length() - 1);
////			String ProId_sqls = "ProId in (";
////			ProId_sqls += proid_list;
////			ProId_sqls += ")";			
////			System.out.println("明细 "+ProId_sqls);
  	  	
//		}
		
 		
	////alpha 2012-9-21 统计sale_daily表  存在的组合记录	
//		 TreeSet lists = new TreeSet(); ///记录 在sale_daily不存在 组合的明细
//		 List<ProductProject> BrandGroup_list = productProjectDAO
//		 .findthisMonthProjectBytype(operatingMonthId, 2); // ////组合方案
//		 for (ProductProject pro_proids : BrandGroup_list) {	
//			 
//			 
//			 ///////2012-9-21 统计同一方案里面  proid   		 
//  List<Object[]> p_proid_list =proProjectRelationTableDAO.findTableGroup_ProidbyproProjectId2(pro_proids.getProductProjectId());
//  String proid_string = p_proid_list.toString();			 
//	if (proid_string.length() > 6) {
//		String proid_lis = proid_string.substring(1, proid_string.length() - 1);
//		String[] proids = proid_lis.split(",");
//		 //通过流水saleid  和　单品代码组(  proid in (XXX,XXX) )  查询 sale_daily表   把满足的记录　插入   sale_daily_group
 	  //  proProjectRelationTableDAO.findGroupSale_daily_byProid(""); 		 
//		}
	
 	
		
		
		
		
		
		
		
		///通过组合方案的单品代码　查询sale_daily表　涉及的流水saleid
// 	List<Object[]> saleid_list =proProjectRelationTableDAO.findSale_daily_byProid(ProId_sqls);						
//		String saleids = saleid_list.toString();	 
//  	    String saleid = saleids.substring(1, saleids.length() - 1); 	    
// 	   String saleid_sql = " saleid in (  " ;
// 	  saleid_sql +=saleid;
// 	 saleid_sql+= ")";
		
		
		
		
 	
	
//需变更算法　select braid,saleid,saleman from  sale_daily where  proid in(00511032 ,00510998)  group  by braid ,saleid ,saleman having  count(proid) > 1
	//select braid,saleid,saleman from  sale_daily_group group  by braid ,saleid ,saleman having  count(proid) > 1 

  
  
//		 List<Object[]> p_proid_list =proProjectRelationTableDAO.findTableGroup_ProidbyproProjectId(pro_proids.getProductProjectId());
//		 System.out.println("siez "+p_proid_list.size());		 
//		 for(Object[] Group:p_proid_list){
////			 System.out.println(Group[0].toString());
////					 System.out.println(Group[1].toString());
//			 }
 		 

//	 proid_string = p_proid_list.toString();			 
//	if (proid_string.length() > 6) {
//		String proid_lis = proid_string.substring(1, proid_string.length() - 1);
//		String ProId_sqls = "ProId in (";
//		ProId_sqls += proid_lis;
//		ProId_sqls += ")";						
//		////统计有组合Prod 流水号  saleid[0]=saleid字段  ，   saleid[1]=braid字段
//		List<Object[]> Sale_daily_saleid=planBrandRelationTableDAO.findSale_daily_saleid_byproid(ProId_sqls);								
//		for(Object[] saleid: Sale_daily_saleid){
//			List<Object[]> Sale_daily_list=planBrandRelationTableDAO.findSale_dailybyproid(ProId_sqls);					
//			for(Object[] Sale_daily: Sale_daily_list){							
//			} 					
//		}
//	}			 
			 
			 
//			 String proids = proid_string.substring(1, proid_string.length() - 1); 
		 
		 
//			 String[] sql_productString =  proids.split(","); 
//			 
//			 System.out.println("proid_string "+sql_productString);
//			 
//			 
//			 for (int i = 0; i < sql_productString.length; i++) {
//			 
//				 if(sql_productString[i].length()>8) sql_productString[i]=sql_productString[i].substring(1, sql_productString[i].length());
//				 
//				 
//                System.out.println(sql_productString[i]);
//		 
//			 }
		 
//		 for (ProProjectRelationTableGroup proids : p_proids) { // /组合案单品明细
//		 String proid = proids.getProduct().getProId();
//		 List<ProProjectRelationTableGroup> RelationTable_Group =
//		 planBrandRelationTableDAO
//		 .RelationTableGroup(proid);
//		 if (RelationTable_Group.size() == 0)
//		 lists.add(proid);
//		 }
	//	 }	
		
	//}

	// //统计门店_品牌小类_商品集销售总金额

	public void Branch_Product_Collect(String operatingMonthId,
			Integer planBrandType) {
 	List<ProductProject> BrandId_list = productProjectDAO
				.findthisMonthProjectBytype(operatingMonthId, planBrandType); // ////品牌_商品集方案
		TreeSet secondPlan_list = new TreeSet(); // 统计同mainPlan 里面副任务数量
		for (ProductProject pro_brandids : BrandId_list) {
			Integer ProductProjectId = pro_brandids.getProductProjectId();
			List<PlanBrand> planbrand_list = planBrandDAO
					.findByplanBrandmainPlan(pro_brandids.getProductProjectId());
			// System.out.println(planbrand_list.size());
			for (PlanBrand pb : planbrand_list) {
				secondPlan_list.add(pb.getPlanBrandsecondPlan());
				// secondPlan_list2.add(pb.getPlanBrandsecondPlan());
			}
			Iterator iterator = secondPlan_list.iterator();
			// Iterator iterator2 = secondPlan_list2.iterator();
			while (iterator.hasNext()) {// /遍历副任务
				String secondplan = iterator.next().toString();
				// //alpha 2012-8-28 通过ProductProjectId,secondPlan planBrandType
				// 取PlanBrand
				List<PlanBrand> p_list_Proid = planBrandDAO
						.findByplanBrandsecondPlan(ProductProjectId,
								secondplan, 0); // 单品
				// System.out.println("p_list_Proid单品 :" + p_list_Proid.size());
				for (PlanBrand p_secondPlan_proid : p_list_Proid) {
					List<Object[]> proid_list = planBrandRelationTableDAO
							.Branch_Collect_Proid(p_secondPlan_proid
									.getProduct().getProId());
					// System.out.println("proid_listproid单品 :" +
					// proid_list.size());
					for (Object[] prolist : proid_list) {
						// System.out.println("proid单品 :"+prolist[1].toString());
						planBrandRelationTableDAO
								.Insert_Collect_Proid(
										prolist[0].toString(),
										prolist[1].toString(),
										pro_brandids.getProductProjectId(),
										p_secondPlan_proid.getPlanBrandId(),
										Double.valueOf(prolist[2].toString()),
										p_secondPlan_proid.getPlanBrandType(),
										p_secondPlan_proid
												.getPlanBrandsecondPlan(),
										p_secondPlan_proid
												.getPlanBrandtaskType(),
										p_secondPlan_proid
												.getPlanBrandunFinishedPoint(),
										p_secondPlan_proid
												.getPlanBrandfinishedPoint(),
										p_secondPlan_proid
												.getPlanBrandfinishedType(),
										p_secondPlan_proid
												.getPlanBrandfinishedQuota(),
										p_secondPlan_proid
												.getPlanBrandfinishedPointQuota(),
										p_secondPlan_proid
												.getPlanBrandunFinishedPointQuota(),
										p_secondPlan_proid
												.getPlanBrandqverQuota(),
										p_secondPlan_proid
												.getPlanBrandqverQuotaPoint());
					}
				} // //////////////////////////////////////////////////////////////////

				// //按saleMan汇总
				for (PlanBrand p_secondPlan_proid : p_list_Proid) {
					List<Object[]> proid_list = planBrandRelationTableDAO
							.Branch_Collect_Proid_SaleMan(p_secondPlan_proid
									.getProduct().getProId());
					// System.out.println("proid_listsaleMan汇总 :" +
					// proid_list.size());
					for (Object[] prolist : proid_list) {
						// System.out.println("saleMan单品汇总 :"+prolist[2].toString());
						planBrandRelationTableDAO.Insert_Collect_Proid_SaleMan(
								prolist[0].toString(), prolist[1].toString(),
								prolist[2].toString(), pro_brandids
										.getProductProjectId(),
								p_secondPlan_proid.getPlanBrandId(), Double
										.valueOf(prolist[3].toString()),
								p_secondPlan_proid.getPlanBrandType(),
								p_secondPlan_proid.getPlanBrandsecondPlan(),
								p_secondPlan_proid.getPlanBrandtaskType(),
								p_secondPlan_proid
										.getPlanBrandunFinishedPoint(),
								p_secondPlan_proid.getPlanBrandfinishedPoint(),
								p_secondPlan_proid.getPlanBrandfinishedType(),
								p_secondPlan_proid.getPlanBrandfinishedQuota(),
								p_secondPlan_proid
										.getPlanBrandfinishedPointQuota(),
								p_secondPlan_proid
										.getPlanBrandunFinishedPointQuota(),
								operatingMonthId);
					}
				}
				// }
				// while (iterator2.hasNext()) {
				// Iterator iterator2 = secondPlan_list2.iterator();

				List<PlanBrand> p_list_Branid = planBrandDAO
						.findByplanBrandsecondPlan(ProductProjectId,
								secondplan, 1); // 品牌
				for (PlanBrand p_secondPlan_brandid : p_list_Branid) {
					List<Object[]> proid_list = planBrandRelationTableDAO
							.Branch_Collect_BrandId(p_secondPlan_brandid
									.getProductBrand().getId());
					// System.out.println("p_secondPlan_brandid :" +
					// proid_list.size());
					for (Object[] prolist : proid_list) {
						// System.out.println("品牌汇总 :"+prolist[1].toString());
						planBrandRelationTableDAO
								.Insert_Collect_BrandId(
										prolist[0].toString(),
										prolist[1].toString(),
										pro_brandids.getProductProjectId(),
										p_secondPlan_brandid.getPlanBrandId(),
										Double.valueOf(prolist[2].toString()),
										p_secondPlan_brandid.getPlanBrandType(),
										p_secondPlan_brandid
												.getPlanBrandsecondPlan(),
										p_secondPlan_brandid
												.getPlanBrandtaskType(),
										p_secondPlan_brandid
												.getPlanBrandunFinishedPoint(),
										p_secondPlan_brandid
												.getPlanBrandfinishedPoint(),
										p_secondPlan_brandid
												.getPlanBrandfinishedType(),
										p_secondPlan_brandid
												.getPlanBrandfinishedQuota(),
										p_secondPlan_brandid
												.getPlanBrandfinishedPointQuota(),
										p_secondPlan_brandid
												.getPlanBrandunFinishedPointQuota(),
										p_secondPlan_brandid
												.getPlanBrandqverQuota(),
										p_secondPlan_brandid
												.getPlanBrandqverQuotaPoint());
					}
				} // //////////////////////////////////////////////////////////////////

				for (PlanBrand p_secondPlan_brandid : p_list_Branid) {
					List<Object[]> proid_list = planBrandRelationTableDAO
							.Branch_Collect_BrandId_SaleMan(p_secondPlan_brandid
									.getProductBrand().getId());
					for (Object[] prolist : proid_list) {
						// System.out.println("SaleMan品牌汇总 :"+prolist[1].toString());
 					planBrandRelationTableDAO
								.Insert_Collect_BrandId_SaleMan(
										prolist[0].toString(),
										prolist[1].toString(),
										prolist[2].toString(),
										pro_brandids.getProductProjectId(),
										p_secondPlan_brandid.getPlanBrandId(),
										Double.valueOf(prolist[3].toString()),
										p_secondPlan_brandid.getPlanBrandType(),
										p_secondPlan_brandid
												.getPlanBrandsecondPlan(),
										p_secondPlan_brandid
												.getPlanBrandtaskType(),
										p_secondPlan_brandid
												.getPlanBrandunFinishedPoint(),
										p_secondPlan_brandid
												.getPlanBrandfinishedPoint(),
										p_secondPlan_brandid
												.getPlanBrandfinishedType(),
										p_secondPlan_brandid
												.getPlanBrandfinishedQuota(),
										p_secondPlan_brandid
												.getPlanBrandfinishedPointQuota(),
										p_secondPlan_brandid
												.getPlanBrandunFinishedPointQuota(),
										operatingMonthId); 
					}
				}			
 			
				
			}// /遍历副任务
			
 			
		} /////////////////for (ProductProject pro_brandids : BrandId_list)
		
 
		
		//2012-9-26 统计单品方案与品牌或商品集重复的金额  失效		
//		// ///////////////////////////////////////统计单品方案与品牌或商品集重复的金额
//		List<PlanBrandRelationTable> PlanBrandRelationTable_list = planBrandRelationTableDAO
//				.findByOperatingMonthid(operatingMonthId); // ////品牌_商品集方案
//		for (PlanBrandRelationTable Re_list : PlanBrandRelationTable_list) {
//			Integer ProjectId = Re_list.getPlanBrand().getProductProject()
//					.getProductProjectId();
//			String BrandId = Re_list.getProductBrand().getId();
//			Integer planBrandId = Re_list.getPlanBrand().getPlanBrandId();
//			List<Object[]> proid_list = planBrandRelationTableDAO
//					.Branch_Collect_Proid_SaleMan(Re_list.getProduct()
//							.getProId());
//			planBrandType = 0; // /0为商品集方案
//			for (Object[] prolist : proid_list) {
//						
// 				planBrandRelationTableDAO.Insert_Collect_Proid_SaleMan(
// 					prolist[0].toString(), prolist[1].toString(),
// 						prolist[2].toString(), ProjectId, BrandId,
// 					Double.valueOf(prolist[3].toString()),
// 						operatingMonthId, planBrandId, planBrandType); // /planBrandtaskType
// 			}		
//		}
		
		
		
		
		//2012-9-26 统计单品方案与品牌或商品集重复的金额  失效		
		// ///////////////////////////////////////统计单品方案与品牌或商品集重复的金额
		List<PlanBrandRelationTable> PlanBrandRelationTable_list = planBrandRelationTableDAO
				.findByOperatingMonthid(operatingMonthId); //  重复数据
    	for (PlanBrandRelationTable Re_list : PlanBrandRelationTable_list) {
    		
    		Integer planBrandId = Re_list.getPlanBrand().getPlanBrandId();
    		String BrandId = Re_list.getProductBrand().getId();
    		String proid= Re_list.getProduct().getProId();
   		
    		PlanBrand pd=planBrandDAO.findById(planBrandId);
    		
    		System.out.print(pd);;
     		
    		Integer ProductProjectId=pd.getProductProject().getProductProjectId();
    		List<Object[]> proid_list = planBrandRelationTableDAO
					.Branch_Collect_Proid_SaleMan(proid); 
			for (Object[] prolist : proid_list) {
			// System.out.println("SaleMan品牌汇总 :"+prolist[1].toString());
			planBrandRelationTableDAO
					.Insert_Collect_BrandId_SaleMan(
							prolist[0].toString(),
							prolist[1].toString(),
							prolist[2].toString(),
							ProductProjectId,
							//pro_brandids.getProductProjectId(),
							pd.getPlanBrandId(),
							Double.valueOf(prolist[3].toString())*-1,
							pd.getPlanBrandType(),
							pd
									.getPlanBrandsecondPlan(),
									pd
									.getPlanBrandtaskType(),
									pd
									.getPlanBrandunFinishedPoint(),
									pd
									.getPlanBrandfinishedPoint(),
									pd
									.getPlanBrandfinishedType(),
									pd
									.getPlanBrandfinishedQuota(),
									pd
									.getPlanBrandfinishedPointQuota(),
									pd
									.getPlanBrandunFinishedPointQuota(),
							operatingMonthId); 
		}
    		
    	}
// 		Integer ProjectId = Re_list.getPlanBrand().getProductProject()
// 					.getProductProjectId();
 //			String BrandId = Re_list.getProductBrand().getId();
//			Integer planBrandId = Re_list.getPlanBrand().getPlanBrandId();
			
		
//			List<Object[]> proid_list = planBrandRelationTableDAO
//					.Branch_Collect_Proid_SaleMan(Re_list.getProduct()
//							.getProId());
//			planBrandType = 0; // /0为商品集方案
//			for (Object[] prolist : proid_list) {
//						
// 				planBrandRelationTableDAO.Insert_Collect_Proid_SaleMan(
// 					prolist[0].toString(), prolist[1].toString(),
// 						prolist[2].toString(), ProjectId, BrandId,
// 					Double.valueOf(prolist[3].toString()),
// 						operatingMonthId, planBrandId, planBrandType); // /planBrandtaskType
// 			}
			
			
//			for (PlanBrand p_secondPlan_brandid : p_list_Branid) {
//				List<Object[]> proid_list = planBrandRelationTableDAO
//						.Branch_Collect_BrandId_SaleMan(p_secondPlan_brandid
//								.getProductBrand().getId());
//				for (Object[] prolist : proid_list) {
//					// System.out.println("SaleMan品牌汇总 :"+prolist[1].toString());
//					planBrandRelationTableDAO
//							.Insert_Collect_BrandId_SaleMan(
//									prolist[0].toString(),
//									prolist[1].toString(),
//									prolist[2].toString(),
//									pro_brandids.getProductProjectId(),
//									p_secondPlan_brandid.getPlanBrandId(),
//									Double.valueOf(prolist[3].toString()),
//									p_secondPlan_brandid.getPlanBrandType(),
//									p_secondPlan_brandid
//											.getPlanBrandsecondPlan(),
//									p_secondPlan_brandid
//											.getPlanBrandtaskType(),
//									p_secondPlan_brandid
//											.getPlanBrandunFinishedPoint(),
//									p_secondPlan_brandid
//											.getPlanBrandfinishedPoint(),
//									p_secondPlan_brandid
//											.getPlanBrandfinishedType(),
//									p_secondPlan_brandid
//											.getPlanBrandfinishedQuota(),
//									p_secondPlan_brandid
//											.getPlanBrandfinishedPointQuota(),
//									p_secondPlan_brandid
//											.getPlanBrandunFinishedPointQuota(),
//									operatingMonthId); 
//				}
//			}
			
 
		

	} // //统计门店_品牌小类_商品集销售总金额

	// 2012-8-24 有单品任务的品牌
	public void IsAddBrandWages_in(String operatingMonthId) {
		Integer sum = 0;
		List<ProductProject> pproject = planBrandRelationTableDAO
				.In_IsAddBrandWages(operatingMonthId);
		TreeSet list = new TreeSet();
		for (ProductProject pp : pproject) {
		//	 System.out.println("有单品任务的品牌 :" + pp.getProductProjectId());
			list.add(pp.getProductProjectId());
		}
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) { // /遍历副任务
			Integer proProjectid = Integer.valueOf(iterator.next().toString());
			
	
			
			
			
			
			
			
			
			
	////////2012-9-26 查询重复单品失效			
//			List<PlanBrand> p_brands = planBrandDAO
//					.findBy_mainPlan_distinct(proProjectid);
//			for (PlanBrand planbrand : p_brands) { // /统计有单品任务的单品金额							
//				 Integer PlanBrandId = planbrand.getPlanBrandId();				
//				//Integer PlanBrandId = planbrand.getProductProject().getProductProjectId();				
//				// //查询重复单品				
//				//System.out.println("PlanBrandId :"+PlanBrandId);				
//				List<Object[]> planbrand_Proid = planBrandRelationTableDAO.findByplanBrandid(PlanBrandId);
//				for (Object[] proid : planbrand_Proid) {
//					List<Object[]> Stemp_list_Repeat = planBrandRelationTableDAO
//							.Project_BraId_Repeat_ProId(proProjectid, proid[0].toString(), 1);
//					for (Object[] R_Proid : Stemp_list_Repeat) {
//						Integer PlanMoneyId = 0;
//						double Repeat_ProId_saleCount = 0;
//						double ComplatePercent = 0;
//						double PlanMoneyCount = 0;
//						Repeat_ProId_saleCount = Double.valueOf(R_Proid[1]
//								.toString());
//						String percentStr = "Repeat_ProId";
// 						planBrandRelationTableDAO.Insert_Brach_Repeat_ProId(
//								proProjectid, operatingMonthId, R_Proid[0]
//										.toString(), PlanMoneyId,
//								PlanMoneyCount, Repeat_ProId_saleCount,
//								ComplatePercent, 3, percentStr, proid[0].toString(),0,0);
//					} 
//				}
//			}

			// /统计有品牌任务的金额
			List<Object[]> Stemp_list = planBrandRelationTableDAO
					.Project_BraId_Sub_Repeat_ProId_Percentage(proProjectid, 1);
			for (Object[] temp : Stemp_list) {
				List<PlanMoney> pmoeny = planMoneyDAO.findByExist(
						operatingMonthId, temp[0].toString(), proProjectid);
				// FindProject_BraId_Repeat_ProId_Store_Count();

				// 搜索重复的单品数据
				List<Object[]> Repert_Proid = planBrandRelationTableDAO
						.FindProject_BraId_Repeat_ProId_Store_Count(
								proProjectid, temp[0].toString(), 3);
				double saleCount_repeat = 0;
				String ProID = "";
				for (Object[] Proid : Repert_Proid) {
					saleCount_repeat = Double.valueOf(Proid[1].toString());
					ProID = Proid[2].toString();
				}
				if (pmoeny.size() > 0) {
					Integer PlanMoneyId = 0;
					double saleCount = 0;
					double ComplatePercent = 0;
					double PlanMoneyCount = 0;
					double planBrandqverQuota = Double.valueOf(temp[2]
							.toString());
					double planBrandqverQuotaPoint = Double.valueOf(temp[3]
							.toString());
					for (PlanMoney p : pmoeny) {
						PlanMoneyCount = p.getPlanMoneyCount();
						PlanMoneyId = p.getPlanMoneyId();
					}
					saleCount = Double.valueOf(temp[1].toString());
					if (PlanMoneyCount == 0) {
						ComplatePercent = 0;
					} else
						ComplatePercent = saleCount / PlanMoneyCount;
					String percentStr = "";

					// ///alpha 2012-9-13 //新增 超定额字段

					// planBrandRelationTableDAO.Insert_Store_CountBrach(
					// proProjectid, operatingMonthId, temp[0].toString(),
					// PlanMoneyId, PlanMoneyCount, saleCount,
					// ComplatePercent, 4, percentStr, saleCount_repeat,
					// ProID);
 			planBrandRelationTableDAO.Insert_Store_CountBrach(
							proProjectid, operatingMonthId, temp[0].toString(),
							PlanMoneyId, PlanMoneyCount, saleCount,
							ComplatePercent, 4, percentStr, saleCount_repeat,
							ProID, planBrandqverQuota, planBrandqverQuotaPoint); 

				} else { // /// PlanMoney 没有该门店定额 // ComplatePercent=100%
					Integer PlanMoneyId = 0;
					double saleCount = 0;
					double ComplatePercent = 1;
					double PlanMoneyCount = 0;
					double planBrandqverQuota = 0;
					double planBrandqverQuotaPoint = 0;
					String percentStr = "没有该门店定额";
					saleCount = Double.valueOf(temp[1].toString());
 				planBrandRelationTableDAO.Insert_Store_CountBrach(
							proProjectid, operatingMonthId, temp[0].toString(),
							PlanMoneyId, PlanMoneyCount, saleCount,
							ComplatePercent, 5, percentStr, saleCount_repeat,
							ProID, planBrandqverQuota, planBrandqverQuotaPoint); 
				}

			} // /统计有品牌任务的金额

			
			
	//////2012-9-23 alpha 		
			// /统计无品牌任务的金额
						List<Object[]> Stemps_list = planBrandRelationTableDAO
								.Project_BraId_Sub_Repeat_ProId_Percentage(proProjectid, 0);
						for (Object[] temp : Stemps_list) {
//							List<PlanMoney> pmoeny = planMoneyDAO.findByExist(
//									operatingMonthId, temp[0].toString(), proProjectid);							 
							// 搜索重复的单品数据
							List<Object[]> Repert_Proid = planBrandRelationTableDAO
									.FindProject_BraId_Repeat_ProId_Store_Count(
											proProjectid, temp[0].toString(), 3);
							double saleCount_repeat = 0;
							String ProID = "";
							for (Object[] Proid : Repert_Proid) {
								saleCount_repeat = Double.valueOf(Proid[1].toString());
								ProID = Proid[2].toString();
							}
							//if (pmoeny.size() > 0) {
								Integer PlanMoneyId = 0;
								double saleCount = 0;
								double ComplatePercent = 1;
								double PlanMoneyCount = 0;
//								double planBrandqverQuota = Double.valueOf(temp[2]
//										.toString());
//								double planBrandqverQuotaPoint = Double.valueOf(temp[3]
//										.toString());
//								for (PlanMoney p : pmoeny) {
//									PlanMoneyCount = p.getPlanMoneyCount();
//									PlanMoneyId = p.getPlanMoneyId();
//								}
								saleCount = Double.valueOf(temp[1].toString());
//								if (PlanMoneyCount == 0) {
//									ComplatePercent = 0;
//								} else
//									ComplatePercent = 1;
								String percentStr = "";
								planBrandRelationTableDAO.Insert_Store_CountBrach(
										proProjectid, operatingMonthId, temp[0].toString(),
										PlanMoneyId, PlanMoneyCount, saleCount,
										ComplatePercent, 7, percentStr, saleCount_repeat,
										ProID, 0, 0);

							 
								   

						} // /统计无品牌任务的金额	
			
			
 		
			
		} // /while

		// System.out.println("sum :" + sum);

	} // /// 有单品任务的品牌

	// 2012-8-25 无单品任务的品牌
	public void IsAddBrandWages_not_in(String operatingMonthId) {
		// TODO Auto-generated method stub
		List<ProductProject> pproject = planBrandRelationTableDAO
				.Not_In_IsAddBrandWages(operatingMonthId);
		TreeSet list = new TreeSet();
		for (ProductProject pp : pproject) {

			// System.out.println("无单品任务的品牌 :" + pp.getProductProjectId());
			list.add(pp.getProductProjectId());
		}
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) { // /遍历副任务
			// System.out.println("无单品任务的品牌 :" + iterator.next());
			Integer proProjectid = Integer.valueOf(iterator.next().toString());
			List<Object[]> Stemp_list = planBrandRelationTableDAO
					.Project_BraId_Percentage(proProjectid, 1);
			for (Object[] temp : Stemp_list) {

				// System.out.println ( temp[0].toString() +"-"+
				// temp[1].toString()+"-" +proProjectid);

				List<PlanMoney> pmoeny = planMoneyDAO.findByExist(
						operatingMonthId, temp[0].toString(), proProjectid);
				if (pmoeny.size() > 0) {
					Integer PlanMoneyId = 0;
					double saleCount = 0;
					double ComplatePercent = 0;
					double PlanMoneyCount = 0;
					for (PlanMoney p : pmoeny) {
						PlanMoneyCount = p.getPlanMoneyCount();
						PlanMoneyId = p.getPlanMoneyId();
					}
					saleCount = Double.valueOf(temp[1].toString());
					if (PlanMoneyCount == 0) {
						ComplatePercent = 0;
					} else
						ComplatePercent = saleCount / PlanMoneyCount;
					String percentStr = "";
					planBrandRelationTableDAO.Insert_Store_Count_Brach(
							proProjectid, operatingMonthId, temp[0].toString(),
							PlanMoneyId, PlanMoneyCount, saleCount,
							ComplatePercent, 0, percentStr,0,0);
				} else { // /// PlanMoney 没有该门店定额 // ComplatePercent=100%
					Integer PlanMoneyId = 0;
					double saleCount = 0;
					double ComplatePercent = 1;
					double PlanMoneyCount = 0;
					String percentStr = "没有该门店定额";
					saleCount = Double.valueOf(temp[1].toString());
 				planBrandRelationTableDAO.Insert_Store_Count_Brach(
							proProjectid, operatingMonthId, temp[0].toString(),
							PlanMoneyId, PlanMoneyCount, saleCount,
							ComplatePercent, 1, percentStr,0,0); 
				}
			}/////////// /统计无单品任务的品牌
			
			List<Object[]> Stemps_list = planBrandRelationTableDAO
					.Project_BraId_Percentage(proProjectid, 0);
			for (Object[] temp : Stemps_list) {
//				List<PlanMoney> pmoeny = planMoneyDAO.findByExist(
//						operatingMonthId, temp[0].toString(), proProjectid);
		 	//if (pmoeny.size() > 0) {
					Integer PlanMoneyId = 0;
					double saleCount = 0;
					double ComplatePercent = 1;
					double PlanMoneyCount = 0;
//					for (PlanMoney p : pmoeny) {
//						PlanMoneyCount = p.getPlanMoneyCount();
//						PlanMoneyId = p.getPlanMoneyId();
//					}
					saleCount = Double.valueOf(temp[1].toString());
//					if (PlanMoneyCount == 0) {
//						ComplatePercent = 0;
//					} else
//						ComplatePercent = saleCount / PlanMoneyCount;
					String percentStr = "";
					planBrandRelationTableDAO.Insert_Store_Count_Brach(
							proProjectid, operatingMonthId, temp[0].toString(),
							PlanMoneyId, PlanMoneyCount, saleCount,
							ComplatePercent, 8, percentStr,0,0);
				//}  
			}///////	/////////// /统计无单品无任务的品牌	
			
			

			
		}// ///iterator
	} // /无单品任务的品牌

	// ///通过门店销售（Store_Count_List） 及员工销售（SaleMan_List） 比例 算 提成

	public void Collect_BrandWages(String operatingMonthId) {
		// TODO Auto-generated method stub

		// 计算有任务品牌 FindStore_CountByProjectId_BraId
 		List<SaleDailyMan> SaleMan_List = planBrandRelationTableDAO
				.findSaleDailyManByOperatingMonthId(operatingMonthId, 1);
		for (SaleDailyMan Sman : SaleMan_List) {
			Integer ProjectId = Sman.getProductProject_m()
					.getProductProjectId();
			String BraId = Sman.getBraId();
			String SaleMan = Sman.getEmployee_m().getEmpId();
			double Point = 0;
			double Sale_Money = 0;
			Sale_Money = Sman.getMoneys();
			double reward = 0;
			Integer planBrandType = Sman.getPlanBrandType();
			String secondPlan = Sman.getPlanBrandsecondPlan();
			List<Store_Count> Store_Count_List = planBrandRelationTableDAO
					.FindStore_CountByProjectId_BraId(ProjectId, BraId);
			// System.out.println("Store_Count_List " +
			// Store_Count_List.size());
			for (Store_Count Scount : Store_Count_List) {
				double Percent = Scount.getComplatePercent();// 取得完成定额比例
				String Proid = Scount.getStore_ProId();
				Integer CountType = Scount.getStore_CountType();
				if (Percent > 1) { // 100%
					// System.out.println("完成100%");
					Point = Sman.getPlanBrandfinishedPoint();
				} else { // 未完成定额
					if (Sman.getPlanBrandfinishedType() == 1) // 有定额
					{
						// System.out.println("有定额");
						if (Percent > Sman.getPlanBrandfinishedQuota()) // 完成100%以下
						// 再判断是否完成定额
						{
							// System.out.println("完成定额");
							Point = Sman.getPlanBrandfinishedPointQuota();
						} else {
							// System.out.println("未完成定额");
							Point = Sman.getPlanBrandunFinishedPoint();
						}
					} else { // 无定额
						// System.out.println("无定额");
						Point = Sman.getPlanBrandunFinishedPoint();
					}
				} // Sman.getProductBrand_m().getId();
					// //通过 BraId, ProjectId, Proid, SaleMan查询有单品任务的品牌数据

				if (CountType == 4 && Proid.length() > 1) {
					reward = Sale_Money * 0;// / 商品集与 品牌方案重复 避免重复提
				}

				 if (planBrandType == 0 && secondPlan.length() != 2) { // /单品方案
																		// secondPlan为空值
					reward = Sale_Money * 0; // / 单品方案,避免重复
				}

				else
					reward = Sale_Money * Point;

				// BrandId
				// Sman.getPlanBrand_m().getPlanBrandId(),Sman.getProId()
				// Sman.getProductBrand_m().getId()

				String ProId = "", BrandId = "";
				double Repeat_ProId_saleCount = 0;
				if (Scount.getRepeat_ProId_saleCount() != null) {
					Repeat_ProId_saleCount = Scount.getRepeat_ProId_saleCount();
				}
				if (Sman.getProId() != null) {
					ProId = Sman.getProId(); // System.out.println(ProId);
				}
				if (Sman.getBrandId() != null) {
					BrandId = Sman.getBrandId(); // System.out.println(BrandId);

				}
				// 保存品牌提成
				planBrandRelationTableDAO.Insert_RewardBrand(BraId, ProjectId,
						operatingMonthId, SaleMan, Scount.getSaleCount(),
						reward / 100, Scount.getComplatePercent(),
						Repeat_ProId_saleCount, Scount.getPlanMoneyCount(),
						Scount.getPlanMoney().getPlanMoneyId(), Sman
								.getPlanBrand_m().getPlanBrandId(), ProId,
						BrandId, Scount.getPercentStr(), Scount
								.getStore_CountType());
				// store_CountType);
			}// ///for Store_Count_List
		} // /for SaleMan_List

		// 统计无任务的品牌 提成
		List<SaleDailyMan> SaleMan_Lists = planBrandRelationTableDAO
				.findSaleDailyManByOperatingMonthId(operatingMonthId, 0);
		
		
//		List<SaleDailyMan> SaleMan_List = planBrandRelationTableDAO
//				.findSaleDailyManByOperatingMonthId(operatingMonthId, 1);
		
		
		
		
		
		// System.out.println("1" +SaleMan_Lists.size());
		
		
		for (SaleDailyMan Sman : SaleMan_Lists) {
		 //System.out.println("2");
			Integer ProjectId = Sman.getProductProject_m().getProductProjectId();
			
			//System.out.println("ProjectId :"+ProjectId);
			
			String secondPlan = Sman.getPlanBrandsecondPlan();
			// System.out.println(" ProjectId:"+ProjectId);
			Integer planBrandType = Sman.getPlanBrandType();
			String BraId = Sman.getBraId();
			String SaleMan = Sman.getEmployee_m().getEmpId();
			double Point = 0;
			double Sale_Money = 0;
			Sale_Money = Sman.getMoneys();
			double reward = 0;

//			List<Store_Count> Store_Count_List = planBrandRelationTableDAO
//					.FindStore_CountByProjectId_BraId(ProjectId, BraId);			 
//			for (Store_Count Scount : Store_Count_List) {
//				System.out.println("1");
//				Point = Sman.getPlanBrandfinishedPoint();
//				Integer CountType = Scount.getStore_CountType();
//				String Proid = Scount.getStore_ProId();
//				if (CountType == 4 && Proid.length() > 1) {
//					reward = Sale_Money * 0;// / 提成为0;避免重复
//				}
//				if (planBrandType == 0 && secondPlan.length() != 2) { // /单品方案
//																		// secondPlan为空值
//					reward = Sale_Money * 0; // / 单品方案,避免重复
//				}
//				else
//					reward = Sale_Money * Point;
//				String ProId = "", BrandId = "";
//				double Repeat_ProId_saleCount = 0;
//				if (Scount.getRepeat_ProId_saleCount() != null) {
//					Repeat_ProId_saleCount = Scount.getRepeat_ProId_saleCount();
//				}
//				if (Sman.getProId() != null) {
//					ProId = Sman.getProId();
//				}
//				if (Sman.getBrandId() != null) {
//					BrandId = Sman.getBrandId();
//				}
//				planBrandRelationTableDAO.Insert_RewardBrand(BraId, ProjectId,
//						operatingMonthId, SaleMan, reward / 100,
//						Repeat_ProId_saleCount, Sman.getPlanBrand_m()
//								.getPlanBrandId(), ProId, BrandId, 6);
//			}
			
			//System.out.println(ProjectId+" "+BraId);	
			
  List<Store_Count> Store_Count_List = planBrandRelationTableDAO.FindStore_CountByProjectId_BraId(ProjectId, BraId);			 
	for (Store_Count Scount : Store_Count_List) {
		//System.out.println("2");
		Point = Sman.getPlanBrandfinishedPoint();
		Integer CountType = Scount.getStore_CountType();
		String Proid = Scount.getStore_ProId();
		if (CountType == 4 && Proid.length() > 1) {
			reward = Sale_Money * 0;// / 提成为0;避免重复
		}
		if (planBrandType == 0 && secondPlan.length() != 2) { // /单品方案
																// secondPlan为空值
			reward = Sale_Money * 0; // / 单品方案,避免重复
		}
		else
			reward = Sale_Money * Point;
		String ProId = "", BrandId = "";
		double Repeat_ProId_saleCount = 0;
		if (Scount.getRepeat_ProId_saleCount() != null) {
			Repeat_ProId_saleCount = Scount.getRepeat_ProId_saleCount();
		}
		if (Sman.getProId() != null) {
			ProId = Sman.getProId();
		}
		if (Sman.getBrandId() != null) {
			BrandId = Sman.getBrandId();
		}
		planBrandRelationTableDAO.Insert_RewardBrand(BraId, ProjectId,
				operatingMonthId, SaleMan, reward / 100,
				Repeat_ProId_saleCount, Sman.getPlanBrand_m()
						.getPlanBrandId(), ProId, BrandId, 6);
	}		
			
			
			
			
			
			
			
			
		}

	}

	public void Count_QverQuota(String operatingMonthId) {

		List<Store_Count> store_count_list = planBrandRelationTableDAO
				.Count_QverQuota(operatingMonthId);

		for (Store_Count store_count : store_count_list) {
			// 判断是否超额
			if (store_count.getComplatePercent() >= store_count
					.getPlanBrandqverQuota() / 100) {
				// 算出超额金额
				double QverQuota_Count = store_count.getSaleCount()
						- store_count.getPlanMoneyCount();
				// 算出超额奖励
				double saleWages = QverQuota_Count
						* store_count.getPlanBrandqverQuotaPoint() / 100;

				planBrandRelationTableDAO.Insert_QverQuota(store_count
						.getProductProject().getProductProjectId(), store_count
						.getSaleCount(), store_count.getOperatingMonth()
						.getOperatingMonthId(), store_count.getPlanMoney()
						.getPlanMoneyId(), store_count.getComplatePercent(),
						store_count.getPercentStr(), store_count.getBranch()
								.getBraId(), store_count.getStore_CountType(),
						store_count.getPlanMoneyCount(), store_count
								.getComplatePercentPart(), store_count
								.getStore_ProId(), store_count
								.getRepeat_ProId_saleCount(), store_count
								.getPlanBrandqverQuota(), store_count
								.getPlanBrandqverQuotaPoint(), saleWages);

			}

		}

	}

	public List<Object[]> Show_BrandWages(String operatingMonthId) {

		return planBrandRelationTableDAO
				.FindRewardBrandBy_OperatingMonthId(operatingMonthId);

		// TODO Auto-generated method stub

	}

	public void deletetable() {

		planBrandRelationTableDAO.delete_table();
		// TODO Auto-generated method stub

	}

	// //alpha 2012-9-14 显示门店超额
	public List<Object[]> ShowQverQuota(String operatingMonthId, String BraId) {
		return planBrandRelationTableDAO
				.Show_QverQuota(operatingMonthId, BraId);
	}

	// //alpha 2012-9-14 显示有在门店销售的员工
	public List<Object[]> SelectSaleDailyMan(String operatingMonthId,
			String BraId) {

		return planBrandRelationTableDAO.select_SaleDailyMan(operatingMonthId,
				BraId);
	}

	// //alpha 2012-9-17 添加门店员工超定额
	public void addRewardQverQuota(String SqlRewardQverQuota, String BraId,
			String operatingMonthId) {
		String[] SqlRewardQverQuota_string = SqlRewardQverQuota.split("#");
		// System.out.println("SqlRewardQverQuota_string ;"+SqlRewardQverQuota_string.length);
		// String[] sql_productString = SqlRewardQverQuota.split("#");

		for (int i = 1; i < SqlRewardQverQuota_string.length; i++) {

			String[] RewardQverQuota = SqlRewardQverQuota_string[i].split(",");

			planBrandRelationTableDAO.saveRewardQverQuota(RewardQverQuota,
					BraId, operatingMonthId);
		}

	}

	// 显示门店员工超定额
	public List<RewardQverQuota> Show_RewardQverQuota(String operatingMonthId,
			String BraId) {

		return planBrandRelationTableDAO.Show_Reward_QverQuota(
				operatingMonthId, BraId);
	}

	// 更新门店员工超定额
	public void updteRewardQverQuota(String RewardQverQuota) {

		String[] SqlRewardQverQuota_string = RewardQverQuota.split("#");

		for (int i = 1; i < SqlRewardQverQuota_string.length; i++) {

			String[] Reward_QverQuota = SqlRewardQverQuota_string[i].split(",");

			RewardQverQuota RQQ = planBrandRelationTableDAO
					.findById_RewardQverQuota(Integer
							.valueOf(Reward_QverQuota[0]));

			RQQ.setQverQuotaMoney(Double.valueOf(Reward_QverQuota[1]));

			planBrandRelationTableDAO.merge_RewardQverQuota(RQQ);

			// planBrandRelationTableDAO.Update_Reward_QverQuota(Reward_QverQuota);
		}

		// TODO Auto-generated method stub

	}

}
// ////单品集
// Iterator iterator_proid = list.iterator();
// while (iterator_proid.hasNext()) { // /遍历副任务
// //System.out.println("无单品任务的品牌 :" + iterator.next());
// Integer proProjectid =
// Integer.valueOf(iterator_proid.next().toString());
//
// System.out.println("proProjectid  :" + proProjectid);
// List<Object[]> Stemp_list_proid =
// planBrandRelationTableDAO.Project_BraId_Percentage(proProjectid, 0);
//
//
// System.out.println("size  :" + Stemp_list_proid.size());
//
//
// for (Object[] temp : Stemp_list_proid) {
// List<PlanMoney> pmoeny = planMoneyDAO.findByExist(
// operatingMonthId, temp[0].toString(), proProjectid);
// if (pmoeny.size() > 0) {
// Integer PlanMoneyId = 0;
// double saleCount = 0;
// double ComplatePercent = 0;
// double PlanMoneyCount = 0;
// for (PlanMoney p : pmoeny) {
// PlanMoneyCount = p.getPlanMoneyCount();
// PlanMoneyId = p.getPlanMoneyId();
// }
// saleCount = Double.valueOf(temp[1].toString());
// if (PlanMoneyCount == 0) {
// ComplatePercent = 0;
// }
// else ComplatePercent = saleCount / PlanMoneyCount;
// String percentStr="";
// planBrandRelationTableDAO.Insert_Store_Count_Brach(
// proProjectid, operatingMonthId, temp[0].toString(),
// PlanMoneyId, PlanMoneyCount, saleCount,
// ComplatePercent,2,percentStr);
// }
// else { // /// PlanMoney 没有该门店定额 // ComplatePercent=100%
// Integer PlanMoneyId = 0;
// double saleCount = 0;
// double ComplatePercent = 1;
// double PlanMoneyCount = 0;
// String percentStr="没有该门店定额";
// saleCount = Double.valueOf(temp[1].toString());
// planBrandRelationTableDAO.Insert_Store_Count_Brach(
// proProjectid, operatingMonthId, temp[0].toString(),
// PlanMoneyId, PlanMoneyCount, saleCount,
// ComplatePercent,3,percentStr);
// }
// }
// }