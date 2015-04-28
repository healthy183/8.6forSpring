package com.kang.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kang.model.TReimitm;

/**
 * A data access object (DAO) providing persistence and search support for
 * TReimitm entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.kang.model.TReimitm
 * @author MyEclipse Persistence Tools
 */

public class TReimitmDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(TReimitmDAO.class);
	// property constants
	public static final String RMITMNAME = "rmitmname";
	public static final String RMITMCOST = "rmitmcost";
	public static final String RMITMDESC = "rmitmdesc";

	protected void initDao() {
		// do nothing
	}

	public void save(TReimitm transientInstance) {
		log.debug("saving TReimitm instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TReimitm persistentInstance) {
		log.debug("deleting TReimitm instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TReimitm findById(java.lang.Integer id) {
		log.debug("getting TReimitm instance with id: " + id);
		try {
			TReimitm instance = (TReimitm) getHibernateTemplate().get(
					"com.kang.model.TReimitm", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<TReimitm> findByExample(TReimitm instance) {
		log.debug("finding TReimitm instance by example");
		try {
			List<TReimitm> results = (List<TReimitm>) getHibernateTemplate()
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TReimitm instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TReimitm as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<TReimitm> findByRmitmname(Object rmitmname) {
		return findByProperty(RMITMNAME, rmitmname);
	}

	public List<TReimitm> findByRmitmcost(Object rmitmcost) {
		return findByProperty(RMITMCOST, rmitmcost);
	}

	public List<TReimitm> findByRmitmdesc(Object rmitmdesc) {
		return findByProperty(RMITMDESC, rmitmdesc);
	}

	public List findAll() {
		log.debug("finding all TReimitm instances");
		try {
			String queryString = "from TReimitm";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TReimitm merge(TReimitm detachedInstance) {
		log.debug("merging TReimitm instance");
		try {
			TReimitm result = (TReimitm) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TReimitm instance) {
		log.debug("attaching dirty TReimitm instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TReimitm instance) {
		log.debug("attaching clean TReimitm instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TReimitmDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TReimitmDAO) ctx.getBean("TReimitmDAO");
	}
}