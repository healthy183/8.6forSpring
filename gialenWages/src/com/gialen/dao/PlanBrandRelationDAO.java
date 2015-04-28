package com.gialen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.Branch;
import com.gialen.model.PlanBrand;
import com.gialen.model.PlanBrandRelationTable;
import com.gialen.model.ProProjectRelationTableGroup;
import com.gialen.model.Product;
import com.gialen.model.ProductBrand;
import com.gialen.model.ProductProject;
import com.gialen.model.RewardBrand;
import com.gialen.model.RewardQverQuota;
import com.gialen.model.SaleDailyMan;
import com.gialen.model.SaleDailyTemp;
import com.gialen.model.StoreCount;
import com.gialen.model.Store_Count;

/**
 * A data access object (DAO) providing persistence and search support for
 * PlanBrandRelationTable entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.gialen.model.PlanBrandRelationTable
 * @author MyEclipse Persistence Tools
 */

public class PlanBrandRelationDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(PlanBrandRelationDAO.class);

	// property constants
	// public static final String PLAN_BRAND_NAME =
	// "PlanBrandRelationTableName";

	protected void initDao() {
		// do nothing
	}

	public void save(PlanBrandRelationTable transientInstance) {
		log.debug("saving PlanBrandRelationTable instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PlanBrandRelationTable persistentInstance) {
		log.debug("deleting PlanBrandRelationTable instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PlanBrandRelationTable findById(java.lang.String id) {
		log.debug("getting PlanBrandRelationTable instance with id: " + id);
		try {
			PlanBrandRelationTable instance = (PlanBrandRelationTable) getHibernateTemplate()
					.get("com.gialen.model.PlanBrandRelationTable", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PlanBrandRelationTable instance) {
		log.debug("finding PlanBrandRelationTable instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding PlanBrandRelationTable instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PlanBrandRelationTable as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all PlanBrandRelationTable instances");
		try {
			String queryString = "from PlanBrandRelationTable";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PlanBrandRelationTable merge(PlanBrandRelationTable detachedInstance) {
		log.debug("merging PlanBrandRelationTable instance");
		try {
			PlanBrandRelationTable result = (PlanBrandRelationTable) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PlanBrandRelationTable instance) {
		log.debug("attaching dirty PlanBrandRelationTable instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PlanBrandRelationTable instance) {
		log.debug("attaching clean PlanBrandRelationTable instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PlanBrandRelationDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (PlanBrandRelationDAO) ctx.getBean("PlanBrandRelationTableDAO");
	}

	public void Separate_SaleDaily(String operatingMonthId, String sql) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		// String hql =
		// "insert into  sale_daily select Hqid ,BraId,SaleDate,ProId,BrandId,SalerId,SaleMan,SaleType,SaleAmt,SaleId,InputDate,PCashPayAmt  from sale_daily_yymm where  SaleDate >= (select operatingStartDate from operatingMonth where operatingMonthId =:operatingMonth_Id ) and SaleDate <= (select operatingEndDate from operatingMonth where operatingMonthId =:operatingMonth_Id) and "
		// + sql;

		String hql = "insert into  sale_daily select Hqid, BraId, SaleDate,ProId,Barcode,ClassId,BrandId, DonateType,TransType,ProductPmtPlanNo,BrandPmtPlanNo ,TransPmtPlanNo,ProductType,SaleTax,PosNo ,SalerId,SaleMan,SaleType,SaleQty,SaleAmt, SaleDisAmt,TransDisAmt,NormalPrice,CurPrice,LastCostPrice,SaleId, MemCardNo,InvoiceId,Points1 ,Points  ,ReturnRat ,InputDate ,sendflag  ,ClassPmtPlanNo ,BrandClassPmtPlanNo,ProductClusterPmtPlanNo,PCashPayAmt,IntegralPayAmt ,'' from sale_daily_yymm where  SaleDate >= (select operatingStartDate from operatingMonth where operatingMonthId ='"
				+ operatingMonthId
				+ "' ) and SaleDate <= (select operatingEndDate from operatingMonth where operatingMonthId ='"
				+ operatingMonthId + "') and ( " + sql +")";
 
		
		
	//	System.out.println("流水　：" +hql);
		
		
		session.createSQLQuery(hql).executeUpdate();
	}

	
	
	
	public void Separate_SaleDaily_Group(String operatingMonthId, String sql) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
 

//		String hql = "insert into  sale_daily select Hqid, BraId, SaleDate,ProId,Barcode,ClassId,BrandId, DonateType,TransType,ProductPmtPlanNo,BrandPmtPlanNo ,TransPmtPlanNo,ProductType,SaleTax,PosNo ,SalerId,SaleMan,SaleType,SaleQty,SaleAmt, SaleDisAmt,TransDisAmt,NormalPrice,CurPrice,LastCostPrice,SaleId, MemCardNo,InvoiceId,Points1 ,Points  ,ReturnRat ,InputDate ,sendflag  ,ClassPmtPlanNo ,BrandClassPmtPlanNo,ProductClusterPmtPlanNo,PCashPayAmt,IntegralPayAmt        from sale_daily_yymm where  SaleDate >= (select operatingStartDate from operatingMonth where operatingMonthId ='"
//				+ operatingMonthId
//				+ "' ) and SaleDate <= (select operatingEndDate from operatingMonth where operatingMonthId ='"
//				+ operatingMonthId + "') and " + sql;
 
		
		String hql = "insert into  sale_daily select Hqid, BraId, SaleDate,ProId,Barcode,ClassId,BrandId, DonateType,TransType,ProductPmtPlanNo,BrandPmtPlanNo ,TransPmtPlanNo,ProductType,SaleTax,PosNo ,SalerId,SaleMan,SaleType,SaleQty,SaleAmt, SaleDisAmt,TransDisAmt,NormalPrice,CurPrice,LastCostPrice,SaleId, MemCardNo,InvoiceId,Points1 ,Points  ,ReturnRat ,InputDate ,sendflag  ,ClassPmtPlanNo ,BrandClassPmtPlanNo,ProductClusterPmtPlanNo,PCashPayAmt,IntegralPayAmt ,'Group' from sale_daily_yymm where  SaleDate >= (select operatingStartDate from operatingMonth where operatingMonthId ='"
 			+ operatingMonthId
 				+ "' ) and SaleDate <= (select operatingEndDate from operatingMonth where operatingMonthId ='"
 			+ operatingMonthId + "') and (" + sql + ")";		
		session.createSQLQuery(hql).executeUpdate();
		
		
	}
	
	
	
	
	
	
	public void Separate_SaleDaily(String operatingMonthId) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();

		String hql = "insert into  sale_daily select Hqid, BraId, SaleDate,ProId,Barcode,ClassId,BrandId, DonateType,TransType,ProductPmtPlanNo,BrandPmtPlanNo ,TransPmtPlanNo,ProductType,SaleTax,PosNo ,SalerId,SaleMan,SaleType,SaleQty,SaleAmt, SaleDisAmt,TransDisAmt,NormalPrice,CurPrice,LastCostPrice,SaleId, MemCardNo,InvoiceId,Points1 ,Points  ,ReturnRat ,InputDate ,sendflag  ,ClassPmtPlanNo ,BrandClassPmtPlanNo,ProductClusterPmtPlanNo,PCashPayAmt,IntegralPayAmt  ,'' from sale_daily_yymm where  SaleDate >= (select operatingStartDate from operatingMonth where operatingMonthId ='"
				+ operatingMonthId
				+ "' ) and SaleDate <= (select operatingEndDate from operatingMonth where operatingMonthId ='"
				+ operatingMonthId + "') ";

		// session.createSQLQuery(hql).setString("operatingMonth_Id",
		// operatingMonthId).executeUpdate();
		session.createSQLQuery(hql).executeUpdate();
	}

	// /2012-8-23统计门店单品销售汇总
	public List<Object[]> Branch_Collect_Proid(String Proid) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		// String hql
		// ="select p from  PlanBrand as p where  p.planBrandsecondPlan = :secondPlan and p.planBrandType =:planBrandType";
		// return session.createQuery(hql).setString("secondPlan",
		// secondPlan).setInteger("planBrandType", planBrandType).list();
		//
		String hql = "select BraId,  ProId , sum(SaleAmt-PCashPayAmt)  from sale_daily where Proid='"
				+ Proid
				+ "' and saleamt<>0  group by BraId , ProId  order by BraId";
		return session.createSQLQuery(hql).list();

	}

	// /2012-8-25 saleman统计saleman门店单品销售汇总
	public List<Object[]> Branch_Collect_Proid_SaleMan(String Proid) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		// String hql
		// ="select p from  PlanBrand as p where  p.planBrandsecondPlan = :secondPlan and p.planBrandType =:planBrandType";
		// return session.createQuery(hql).setString("secondPlan",
		// secondPlan).setInteger("planBrandType", planBrandType).list();
		//
		String hql = "select BraId,SaleMan,  ProId , sum(SaleAmt-PCashPayAmt)  from sale_daily where Proid='"
				+ Proid
				+ "' and saleamt<>0  group by BraId ,SaleMan, ProId  order by BraId";
		return session.createSQLQuery(hql).list();

	}

	// /2012-8-23统计SaleMan门店品牌销售汇总
	public List<Object[]> Branch_Collect_BrandId_SaleMan(String Proid) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		String hql = "select BraId, SaleMan, BrandId , sum(SaleAmt-PCashPayAmt)  from sale_daily where BrandId='"
				+ Proid
				+ "' and saleamt<>0  group by BraId ,SaleMan, BrandId  order by BraId";
		return session.createSQLQuery(hql).list();

	}

	// /2012-8-23统计门店品牌销售汇总
	public List<Object[]> Branch_Collect_BrandId(String Proid) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		String hql = "select BraId,  BrandId , sum(SaleAmt-PCashPayAmt)  from sale_daily where BrandId='"
				+ Proid
				+ "' and saleamt<>0  group by BraId , BrandId  order by BraId";
		return session.createSQLQuery(hql).list();

	}

	// //2012-8-23 插入门店单品销售汇总
	public void Insert_Collect_Proid(String BraId, String ProId,
			Integer productProjectId, Integer planBrandId, Double moneys,
			Integer planBrandType, String planBrandsecondPlan,
			Integer planBrandtaskType, Double planBrandunFinishedPoint,
			Double planBrandfinishedPoint, Integer planBrandfinishedType,
			Double planBrandfinishedQuota, Double planBrandfinishedPointQuota,
			Double planBrandunFinishedPointQuota, Double planBrandqverQuota,
			Double planBrandqverQuotaPoint) {

		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();

		String hql = "insert into sale_daily_temp  ( BraId,ProId,productProjectId,planBrandId,moneys,planBrandType,"
				+ " planBrandsecondPlan,planBrandtaskType,planBrandunFinishedPoint,planBrandfinishedPoint,"
				+ " planBrandfinishedType,planBrandfinishedQuota,planBrandfinishedPointQuota,planBrandunFinishedPointQuota,planBrandqverQuota,planBrandqverQuotaPoint "
				+ ")  values ('"
				+ BraId
				+ "' , '"
				+ ProId
				+ "' , "
				+ productProjectId
				+ " , "
				+ planBrandId
				+ " , "
				+ moneys
				+ " , "
				+ planBrandType
				+ " , '"
				+ planBrandsecondPlan
				+ "' , "
				+ planBrandtaskType
				+ " , "
				+ planBrandunFinishedPoint
				+ " , "
				+ planBrandfinishedPoint
				+ " , "
				+ planBrandfinishedType
				+ " , "
				+ planBrandfinishedQuota
				+ " , "
				+ planBrandfinishedPointQuota
				+ " , "
				+ planBrandunFinishedPointQuota
				+ " , "
				+ planBrandqverQuota + " , " + planBrandqverQuotaPoint + " )  ";

		// System.out.println("hql : " +hql); //
		// planBrandqverQuota,planBrandqverQuotaPoint

		session.createSQLQuery(hql).executeUpdate();
	}

	// //2012-8-23 插入门店品牌销售汇总
	public void Insert_Collect_BrandId(String BraId, String BrandId,
			Integer productProjectId, Integer planBrandId, Double moneys,
			Integer planBrandType, String planBrandsecondPlan,
			Integer planBrandtaskType, Double planBrandunFinishedPoint,
			Double planBrandfinishedPoint, Integer planBrandfinishedType,
			Double planBrandfinishedQuota, Double planBrandfinishedPointQuota,
			Double planBrandunFinishedPointQuota, Double planBrandqverQuota,
			Double planBrandqverQuotaPoint) {

		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();

		String hqls = "insert into sale_daily_temp  ( BraId,BrandId,productProjectId,planBrandId,moneys,planBrandType,"
				+ " planBrandsecondPlan,planBrandtaskType,planBrandunFinishedPoint,planBrandfinishedPoint,"
				+ " planBrandfinishedType,planBrandfinishedQuota,planBrandfinishedPointQuota,planBrandunFinishedPointQuota, planBrandqverQuota,planBrandqverQuotaPoint "
				+ ")  values ('"
				+ BraId
				+ "' , '"
				+ BrandId
				+ "' , "
				+ productProjectId
				+ " , "
				+ planBrandId
				+ " , "
				+ moneys
				+ " , "
				+ planBrandType
				+ " , '"
				+ planBrandsecondPlan
				+ "' , "
				+ planBrandtaskType
				+ " , "
				+ planBrandunFinishedPoint
				+ " , "
				+ planBrandfinishedPoint
				+ " , "
				+ planBrandfinishedType
				+ " , "
				+ planBrandfinishedQuota
				+ " , "
				+ planBrandfinishedPointQuota
				+ " , "
				+ planBrandunFinishedPointQuota
				+ ", "
				+ planBrandqverQuota + " , " + planBrandqverQuotaPoint + ")  ";

		session.createSQLQuery(hqls).executeUpdate();
	}

	// /////////////////////////////////

	// //2012-8-23 插入门店单品销售汇总
	public void Insert_Collect_Proid_SaleMan(String BraId, String SaleMan,
			String ProId, Integer productProjectId, Integer planBrandId,
			Double moneys, Integer planBrandType, String planBrandsecondPlan,
			Integer planBrandtaskType, Double planBrandunFinishedPoint,
			Double planBrandfinishedPoint, Integer planBrandfinishedType,
			Double planBrandfinishedQuota, Double planBrandfinishedPointQuota,
			Double planBrandunFinishedPointQuota, String operatingMonthId) {

		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();

		String hql = "insert into sale_daily_man  ( BraId,SaleMan,ProId,productProjectId,planBrandId,moneys,planBrandType, planBrandsecondPlan,planBrandtaskType,planBrandunFinishedPoint,planBrandfinishedPoint,"
				+ " planBrandfinishedType,planBrandfinishedQuota,planBrandfinishedPointQuota,planBrandunFinishedPointQuota ,operatingMonthId)  values ('"
				+ BraId
				+ "' ,'"
				+ SaleMan
				+ "' ,'"
				+ ProId
				+ "' , "
				+ productProjectId
				+ " , "
				+ planBrandId
				+ " , "
				+ moneys
				+ " , "
				+ planBrandType
				+ " , '"
				+ planBrandsecondPlan
				+ "' , "
				+ planBrandtaskType
				+ " , "
				+ planBrandunFinishedPoint
				+ " , "
				+ planBrandfinishedPoint
				+ " , "
				+ planBrandfinishedType
				+ " , "
				+ planBrandfinishedQuota
				+ " , "
				+ planBrandfinishedPointQuota
				+ " , "
				+ planBrandunFinishedPointQuota
				+ ",'"
				+ operatingMonthId
				+ "')";

		session.createSQLQuery(hql).executeUpdate();
	}

	// //2012-8-23 插入门店品牌销售汇总
	public void Insert_Collect_BrandId_SaleMan(String BraId, String SaleMan,
			String BrandId, Integer productProjectId, Integer planBrandId,
			Double moneys, Integer planBrandType, String planBrandsecondPlan,
			Integer planBrandtaskType, Double planBrandunFinishedPoint,
			Double planBrandfinishedPoint, Integer planBrandfinishedType,
			Double planBrandfinishedQuota, Double planBrandfinishedPointQuota,
			Double planBrandunFinishedPointQuota, String operatingMonthId) {

		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();

		String hqls = "insert into sale_daily_man  ( BraId,SaleMan,BrandId,productProjectId,planBrandId,moneys,planBrandType,  planBrandsecondPlan,planBrandtaskType,planBrandunFinishedPoint,planBrandfinishedPoint,"
				+ " planBrandfinishedType,planBrandfinishedQuota,planBrandfinishedPointQuota,planBrandunFinishedPointQuota , operatingMonthId)  values ('"
				+ BraId
				+ "' , '"
				+ SaleMan
				+ "' ,'"
				+ BrandId
				+ "' , "
				+ productProjectId
				+ " , "
				+ planBrandId
				+ " , "
				+ moneys
				+ ","
				+ planBrandType
				+ " , '"
				+ planBrandsecondPlan
				+ "' , "
				+ planBrandtaskType
				+ " , "
				+ planBrandunFinishedPoint
				+ " , "
				+ planBrandfinishedPoint
				+ " , "
				+ planBrandfinishedType
				+ " , "
				+ planBrandfinishedQuota
				+ " , "
				+ planBrandfinishedPointQuota
				+ " , "
				+ planBrandunFinishedPointQuota
				+ ",'"
				+ operatingMonthId
				+ "')";

		session.createSQLQuery(hqls).executeUpdate();
	}

	// //2012-8-28 通过PlanBrandRelationTable 统计门店单品销售汇总
	public void Insert_Collect_Proid_Repeat_SaleMan(String BraId,
			String SaleMan, String ProId, Integer productProjectId,
			Integer planBrandId, Double moneys, Integer planBrandType,
			String planBrandsecondPlan, Integer planBrandtaskType,
			Double planBrandunFinishedPoint, Double planBrandfinishedPoint,
			Integer planBrandfinishedType, Double planBrandfinishedQuota,
			Double planBrandfinishedPointQuota,
			Double planBrandunFinishedPointQuota, String operatingMonthId) {

		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();

		String hql = "insert into sale_daily_man  ( BraId,SaleMan,ProId,productProjectId,planBrandId,moneys,planBrandType, planBrandsecondPlan,planBrandtaskType,planBrandunFinishedPoint,planBrandfinishedPoint,"
				+ " planBrandfinishedType,planBrandfinishedQuota,planBrandfinishedPointQuota,planBrandunFinishedPointQuota ,operatingMonthId)  values ('"
				+ BraId
				+ "' ,'"
				+ SaleMan
				+ "' ,'"
				+ ProId
				+ "' , "
				+ productProjectId
				+ " , "
				+ planBrandId
				+ " , "
				+ moneys
				+ " , "
				+ planBrandType
				+ " , '"
				+ planBrandsecondPlan
				+ "' , "
				+ planBrandtaskType
				+ " , "
				+ planBrandunFinishedPoint
				+ " , "
				+ planBrandfinishedPoint
				+ " , "
				+ planBrandfinishedType
				+ " , "
				+ planBrandfinishedQuota
				+ " , "
				+ planBrandfinishedPointQuota
				+ " , "
				+ planBrandunFinishedPointQuota
				+ ",'"
				+ operatingMonthId
				+ "')";

		session.createSQLQuery(hql).executeUpdate();
	}

	// //////////////////////////////

	// 无单品任务的品牌 Not_In_IsAddBrandWages
	public List<ProductProject> Not_In_IsAddBrandWages(String operatingMonthId) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();

		// String hql =
		// "select p from ProductProject p where p.operatingMonth.operatingMonthId= :operatingMonthId and p.isAddBrandWages =:isAddBrandWages and p.projectStatus=0";

		String hql = "select p from ProductProject p where p.operatingMonth.operatingMonthId= :operatingMonthId and p.projectStatus=0 and p.productProjectId not in (select distinct (t.productProject.productProjectId) from PlanBrand t where t.planBrandId in ( select y.planBrand.planBrandId from PlanBrandRelationTable y) )";

		return session.createQuery(hql)
				.setString("operatingMonthId", operatingMonthId).list();
	}

	// 有单品任务的品牌 In_IsAddBrandWages

	public List<ProductProject> In_IsAddBrandWages(String operatingMonthId) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();

		// String hql =
		// "select p from ProductProject p where p.operatingMonth.operatingMonthId= :operatingMonthId and p.isAddBrandWages =:isAddBrandWages and p.projectStatus=0";

		String hql = "select p from ProductProject p where p.operatingMonth.operatingMonthId= :operatingMonthId and p.projectStatus=0 and p.productProjectId in (select distinct (t.productProject.productProjectId) from PlanBrand t where t.planBrandId in ( select y.planBrand.planBrandId from PlanBrandRelationTable y) )";

		return session.createQuery(hql)
				.setString("operatingMonthId", operatingMonthId).list();
	}

	// //2012-8-25统计方案——门店——完成比例

	public List<Object[]> Project_BraId_Percentage(Integer productProjectId,
			Integer planBrandtaskType) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		// String hql =
		// "select p from ProductProject p where p.operatingMonth.operatingMonthId= :operatingMonthId and p.isAddBrandWages =:isAddBrandWages and p.projectStatus=0";
		// String hql =
		// "select p from ProductProject p where p.operatingMonth.operatingMonthId= :operatingMonthId and p.projectStatus=0 and p.productProjectId in (select distinct (t.productProject.productProjectId) from PlanBrand t where t.planBrandId in ( select y.planBrand.planBrandId from PlanBrandRelationTable y) )";
		// select braid,sum(moneys) from Sale_Daily_Temp p where
		// p.productProjectId ='115' and p.planBrandtaskType=1 group by braid
		// order by braid
		// String hql =
		// "select p from SaleDailyTemp p where p.productProject_t.productProjectId =:productProjectId and p.planBrandtaskType =:planBrandtaskType order by p.id.branch.braId";
		String hql = "select p.id.branch.braId ,sum(p.moneys) from SaleDailyTemp p where p.productProject_t.productProjectId =:productProjectId  and  p.planBrandtaskType =:planBrandtaskType group by p.id.branch.braId";
		return session.createQuery(hql)
				.setInteger("productProjectId", productProjectId)
				.setInteger("planBrandtaskType", planBrandtaskType).list();
	}

	// //2012-8-27统计方案——门店——重复单品金额

	public List<Object[]> FindProject_BraId_Repeat_ProId_Store_Count(
			Integer productProjectId, String BraId, Integer store_CountType) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();

		String hql = "select p.branch.braId ,sum(p.Repeat_ProId_saleCount),p.store_ProId from Store_Count p where  p.branch.braId =:BraId  and  p.store_CountType =:store_CountType and p.productProject.productProjectId =:productProjectId   group by p.branch.braId ,p.store_ProId";
		return session.createQuery(hql)
				.setInteger("productProjectId", productProjectId)
				.setInteger("store_CountType", store_CountType)
				.setString("BraId", BraId).list();
	}

	// //2012-8-27统计方案——门店——完成比例品牌金额

	public List<Object[]> Project_BraId_Sub_Repeat_ProId_Percentage(
			Integer productProjectId, Integer planBrandtaskType) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();

		// String hql =
		// "select p.id.branch.braId ,sum(p.moneys) , p.planBrandqverQuota , p.planBrandqverQuotaPoint from SaleDailyTemp p where p.productProject_t.productProjectId =:productProjectId and  p.planBrandtaskType =:planBrandtaskType group by p.id.branch.braId , p.planBrandqverQuota , p.planBrandqverQuotaPoint";

		String Sql = "select BraId,sum(moneys),planBrandqverQuota,planBrandqverQuotaPoint from sale_daily_temp where productProjectId="
				+ productProjectId
				+ " and planBrandtaskType="
				+ planBrandtaskType + " group by BraId,planBrandqverQuota,planBrandqverQuotaPoint";

		// return session.createQuery(hql)
		// .setInteger("productProjectId", productProjectId)
		// .setInteger("planBrandtaskType", planBrandtaskType).list();

		return session.createSQLQuery(Sql).list();

	}

	// //2012-8-23 插入门店
	//
	//
	// public void Insert_Store_Count_Brach(String BraId,String SaleMan ,String
	// BrandId,
	// Integer productProjectId, Integer planBrandId, Double moneys
	// ) {
	//
	// Session session = getHibernateTemplate().getSessionFactory()
	// .getCurrentSession();
	//
	// String hqls =
	// "insert into sale_daily_man  ( BraId,SaleMan,BrandId,productProjectId,planBrandId,moneys,planBrandType )  values ('"
	// + BraId
	// + "' , '"
	// + SaleMan
	// + "' ,'"
	// + BrandId
	// + "' , "
	// + productProjectId
	// + " , "
	// + planBrandId
	// + " , "
	// + moneys
	// + ",1)  ";
	//
	// session.createSQLQuery(hqls).executeUpdate();
	// }

	// //2012-8-23 插入 门店完成方案比例
	public void Insert_Store_Count_Brach(Integer proProjectid,
			String operatingMonthId, String BraId, Integer planMoneyId,
			double planMoneyCount, double saleCount, double complatePercent,
			Integer store_CountType, String percentStr,double planBrandqverQuota, double planBrandqverQuotaPoint) {

		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();

		String hqls = "insert into store_Count(productProjectId,operatingMonthId,BraId,planMoneyId,planMoneyCount,saleCount,complatePercent,store_CountType,percentStr,planBrandqverQuota,planBrandqverQuotaPoint) values ("
				+ proProjectid
				+ ",'"
				+ operatingMonthId
				+ "','"
				+ BraId
				+ "',"
				+ planMoneyId
				+ ","
				+ planMoneyCount
				+ ","
				+ saleCount
				+ ","
				+ complatePercent
				+ ","
				+ store_CountType
				+ ",'"
				+ percentStr
				+ "',"
				+ planBrandqverQuota + "," + planBrandqverQuotaPoint + ")";

		// System.out.println("HQlS :" + hqls);

		session.createSQLQuery(hqls).executeUpdate();

		// TODO Auto-generated method stub

	}

	// //2012-8-23 统计 门店单品金额
	public void Insert_Brach_Repeat_ProId(Integer proProjectid,
			String operatingMonthId, String BraId, Integer planMoneyId,
			double planMoneyCount, double Repeat_ProId_saleCount,
			double complatePercent, Integer store_CountType, String percentStr,
			String ProId,double planBrandqverQuota, double planBrandqverQuotaPoint) {

		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();

		String hqls = "insert into store_Count(productProjectId,operatingMonthId,BraId,planMoneyId,planMoneyCount,Repeat_ProId_saleCount,complatePercent,store_CountType,percentStr,store_ProId,planBrandqverQuota,planBrandqverQuotaPoint) values ("
				+ proProjectid
				+ ",'"
				+ operatingMonthId
				+ "','"
				+ BraId
				+ "',"
				+ planMoneyId
				+ ","
				+ planMoneyCount
				+ ","
				+ Repeat_ProId_saleCount
				+ ","
				+ complatePercent
				+ ","
				+ store_CountType
				+ ",'"
				+ percentStr
				+ "'"
				+ ",'"
				+ ProId
				+ "',"
				+ planBrandqverQuota + "," + planBrandqverQuotaPoint + ")";

		// System.out.println("HQlS :" + hqls);

		session.createSQLQuery(hqls).executeUpdate();

		// TODO Auto-generated method stub

	}

