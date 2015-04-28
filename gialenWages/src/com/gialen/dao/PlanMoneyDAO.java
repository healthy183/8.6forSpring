package com.gialen.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.PlanMoney;

/**
 * A data access object (DAO) providing persistence and search support for
 * PlanMoney entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.gialen.model.PlanMoney
 * @author MyEclipse Persistence Tools
 */

public class PlanMoneyDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(PlanMoneyDAO.class);
	// property constants
	public static final String PLAN_MONEY_COUNT = "planMoneyCount";

	protected void initDao() {
		// do nothing
	}

	public void save(PlanMoney transientInstance) {
		log.debug("saving PlanMoney instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PlanMoney persistentInstance) {
		log.debug("deleting PlanMoney instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PlanMoney findById(java.lang.Integer id) {
		log.debug("getting PlanMoney instance with id: " + id);
		try {
			PlanMoney instance = (PlanMoney) getHibernateTemplate().get(
					"com.gialen.model.PlanMoney", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PlanMoney instance) {
		log.debug("finding PlanMoney instance by example");
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
		log.debug("finding PlanMoney instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from PlanMoney as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPlanMoneyCount(Object planMoneyCount) {
		return findByProperty(PLAN_MONEY_COUNT, planMoneyCount);
	}

	public List findAll() {
		log.debug("finding all PlanMoney instances");
		try {
			String queryString = "from PlanMoney";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PlanMoney merge(PlanMoney detachedInstance) {
		log.debug("merging PlanMoney instance");
		try {
			PlanMoney result = (PlanMoney) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PlanMoney instance) {
		log.debug("attaching dirty PlanMoney instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PlanMoney instance) {
		log.debug("attaching clean PlanMoney instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PlanMoneyDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PlanMoneyDAO) ctx.getBean("PlanMoneyDAO");
	}

	//查询当前门店 当前营运月计划销售金额   
	public List<PlanMoney> getThisMounththisBrandPlanMoney(
			String operatingMonthId, String braId) {//p.productProject.productProjectId = 1啊峰话要写死
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="from PlanMoney as p where p.branch.braId = :braId and " +
			"p.operatingMonth.operatingMonthId = :operatingMonthId and " +
				"p.planMoneyType = 0 and p.productProject.productProjectId = 1";
		return	session.createQuery(hql)
			.setString("operatingMonthId", operatingMonthId)
				.setString("braId",braId).list();
	}

	// 2012-8-15通过 operatingMonthId,BraId ,productProjectId 查询PlanMoney
		public List<PlanMoney> findByExist(String operatingMonthId, String BraId,
				Integer productProjectId) {
			String hql = "select p from PlanMoney as p where p.operatingMonth.operatingMonthId= :operatingMonthId and p.branch.braId= :BraId and p.productProject.productProjectId= :productProjectId";
			Session session = getHibernateTemplate().getSessionFactory()
					.getCurrentSession();

			return session.createQuery(hql)
					.setString("operatingMonthId", operatingMonthId)
					.setString("BraId", BraId)
					.setInteger("productProjectId", productProjectId).list();

			// String hql
			// ="select new ProductClass(id,classId,className) from ProductClass as p "
			// +
			// "where p.classId like :parentClassId and p.level = :level2 and p.className not like :className";
			// Session session =
			// getHibernateTemplate().getSessionFactory().getCurrentSession();
			// return session.createQuery(hql).setString("className", "%停用%")
			// .setString("parentClassId",parentClassId+"%").setString("level2",level2)
			// .setCacheable(true).list();
		}

		public List<PlanMoney> findByPlanMoneyType_OperatingMonthId(
				Integer planMoneyType, String operatingMonthId) {
			String hql = "select p from PlanMoney as p where p.operatingMonth.operatingMonthId= :operatingMonthId and planMoneyType= :planMoneyType";
			Session session = getHibernateTemplate().getSessionFactory()
					.getCurrentSession();
			return session.createQuery(hql)
					.setString("operatingMonthId", operatingMonthId)
					.setInteger("planMoneyType", planMoneyType).list();
		}

		
		
		
		/////修改添加营运月属性2012-9-1
		
		
		public List<PlanMoney> findByProductProjectId( Integer productProjectId,String operatingMonthId) {
			System.out.println( "productProjectId3 "+productProjectId);
			String hql = "select p from PlanMoney as p where p.operatingMonth.operatingMonthId= :operatingMonthId and p.productProject.productProjectId= :productProjectId";
			Session session = getHibernateTemplate().getSessionFactory()
					.getCurrentSession();
			return session.createQuery(hql)
					.setString("operatingMonthId", operatingMonthId).setInteger("productProjectId", productProjectId).list();
		}

		
		
		
		
		
		
		
		public List<Object[]> ShowPlanMoneys(String operatingMonthId) {
//			System.out.println( "productProjectId3 "+productProjectId);
//			String hql = "select p from PlanMoney as p where p.productProject.productProjectId= :productProjectId";
//			Session session = getHibernateTemplate().getSessionFactory()
//					.getCurrentSession();
//			return session.createQuery(hql).setInteger("productProjectId", productProjectId).list();
			
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			
			return 	session.createSQLQuery("{call pro_project_list('"+operatingMonthId+"')}").list();
		}
		 
}