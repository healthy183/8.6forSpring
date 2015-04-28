package com.gialen.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.Grundbonus;

/**
 * A data access object (DAO) providing persistence and search support for
 * Grundbonus entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.gialen.model.Grundbonus
 * @author MyEclipse Persistence Tools
 */

public class GrundbonusDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(GrundbonusDAO.class);
	// property constants
	public static final String GRUNDBONUS_MONEY = "grundbonusMoney";

	protected void initDao() {
		// do nothing
	}

	public void save(Grundbonus transientInstance) {
		log.debug("saving Grundbonus instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Grundbonus persistentInstance) {
		log.debug("deleting Grundbonus instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Grundbonus findById(java.lang.Integer id) {
		log.debug("getting Grundbonus instance with id: " + id);
		try {
			Grundbonus instance = (Grundbonus) getHibernateTemplate().get(
					"com.gialen.model.Grundbonus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Grundbonus instance) {
		log.debug("finding Grundbonus instance by example");
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
		log.debug("finding Grundbonus instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Grundbonus as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByGrundbonusMoney(Object grundbonusMoney) {
		return findByProperty(GRUNDBONUS_MONEY, grundbonusMoney);
	}

	public List findAll() {
		log.debug("finding all Grundbonus instances");
		try {
			String queryString = "from Grundbonus";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Grundbonus merge(Grundbonus detachedInstance) {
		log.debug("merging Grundbonus instance");
		try {
			Grundbonus result = (Grundbonus) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Grundbonus instance) {
		log.debug("attaching dirty Grundbonus instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Grundbonus instance) {
		log.debug("attaching clean Grundbonus instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static GrundbonusDAO getFromApplicationContext(ApplicationContext ctx) {
		return (GrundbonusDAO) ctx.getBean("GrundbonusDAO");
	}

	//查询今个月的 档期预计金额  公司类型 地区 年份   月份 X序号 Y序号  
						//0      0  2012 06 01   01
	public List<Grundbonus> findThisMonthGrundbonus(String s) {
		String hql ="from Grundbonus as g where g.grundbonusId like ? ";
		return getHibernateTemplate().find(hql,new Object[]{s+"____"});
	}
	//保存 奖金表
	public void saveGrundbonus(List<Grundbonus> grundbonusList) {
		//getHibernateTemplate().saveOrUpdateAll(grundbonusList);
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		//session.flush();
		//session.clear();//你的技术水平去到，事实去到客户公司
		for(int i = 0;i<grundbonusList.size();i++){
			session.save(grundbonusList.get(i));
		}/**/
		session.flush();
		session.close();
	}
	
	//重新上传的后就级联删除今年当月营运月对应类型提成标准的所有记录
	public void deleteThisMonthGrundbonus(String string) {
		
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		
		String hql = "delete from  Grundbonus as g where g.grundbonusId like :string";
		
		//session.createSQLQuery(hql).setString("string",string+"____").executeUpdate();
		session.createQuery(hql).setString("string",new String(string+"____")).executeUpdate();
		
		String delCom = "delete from ComplatePercent as c where c.complatePercentId like :string";
		//session.createSQLQuery(delCom).setString("string",string+"__").executeUpdate();
		session.createQuery(delCom).setString("string",new String(string+"__")).executeUpdate();
		
		String delPla = "delete from OperatingMonthPlanMoney as o where o.operatingMonthPathMoneyId like :string";
		//session.createSQLQuery(delPla).setString("string",string+"__").executeUpdate();
		session.createQuery(delPla).setString("string",new String(string+"__")).executeUpdate();
		session.flush();
		session.close();
	}

	 //根据营运月id查询是否已经填写了门店销售安排
	public List<Grundbonus> findThisMonthGrundbonusByMonthId(
			String operatingMonthId) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql = "from Grundbonus as g where g.operatingMonthPlanMoney.operatingMonth.operatingMonthId = :operatingMonthId";
		return session.createQuery(hql).setString("operatingMonthId",operatingMonthId).list();
		
	}

	//再根据当前营运月,当前预计销售金额，以及完成额，查询奖金
	public Double findWagesByThisMonthPlanMoneyComplatePercent(
			String thisMonthId, Double planMoneyCount, Double complatePercentVar ,Integer operatingMonthPathMoneyType) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="select grundbonusMoney from  Grundbonus as g where " +
		 "g.operatingMonthPlanMoney.operatingMonthPathMinMoneyCount <= :planMoneyCount and " +
			"g.operatingMonthPlanMoney.operatingMonthPathMaxMoneyCount > :planMoneyCount and " +
				"g.complatePercent.operatingMonthPathMinMoneyCount <= :complatePercentVar and  " +
					"g.complatePercent.operatingMonthPathMaxMoneyCount > :complatePercentVar and " +
					"g.operatingMonthPlanMoney.operatingMonth.operatingMonthId = :thisMonthId and " +
					"g.complatePercent.operatingMonth.operatingMonthId = :thisMonthId and " +
					"g.operatingMonthPathMoneyType = :operatingMonthPathMoneyType";
		//List<Grundbonus> grundbonusList
		 return (Double)session.createQuery(hql)
		  .setString("thisMonthId",thisMonthId).setDouble("planMoneyCount", planMoneyCount)
		  	.setDouble("complatePercentVar", complatePercentVar)
		  	  .setInteger("operatingMonthPathMoneyType",operatingMonthPathMoneyType)
		  	     .uniqueResult();
	}

	//根据当前营运月,完成额，查询门店总提(总提 type是2)
	public Double findWagesPercentByThisMonthPlanMoneyComplatePercent(
			String thisMonthId, Double complatePercentVar, String storeType,
			Integer operatingMonthPathMoneyType) {

		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="select rewardPercent from Grundbonus as g where " +
			"g.operatingMonthPlanMoney.operatingMonth.operatingMonthId = :thisMonthId and " +
				"g.operatingMonthPlanMoney.operatingMonthPathMoneyType = :operatingMonthPathMoneyType and " +
				"g.operatingMonthPlanMoney.place = :storeType and " +
				"g.complatePercent.operatingMonth.operatingMonthId = :thisMonthId and " +
				"g.complatePercent.operatingMonthPathMinMoneyCount <= :complatePercentVar and " +
				"g.complatePercent.operatingMonthPathMaxMoneyCount > :complatePercentVar and " +
				"g.complatePercent.operatingMonthPathMoneyType = :operatingMonthPathMoneyType and " +
				"g.operatingMonthPathMoneyType = :operatingMonthPathMoneyType and " +
				"g.place = :storeType";
		
		return	(Double) session.createQuery(hql).setString("thisMonthId",thisMonthId)
			.setDouble("complatePercentVar",complatePercentVar)
				.setString("storeType",storeType)
					.setInteger("operatingMonthPathMoneyType",operatingMonthPathMoneyType)
						.uniqueResult();
		
	}

	
	
	
	
	
}