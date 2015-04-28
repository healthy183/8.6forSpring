package com.gialen.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.Product;
import com.gialen.model.ProductBrand;
import com.gialen.model.ProductClass;

/**
 	* A data access object (DAO) providing persistence and search support for ProductClass entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.gialen.model.ProductClass
  * @author MyEclipse Persistence Tools 
 */

public class ProductClassDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(ProductClassDAO.class);
		//property constants
	public static final String CLASS_ID = "classId";
	public static final String CLASS_NAME = "className";
	public static final String CLASS_TYPE = "classType";
	public static final String PARENTCODE = "parentcode";
	public static final String LEVEL = "level";
	public static final String HASCHILD = "haschild";
	public static final String HASREL = "hasrel";
	public static final String STATUS = "status";
	public static final String STATUS1 = "status1";



	protected void initDao() {
		//do nothing
	}
    
    public void save(ProductClass transientInstance) {
        log.debug("saving ProductClass instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(ProductClass persistentInstance) {
        log.debug("deleting ProductClass instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ProductClass findById( java.lang.String id) {
        log.debug("getting ProductClass instance with id: " + id);
        try {
            ProductClass instance = (ProductClass) getHibernateTemplate()
                    .get("com.gialen.model.ProductClass", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(ProductClass instance) {
        log.debug("finding ProductClass instance by example");
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
      log.debug("finding ProductClass instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from ProductClass as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByClassId(Object classId
	) {
		return findByProperty(CLASS_ID, classId
		);
	}
	
	public List findByClassName(Object className
	) {
		return findByProperty(CLASS_NAME, className
		);
	}
	
	public List findByClassType(Object classType
	) {
		return findByProperty(CLASS_TYPE, classType
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
	
	public List findByStatus(Object status
	) {
		return findByProperty(STATUS, status
		);
	}
	
	public List findByStatus1(Object status1
	) {
		return findByProperty(STATUS1, status1
		);
	}
	

	public List findAll() {
		log.debug("finding all ProductClass instances");
		try {
			String queryString = "from ProductClass";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public ProductClass merge(ProductClass detachedInstance) {
        log.debug("merging ProductClass instance");
        try {
            ProductClass result = (ProductClass) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ProductClass instance) {
        log.debug("attaching dirty ProductClass instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ProductClass instance) {
        log.debug("attaching clean ProductClass instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static ProductClassDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (ProductClassDAO) ctx.getBean("ProductClassDAO");
	}

	public List<ProductClass> findAllProductClass() {
		String hql ="from ProductClass as p where p.className not like :String";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return session.createQuery(hql).setString("String", "%停用%").setCacheable(true).list();
	}

	//根据 level 查找类别
	public List<ProductClass> getClassByLevel(String classLevel) {
		String hql ="from ProductClass as p where p.level = :classLevel and p.className not like :String";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return  session.createQuery(hql).setString("classLevel",classLevel).setString("String", "%停用%").setCacheable(true).list();
	}
	
	//查询不含停用的
	public List<ProductClass> findAllBigClass() {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="select new ProductClass(id,classId,className) from ProductClass as p where p.level = 1 and p.className not like :className";
		return session.createQuery(hql).setString("className", "%停用%").setCacheable(true).list();
	}

	//根据父类别查子类别
	public List<ProductClass> findClassByParentClass(String parentClassId,
			String level2) {
		String hql ="select new ProductClass(id,classId,className) from ProductClass as p " +
				"where p.classId like :parentClassId and p.level = :level2 and p.className not like :className";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return session.createQuery(hql).setString("className", "%停用%")
				.setString("parentClassId",parentClassId+"%").setString("level2",level2)
					.setCacheable(true).list();
	}

	
}