//	public List<PlanBrandRelationTable> findByplanBrandid(Integer planBrandId) {
//		Session session = getHibernateTemplate().getSessionFactory()
//				.getCurrentSession();
//		String hql = "select p from  PlanBrandRelationTable as p where  p.planBrand.planBrandId = :planBrandId  ";
//		return session.createQuery(hql).setInteger("planBrandId", planBrandId)
//				.list();
//	}
	
	//2012-9-25
	public List<Object[]> findByplanBrandid(Integer planBrandId) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		String hql = "select p.proId,p.BrandId from  planBrandRelationTable as p where  p.planBrandId ="+planBrandId ;
		 
		
		return session.createSQLQuery(hql).list();
		
	}
	
	
	
	
	
	
	

	// //2012-8-25 统计方案——门店——重复数据

	public List<Object[]> Project_BraId_Repeat_ProId(Integer productProjectId,
			String ProId, Integer planBrandtaskType) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		// String hql =
		// "select p from ProductProject p where p.operatingMonth.operatingMonthId= :operatingMonthId and p.isAddBrandWages =:isAddBrandWages and p.projectStatus=0";
		// String hql =
		// "select p from ProductProject p where p.operatingMonth.operatingMonthId= :operatingMonthId and p.projectStatus=0 and p.productProjectId in (select distinct (t.productProject.productProjectId) from PlanBrand t where t.planBrandId in ( select y.planBrand.planBrandId from PlanBrandRelationTable y) )";
		// select braid,sum(moneys) from Sale_Daily_Temp p where
		// p.productProjectId ='115' and p.planBrandtaskType=1 group by braid
		// order by braid
		// String hql =
		// "select p from SaleDailyTemp p where p.productProject_t.productProjectId =:productProjectId and p.planBrandtaskType =:planBrandtaskType order by p.id.branch.braId";

		String hql = "select BraId, sum(moneys) from sale_daily_temp  where ProId='"
				+ ProId
				+ "' and productProjectId="
				+ productProjectId
				+ " and  planBrandtaskType="
				+ planBrandtaskType
				+ "  group by BraId";
		// String hql =
		// "select p.id.branch.braId ,sum(p.moneys) from SaleDailyTemp p where p.productProject_t.productProjectId =:productProjectId and  p.planBrandtaskType =:planBrandtaskType and  p.id.product.proId =:ProId group by p.id.branch.braId";
		// return session.createQuery(hql).setString("ProId", ProId)
		// .setInteger("productProjectId", productProjectId)
		// .setInteger("planBrandtaskType", planBrandtaskType).list();

		// System.out.println("HQlS :" + hql);
		return session.createSQLQuery(hql).list();
	}

	// //2012-8-23 插入 有单品任务的品牌方案比例
	public void Insert_Store_CountBrach(Integer proProjectid,
			String operatingMonthId, String BraId, Integer planMoneyId,
			double planMoneyCount, double saleCount, double complatePercent,
			Integer store_CountType, String percentStr,
			double saleCount_repeat, String store_ProId,
			double planBrandqverQuota, double planBrandqverQuotaPoint) {

		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();

		String hqls = "insert into store_Count(productProjectId,operatingMonthId,BraId,planMoneyId,planMoneyCount,saleCount,complatePercent,store_CountType,percentStr,Repeat_ProId_saleCount,store_ProId,planBrandqverQuota,planBrandqverQuotaPoint) values ("
				+ proProjectid
				+ ",'"
				+ operatingMonthId
				+ "','"
				+ BraId
				+ "',"
				+ planMoneyId
				+ ","
				+ planMoneyCount
				+ ","
				+ saleCount
				+ ","
				+ complatePercent
				+ ","
				+ store_CountType
				+ ",'"
				+ percentStr
				+ "',"
				+ saleCount_repeat
				+ ",'"
				+ store_ProId
				+ "',"
				+ planBrandqverQuota + "," + planBrandqverQuotaPoint + ")";

		// System.out.println("HQlS :" + hqls);

		session.createSQLQuery(hqls).executeUpdate();

		// TODO Auto-generated method stub

	}

	// ///2012-8-27通过operatingMonthId 返回 sale_daily_man
	public List<SaleDailyMan> findSaleDailyManByOperatingMonthId(
			String operatingMonthId, Integer planBrandtaskType) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		String hql = "select p from SaleDailyMan p where p.operatingMonth.operatingMonthId = :operatingMonthId  and p.planBrandtaskType =:planBrandtaskType";
		return session.createQuery(hql)
				.setString("operatingMonthId", operatingMonthId)
				.setInteger("planBrandtaskType", planBrandtaskType).list();
	}

	// /////
	public List<Store_Count> FindStore_CountByProjectId_BraId(
			Integer ProjectId, String BraId) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();

		String hql = "select p from Store_Count p where p.productProject.productProjectId =:ProjectId and p.branch.braId=:BraId and p.store_CountType<>3";
		return session.createQuery(hql).setInteger("ProjectId", ProjectId)
				.setString("BraId", BraId).list();
	}
	
	
	
	
	

	// //2012-8-23 插入 有单品任务的品牌方案比例
	public void Insert_RewardBrand(String BraId, Integer proProjectid,
			String operatingMonthId, String SaleMan, double saleCount,
			double saleWages, double complatePercent, double saleCount_repeat,
			double planMoneyCount, Integer planMoneyId,

			Integer planBrandId, String ProId, String BrandId,
			String percentStr, Integer store_CountType) {
		// public void Insert_RewardBrand(String BraId, Integer proProjectid,
		// String operatingMonthId, String SaleMan, double saleCount,
		// double saleWages, double complatePercent,
		// double planMoneyCount, Integer planMoneyId,
		// Integer planBrandId,
		// String percentStr, Integer store_CountType) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();

		// String hqls =
		// "insert into rewardBrand( planBrandId,saleWages,SaleMan,productProjectId,operatingMonthId,BraId,planMoneyId,planMoneyCount,saleCount,complatePercent,store_CountType,percentStr ) values ("
		// + planBrandId
		// + ","
		// + saleWages
		// + ",'"
		// + SaleMan
		// + "',"
		// + proProjectid
		// + ",'"
		// + operatingMonthId
		// + "','"
		// + BraId
		// + "',"
		// + planMoneyId
		// + ","
		// + planMoneyCount
		// + ","
		// + saleCount
		// + ","
		// + complatePercent
		// + ","
		// + store_CountType
		// + ",'"
		// + percentStr
		// + "')";

		String hqls = "insert into rewardBrand(ProId,BrandId,planBrandId,saleWages,SaleMan,productProjectId,operatingMonthId,BraId,planMoneyId,planMoneyCount,saleCount,complatePercent,store_CountType,percentStr,Repeat_ProId_saleCount) values ('"
				+ ProId
				+ "','"
				+ BrandId
				+ "',"
				+ planBrandId
				+ ","
				+ saleWages
				+ ",'"
				+ SaleMan
				+ "',"
				+ proProjectid
				+ ",'"
				+ operatingMonthId
				+ "','"
				+ BraId
				+ "',"
				+ planMoneyId
				+ ","
				+ planMoneyCount
				+ ","
				+ saleCount
				+ ","
				+ complatePercent
				+ ","
				+ store_CountType
				+ ",'"
				+ percentStr
				+ "',"
				+ saleCount_repeat + ")";

		// System.out.println("HQlS rewardBrand:" + hqls);

		session.createSQLQuery(hqls).executeUpdate();

		// TODO Auto-generated method stub

	}

	// //2012-8-23 插入 无单品任务的品牌方案比例
	public List<SaleDailyMan> findSaleDailyManByProId_BraId_Project(
			String BraId, Integer ProjectId, String ProId, String SaleMan) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		String hql = "select p from  SaleDailyMan as p where  p.id.product.proId =:proId and p.id.branch.braId =:BraId and p.productProject_m.productProjectId =:ProjectId and p.employee_m.empId =:SaleMan";
		return session.createQuery(hql).setString("BraId", BraId)
				.setString("ProId", ProId).setString("SaleMan", SaleMan)
				.setInteger("ProjectId", ProjectId).list();
	}

	public void Insert_RewardBrand(String BraId, Integer projectId,
			String operatingMonthId, String SaleMan, double reward,
			double repeat_ProId_saleCount, Integer planBrandId, String ProId,
			String BrandId, Integer store_CountType) {

		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();

		String hqls = "insert into rewardBrand(ProId,BrandId,planBrandId,saleWages,SaleMan,productProjectId,operatingMonthId,BraId,store_CountType,Repeat_ProId_saleCount) values ('"
				+ ProId
				+ "','"
				+ BrandId
				+ "',"
				+ planBrandId
				+ ","
				+ reward
				+ ",'"
				+ SaleMan
				+ "',"
				+ projectId
				+ ",'"
				+ operatingMonthId
				+ "','"
				+ BraId
				+ "',"
				+ store_CountType
				+ ","
				+ repeat_ProId_saleCount + ")";

		// System.out.println("HQlS rewardBrand:" + hqls);

		session.createSQLQuery(hqls).executeUpdate();

	}

	// ///
	public List<PlanBrandRelationTable> findByOperatingMonthid(
			String OperatingMonthId) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		String hql = "select p from  PlanBrandRelationTable as p where  p.operatingMonth.operatingMonthId = :OperatingMonthId";
		return session.createQuery(hql)
				.setString("OperatingMonthId", OperatingMonthId).list();
	}

	public void Insert_Collect_Proid_SaleMan(String BraId, String saleMan,
			String ProId, Integer projectId, String brandId, Double moneys,
			String operatingMonthId, Integer planBrandId, Integer planBrandType) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		String hql = "insert into sale_daily_man  ( BraId,SaleMan,ProId,BrandId,productProjectId,moneys,operatingMonthId,planBrandId,planBrandType )  values ('"
				+ BraId
				+ "','"
				+ saleMan
				+ "','"
				+ ProId
				+ "','"
				+ brandId
				+ "',"
				+ projectId
				+ ","
				+ moneys
				+ ",'"
				+ operatingMonthId
				+ "'," + planBrandId + "," + planBrandType + " )";

		session.createSQLQuery(hql).executeUpdate();

		// TODO Auto-generated method stub

	}

	public List<Object[]> FindRewardBrandBy_OperatingMonthId(
			String OperatingMonthId) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		// String hql =
		// "select p from  SaleDailyMan as p where  p.id.product.proId =:proId and p.id.branch.braId =:BraId and p.productProject_m.productProjectId =:ProjectId and p.employee_m.empId =:SaleMan";
		// return session.createQuery(hql).setString("BraId", BraId)
		// .setString("ProId", ProId).setString("SaleMan", SaleMan)
		// .setInteger("ProjectId", ProjectId).list();

		// String hql =
		// "select p from  RewardBrand p.operatingMonth.operatingMonthId = :OperatingMonthId order by p.branch.braId";
		// braName
		// String hql =
		// "select p.branch.braName,p.employee.empName,p.operatingMonth.operatingMonthName,p.productProject.productProjectId,p.saleWages, from  RewardBrand p where p.operatingMonth.operatingMonthId = :OperatingMonthId order by p.branch.braId";
		String hql = "select p.branch.braName,p.employee.empName,p.operatingMonth.operatingMonthName,sum(p.saleWages) from RewardBrand p where p.operatingMonth.operatingMonthId = :OperatingMonthId group by p.branch.braName ,p.employee.empName,p.operatingMonth.operatingMonthName order by p.branch.braName";

		return session.createQuery(hql)
				.setString("OperatingMonthId", OperatingMonthId).list();
	}

	public void delete_table() {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		session.createSQLQuery("{call delete_table()}").executeUpdate();

	}

	// //2012-9-13 alpha 统计超额
	public List<Store_Count> Count_QverQuota(String OperatingMonthId) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		String hql = "select p from Store_Count p where p.operatingMonth.operatingMonthId =:OperatingMonthId order by p.branch.braId ";
		return session.createQuery(hql)
				.setString("OperatingMonthId", OperatingMonthId).list();

	}

	// ///2012-9-14 查询超额表
	public void Insert_QverQuota(Integer productProjectId, Double saleCount,
			String operatingMonthId, Integer planMoneyId,
			Double complatePercent, String percentStr, String braid,
			Integer store_CountType, Double planMoneyCount,
			Double complatePercentPart, String store_ProId,
			Double Repeat_ProId_saleCount, Double planBrandqverQuota,
			Double planBrandqverQuotaPoint, Double saleQverQuota) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		String hql = "insert into qverQuota  ( productProjectId,saleCount,operatingMonthId,planMoneyId,complatePercent,percentStr,braid,store_CountType,planMoneyCount,complatePercentPart,store_ProId,Repeat_ProId_saleCount,planBrandqverQuota,planBrandqverQuotaPoint,saleQverQuota )  values ("
				+ productProjectId
				+ ","
				+ saleCount
				+ ",'"
				+ operatingMonthId
				+ "',"
				+ planMoneyId
				+ ","
				+ complatePercent
				+ ",'"
				+ percentStr
				+ "','"
				+ braid
				+ "',"
				+ store_CountType
				+ ","
				+ planMoneyCount
				+ ","
				+ complatePercentPart
				+ ",'"
				+ store_ProId
				+ "',"
				+ Repeat_ProId_saleCount
				+ ","
				+ planBrandqverQuota
				+ ","
				+ planBrandqverQuotaPoint
				+ ","
				+ saleQverQuota + ")";

		session.createSQLQuery(hql).executeUpdate();

		// TODO Auto-generated method stub

	}

	public List<Object[]> Show_QverQuota(String operatingMonthId, String BraId) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();

		// String hql =
		// "select BraId,SaleMan,  ProId , sum(SaleAmt-PCashPayAmt)  from sale_daily where Proid='"
		// + Proid
		// + "' and saleamt<>0  group by BraId ,SaleMan, ProId  order by BraId";
		// return session.createSQLQuery(hql).list();

		String sql = "select p.productProjectName , q.saleQverQuota  from productproject p inner join qverQuota  q on p.productProjectId=q.productProjectId  inner join branch b  on q.braid=b.BraId  where b.BraId='"
				+ BraId
				+ "' and q.operatingMonthId='"
				+ operatingMonthId
				+ "' ";
		return session.createSQLQuery(sql).list();
	}

	// 2012-9-14 查询有门店销售的员工
	public List<Object[]> select_SaleDailyMan(String operatingMonthId,
			String BraId) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		// String sql =
		// "select distinct e.EmpId ,e.EmpName from sale_daily_man s inner join employee  e on s.BraId=e.BraId  where s.operatingMonthId='"+operatingMonthId+"' and s.BraId='"+BraId+"' order by EmpId "
		// ;
		String sql = " select distinct s.PersonalId,TRUENAME,NAMES,EmpId,PERSONID from CPCJOBCODE c " +
				"inner join PSNACCOUNT p  on c.JOBCODEID=p.JOBCODE " +
				"inner join employee s   on s.PersonalId=p.EMPLOYEEID " +
				"inner join sale_daily_man m on m.BraId=s.BraId and m.saleman=s.EmpId " +
				"where  MEMO='门店' and NAMES not like '%店%'  and NAMES not like '%星%' and m.operatingMonthId='"+operatingMonthId+"' and m.BraId='"+BraId+"'";
		// return session.createQuery(hql).setString("operatingMonthId",
		// operatingMonthId).setString("BraId", BraId).list();
		return session.createSQLQuery(sql).list();

	}

	
	
	
	
	
	public void saveRewardQverQuota (
			String[] checkboxVarstrForString ,String BraId,String operatingMonthId) {

		// session记得关闭
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		// Transaction tr = session.beginTransaction();
		
		//System.out.println("length "+checkboxVarstrForString.length);
		
		
		Connection connection = session.connection();
		String sql = "insert into rewardQverQuota(EmpId,BraId,operatingMonthId,PERSONID,qverQuotaMoney) values(?,?,?,?,?)";
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);		
			for (int i = 0; i <1; i++) {	
				
				
			// System.out.println(checkboxVarstrForString[2]+" "+BraId+" "+operatingMonthId+" "+checkboxVarstrForString[4]+" "+checkboxVarstrForString[3]);				
				prepareStatement.setString(1, checkboxVarstrForString[2]);///EmpId
				prepareStatement.setString(2, BraId);
				prepareStatement.setString(3, operatingMonthId);
				prepareStatement.setString(4, checkboxVarstrForString[4]);   ///PERSONID
				prepareStatement.setDouble(5, Double.valueOf(checkboxVarstrForString[3]));///qverQuotaMoney
				prepareStatement.addBatch();// 把sql放到一个容器中
			}

			
			
			
			 prepareStatement.executeBatch();
			// tr.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		session.close();

	}
	
	
	
	
	
	
	
	
	public List<RewardQverQuota> Show_Reward_QverQuota(String operatingMonthId, String BraId) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		String hql = "select p from RewardQverQuota p where p.operatingMonth.operatingMonthId =:operatingMonthId and p.branch.braId =:braId";		
		return session.createQuery(hql).setString("operatingMonthId", operatingMonthId).setString("braId", BraId).list();
		 
	}
	
	
	
	
	
