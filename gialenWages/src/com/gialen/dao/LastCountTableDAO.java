package com.gialen.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.LastCountTable;

/**
 * A data access object (DAO) providing persistence and search support for
 * LastCountTable entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.gialen.model.LastCountTable
 * @author MyEclipse Persistence Tools
 */

public class LastCountTableDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(LastCountTableDAO.class);
	// property constants
	public static final String JOB_WAGES = "jobWages";
	public static final String EXCEED_AMT = "exceedAmt";
	public static final String EXCEED_WAGES = "exceedWages";
	public static final String PRO_SALE_AMT = "proSaleAmt";
	public static final String PRO_SALE_WAGES = "proSaleWages";
	public static final String BRANCH_SALE_AMT = "branchSaleAmt";
	public static final String BRANCH_SALE_WAGES = "branchSaleWages";
	public static final String TEAM_SALE_AMT = "teamSaleAmt";
	public static final String TEAM_SALE_WAGES = "teamSaleWages";
	public static final String SUM_SALE_AMT = "sumSaleAmt";
	public static final String SUMSALE_WAGES = "sumsaleWages";

	protected void initDao() {
		// do nothing
	}

	public void save(LastCountTable transientInstance) {
		log.debug("saving LastCountTable instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LastCountTable persistentInstance) {
		log.debug("deleting LastCountTable instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LastCountTable findById(java.lang.Integer id) {
		log.debug("getting LastCountTable instance with id: " + id);
		try {
			LastCountTable instance = (LastCountTable) getHibernateTemplate()
					.get("com.gialen.model.LastCountTable", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LastCountTable instance) {
		log.debug("finding LastCountTable instance by example");
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
		log.debug("finding LastCountTable instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from LastCountTable as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByJobWages(Object jobWages) {
		return findByProperty(JOB_WAGES, jobWages);
	}

	public List findByExceedAmt(Object exceedAmt) {
		return findByProperty(EXCEED_AMT, exceedAmt);
	}

	public List findByExceedWages(Object exceedWages) {
		return findByProperty(EXCEED_WAGES, exceedWages);
	}

	public List findByProSaleAmt(Object proSaleAmt) {
		return findByProperty(PRO_SALE_AMT, proSaleAmt);
	}

	public List findByProSaleWages(Object proSaleWages) {
		return findByProperty(PRO_SALE_WAGES, proSaleWages);
	}

	public List findByBranchSaleAmt(Object branchSaleAmt) {
		return findByProperty(BRANCH_SALE_AMT, branchSaleAmt);
	}

	public List findByBranchSaleWages(Object branchSaleWages) {
		return findByProperty(BRANCH_SALE_WAGES, branchSaleWages);
	}

	public List findByTeamSaleAmt(Object teamSaleAmt) {
		return findByProperty(TEAM_SALE_AMT, teamSaleAmt);
	}

	public List findByTeamSaleWages(Object teamSaleWages) {
		return findByProperty(TEAM_SALE_WAGES, teamSaleWages);
	}

	public List findBySumSaleAmt(Object sumSaleAmt) {
		return findByProperty(SUM_SALE_AMT, sumSaleAmt);
	}

	public List findBySumsaleWages(Object sumsaleWages) {
		return findByProperty(SUMSALE_WAGES, sumsaleWages);
	}

	public List findAll() {
		log.debug("finding all LastCountTable instances");
		try {
			String queryString = "from LastCountTable";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LastCountTable merge(LastCountTable detachedInstance) {
		log.debug("merging LastCountTable instance");
		try {
			LastCountTable result = (LastCountTable) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LastCountTable instance) {
		log.debug("attaching dirty LastCountTable instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LastCountTable instance) {
		log.debug("attaching clean LastCountTable instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LastCountTableDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LastCountTableDAO) ctx.getBean("LastCountTableDAO");
	}

	public List<LastCountTable> showUsrWages(String operatingMonthId) {
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		String hql = "from LastCountTable as t where t.operatingMonth.operatingMonthId = :operatingMonthId order by t.branch.braId,t.employee.empId";
		return	session.createQuery(hql).setString("operatingMonthId",operatingMonthId).list();
	}
}