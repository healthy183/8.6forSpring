package com.gialen.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.CollectUsrWages;

/**
 * A data access object (DAO) providing persistence and search support for
 * CollectUsrWages entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.gialen.model.CollectUsrWages
 * @author MyEclipse Persistence Tools
 */

public class CollectUsrWagesDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(CollectUsrWagesDAO.class);
	// property constants
	public static final String EMPLOYEEID = "employeeid";
	public static final String NAMES = "names";
	public static final String FILM_AREA_NAME = "filmAreaName";
	public static final String BIG_AREA_NAME = "bigAreaName";
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

	public void save(CollectUsrWages transientInstance) {
		log.debug("saving CollectUsrWages instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CollectUsrWages persistentInstance) {
		log.debug("deleting CollectUsrWages instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CollectUsrWages findById(java.lang.Integer id) {
		log.debug("getting CollectUsrWages instance with id: " + id);
		try {
			CollectUsrWages instance = (CollectUsrWages) getHibernateTemplate()
					.get("com.gialen.model.CollectUsrWages", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CollectUsrWages instance) {
		log.debug("finding CollectUsrWages instance by example");
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
		log.debug("finding CollectUsrWages instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CollectUsrWages as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByEmployeeid(Object employeeid) {
		return findByProperty(EMPLOYEEID, employeeid);
	}

	public List findByNames(Object names) {
		return findByProperty(NAMES, names);
	}

	public List findByFilmAreaName(Object filmAreaName) {
		return findByProperty(FILM_AREA_NAME, filmAreaName);
	}

	public List findByBigAreaName(Object bigAreaName) {
		return findByProperty(BIG_AREA_NAME, bigAreaName);
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
		log.debug("finding all CollectUsrWages instances");
		try {
			String queryString = "from CollectUsrWages";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CollectUsrWages merge(CollectUsrWages detachedInstance) {
		log.debug("merging CollectUsrWages instance");
		try {
			CollectUsrWages result = (CollectUsrWages) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CollectUsrWages instance) {
		log.debug("attaching dirty CollectUsrWages instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CollectUsrWages instance) {
		log.debug("attaching clean CollectUsrWages instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CollectUsrWagesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (CollectUsrWagesDAO) ctx.getBean("CollectUsrWagesDAO");
	}
}