package com.gialen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.SaleDailyProductPeople;
import com.gialen.model.SaleDailyProductPeopleId;
import com.gialen.model.vo.SaleDailyProductPeopleVo;

/**
 * A data access object (DAO) providing persistence and search support for
 * SaleDailyProductPeople entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.gialen.model.SaleDailyProductPeople
 * @author MyEclipse Persistence Tools
 */

public class SaleDailyProductPeopleDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(SaleDailyProductPeopleDAO.class);
	// property constants
	public static final String SALE_QTY = "saleQty";
	public static final String SALE_AMT = "saleAmt";
	public static final String SALE_WAGES = "saleWages";

	protected void initDao() {
		// do nothing
	}

	public void save(SaleDailyProductPeople transientInstance) {
		log.debug("saving SaleDailyProductPeople instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SaleDailyProductPeople persistentInstance) {
		log.debug("deleting SaleDailyProductPeople instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SaleDailyProductPeople findById(
			com.gialen.model.SaleDailyProductPeopleId id) {
		log.debug("getting SaleDailyProductPeople instance with id: " + id);
		try {
			SaleDailyProductPeople instance = (SaleDailyProductPeople) getHibernateTemplate()
					.get("com.gialen.model.SaleDailyProductPeople", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SaleDailyProductPeople instance) {
		log.debug("finding SaleDailyProductPeople instance by example");
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
		log.debug("finding SaleDailyProductPeople instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SaleDailyProductPeople as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySaleQty(Object saleQty) {
		return findByProperty(SALE_QTY, saleQty);
	}

	public List findBySaleAmt(Object saleAmt) {
		return findByProperty(SALE_AMT, saleAmt);
	}

	public List findBySaleWages(Object saleWages) {
		return findByProperty(SALE_WAGES, saleWages);
	}

	public List findAll() {
		log.debug("finding all SaleDailyProductPeople instances");
		try {
			String queryString = "from SaleDailyProductPeople";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SaleDailyProductPeople merge(SaleDailyProductPeople detachedInstance) {
		log.debug("merging SaleDailyProductPeople instance");
		try {
			SaleDailyProductPeople result = (SaleDailyProductPeople) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SaleDailyProductPeople instance) {
		log.debug("attaching dirty SaleDailyProductPeople instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SaleDailyProductPeople instance) {
		log.debug("attaching clean SaleDailyProductPeople instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SaleDailyProductPeopleDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (SaleDailyProductPeopleDAO) ctx
				.getBean("SaleDailyProductPeopleDAO");
	}
	
	//保存到SaleDailyProductPeople
	public void saveToSaleDailyProductPeople
		(List<SaleDailyProductPeopleVo> saleDailyProductPeopleVoList, String operatingMonthId) {
		
		System.out.println("a");
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		
		System.out.println("b");
		Connection connection = session.connection();
		System.out.println("c");
		String sql ="insert into saleDaily_product_people values(?,?,?,?,?,?,?)";
		System.out.println("d");
		try {
			PreparedStatement prepareStatement = connection
					.prepareStatement(sql);
			
			for(SaleDailyProductPeopleVo sale : saleDailyProductPeopleVoList){
				
				prepareStatement.setString(1,sale.getEmpId());
				prepareStatement.setString(2,sale.getProId());
				prepareStatement.setString(3, sale.getOperatingMonthId());
				prepareStatement.setDouble(4, sale.getSaleQty());
				prepareStatement.setDouble(5, sale.getSaleAmt());
				prepareStatement.setDouble(6, sale.getSaleWages());
				prepareStatement.setString(7, sale.getBraId());
				
				prepareStatement.addBatch();// 把sql放到一个容器中
			}
			
			prepareStatement.executeBatch();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("e");
		session.flush();
		session.close();
	}

	
	//4,按 门店 员工 分组 统计 销售总金额 总数量 总奖金
	public List<Object[]> findSaleByBraIdUsr() {
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		/*String sql  = "select SaleMan,sum(SaleQty),sum(SaleAmt),sum(saleWages),BraId " +
			"from saleDaily_product_people group by SaleMan,BraId";
		return session.createSQLQuery(sql).list();*/
		
		String hql = "select s.id.employee.empId,sum(s.saleQty),sum(s.saleAmt),sum(s.saleWages),s.id.branch.braId " +
			"from SaleDailyProductPeople as s " +
				"group by s.id.employee.empId,s.id.branch.braId";
		return session.createQuery(hql).list();
		
	}
}