package com.gialen.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.Branch;
import com.gialen.model.PlanBrand;
import com.gialen.model.ProductBrand;

/**
 * A data access object (DAO) providing persistence and search support for
 * PlanBrand entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.gialen.model.PlanBrand
 * @author MyEclipse Persistence Tools
 */

public class PlanBrandDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(PlanBrandDAO.class);
	// property constants
	public static final String PLAN_BRAND_NAME = "planBrandName";

	protected void initDao() {
		// do nothing
	}

	public void save(PlanBrand transientInstance) {
		log.debug("saving PlanBrand instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PlanBrand persistentInstance) {
		log.debug("deleting PlanBrand instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PlanBrand findById(java.lang.Integer id) {
		log.debug("getting PlanBrand instance with id: " + id);
		try {
			PlanBrand instance = (PlanBrand) getHibernateTemplate().get(
					"com.gialen.model.PlanBrand", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PlanBrand instance) {
		log.debug("finding PlanBrand instance by example");
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
		log.debug("finding PlanBrand instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from PlanBrand as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPlanBrandName(Object planBrandName) {
		return findByProperty(PLAN_BRAND_NAME, planBrandName);
	}

	public List findAll() {
		log.debug("finding all PlanBrand instances");
		try {
			String queryString = "from PlanBrand";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PlanBrand merge(PlanBrand detachedInstance) {
		log.debug("merging PlanBrand instance");
		try {
			PlanBrand result = (PlanBrand) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PlanBrand instance) {
		log.debug("attaching dirty PlanBrand instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PlanBrand instance) {
		log.debug("attaching clean PlanBrand instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PlanBrandDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PlanBrandDAO) ctx.getBean("PlanBrandDAO");
	}

	public List<PlanBrand> findThisMonthArrangeBrand(String operatingMonthId) {
		String hql = "from  PlanBrand as p where p.planBrandId like :operatingMonthId and " +
				"p.planBrandType = 0";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql).setString("operatingMonthId", operatingMonthId+0+"%");
		return query.setCacheable(true).list();
	}
	
	//查询所有品牌大类
	public List<ProductBrand> findAllBigBrand() {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			String hql ="select new ProductBrand(id,brandId,brandName) from ProductBrand as p where p.level = 1";
			return session.createQuery(hql).setCacheable(true).list();
		}
	
	

	// //////////2012-8-14
	public List<Branch> findArrangeBranch() {
		String hql = "select p from  Branch as p where p.braType=0 and p.status not in (8,9)  ";
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();

		Query query = session.createQuery(hql);
		return query.setCacheable(true).list();
	}

	// //////////////2012-8-16 通过ProjectId planBrandType 查询品牌方案
	public List<PlanBrand> findPlanBrandByProductProjectId_planBrandType(
			String productProjectId, int planBrandType) {
		String hql = "select p from  PlanBrand as p where p.planBrandType =:planBrandType and  p.productProject.productProjectId = :productProjectId ";
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		return session.createQuery(hql)
				.setString("productProjectId", productProjectId)
				.setInteger("planBrandType", planBrandType).list();

		// "p.planBrandType = 0";
		// Session session =
		// getHibernateTemplate().getSessionFactory().getCurrentSession();
		// Query query = session.createQuery(hql).setString("operatingMonthId",
		// operatingMonthId+0+"%");
		// return query.setCacheable(true).list();
	}

	//根据项目id和	 planBrandType 0 底层单品 1搜索出的列表 2综合品牌表 查找:门店预计、品牌预计额  计划综合品牌表
	public List<PlanBrand> findByPlanBrandType_Id(Integer projectId,
			Integer planBrandType) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		String hql = "select p from PlanBrand as p " +
			"where  p.productProject.productProjectId = :projectId and " +
				"p.planBrandType =:planBrandType";
		return session.createQuery(hql).setInteger("projectId", projectId)
				.setInteger("planBrandType", planBrandType).list();
	}

	
	
	
	public List<PlanBrand> findByPlanBrand_Id_SQL(Integer projectId,
			Integer planBrandType) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		String hql = "select p from  PlanBrand as p where  p.productProject.productProjectId = :projectId and p.planBrandType =:planBrandType";
		return session.createQuery(hql).setInteger("projectId", projectId)
				.setInteger("planBrandType", planBrandType).list();
	}
	
	//////alpha 2012-8-24  通过projectId 取 PlanBrand
	public List<PlanBrand> findByPlanBrandType_Id(Integer projectId) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		String hql = "select p from  PlanBrand as p where  p.productProject.productProjectId = :projectId ";
		return session.createQuery(hql).setInteger("projectId", projectId).list();
	}
	
	
	
	
	
	public List<PlanBrand> findByplanBrandmainPlan(Integer projectId) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		String hql = "select p from  PlanBrand as p where  p.productProject.productProjectId = :projectId  ";
		return session.createQuery(hql).setInteger("projectId", projectId)
				.list();
	}

	public List<PlanBrand> findByplanBrandsecondPlan(String secondPlan,
			Integer planBrandType) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		String hql = "select p from  PlanBrand as p where  p.planBrandsecondPlan = :secondPlan and p.planBrandType =:planBrandType";
		return session.createQuery(hql).setString("secondPlan", secondPlan)
				.setInteger("planBrandType", planBrandType).list();
	}

	
	
	////通过ProductProjectId,secondPlan planBrandType
	public List<PlanBrand> findByplanBrandsecondPlan(Integer ProductProjectId,String secondPlan,
			Integer planBrandType) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		String hql = "select p from  PlanBrand as p where  p.planBrandsecondPlan = :secondPlan and p.planBrandType =:planBrandType and p.productProject.productProjectId =:ProductProjectId";
		return session.createQuery(hql).setString("secondPlan", secondPlan).setInteger("ProductProjectId", ProductProjectId)
				.setInteger("planBrandType", planBrandType).list();
	}
	
	
	
	
	
	
	////
	public List<PlanBrand> findByplanBrandsecondPlan(Integer projectId,String secondPlan) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		String hql = "select p from  PlanBrand as p where  p.planBrandsecondPlan = :secondPlan  ";
		return session.createQuery(hql).setString("secondPlan", secondPlan).list();
				
	} 
	
 
	public List<PlanBrand> findBy_mainPlan_distinct(Integer projectId) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
//		String hql = "select distinct(p) from  PlanBrand as p where  p.planBrandsecondPlan = :secondPlan  ";
//		return session.createQuery(hql).setString("secondPlan", secondPlan).list();
		String hql = "select distinct(p) from  PlanBrand as p where  p.productProject.productProjectId = :projectId  ";
		return session.createQuery(hql).setInteger("projectId", projectId).list();
				
	} 	
	
	
	
	
	
	public List<PlanBrand> findByMonth_planBrandType(String operatingMonthId,Integer planBrandType) {
//		String hql = "from  PlanBrand as p where p.planBrandId like :operatingMonthId and "
//				+ "p.planBrandType = 0";
//		Session session = getHibernateTemplate().getSessionFactory()
//				.getCurrentSession();
//		Query query = session.createQuery(hql).setString("operatingMonthId",
//				operatingMonthId + 0 + "%");
		
		String hql = " select p from  PlanBrand  p where p.operatingMonth.operatingMonthId =:operatingMonthId and p.planBrandType =:planBrandType and p.productProject.projectStatus = 0"  ;
		
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return session.createQuery(hql).setString("operatingMonthId",operatingMonthId).setInteger("planBrandType", planBrandType).list();
		//return query.setCacheable(true).list();
	}
	
	
	
	public List<PlanBrand> findByOperatingMonthId(String operatingMonthId ) {
//		String hql = "from  PlanBrand as p where p.planBrandId like :operatingMonthId and "
//				+ "p.planBrandType = 0";
//		Session session = getHibernateTemplate().getSessionFactory()
//				.getCurrentSession();
//		Query query = session.createQuery(hql).setString("operatingMonthId",
//				operatingMonthId + 0 + "%");
		
		String hql = " select p from  PlanBrand  p where p.operatingMonth.operatingMonthId =:operatingMonthId and p.productProject.projectStatus = 0"  ;
		
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return session.createQuery(hql).setString("operatingMonthId",operatingMonthId).list();
		//return query.setCacheable(true).list();
	}
	
	
	
	
	
	
	///2012-9-19 alpha
	public List<Object[]> findByOperatingMonthId_proid(String operatingMonthId) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		String sql = "select distinct(d.proid) from planbrand d inner join productProject t on d.planBrandmainPlan=t.productProjectId  where  d.planBrandType=0 and t.projectStatus=0 and d.productBrandId  not in (select s.productBrandId from planbrand as s where s.planBrandType=1 and s.operatingMonthId=d.operatingMonthId) and t.operatingMonthId='"
				+ operatingMonthId + "'";
		return session.createSQLQuery(sql).list();

	}
	
	
	
	
	
}