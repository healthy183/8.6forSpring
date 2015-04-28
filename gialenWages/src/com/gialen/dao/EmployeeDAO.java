package com.gialen.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.Employee;

/**
 	* A data access object (DAO) providing persistence and search support for Employee entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.gialen.model.Employee
  * @author MyEclipse Persistence Tools 
 */

public class EmployeeDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(EmployeeDAO.class);
		//property constants
	public static final String PURCHASE_ID = "purchaseId";
	public static final String DEPT_ID = "deptId";
	public static final String EMP_NAME = "empName";
	public static final String HEADSHIP = "headship";
	public static final String SEX = "sex";
	public static final String ADDR = "addr";
	public static final String TEL = "tel";
	public static final String MOBILE = "mobile";
	public static final String EMP_TYPE = "empType";
	public static final String SECTION_ID = "sectionId";
	public static final String HEADUSER = "headuser";
	public static final String STATUS = "status";
	public static final String PERSONAL_ID = "personalId";
	public static final String REMARK = "remark";
	public static final String INCENTIVEROLEID = "incentiveroleid";



	protected void initDao() {
		//do nothing
	}
    
    public void save(Employee transientInstance) {
        log.debug("saving Employee instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Employee persistentInstance) {
        log.debug("deleting Employee instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Employee findById( java.lang.String id) {
        log.debug("getting Employee instance with id: " + id);
        try {
            Employee instance = (Employee) getHibernateTemplate()
                    .get("com.gialen.model.Employee", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Employee instance) {
        log.debug("finding Employee instance by example");
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
      log.debug("finding Employee instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Employee as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByPurchaseId(Object purchaseId
	) {
		return findByProperty(PURCHASE_ID, purchaseId
		);
	}
	
	public List findByDeptId(Object deptId
	) {
		return findByProperty(DEPT_ID, deptId
		);
	}
	
	public List findByEmpName(Object empName
	) {
		return findByProperty(EMP_NAME, empName
		);
	}
	
	public List findByHeadship(Object headship
	) {
		return findByProperty(HEADSHIP, headship
		);
	}
	
	public List findBySex(Object sex
	) {
		return findByProperty(SEX, sex
		);
	}
	
	public List findByAddr(Object addr
	) {
		return findByProperty(ADDR, addr
		);
	}
	
	public List findByTel(Object tel
	) {
		return findByProperty(TEL, tel
		);
	}
	
	public List findByMobile(Object mobile
	) {
		return findByProperty(MOBILE, mobile
		);
	}
	
	public List findByEmpType(Object empType
	) {
		return findByProperty(EMP_TYPE, empType
		);
	}
	
	public List findBySectionId(Object sectionId
	) {
		return findByProperty(SECTION_ID, sectionId
		);
	}
	
	public List findByHeaduser(Object headuser
	) {
		return findByProperty(HEADUSER, headuser
		);
	}
	
	public List findByStatus(Object status
	) {
		return findByProperty(STATUS, status
		);
	}
	
	public List findByPersonalId(Object personalId
	) {
		return findByProperty(PERSONAL_ID, personalId
		);
	}
	
	public List findByRemark(Object remark
	) {
		return findByProperty(REMARK, remark
		);
	}
	
	public List findByIncentiveroleid(Object incentiveroleid
	) {
		return findByProperty(INCENTIVEROLEID, incentiveroleid
		);
	}
	

	public List findAll() {
		log.debug("finding all Employee instances");
		try {
			String queryString = "from Employee";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Employee merge(Employee detachedInstance) {
        log.debug("merging Employee instance");
        try {
            Employee result = (Employee) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Employee instance) {
        log.debug("attaching dirty Employee instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Employee instance) {
        log.debug("attaching clean Employee instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static EmployeeDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (EmployeeDAO) ctx.getBean("EmployeeDAO");
	}
}