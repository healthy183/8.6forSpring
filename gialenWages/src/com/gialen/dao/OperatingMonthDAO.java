package com.gialen.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.OperatingMonth;

/**
 * A data access object (DAO) providing persistence and search support for
 * OperatingMonth entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.gialen.model.OperatingMonth
 * @author MyEclipse Persistence Tools
 */

public class OperatingMonthDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(OperatingMonthDAO.class);
	// property constants
	public static final String OPERATING_MONTH_DATE = "operatingMonthDate";
	public static final String OPERATING_MONTH_NAME = "operatingMonthName";
	public static final String OPERATING_START_DATE = "operatingStartDate";
	public static final String OPERATING_END_DATE = "operatingEndDate";
	public static final String OPERATING_MONTH_TYPE = "operatingMonthType";
	public static final String OPERATING_MONTH_COUNT = "operatingMonthCount";
	public static final String OPERATING_MONTH_BZ = "operatingMonthBz";
	public static final String OPEERATING_MONTH_SIZE = "opeeratingMonthSize";

	protected void initDao() {
		// do nothing
	}

	public void save(OperatingMonth transientInstance) {
		log.debug("saving OperatingMonth instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OperatingMonth persistentInstance) {
		log.debug("deleting OperatingMonth instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OperatingMonth findById(java.lang.String id) {
		log.debug("getting OperatingMonth instance with id: " + id);
		try {
			OperatingMonth instance = (OperatingMonth) getHibernateTemplate()
					.get("com.gialen.model.OperatingMonth", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(OperatingMonth instance) {
		log.debug("finding OperatingMonth instance by example");
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
		log.debug("finding OperatingMonth instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from OperatingMonth as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByOperatingMonthDate(Object operatingMonthDate) {
		return findByProperty(OPERATING_MONTH_DATE, operatingMonthDate);
	}

	public List findByOperatingMonthName(Object operatingMonthName) {
		return findByProperty(OPERATING_MONTH_NAME, operatingMonthName);
	}

	public List findByOperatingStartDate(Object operatingStartDate) {
		return findByProperty(OPERATING_START_DATE, operatingStartDate);
	}

	public List findByOperatingEndDate(Object operatingEndDate) {
		return findByProperty(OPERATING_END_DATE, operatingEndDate);
	}

	public List findByOperatingMonthType(Object operatingMonthType) {
		return findByProperty(OPERATING_MONTH_TYPE, operatingMonthType);
	}

	public List findByOperatingMonthCount(Object operatingMonthCount) {
		return findByProperty(OPERATING_MONTH_COUNT, operatingMonthCount);
	}

	public List findByOperatingMonthBz(Object operatingMonthBz) {
		return findByProperty(OPERATING_MONTH_BZ, operatingMonthBz);
	}

	public List findByOpeeratingMonthSize(Object opeeratingMonthSize) {
		return findByProperty(OPEERATING_MONTH_SIZE, opeeratingMonthSize);
	}

	public List findAll() {
		log.debug("finding all OperatingMonth instances");
		try {
			String queryString = "from OperatingMonth";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OperatingMonth merge(OperatingMonth detachedInstance) {
		log.debug("merging OperatingMonth instance");
		try {
			OperatingMonth result = (OperatingMonth) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OperatingMonth instance) {
		log.debug("attaching dirty OperatingMonth instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OperatingMonth instance) {
		log.debug("attaching clean OperatingMonth instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OperatingMonthDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (OperatingMonthDAO) ctx.getBean("OperatingMonthDAO");
	}
	
		/*//获得今年已经分配好的营运周安排
		public List<OperatingMonth> operatingMonthIsWrite(String thisYear) {
			String hql = "from OperatingMonth as o where substring(o.operatingMonthDate,1,4) = ?";
			return  getHibernateTemplate().find(hql,new Object[]{thisYear});	
		}*/
		
		//保存营运月
		public void addOperatingMonth(List<OperatingMonth> seasonList) {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			for(int i = 0;i<seasonList.size();i++){
				session.save(seasonList.get(i));
				if(i%2 == 0){
					session.flush();
					session.clear();
				}
			}
		
		}
		//查询出今年的营运月 跟营运月主键查询 格式:020120101  0(区域type 广州为0) 2012(今年) XXXX(六个字符串 
		public List<OperatingMonth> findThisYearsWeek(String string) {
			String hql = "from OperatingMonth as o where o.operatingMonthId like ?";
			return getHibernateTemplate().find(hql,new Object[]{string+"______"});
		}
		
		//查询出今年的营运月 跟营运月主键查询 格式:020120101  0(区域type 广州为0) 2012(今年) XXXX(六个字符串 
		public List<OperatingMonth> findThisYearsMonth(String string) {
			String hql = "from OperatingMonth as o where o.operatingMonthId like ?";
			return getHibernateTemplate().find(hql,new Object[]{string+"____"});
				}
				//查找今年所有的营运月 type=1 春季01季
		public List<OperatingMonth> findThisYearAllOperatingMonth(
			String operatingMonthType, String thisYear) {
		String hql = "from OperatingMonth as o where o.operatingMonthId like :thisYear and "+ 
			"o.operatingMonthTimeType = 1 and operatingMonthType = :operatingMonthType";
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			return session.createQuery(hql)
				.setString("thisYear",operatingMonthType+thisYear+"%")
					.setString("operatingMonthType",operatingMonthType)
						.setCacheable(true).list();
	}
				
				
				
		//更新营运月开始结束时间
		public void updtMonthDate(List<OperatingMonth> weekList) {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			for(int i = 0;i<weekList.size();i++){
				session.merge(weekList.get(i));
				if(i%4 == 0){
					session.flush();
					session.clear();
				}
			}
		}

		//级联删除今年营运月
		public void delMonth(String s) {
			String hql = "delete from OperatingMonth as o where o.operatingMonthId like :s ";
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.createQuery(hql).setString("s",s+"______").executeUpdate();
			session.createQuery(hql).setString("s",s+"____").executeUpdate();
			session.createQuery(hql).setString("s",s+"__").executeUpdate();
			//session.close();
		}

		//查找今天所在的营运月 
		public List<OperatingMonth> findThisOperatingMonth(String areaType, String thisDate) {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			String hql = "from OperatingMonth o where " +
					" o.operatingStartDate <= :thisDate and o.operatingEndDate >= :thisDate " +
						"and o.operatingMonthType = :areaType " +
							"and o.operatingMonthTimeType = 1";
			return session.createQuery(hql)
					.setString("thisDate",thisDate)
						.setString("areaType",areaType)
								.list();//.setCacheable(true)
		}

		//查找下一个营运月
		public List<OperatingMonth> findNextOperatingMonth(
				String operatingMonthType, String thisDate) {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			String hql ="from OperatingMonth o where  o.operatingStartDate > :thisDate " +
					"and o.operatingMonthType = :operatingMonthType and o.operatingMonthTimeType = 1";
			
			return session.createQuery(hql).setString("thisDate",thisDate)
				.setString("operatingMonthType",operatingMonthType)
					.list();//.setCacheable(true).
		}

		//查找当前日期的上一个营运月
		public List<OperatingMonth> findPrevOperatingMonth(
				String operatingMonthType, String thisDate) {
			
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			String hql ="from OperatingMonth o where  operatingEndDate < :thisDate " +
					"and o.operatingMonthType = :operatingMonthType and o.operatingMonthTimeType = 1";
			
			return session.createQuery(hql).setString("thisDate",thisDate)
				.setString("operatingMonthType",operatingMonthType)
					.list();//.setCacheable(true)
		}
		
		//查询营运月
		public OperatingMonth findOperatingMonthId(String operatingMonthId) {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			String hql ="from OperatingMonth as o where o.operatingMonthId = :operatingMonthId";
			return (OperatingMonth)session.createQuery(hql).setString("operatingMonthId",operatingMonthId).uniqueResult();
		}

		
		

	/*	public void addThisMoth(OperatingMonth operatingMonthSeason) {
			String hql = "insert into OperatingMonth values" +
					"	(':operatingMonthId',':operatingMonth',':operatingMonthDate'," +
					" ':operatingMonthName',':operatingStartDate',':operatingEndDate',':operatingMonthType'," +
					" ':operatingMonthCount',':operatingMonthBz',':opeeratingMonthSize')";
			
			System.out.println(operatingMonthSeason.getOperatingMonthId() == null);
			String sql = "insert into OperatingMonth(operatingMonthId,operatingMonthDate,operatingMonthName," +
					"operatingStartDate,operatingEndDate,operatingMonthType,operatingMonthCount,operatingMonthBz,opeeratingMonthSize)" +
					"values("+operatingMonthSeason.getOperatingMonth().getOperatingMonthId()
						+","+operatingMonthSeason.getOperatingMonthDate()+","+operatingMonthSeason.getOperatingMonthName()
							+","+operatingMonthSeason.getOperatingStartDate()+","+operatingMonthSeason.getOperatingEndDate()
								+","+operatingMonthSeason.getOperatingMonthType()+","+operatingMonthSeason.getOperatingMonthCount()
									+","+operatingMonthSeason.getOperatingMonthBz()+","+operatingMonthSeason.getOpeeratingMonthSize()+")";
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			session.createSQLQuery(sql).executeUpdate();
		}*/
	
	
	
}