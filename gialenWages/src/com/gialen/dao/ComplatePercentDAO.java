package com.gialen.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.ComplatePercent;

/**
 * A data access object (DAO) providing persistence and search support for
 * ComplatePercent entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.gialen.model.ComplatePercent
 * @author MyEclipse Persistence Tools
 */

public class ComplatePercentDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ComplatePercentDAO.class);
	// property constants
	public static final String COMPLATE_PERCENT_NAME = "complatePercentName";
	public static final String OPERATING_MONTH_PATH_MIN_MONEY_COUNT = "operatingMonthPathMinMoneyCount";
	public static final String OPERATING_MONTH_PATH_MAX_MONEY_COUNT = "operatingMonthPathMaxMoneyCount";
	public static final String OPERATING_MONTH_PATH_MONEY_TYPE = "operatingMonthPathMoneyType";

	protected void initDao() {
		// do nothing
	}

	public void save(ComplatePercent transientInstance) {
		log.debug("saving ComplatePercent instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ComplatePercent persistentInstance) {
		log.debug("deleting ComplatePercent instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ComplatePercent findById(java.lang.Integer id) {
		log.debug("getting ComplatePercent instance with id: " + id);
		try {
			ComplatePercent instance = (ComplatePercent) getHibernateTemplate()
					.get("com.gialen.model.ComplatePercent", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ComplatePercent instance) {
		log.debug("finding ComplatePercent instance by example");
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
		log.debug("finding ComplatePercent instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ComplatePercent as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByComplatePercentName(Object complatePercentName) {
		return findByProperty(COMPLATE_PERCENT_NAME, complatePercentName);
	}

	public List findByOperatingMonthPathMinMoneyCount(
			Object operatingMonthPathMinMoneyCount) {
		return findByProperty(OPERATING_MONTH_PATH_MIN_MONEY_COUNT,
				operatingMonthPathMinMoneyCount);
	}

	public List findByOperatingMonthPathMaxMoneyCount(
			Object operatingMonthPathMaxMoneyCount) {
		return findByProperty(OPERATING_MONTH_PATH_MAX_MONEY_COUNT,
				operatingMonthPathMaxMoneyCount);
	}

	public List findByOperatingMonthPathMoneyType(
			Object operatingMonthPathMoneyType) {
		return findByProperty(OPERATING_MONTH_PATH_MONEY_TYPE,
				operatingMonthPathMoneyType);
	}

	public List findAll() {
		log.debug("finding all ComplatePercent instances");
		try {
			String queryString = "from ComplatePercent";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ComplatePercent merge(ComplatePercent detachedInstance) {
		log.debug("merging ComplatePercent instance");
		try {
			ComplatePercent result = (ComplatePercent) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ComplatePercent instance) {
		log.debug("attaching dirty ComplatePercent instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ComplatePercent instance) {
		log.debug("attaching clean ComplatePercent instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ComplatePercentDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ComplatePercentDAO) ctx.getBean("ComplatePercentDAO");
	}

	//查询今个营运月的档期完成额度表  主键格式是:  对应营运月主键+区域门店类型+序号  020120101 0 01
	public List<ComplatePercent> findComplatePercent(String s) {
		String hql ="from ComplatePercent as c where c.complatePercentId like ? order by complatePercentId";
		return getHibernateTemplate().find(hql,new Object[]{s+"__"});
	}

	public void merge(List<ComplatePercent> complatePercentList) {
		//Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		for(ComplatePercent complatePercent : complatePercentList){
			getHibernateTemplate().saveOrUpdate(complatePercent);
		}
	}
}