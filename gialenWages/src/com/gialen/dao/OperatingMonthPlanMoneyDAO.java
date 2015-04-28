package com.gialen.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.OperatingMonthPlanMoney;

/**
 * A data access object (DAO) providing persistence and search support for
 * OperatingMonthPlanMoney entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.gialen.model.OperatingMonthPlanMoney
 * @author MyEclipse Persistence Tools
 */

public class OperatingMonthPlanMoneyDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(OperatingMonthPlanMoneyDAO.class);
	// property constants
	public static final String OPERATING_MONTH_PLAN_MONEY_NAME = "operatingMonthPlanMoneyName";
	public static final String OPERATING_MONTH_PATH_MIN_MONEY_COUNT = "operatingMonthPathMinMoneyCount";
	public static final String OPERATING_MONTH_PATH_MAX_MONEY_COUNT = "operatingMonthPathMaxMoneyCount";
	public static final String OPERATING_MONTH_PATH_MONEY_TYPE = "operatingMonthPathMoneyType";

	protected void initDao() {
		// do nothing
	}

	public void save(OperatingMonthPlanMoney transientInstance) {
		log.debug("saving OperatingMonthPlanMoney instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OperatingMonthPlanMoney persistentInstance) {
		log.debug("deleting OperatingMonthPlanMoney instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OperatingMonthPlanMoney findById(java.lang.Integer id) {
		log.debug("getting OperatingMonthPlanMoney instance with id: " + id);
		try {
			OperatingMonthPlanMoney instance = (OperatingMonthPlanMoney) getHibernateTemplate()
					.get("com.gialen.model.OperatingMonthPlanMoney", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(OperatingMonthPlanMoney instance) {
		log.debug("finding OperatingMonthPlanMoney instance by example");
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
		log.debug("finding OperatingMonthPlanMoney instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from OperatingMonthPlanMoney as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByOperatingMonthPlanMoneyName(
			Object operatingMonthPlanMoneyName) {
		return findByProperty(OPERATING_MONTH_PLAN_MONEY_NAME,
				operatingMonthPlanMoneyName);
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
		log.debug("finding all OperatingMonthPlanMoney instances");
		try {
			String queryString = "from OperatingMonthPlanMoney";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OperatingMonthPlanMoney merge(
			OperatingMonthPlanMoney detachedInstance) {
		log.debug("merging OperatingMonthPlanMoney instance");
		try {
			OperatingMonthPlanMoney result = (OperatingMonthPlanMoney) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OperatingMonthPlanMoney instance) {
		log.debug("attaching dirty OperatingMonthPlanMoney instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OperatingMonthPlanMoney instance) {
		log.debug("attaching clean OperatingMonthPlanMoney instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OperatingMonthPlanMoneyDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (OperatingMonthPlanMoneyDAO) ctx
				.getBean("OperatingMonthPlanMoneyDAO");
	}
	
	//查询今个营运月的档期预计金额表   主键格式: 对应营运月主键+区域门店类型+序号  020120101 0 01
	public List<OperatingMonthPlanMoney> findOperatingMonthPlanMoney(
			String s) {
		String hql = "from OperatingMonthPlanMoney as o where o.operatingMonthPathMoneyId like ? " +
				"order by operatingMonthPathMoneyId";
		return getHibernateTemplate().find(hql,new Object[]{s+"__"});
	}
}