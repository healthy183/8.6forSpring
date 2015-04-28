package com.gialen.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.ProductBrand;
import com.gialen.model.ProductClass;

/**
 	* A data access object (DAO) providing persistence and search support for ProductBrand entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.gialen.model.ProductBrand
  * @author MyEclipse Persistence Tools 
 */

public class ProductBrandDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(ProductBrandDAO.class);
		//property constants
	public static final String BRAND_ID = "brandId";
	public static final String BRAND_NAME = "brandName";
	public static final String PARENTCODE = "parentcode";
	public static final String LEVEL = "level";
	public static final String HASCHILD = "haschild";
	public static final String HASREL = "hasrel";
	public static final String REGION = "region";
	public static final String GRADE = "grade";
	public static final String DATAGROUPID = "datagroupid";



	protected void initDao() {
		//do nothing
	}
    
    public void save(ProductBrand transientInstance) {
        log.debug("saving ProductBrand instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(ProductBrand persistentInstance) {
        log.debug("deleting ProductBrand instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ProductBrand findById( java.lang.String id) {
        log.debug("getting ProductBrand instance with id: " + id);
        try {
            ProductBrand instance = (ProductBrand) getHibernateTemplate()
                    .get("com.gialen.model.ProductBrand", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(ProductBrand instance) {
        log.debug("finding ProductBrand instance by example");
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
      log.debug("finding ProductBrand instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from ProductBrand as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByBrandId(Object brandId
	) {
		return findByProperty(BRAND_ID, brandId
		);
	}
	
	public List findByBrandName(Object brandName
	) {
		return findByProperty(BRAND_NAME, brandName
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
	
	public List findByRegion(Object region
	) {
		return findByProperty(REGION, region
		);
	}
	
	public List findByGrade(Object grade
	) {
		return findByProperty(GRADE, grade
		);
	}
	
	public List findByDatagroupid(Object datagroupid
	) {
		return findByProperty(DATAGROUPID, datagroupid
		);
	}
	

	public List findAll() {
		log.debug("finding all ProductBrand instances");
		try {
			String queryString = "from ProductBrand";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public ProductBrand merge(ProductBrand detachedInstance) {
        log.debug("merging ProductBrand instance");
        try {
            ProductBrand result = (ProductBrand) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ProductBrand instance) {
        log.debug("attaching dirty ProductBrand instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ProductBrand instance) {
        log.debug("attaching clean ProductBrand instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static ProductBrandDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (ProductBrandDAO) ctx.getBean("ProductBrandDAO");
	}

	//根据level查询 大中小品牌
	public List<ProductBrand> getbrandByLevel(String brandLevel) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="select new ProductBrand(id,brandId,brandName) from ProductBrand as p where p.level = :brandLevel and " +
				" p.brandName not like :brandName"; 
		return session.createQuery(hql)
				.setString("brandLevel", brandLevel).setString("brandName","%停用%")
					.setCacheable(true).list();
	}

	 //根据父品牌查子品牌 
	public List<ProductBrand> findBrandByParentBrand(String bigBrandId,String level) {
		
		String hql ="select new ProductBrand(id,brandId,brandName) from ProductBrand as p " +
						"where p.brandId like :bigBrandId and  p.level = :brandLevel and " +
							"p.brandName not like :brandName";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return session.createQuery(hql)
			.setString("bigBrandId",bigBrandId+"%").setString("brandLevel",level)
				.setString("brandName","%停用%").setCacheable(true).list();
	}

	

	
}