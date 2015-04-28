package com.gialen.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.StoreCount;

/**
 * A data access object (DAO) providing persistence and search support for
 * StoreCount entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.gialen.model.StoreCount
 * @author MyEclipse Persistence Tools
 */

public class StoreCountDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(StoreCountDAO.class);
	// property constants
	public static final String SALE_COUNT = "saleCount";

	protected void initDao() {
		// do nothing
	}

	public void save(StoreCount transientInstance) {
		log.debug("saving StoreCount instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(StoreCount persistentInstance) {
		log.debug("deleting StoreCount instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StoreCount findById(java.lang.Integer id) {
		log.debug("getting StoreCount instance with id: " + id);
		try {
			StoreCount instance = (StoreCount) getHibernateTemplate().get(
					"com.gialen.model.StoreCount", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(StoreCount instance) {
		log.debug("finding StoreCount instance by example");
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
		log.debug("finding StoreCount instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from StoreCount as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySaleCount(Object saleCount) {
		return findByProperty(SALE_COUNT, saleCount);
	}

	public List findAll() {
		log.debug("finding all StoreCount instances");
		try {
			String queryString = "from StoreCount";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public StoreCount merge(StoreCount detachedInstance) {
		log.debug("merging StoreCount instance");
		try {
			StoreCount result = (StoreCount) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(StoreCount instance) {
		log.debug("attaching dirty StoreCount instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StoreCount instance) {
		log.debug("attaching clean StoreCount instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static StoreCountDAO getFromApplicationContext(ApplicationContext ctx) {
		return (StoreCountDAO) ctx.getBean("StoreCountDAO");
	}

	//查询当前营运月门店的总销售数据
	public List<StoreCount> findThisStoreStoreCount(String operatingMonthId) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="from StoreCount as s where s.operatingMonth.operatingMonthId = :operatingMonthId and " +
				"s.storeCountType = 0  order by s.orgstdStruct.unitcode";
		//session.createuerSQLQy("sql");
		return session.createQuery(hql)
			.setString("operatingMonthId", operatingMonthId)
				.list(); //.setCacheable(true)
	}

	
	//批量保存 storeCount
	public void saveOrupdateAll(List<StoreCount> storeCountList) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		//getHibernateTemplate().saveOrUpdateAll(storeCountList);
		for(int i =0;i<storeCountList.size();i++){
			session.save(storeCountList.get(i));
			
			if(i%50 == 0){
				session.flush();
				session.clear();
			}
		}
		
		session.flush();
		session.clear();
		session.close();
	}

	//查询上个营运月的片区提成StoreCount
	public List<StoreCount> findThisMonthFilmAreaWages(String operatingMonthId,int i) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		//Session session = getHibernateTemplate().getSessionFactory().openSession();
		String hql ="from StoreCount as s where s.operatingMonth.operatingMonthId = :operatingMonthId and " +
				"s.storeCountType = :i order by s.orgstdStruct.unitcode";
		//session.createuerSQLQy("sql");
		List<StoreCount> list = session.createQuery(hql)
			.setString("operatingMonthId", operatingMonthId).setInteger("i",i)
				.list();//.setCacheable(true)
		
		//session.close();
		return list;
	}
	
	//从 StoreCount表中 查询所有片区的实际以及计划销售情况
	public List<StoreCount> findFilmAreaStoreCount(String operatingMonthId,
			List<String> unitIdStoreList, int i) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="select new StoreCount(saleCount,planMoneyCount) from StoreCount as s " +
				"where s.operatingMonth.operatingMonthId = :operatingMonthId and s.storeCountType = :i and " +
				"s.orgstdStruct.unitid in (:unitIdStoreList)";
		return session.createQuery(hql).setString("operatingMonthId",operatingMonthId)
			.setInteger("i",i).setParameterList("unitIdStoreList",unitIdStoreList).list();
		
	}

	//查询上个营运月的片区 区域提成StoreCount
	public List<StoreCount> findThisMonthAreaWages(String operatingMonthId,
			int i) {
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		//Session session = getHibernateTemplate().getSessionFactory().openSession();
		String hql ="from StoreCount as s where s.operatingMonth.operatingMonthId = :operatingMonthId and " +
				"s.storeCountType != :i order by s.orgstdStruct.unitcode";
		//session.createuerSQLQy("sql");
		return session.createQuery(hql)
			.setString("operatingMonthId", operatingMonthId).setInteger("i",i)
				.list();
		
	}

	public List<StoreCount> findThisStoreStoreCount(String operatingMonthId,
			List<String> strList) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="from StoreCount as s where s.operatingMonth.operatingMonthId = :operatingMonthId and " +
				"s.storeCountType = 0 and  s.branch.braId in (:strList)  order by s.orgstdStruct.unitcode";
		//session.createuerSQLQy("sql");
		return session.createQuery(hql).setString("operatingMonthId", operatingMonthId)
			.setParameterList("strList",strList).list(); //.setCacheable(true)
	}
}