package com.kang.dao;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kang.model.TReimapp;

/**
 * A data access object (DAO) providing persistence and search support for
 * TReimapp entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.kang.model.TReimapp
 * @author MyEclipse Persistence Tools
 */

public class TReimappDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(TReimappDAO.class);
	// property constants
	public static final String APPTEXT = "apptext";
	public static final String APPDESC = "appdesc";

	protected void initDao() {
		// do nothing
	}

	public void save(TReimapp transientInstance) {
		log.debug("saving TReimapp instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TReimapp persistentInstance) {
		log.debug("deleting TReimapp instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TReimapp findById(java.lang.Integer id) {
		log.debug("getting TReimapp instance with id: " + id);
		try {
			TReimapp instance = (TReimapp) getHibernateTemplate().get(
					"com.kang.model.TReimapp", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<TReimapp> findByExample(TReimapp instance) {
		log.debug("finding TReimapp instance by example");
		try {
			List<TReimapp> results = (List<TReimapp>) getHibernateTemplate()
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
		log.debug("finding TReimapp instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TReimapp as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<TReimapp> findByApptext(Object apptext) {
		return findByProperty(APPTEXT, apptext);
	}

	public List<TReimapp> findByAppdesc(Object appdesc) {
		return findByProperty(APPDESC, appdesc);
	}

	public List findAll() {
		log.debug("finding all TReimapp instances");
		try {
			String queryString = "from TReimapp";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TReimapp merge(TReimapp detachedInstance) {
		log.debug("merging TReimapp instance");
		try {
			TReimapp result = (TReimapp) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TReimapp instance) {
		log.debug("attaching dirty TReimapp instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TReimapp instance) {
		log.debug("attaching clean TReimapp instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TReimappDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TReimappDAO) ctx.getBean("TReimappDAO");
	}
}