package com.gialen.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.District;

/**
 	* A data access object (DAO) providing persistence and search support for District entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.gialen.model.District
  * @author MyEclipse Persistence Tools 
 */

public class DistrictDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(DistrictDAO.class);
		//property constants
	public static final String DISTRICTCODE = "districtcode";
	public static final String DISTRICT_NAME = "districtName";
	public static final String PARENTCODE = "parentcode";
	public static final String LEVEL = "level";
	public static final String HASCHILD = "haschild";
	public static final String HASREL = "hasrel";



	protected void initDao() {
		//do nothing
	}
    
    public void save(District transientInstance) {
        log.debug("saving District instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(District persistentInstance) {
        log.debug("deleting District instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public District findById( java.lang.String id) {
        log.debug("getting District instance with id: " + id);
        try {
            District instance = (District) getHibernateTemplate()
                    .get("com.gialen.model.District", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(District instance) {
        log.debug("finding District instance by example");
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
      log.debug("finding District instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from District as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByDistrictcode(Object districtcode
	) {
		return findByProperty(DISTRICTCODE, districtcode
		);
	}
	
	public List findByDistrictName(Object districtName
	) {
		return findByProperty(DISTRICT_NAME, districtName
		);
	}
	
	public List findByParentcode(Object parentcode
	) {
		return findByProperty(PARENTCODE, parentcode
		);
	}
	
	public List findByLevel(Object level
	) {
		return findByProperty(LEVEL, level
		);
	}
	
	public List findByHaschild(Object haschild
	) {
		return findByProperty(HASCHILD, haschild
		);
	}
	
	public List findByHasrel(Object hasrel
	) {
		return findByProperty(HASREL, hasrel
		);
	}
	

	public List findAll() {
		log.debug("finding all District instances");
		try {
			String queryString = "from District";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public District merge(District detachedInstance) {
        log.debug("merging District instance");
        try {
            District result = (District) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(District instance) {
        log.debug("attaching dirty District instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(District instance) {
        log.debug("attaching clean District instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static DistrictDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (DistrictDAO) ctx.getBean("DistrictDAO");
	}
}