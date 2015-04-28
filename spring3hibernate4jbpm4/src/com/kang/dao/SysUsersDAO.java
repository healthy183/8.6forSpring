package com.kang.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kang.model.SysUsers;

/**
 * A data access object (DAO) providing persistence and search support for
 * SysUsers entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.kang.model.SysUsers
 * @author MyEclipse Persistence Tools
 */

public class SysUsersDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(SysUsersDAO.class);
	// property constants
	public static final String USRNAME = "usrname";
	public static final String USRPWD = "usrpwd";
	public static final String USRTYPE = "usrtype";
	public static final String USRMGR = "usrmgr";

	protected void initDao() {
		// do nothing
	}

	public void save(SysUsers transientInstance) {
		log.debug("saving SysUsers instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SysUsers persistentInstance) {
		log.debug("deleting SysUsers instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SysUsers findById(java.lang.Integer id) {
		log.debug("getting SysUsers instance with id: " + id);
		try {
			SysUsers instance = (SysUsers) getHibernateTemplate().get(
					"com.kang.model.SysUsers", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SysUsers> findByExample(SysUsers instance) {
		log.debug("finding SysUsers instance by example");
		try {
			List<SysUsers> results = (List<SysUsers>) getHibernateTemplate()
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
		log.debug("finding SysUsers instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SysUsers as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SysUsers> findByUsrname(Object usrname) {
		return findByProperty(USRNAME, usrname);
	}

	public List<SysUsers> findByUsrpwd(Object usrpwd) {
		return findByProperty(USRPWD, usrpwd);
	}

	public List<SysUsers> findByUsrtype(Object usrtype) {
		return findByProperty(USRTYPE, usrtype);
	}

	public List<SysUsers> findByUsrmgr(Object usrmgr) {
		return findByProperty(USRMGR, usrmgr);
	}

	public List findAll() {
		log.debug("finding all SysUsers instances");
		try {
			String queryString = "from SysUsers";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SysUsers merge(SysUsers detachedInstance) {
		log.debug("merging SysUsers instance");
		try {
			SysUsers result = (SysUsers) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SysUsers instance) {
		log.debug("attaching dirty SysUsers instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SysUsers instance) {
		log.debug("attaching clean SysUsers instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SysUsersDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SysUsersDAO) ctx.getBean("SysUsersDAO");
	}
}