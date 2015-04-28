package com.gialen.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.PlanBrand;
import com.gialen.model.ProProjectRelationTableGroup;
import com.gialen.model.Product;
import com.gialen.model.ProductBrand;
import com.gialen.model.ProductProject;

/**
 * A data access object (DAO) providing persistence and search support for
 * ProductProject entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.gialen.model.ProductProject
 * @author MyEclipse Persistence Tools
 */

public class ProductProjectDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ProductProjectDAO.class);
	// property constants
	public static final String PRODUCT_PROJECT_NAME = "productProjectName";
	public static final String PRODUCT_PROJECT_TYPE = "productProjectType";
	public static final String PRODUCT_PROJECT_VAL = "productProjectVal";
	public static final String IS_ADD_BRAND_WAGES = "isAddBrandWages";

	protected void initDao() {
		// do nothing
	}

	public void save(ProductProject transientInstance) {
		log.debug("saving ProductProject instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ProductProject persistentInstance) {
		log.debug("deleting ProductProject instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ProductProject findById(java.lang.Integer id) {
		log.debug("getting ProductProject instance with id: " + id);
		try {
			ProductProject instance = (ProductProject) getHibernateTemplate()
					.get("com.gialen.model.ProductProject", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ProductProject instance) {
		log.debug("finding ProductProject instance by example");
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
		log.debug("finding ProductProject instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ProductProject as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByProductProjectName(Object productProjectName) {
		return findByProperty(PRODUCT_PROJECT_NAME, productProjectName);
	}

	public List findByProductProjectType(Object productProjectType) {
		return findByProperty(PRODUCT_PROJECT_TYPE, productProjectType);
	}

	public List findByProductProjectVal(Object productProjectVal) {
		return findByProperty(PRODUCT_PROJECT_VAL, productProjectVal);
	}

	public List findByIsAddBrandWages(Object isAddBrandWages) {
		return findByProperty(IS_ADD_BRAND_WAGES, isAddBrandWages);
	}

	public List findAll() {
		log.debug("finding all ProductProject instances");
		try {
			String queryString = "from ProductProject";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ProductProject merge(ProductProject detachedInstance) {
		log.debug("merging ProductProject instance");
		try {
			ProductProject result = (ProductProject) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ProductProject instance) {
		log.debug("attaching dirty ProductProject instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ProductProject instance) {
		log.debug("attaching clean ProductProject instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProductProjectDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ProductProjectDAO) ctx.getBean("ProductProjectDAO");
	}
	
	//根据当前类型查找单品,类型，品牌方案
	public List<ProductProject> findthisMonthProjectBytype(
			String operatingMonthId, int projectType) {
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="from ProductProject as p where p.operatingMonth.operatingMonthId = :operatingMonthId and " +
				"p.projectType = :projectType and p.projectStatus = 0 order by p.productProjectId";
		return session.createQuery(hql)  //.setCacheable(true)
			.setString("operatingMonthId",operatingMonthId.trim())
					.setInteger("projectType",projectType).list();
		
	}
	
	
	
	
	
	

	//根据当前营运月 和类型 0单品方案 1品牌方案 2类别方案   查找品牌方案(分页)
	public List<ProductProject> findthisMonthProjectBytypeForpage(
			String operatingMonthId, int projectType, int pageNum, int pageSize) {
		
		/*if(pageNum < 1){
			pageNum = 1;
		}(pageNum-1)*/
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="from ProductProject as p where p.operatingMonth.operatingMonthId = :operatingMonthId and " +
				"p.projectType = :projectType and p.projectStatus = 0";
		return session.createQuery(hql)  //.setCacheable(true)
			.setString("operatingMonthId",operatingMonthId.trim())
					.setInteger("projectType",projectType)
						.setFirstResult((pageNum)*pageSize).setMaxResults(pageSize).list();
		
		
	}
	
	//分页查找指定月的作废项目
		public List<ProductProject> findthisMonthInvalidProjectForpage(
				String operatingMonthId, int projectType, int pagenum, int pageSize) {
			
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			String hql ="from ProductProject as p where p.operatingMonth.operatingMonthId = :operatingMonthId and " +
					"p.projectType = :projectType and p.projectStatus = 1";
			return session.createQuery(hql)  //.setCacheable(true)
				.setString("operatingMonthId",operatingMonthId.trim())
						.setInteger("projectType",projectType)
							.setFirstResult((pagenum)*pageSize).setMaxResults(pageSize).list();
			
		}
	
	
	
	
	

	//根据当前营运月 和类型 0单品方案 1品牌方案 2类别方案   查找品牌方案 总行数
	public long findthisMonthProjectBytypeRownums(String operatingMonthId,
			int projectType) {
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="select count(*) from ProductProject as p where p.operatingMonth.operatingMonthId = :operatingMonthId and " +
				"p.projectType = :projectType and p.projectStatus = 0";
		return (Long) session.createQuery(hql)  //.setCacheable(true)
			.setString("operatingMonthId",operatingMonthId.trim())
					.setInteger("projectType",projectType).uniqueResult();
	}
	//根据当前营运月 和类型 0单品方案 1品牌方案 2类别方案   查找作废品牌方案 总行数
	public long findthisMonthInvalidProjectRownums(String operatingMonthId,
			int projectType) {
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="select count(*) from ProductProject as p where p.operatingMonth.operatingMonthId = :operatingMonthId and " +
				"p.projectType = :projectType and p.projectStatus = 1";
		return (Long) session.createQuery(hql)  //.setCacheable(true)
			.setString("operatingMonthId",operatingMonthId.trim())
					.setInteger("projectType",projectType).uniqueResult();
	}
	
	
	
	
	
	//根据id查找ProductProject
	public ProductProject findProductProjectById(Integer valueOf) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="from ProductProject as p where p.productProjectId = :valueOf";
		return (ProductProject)session.createQuery(hql).setInteger("valueOf",valueOf).list().get(0);
	}
	
	

	
	//alpha 2012-8-13 保存品牌
	public void save(PlanBrand transientInstance) {
		log.debug("saving ProductProject instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	//alpha 2012-8-14  通过id查找品牌
	public ProductBrand findById(java.lang.String id) {
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
	
	
	//alpha 2012-8-14   
	public Product find_productById(java.lang.String id) {
		log.debug("getting Product instance with id: " + id);
		try {
			Product instance = (Product) getHibernateTemplate()
					.get("com.gialen.model.Product", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
	
	//alpha 2012-8-17 删除当前品牌提成方案所有关系表
	public void delAllChlidren(Integer proProjectId) {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			String hql ="delete from PlanBrand as p " +
					"where p.productProject.productProjectId = :proProjectId";
			 session.createQuery(hql).setInteger("proProjectId", proProjectId).executeUpdate();
	}
	
	//根据当前类型查找单品,类型，品牌方案
		public List<ProductProject> findthisMonthProjectBytype(
				String operatingMonthId ) {
			//System.out.println(operatingMonthId.equals("020120308"));
			//System.out.println(projectType == 0);
			
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			String hql ="from ProductProject as p where p.operatingMonth.operatingMonthId = :operatingMonthId and " +
					" p.projectStatus = 0";
			return session.createQuery(hql)  //.setCacheable(true)
				.setString("operatingMonthId",operatingMonthId.trim()).list();
						 
			
		}
		
		
		
		
		
		
		
		
		
		//2012-9-4    //取得有定额方案的数量  
		public List<ProductProject> findthisMonthProjectByProjectType_ProductProjectType(
				String operatingMonthId, int projectType,int productProjectType) {
			//System.out.println(operatingMonthId.equals("020120308"));
			//System.out.println(projectType == 0);
			
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			String hql ="from ProductProject as p where p.operatingMonth.operatingMonthId = :operatingMonthId and " +
					"p.projectType = :projectType and p.projectStatus = 0 and p.productProjectType =:productProjectType order by p.productProjectId";
			return session.createQuery(hql)  //.setCacheable(true)
				.setString("operatingMonthId",operatingMonthId)
				.setInteger("productProjectType",productProjectType)
						.setInteger("projectType",projectType).list();
			
		}
		
		//根据单品id 营运月id 查找单品方案
		public List<ProductProject> findProjectByProIdAndMonthId(String proId,
				String operatingMonthId) {
			
			Session session = getHibernateTemplate().getSessionFactory().openSession();
			String hql = "select new ProductProject(productProjectType,productProjectVal) from ProductProject as p " +
				"where p.operatingMonth.operatingMonthId = :operatingMonthId " +
					"and p.projectType = 0 and p.projectStatus = 0 and p.productProjectId " +
						"in (select distinct t.productProject.productProjectId  " +
							"from ProProjectRelationTable as t where t.product.proId = :proId)";
			
			List<ProductProject> list =	session.createQuery(hql)
				.setString("operatingMonthId",operatingMonthId)
					.setString("proId",proId).list();
			
			session.close();
			  return list;
		}
		
		
		
		
		
		
		
}