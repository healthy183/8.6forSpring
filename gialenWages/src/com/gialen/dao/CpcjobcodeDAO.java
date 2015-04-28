package com.gialen.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.Cpcjobcode;

/**
 * A data access object (DAO) providing persistence and search support for
 * Cpcjobcode entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.gialen.model.Cpcjobcode
 * @author MyEclipse Persistence Tools
 */

public class CpcjobcodeDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(CpcjobcodeDAO.class);
	// property constants
	public static final String CODE = "code";
	public static final String NAMES = "names";
	public static final String MANAGELAYERID = "managelayerid";
	public static final String MEMO = "memo";
	public static final String ORDERS = "orders";
	public static final String MODIFYDATE = "modifydate";
	public static final String MODIFYUSERID = "modifyuserid";
	public static final String ISEFFECT = "iseffect";
	public static final String ISDELETED = "isdeleted";

	protected void initDao() {
		// do nothing
	}

	public void save(Cpcjobcode transientInstance) {
		log.debug("saving Cpcjobcode instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Cpcjobcode persistentInstance) {
		log.debug("deleting Cpcjobcode instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Cpcjobcode findById(java.lang.String id) {
		log.debug("getting Cpcjobcode instance with id: " + id);
		try {
			Cpcjobcode instance = (Cpcjobcode) getHibernateTemplate().get(
					"com.gialen.model.Cpcjobcode", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Cpcjobcode instance) {
		log.debug("finding Cpcjobcode instance by example");
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
		log.debug("finding Cpcjobcode instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Cpcjobcode as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List findByNames(Object names) {
		return findByProperty(NAMES, names);
	}

	public List findByManagelayerid(Object managelayerid) {
		return findByProperty(MANAGELAYERID, managelayerid);
	}

	public List findByMemo(Object memo) {
		return findByProperty(MEMO, memo);
	}

	public List findByOrders(Object orders) {
		return findByProperty(ORDERS, orders);
	}

	public List findByModifydate(Object modifydate) {
		return findByProperty(MODIFYDATE, modifydate);
	}

	public List findByModifyuserid(Object modifyuserid) {
		return findByProperty(MODIFYUSERID, modifyuserid);
	}

	public List findByIseffect(Object iseffect) {
		return findByProperty(ISEFFECT, iseffect);
	}

	public List findByIsdeleted(Object isdeleted) {
		return findByProperty(ISDELETED, isdeleted);
	}

	public List findAll() {
		log.debug("finding all Cpcjobcode instances");
		try {
			String queryString = "from Cpcjobcode";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Cpcjobcode merge(Cpcjobcode detachedInstance) {
		log.debug("merging Cpcjobcode instance");
		try {
			Cpcjobcode result = (Cpcjobcode) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Cpcjobcode instance) {
		log.debug("attaching dirty Cpcjobcode instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Cpcjobcode instance) {
		log.debug("attaching clean Cpcjobcode instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CpcjobcodeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CpcjobcodeDAO) ctx.getBean("CpcjobcodeDAO");
	}
}