//	public  RewardQverQuota  Find_Reward_QverQuota(Integer ids) {
//		Session session = getHibernateTemplate().getSessionFactory()
//				.getCurrentSession();
//		String hql = "select p from RewardQverQuota p where p.ids =:ids ";		
//		return session.createQuery(hql).setInteger("ids",ids).list();
//	}
	
	
	public RewardQverQuota findById_RewardQverQuota(java.lang.Integer id) {
		log.debug("getting StoreCount instance with id: " + id);
		try {
			RewardQverQuota instance = (RewardQverQuota) getHibernateTemplate().get(
					"com.gialen.model.RewardQverQuota", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
 
	
	
	public RewardQverQuota merge_RewardQverQuota(RewardQverQuota detachedInstance) {
		log.debug("merging RewardQverQuota instance");
		try {
			RewardQverQuota result = (RewardQverQuota) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
 
	////alpha 2012-9-20  查询sale_daily是否存在 组合的明细
	public List<ProProjectRelationTableGroup> RelationTableGroup(String proid) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();

		// String hql =
		// "select p from ProductProject p where p.operatingMonth.operatingMonthId= :operatingMonthId and p.isAddBrandWages =:isAddBrandWages and p.projectStatus=0";

		String hql = "select p from ProProjectRelationTableGroup p where p.product.proId =:proid ";

		return session.createQuery(hql).setString("proid", proid).list();
	}
	
	
//	////alpha 2012-9-20 通过branid 统计组合与品牌方案不重复的proid
//	
	public List<Object[]> findTableGroupByOperatingMonthId_proid(String operatingMonthId) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		// String hql =
		// "select p from ProductProject p where p.operatingMonth.operatingMonthId= :operatingMonthId and p.isAddBrandWages =:isAddBrandWages and p.projectStatus=0";
		String sql = " select distinct(proid) from proProjectRelationTable_Group where productProjectId in (select productProjectId from productProject where projectStatus=0 and projectType=2 and operatingMonthId='"+operatingMonthId+"') and brandid  not in (select distinct (productbrandid) from planBrand where planBrandmainPlan in ( select productProjectId from productProject where projectStatus=0 and projectType=1 and operatingMonthId='"+operatingMonthId+"'))";
		return session.createSQLQuery(sql).list(); 
	
	}
	
	
	
 
	
	
	
	
	
	
	
	////2012-9-21 alpha   通过proid 统计sale_daily表   saleid不重复记录   
	public List<Object[]> findSale_daily_saleid_byproid(String sql_proid_list) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		 	String sql = "select distinct (saleid) ,braid from sale_daily  where "+  sql_proid_list +" order by braid ";
		return session.createSQLQuery(sql).list(); 
	
	}
	

	public List<Object[]> findSale_dailybyproid(String saleid_braid_sql) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		 	String sql = "select braid,saleman,proid,saleid ,sum(saleqty) as num from sale_daily  where "+   saleid_braid_sql   + " group by braid,saleman,proid,saleid ";
		return session.createSQLQuery(sql).list(); 
	
	}
	
	
	///2012-9-25通过ProductProject的projectType 查询 所以不重复proid
		public List<Object[]> findTableGroupbyprojectType(
				String operatingMonthId, int projectType,int isAddBrandWages) {
			
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			String hql="SELECT distinct(p.ProId) FROM  proProjectRelationTable_Group p  inner join  productProject j on p.productProjectId=j.productProjectId and j.projectType="+isAddBrandWages+" and j.projectType='"+projectType+"' and j.projectStatus=0 and j.operatingMonthId='"+operatingMonthId+"'";
			return session.createSQLQuery(hql).list(); 
//			String hql ="from ProProjectRelationTableGroup as p where p.operatingMonth.operatingMonthId = :operatingMonthId and " +
//					"p.projectType = :projectType and p.projectStatus = 0 order by p.productProjectId";
//			return session.createQuery(hql)  //.setCacheable(true)
//				.setString("operatingMonthId",operatingMonthId.trim())
//						.setInteger("projectType",projectType).list();
			
		}
	
	
}