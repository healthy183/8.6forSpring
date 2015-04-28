package com.kang.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kang.model.TReim;

/**
 * A data access object (DAO) providing persistence and search support for TReim
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.kang.model.TReim
 * @author MyEclipse Persistence Tools
 */

public class TReimDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(TReimDAO.class);
	// property constants
	public static final String RMNAME = "rmname";
	public static final String RMDESC = "rmdesc";

	protected void initDao() {
		// do nothing
	}

	public void save(TReim transientInstance) {
		log.debug("saving TReim instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TReim persistentInstance) {
		log.debug("deleting TReim instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TReim findById(java.lang.Integer id) {
		log.debug("getting TReim instance with id: " + id);
		try {
			TReim instance = (TReim) getHibernateTemplate().get(
					"com.kang.model.TReim", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<TReim> findByExample(TReim instance) {
		log.debug("finding TReim instance by example");
		try {
			List<TReim> results = (List<TReim>) getHibernateTemplate()
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
		log.debug("finding TReim instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TReim as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<TReim> findByRmname(Object rmname) {
		return findByProperty(RMNAME, rmname);
	}

	public List<TReim> findByRmdesc(Object rmdesc) {
		return findByProperty(RMDESC, rmdesc);
	}

	public List findAll() {
		log.debug("finding all TReim instances");
		try {
			String queryString = "from TReim";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TReim merge(TReim detachedInstance) {
		log.debug("merging TReim instance");
		try {
			TReim result = (TReim) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TReim instance) {
		log.debug("attaching dirty TReim instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TReim instance) {
		log.debug("attaching clean TReim instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TReimDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TReimDAO) ctx.getBean("TReimDAO");
	}
}