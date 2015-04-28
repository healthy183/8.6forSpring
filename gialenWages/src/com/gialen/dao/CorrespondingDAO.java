package com.gialen.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.Branch;
import com.gialen.model.Corresponding;
import com.gialen.model.OrgstdStruct;
import com.gialen.model.StoreCount;

/**
 	* A data access object (DAO) providing persistence and search support for Corresponding entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.gialen.model.Corresponding
  * @author MyEclipse Persistence Tools 
 */

public class CorrespondingDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(CorrespondingDAO.class);
		//property constants
	public static final String BRA_NAME = "braName";
	public static final String UNITNAME = "unitname";
	public static final String STORE_TYPE = "storeType";
	public static final String REMARK1 = "remark1";
	public static final String REMARK2 = "remark2";



	protected void initDao() {
		//do nothing
	}
    
    public void save(Corresponding transientInstance) {
        log.debug("saving Corresponding instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Corresponding persistentInstance) {
        log.debug("deleting Corresponding instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Corresponding findById( java.lang.Integer id) {
        log.debug("getting Corresponding instance with id: " + id);
        try {
            Corresponding instance = (Corresponding) getHibernateTemplate()
                    .get("com.gialen.model.Corresponding", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Corresponding instance) {
        log.debug("finding Corresponding instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Corresponding instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Corresponding as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByBraName(Object braName
	) {
		return findByProperty(BRA_NAME, braName
		);
	}
	
	public List findByUnitname(Object unitname
	) {
		return findByProperty(UNITNAME, unitname
		);
	}
	
	public List findByStoreType(Object storeType
	) {
		return findByProperty(STORE_TYPE, storeType
		);
	}
	
	public List findByRemark1(Object remark1
	) {
		return findByProperty(REMARK1, remark1
		);
	}
	
	public List findByRemark2(Object remark2
	) {
		return findByProperty(REMARK2, remark2
		);
	}
	

	public List findAll() {
		log.debug("finding all Corresponding instances");
		try {
			String queryString = "from Corresponding";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Corresponding merge(Corresponding detachedInstance) {
        log.debug("merging Corresponding instance");
        try {
            Corresponding result = (Corresponding) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Corresponding instance) {
        log.debug("attaching dirty Corresponding instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Corresponding instance) {
        log.debug("attaching clean Corresponding instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static CorrespondingDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (CorrespondingDAO) ctx.getBean("CorrespondingDAO");
	}

	public void mergeCorresponding(List<Corresponding> correspondingList) {
		 getHibernateTemplate().saveOrUpdateAll(correspondingList);
		
	}
	
	//根据佳讯门店编号 查询中间表信息
	public List<Corresponding> findCorrespondingByBrandId(String lastbraIdvar) {
		String hql ="from Corresponding as c where c.branch.braId = :lastbraIdvar";	
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return session.createQuery(hql).setString("lastbraIdvar",lastbraIdvar).list();	
	}
	
	public List<Corresponding> findCorrespondingByBrandId(
			String correspondingIdVar, String lastbraIdvar) {
		String hql ="from Corresponding as c where c.branch.braId = :lastbraIdvar " +
				"and c.correspondingId != :correspondingIdVar";	
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return session.createQuery(hql).setString("lastbraIdvar",lastbraIdvar)
					.setInteger("correspondingIdVar", Integer.valueOf(correspondingIdVar)).list();	
	}
	

	public List<Corresponding> findOrgstdStructByBrandId(String lastunitidvar) {
		String hql ="from Corresponding as c where c.orgstdStruct.unitcode = :lastunitidvar";	
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return session.createQuery(hql).setString("lastunitidvar",lastunitidvar).list();	
	}

	public List<Corresponding> findOrgstdStructByBrandId(
			String correspondingIdVar, String unitidVar) {
		String hql ="from Corresponding as c where c.orgstdStruct.unitcode = :unitidVar " +
				"and c.correspondingId != :correspondingIdVar";	
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return session.createQuery(hql).setString("unitidVar",unitidVar)
				.setInteger("correspondingIdVar", Integer.valueOf(correspondingIdVar)).list();	
	}

	//根据所有门店的unitId从对应表中查询的braId
	/*public List<String> findBraIdBynitIdList(List<String> unitIdStoreList) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="select distinct c.branch.braId from Corresponding as c where c.orgstdStruct.unitid in (:unitIdStoreList)";
		return session.createQuery(hql).setParameterList("unitIdStoreList", unitIdStoreList).list();
	}*/

	//根据佳讯门店获取中间表数据 设置一对多
	public List<Corresponding> findByBra(String braId) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="from Corresponding as c where c.branch.braId = :braId";
		return session.createQuery(hql).setString("braId", braId).list();
	}

	////根据所有门店的unitId 从表StoreCount查询出当前片区下所有门店实际和预计销售金额
	public List<StoreCount> findstoreCountByunitIdList(
			List<String> unitIdStoreList,String operatingMonthId) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		String hql = " from StoreCount as s " +
			"where s.orgstdStruct.unitid in (:unitIdStoreList) and " +
				"s.operatingMonth.operatingMonthId = :operatingMonthId ";
		return	session.createQuery(hql).setParameterList("unitIdStoreList",unitIdStoreList)
		.setString("operatingMonthId",operatingMonthId).list();
	}

	//根据hr门店编号 查询中间表佳讯门店
	public Branch findBranchByUnitid(String unitid) {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			String hql ="select distinct branch from Corresponding as c where c.orgstdStruct.unitid = :unitid ";
			return (Branch) session.createQuery(hql).setString("unitid",unitid).uniqueResult();
		}

	//C6 单点登录
		public List<Corresponding> lgnByOA(String loginCode) {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			String hql = "from Corresponding as c where c.LoginCode = :loginCode ";
			return session.createQuery(hql).setString("loginCode",loginCode).list();
		}

		////从中间表Corresponding  获取 braId  unitcode 
		public List<Object[]> findUnitcodeAndbraId(String unitcode) {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			String hql = "select c.branch.braId,c.orgstdStruct.unitcode,c.orgstdStruct.unitcode " +
					"from Corresponding as c " +
				"where c.orgstdStruct.unitcode like :unitcode and " +
					"c.orgstdStruct.unitcode != :notUnitcode";
			return 	session.createQuery(hql).setString("unitcode",unitcode+"%")
				.setString("notUnitcode",unitcode).list();
		}

	

	
	
	
	
	
	
}