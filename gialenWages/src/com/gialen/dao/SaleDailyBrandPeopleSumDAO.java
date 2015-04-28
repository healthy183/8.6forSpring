package com.gialen.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.SaleDailyBrandPeopleSum;
import com.gialen.model.SaleDailyBrandPeopleSumId;

/**
 * A data access object (DAO) providing persistence and search support for
 * SaleDailyBrandPeopleSum entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.gialen.model.SaleDailyBrandPeopleSum
 * @author MyEclipse Persistence Tools
 */

public class SaleDailyBrandPeopleSumDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(SaleDailyBrandPeopleSumDAO.class);
	// property constants
	public static final String SALE_AMT = "saleAmt";
	public static final String SALE_WAGES = "saleWages";

	protected void initDao() {
		// do nothing
	}

	public void save(SaleDailyBrandPeopleSum transientInstance) {
		log.debug("saving SaleDailyBrandPeopleSum instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SaleDailyBrandPeopleSum persistentInstance) {
		log.debug("deleting SaleDailyBrandPeopleSum instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SaleDailyBrandPeopleSum findById(
			com.gialen.model.SaleDailyBrandPeopleSumId id) {
		log.debug("getting SaleDailyBrandPeopleSum instance with id: " + id);
		try {
			SaleDailyBrandPeopleSum instance = (SaleDailyBrandPeopleSum) getHibernateTemplate()
					.get("com.gialen.model.SaleDailyBrandPeopleSum", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SaleDailyBrandPeopleSum instance) {
		log.debug("finding SaleDailyBrandPeopleSum instance by example");
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
		log.debug("finding SaleDailyBrandPeopleSum instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SaleDailyBrandPeopleSum as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySaleAmt(Object saleAmt) {
		return findByProperty(SALE_AMT, saleAmt);
	}

	public List findBySaleWages(Object saleWages) {
		return findByProperty(SALE_WAGES, saleWages);
	}

	public List findAll() {
		log.debug("finding all SaleDailyBrandPeopleSum instances");
		try {
			String queryString = "from SaleDailyBrandPeopleSum";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SaleDailyBrandPeopleSum merge(
			SaleDailyBrandPeopleSum detachedInstance) {
		log.debug("merging SaleDailyBrandPeopleSum instance");
		try {
			SaleDailyBrandPeopleSum result = (SaleDailyBrandPeopleSum) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SaleDailyBrandPeopleSum instance) {
		log.debug("attaching dirty SaleDailyBrandPeopleSum instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SaleDailyBrandPeopleSum instance) {
		log.debug("attaching clean SaleDailyBrandPeopleSum instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SaleDailyBrandPeopleSumDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (SaleDailyBrandPeopleSumDAO) ctx
				.getBean("SaleDailyBrandPeopleSumDAO");
	}
}