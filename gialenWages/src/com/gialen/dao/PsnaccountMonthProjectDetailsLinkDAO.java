package com.gialen.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.PsnaccountMonthProjectDetailsLink;

/**
 * A data access object (DAO) providing persistence and search support for
 * PsnaccountMonthProjectDetailsLink entities. Transaction control of the
 * save(), update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.gialen.model.PsnaccountMonthProjectDetailsLink
 * @author MyEclipse Persistence Tools
 */

public class PsnaccountMonthProjectDetailsLinkDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(PsnaccountMonthProjectDetailsLinkDAO.class);
	// property constants
	public static final String SALE_NUM = "saleNum";
	public static final String SALE_COUNT = "saleCount";
	public static final String SALE_WAGES = "saleWages";
	public static final String PCASH_PAY_AMT = "pcashPayAmt";

	protected void initDao() {
		// do nothing
	}

	public void save(PsnaccountMonthProjectDetailsLink transientInstance) {
		log.debug("saving PsnaccountMonthProjectDetailsLink instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PsnaccountMonthProjectDetailsLink persistentInstance) {
		log.debug("deleting PsnaccountMonthProjectDetailsLink instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PsnaccountMonthProjectDetailsLink findById(java.lang.Integer id) {
		log.debug("getting PsnaccountMonthProjectDetailsLink instance with id: "
				+ id);
		try {
			PsnaccountMonthProjectDetailsLink instance = (PsnaccountMonthProjectDetailsLink) getHibernateTemplate()
					.get("com.gialen.model.PsnaccountMonthProjectDetailsLink",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PsnaccountMonthProjectDetailsLink instance) {
		log.debug("finding PsnaccountMonthProjectDetailsLink instance by example");
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
		log.debug("finding PsnaccountMonthProjectDetailsLink instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PsnaccountMonthProjectDetailsLink as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySaleNum(Object saleNum) {
		return findByProperty(SALE_NUM, saleNum);
	}

	public List findBySaleCount(Object saleCount) {
		return findByProperty(SALE_COUNT, saleCount);
	}

	public List findBySaleWages(Object saleWages) {
		return findByProperty(SALE_WAGES, saleWages);
	}

	public List findByPcashPayAmt(Object pcashPayAmt) {
		return findByProperty(PCASH_PAY_AMT, pcashPayAmt);
	}

	public List findAll() {
		log.debug("finding all PsnaccountMonthProjectDetailsLink instances");
		try {
			String queryString = "from PsnaccountMonthProjectDetailsLink";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PsnaccountMonthProjectDetailsLink merge(
			PsnaccountMonthProjectDetailsLink detachedInstance) {
		log.debug("merging PsnaccountMonthProjectDetailsLink instance");
		try {
			PsnaccountMonthProjectDetailsLink result = (PsnaccountMonthProjectDetailsLink) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PsnaccountMonthProjectDetailsLink instance) {
		log.debug("attaching dirty PsnaccountMonthProjectDetailsLink instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PsnaccountMonthProjectDetailsLink instance) {
		log.debug("attaching clean PsnaccountMonthProjectDetailsLink instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PsnaccountMonthProjectDetailsLinkDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (PsnaccountMonthProjectDetailsLinkDAO) ctx
				.getBean("PsnaccountMonthProjectDetailsLinkDAO");
	}

	
	//批量新增
	public void saveAllLink(List<PsnaccountMonthProjectDetailsLink> allLink) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		for(int i = 0;i<allLink.size();i++){
			session.save(allLink.get(i));
			if(i % 50 == 0){
				session.flush();
				session.clear();
			}
		}
		
	}
	
	//查询上个营运月的的员工单品销售提成表 //order by p.psnaccount.personid
	public List<PsnaccountMonthProjectDetailsLink> findLastMonthPsnaccountMonthSaleCountLink(
			String operatingMonthId) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="from PsnaccountMonthProjectDetailsLink as p where  p.operatingMonth.operatingMonthId = :operatingMonthId and " +
				"p.linktype = 0  order by p.branch.braId";
		return session.createQuery(hql).setString("operatingMonthId",operatingMonthId).list();
	
	}

	public List<PsnaccountMonthProjectDetailsLink> findLastMonthPsnaccountMonthSaleCountLink(
			String operatingMonthId, String unitcode) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="from PsnaccountMonthProjectDetailsLink as p where  p.operatingMonth.operatingMonthId = :operatingMonthId and " +
				" p.orgstdStruct.unitcode like :unitcode  and " +
				"p.linktype = 0  order by p.branch.braId";
		
		return session.createQuery(hql).setString("operatingMonthId",operatingMonthId)
				.setString("unitcode",unitcode+"%").list();
		
		
	}
}