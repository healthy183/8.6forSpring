package com.gialen.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.Product;
import com.gialen.model.ProductBrand;

/**
 	* A data access object (DAO) providing persistence and search support for Product entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.gialen.model.Product
  * @author MyEclipse Persistence Tools 
 */

public class ProductDAO extends HibernateDaoSupport  {
	private static final Logger log = LoggerFactory.getLogger(ProductDAO.class);
		//property constants
	public static final String BARCODE = "barcode";
	public static final String PRO_NAME = "proName";
	public static final String PRO_SNAME = "proSname";
	public static final String SPEC = "spec";
	public static final String STAT_ID = "statId";
	public static final String GRADE = "grade";
	public static final String AREA = "area";
	public static final String SUP_ID = "supId";
	public static final String MEASURE_ID = "measureId";
	public static final String PACKET_QTY = "packetQty";
	public static final String PACKET_QTY1 = "packetQty1";
	public static final String WEIGHT = "weight";
	public static final String LENGTH = "length";
	public static final String WIDTH = "width";
	public static final String HEIGHT = "height";
	public static final String TAX_TYPE = "taxType";
	public static final String IN_TAX = "inTax";
	public static final String SALE_TAX = "saleTax";
	public static final String IN_PRICE = "inPrice";
	public static final String TAX_PRICE = "taxPrice";
	public static final String NORMAL_PRICE = "normalPrice";
	public static final String MEMBER_PRICE = "memberPrice";
	public static final String GROUP_PRICE = "groupPrice";
	public static final String MAIN_FLAG = "mainFlag";
	public static final String PRO_FLAG = "proFlag";
	public static final String WEIGHT_FLAG = "weightFlag";
	public static final String BARMODE = "barmode";
	public static final String ORDER_MODE = "orderMode";
	public static final String MIN_ORDER_QTY = "minOrderQty";
	public static final String ORDER_MULTIPLIER = "orderMultiplier";
	public static final String FRESH_MODE = "freshMode";
	public static final String RETURN_RAT = "returnRat";
	public static final String WARRANTY_DAYS = "warrantyDays";
	public static final String UDF1 = "udf1";
	public static final String UDF2 = "udf2";
	public static final String UDF3 = "udf3";
	public static final String STATUS = "status";
	public static final String PROMT_FLAG = "promtFlag";
	public static final String POT_FLAG = "potFlag";
	public static final String CAN_CHANGE_PRICE = "canChangePrice";
	public static final String AVGCOSTPRICE = "avgcostprice";
	public static final String CARDPOINT = "cardpoint";
	public static final String SUP_PMT_FLAG = "supPmtFlag";
	public static final String OPERATORID = "operatorid";



	protected void initDao() {
		//do nothing
	}
    
    public void save(Product transientInstance) {
        log.debug("saving Product instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Product persistentInstance) {
        log.debug("deleting Product instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Product findById( java.lang.String id) {
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
    
    
    public List findByExample(Product instance) {
        log.debug("finding Product instance by example");
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
      log.debug("finding Product instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Product as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByBarcode(Object barcode
	) {
		return findByProperty(BARCODE, barcode
		);
	}
	
	public List findByProName(Object proName
	) {
		return findByProperty(PRO_NAME, proName
		);
	}
	
	public List findByProSname(Object proSname
	) {
		return findByProperty(PRO_SNAME, proSname
		);
	}
	
	public List findBySpec(Object spec
	) {
		return findByProperty(SPEC, spec
		);
	}
	
	public List findByStatId(Object statId
	) {
		return findByProperty(STAT_ID, statId
		);
	}
	
	public List findByGrade(Object grade
	) {
		return findByProperty(GRADE, grade
		);
	}
	
	public List findByArea(Object area
	) {
		return findByProperty(AREA, area
		);
	}
	
	public List findBySupId(Object supId
	) {
		return findByProperty(SUP_ID, supId
		);
	}
	
	public List findByMeasureId(Object measureId
	) {
		return findByProperty(MEASURE_ID, measureId
		);
	}
	
	public List findByPacketQty(Object packetQty
	) {
		return findByProperty(PACKET_QTY, packetQty
		);
	}
	
	public List findByPacketQty1(Object packetQty1
	) {
		return findByProperty(PACKET_QTY1, packetQty1
		);
	}
	
	public List findByWeight(Object weight
	) {
		return findByProperty(WEIGHT, weight
		);
	}
	
	public List findByLength(Object length
	) {
		return findByProperty(LENGTH, length
		);
	}
	
	public List findByWidth(Object width
	) {
		return findByProperty(WIDTH, width
		);
	}
	
	public List findByHeight(Object height
	) {
		return findByProperty(HEIGHT, height
		);
	}
	
	public List findByTaxType(Object taxType
	) {
		return findByProperty(TAX_TYPE, taxType
		);
	}
	
	public List findByInTax(Object inTax
	) {
		return findByProperty(IN_TAX, inTax
		);
	}
	
	public List findBySaleTax(Object saleTax
	) {
		return findByProperty(SALE_TAX, saleTax
		);
	}
	
	public List findByInPrice(Object inPrice
	) {
		return findByProperty(IN_PRICE, inPrice
		);
	}
	
	public List findByTaxPrice(Object taxPrice
	) {
		return findByProperty(TAX_PRICE, taxPrice
		);
	}
	
	public List findByNormalPrice(Object normalPrice
	) {
		return findByProperty(NORMAL_PRICE, normalPrice
		);
	}
	
	public List findByMemberPrice(Object memberPrice
	) {
		return findByProperty(MEMBER_PRICE, memberPrice
		);
	}
	
	public List findByGroupPrice(Object groupPrice
	) {
		return findByProperty(GROUP_PRICE, groupPrice
		);
	}
	
	public List findByMainFlag(Object mainFlag
	) {
		return findByProperty(MAIN_FLAG, mainFlag
		);
	}
	
	public List findByProFlag(Object proFlag
	) {
		return findByProperty(PRO_FLAG, proFlag
		);
	}
	
	public List findByWeightFlag(Object weightFlag
	) {
		return findByProperty(WEIGHT_FLAG, weightFlag
		);
	}
	
	public List findByBarmode(Object barmode
	) {
		return findByProperty(BARMODE, barmode
		);
	}
	
	public List findByOrderMode(Object orderMode
	) {
		return findByProperty(ORDER_MODE, orderMode
		);
	}
	
	public List findByMinOrderQty(Object minOrderQty
	) {
		return findByProperty(MIN_ORDER_QTY, minOrderQty
		);
	}
	
	public List findByOrderMultiplier(Object orderMultiplier
	) {
		return findByProperty(ORDER_MULTIPLIER, orderMultiplier
		);
	}
	
	public List findByFreshMode(Object freshMode
	) {
		return findByProperty(FRESH_MODE, freshMode
		);
	}
	
	public List findByReturnRat(Object returnRat
	) {
		return findByProperty(RETURN_RAT, returnRat
		);
	}
	
	public List findByWarrantyDays(Object warrantyDays
	) {
		return findByProperty(WARRANTY_DAYS, warrantyDays
		);
	}
	
	public List findByUdf1(Object udf1
	) {
		return findByProperty(UDF1, udf1
		);
	}
	
	public List findByUdf2(Object udf2
	) {
		return findByProperty(UDF2, udf2
		);
	}
	
	public List findByUdf3(Object udf3
	) {
		return findByProperty(UDF3, udf3
		);
	}
	
	public List findByStatus(Object status
	) {
		return findByProperty(STATUS, status
		);
	}
	
	public List findByPromtFlag(Object promtFlag
	) {
		return findByProperty(PROMT_FLAG, promtFlag
		);
	}
	
	public List findByPotFlag(Object potFlag
	) {
		return findByProperty(POT_FLAG, potFlag
		);
	}
	
	public List findByCanChangePrice(Object canChangePrice
	) {
		return findByProperty(CAN_CHANGE_PRICE, canChangePrice
		);
	}
	
	public List findByAvgcostprice(Object avgcostprice
	) {
		return findByProperty(AVGCOSTPRICE, avgcostprice
		);
	}
	
	public List findByCardpoint(Object cardpoint
	) {
		return findByProperty(CARDPOINT, cardpoint
		);
	}
	
	public List findBySupPmtFlag(Object supPmtFlag
	) {
		return findByProperty(SUP_PMT_FLAG, supPmtFlag
		);
	}
	
	public List findByOperatorid(Object operatorid
	) {
		return findByProperty(OPERATORID, operatorid
		);
	}
	

	public List findAll() {
		log.debug("finding all Product instances");
		try {
			String queryString = "from Product";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Product merge(Product detachedInstance) {
        log.debug("merging Product instance");
        try {
            Product result = (Product) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Product instance) {
        log.debug("attaching dirty Product instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Product instance) {
        log.debug("attaching clean Product instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static ProductDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (ProductDAO) ctx.getBean("ProductDAO");
	}
	
	
	//单品编号名称 模糊查询 //,productClass,productBrand
	public List<Object[]> dwrFindProduct
		(String proIdKeywordVal,String barcodeKeywordVar,String proNameKeyWordVal,
				List<String> proFlaglist,List<String> ProStatuslist,List<String> proIdClassStrlist) {
			String hql ="select distinct p.proId,p.productClass.className,p.productBrand.brandName,p.barcode,p.proName,p.spec,p.status,p.proFlag " +
					"from Product as p " +
						"where p.proId like :proIdKeywordVal and p.barcode like :barcodeKeywordVar and " +
							"p.proName like :proNameKeyWordVal and " +
								"p.proFlag in (:proFlaglist) and p.status in (:ProStatuslist)  order by p.proId " ;
								//"and   p.proId not in (:proIdClassStrlist) order by p.proId";
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			return  session.createQuery(hql)
						.setString("proIdKeywordVal","%"+proIdKeywordVal+"%")
							.setString("barcodeKeywordVar","%"+barcodeKeywordVar+"%")
								.setString("proNameKeyWordVal","%"+proNameKeyWordVal+"%")
									.setParameterList("proFlaglist", proFlaglist)
										.setParameterList("ProStatuslist", ProStatuslist)
										/*.setParameterList("proIdClassStrlist", proIdClassStrlist)*/
											.setCacheable(true).list();
		}
	
	//单品编号名称  类别class 模糊查询
		public List<Object[]> findProductByClass
			(String proIdKeywordVal,String barcodeKeywordVar,
				String proNameKeyWordVal, String showClassVal,
					List<String> proFlaglist,List<String> ProStatuslist,List<String> proIdClassStrlist) {

			String hql = "select distinct p.proId,p.productClass.className,p.productBrand.brandName,p.barcode,p.proName,p.spec,p.status,p.proFlag " +
					"from Product as p where p.proId like :proIdKeywordVal and p.barcode like :barcodeKeywordVar and "
					+ "p.proName like :proNameKeyWordVal and p.productClass.classId like :showClassVal and " +
					"p.proFlag in (:proFlaglist) and p.status in (:ProStatuslist)  order by p.proId " ;/*and " +
					"p.proId not in (:proIdClassStrlist) order by p.proId";*/
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			return session.createQuery(hql)
				.setString("proIdKeywordVal", "%"+proIdKeywordVal+"%")
					.setString("barcodeKeywordVar","%"+barcodeKeywordVar+"%")
						.setString("proNameKeyWordVal", "%"+proNameKeyWordVal+"%")
							.setString("showClassVal", showClassVal+"%")
								.setParameterList("proFlaglist", proFlaglist)
									.setParameterList("ProStatuslist", ProStatuslist)
										/*.setParameterList("proIdClassStrlist", proIdClassStrlist)*/
									    	.setCacheable(true).list();
		}
	
	//根据单品编号,单品名称 品牌 查询单品
		public List<Object[]> dwrFindProductByBrand(String proIdKeywordVal,String barcodeKeywordVar,
				String proNameKeyWordVal, String showbrandVal,List<String> proFlaglist,
					List<String> ProStatuslist,List<String> proIdClassStrlist) {
			String hql ="select distinct p.proId,p.productClass.className,p.productBrand.brandName,p.barcode,p.proName,p.spec,p.status,p.proFlag " +
					"from Product as p where p.proId like :proIdKeywordVal and p.barcode like :barcodeKeywordVar and " +
					"p.proName like :proNameKeyWordVal and p.productBrand.brandId like :showbrandVal and " +
					"p.proFlag in (:proFlaglist) and p.status in (:ProStatuslist) order by p.proId "; /*and " +
					"p.proId not in (:proIdClassStrlist) order by p.proId";*/
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			return  session.createQuery(hql)
					.setString("proIdKeywordVal","%"+proIdKeywordVal+"%")
						.setString("barcodeKeywordVar","%"+barcodeKeywordVar+"%")
							.setString("proNameKeyWordVal","%"+proNameKeyWordVal+"%")
								.setString("showbrandVal", showbrandVal+"%")
									.setParameterList("proFlaglist", proFlaglist)
										.setParameterList("ProStatuslist", ProStatuslist)
											/*.setParameterList("proIdClassStrlist", proIdClassStrlist)*/
												.setCacheable(true).list();
		}
	
	
	
	public List<Object[]> dwrFindProductByBrandAndClass
		(String proIdKeywordVal,String barcodeKeywordVar,
				String proNameKeyWordVal, String showbrandVal,
					String showClassVal,List<String> proFlaglist,
						List<String> ProStatuslist,List<String> proIdClassStrlist) {
		
		String hql = "select distinct p.proId,p.productClass.className,p.productBrand.brandName,p.barcode,p.proName,p.spec,p.status,p.proFlag " +
				"from Product as p where p.proId like :proIdKeywordVal and p.barcode like :barcodeKeywordVar and " +
				"p.proName like :proNameKeyWordVal and p.productClass.classId like :showClassVal and " +
				"p.productBrand.brandId like :showbrandVal and " +
				"p.proFlag in (:proFlaglist) and p.status in (:ProStatuslist) order by p.proId " ;/*and " +
				"p.proId not in (:proIdClassStrlist) order by p.proId";*/
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return	session.createQuery(hql)
					.setString("proIdKeywordVal","%"+proIdKeywordVal+"%")
						.setString("barcodeKeywordVar","%"+barcodeKeywordVar+"%")
							.setString("proNameKeyWordVal","%"+proNameKeyWordVal+"%")
								.setString("showClassVal",showClassVal+"%")
									.setString("showbrandVal", showbrandVal+"%")
										.setParameterList("proFlaglist", proFlaglist)
											.setParameterList("ProStatuslist", ProStatuslist)
												/*.setParameterList("proIdClassStrlist", proIdClassStrlist)*/
													.setCacheable(true).list();
	}

	 //查询当前单品项目下的所有单品
	public List<Product> findProductByProject(String productProjectId) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		//String myHql = "select distinct new Product(proId,proName) from Product as p " +
		//		"where p.proId in(select r.product.proId from ProProjectRelationTable as r where r.productProject.productProjectId = :productProjectId)";
		
		/*String hql ="select  p.product from ProProjectRelationTable as p where p.productProject.productProjectId = :productProjectId";
		return	session.createQuery(hql).setInteger("productProjectId",Integer.valueOf(productProjectId))
					.setCacheable(true).list();*/
		
		String  sql = "select r.proId,r.proName from product as r where r.proid " +
						"in(select p.ProId from proProjectRelationTable as p where p.productProjectId = ?) order by r.proId";
		List<Product> list =(List<Product>)session.createSQLQuery(sql).setInteger(0, Integer.valueOf(productProjectId)).list();
		return list;
	}
	
	
	
	//查询所有单品方案中单品的主键 
	public List<String> findThisMonthProjectsProid(String thisMonthId) {
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql = "select distinct p.product.proId " +
			"from ProProjectRelationTable as p " +
				"where p.productProject.operatingMonth.operatingMonthId = :thisMonthId";
		return session.createQuery(hql).setString("thisMonthId",thisMonthId).list();
	}

	
	

 
	
	
	
	//alpha 2012-8-10
	public List<ProductBrand> dwrFindProduct
	(String bigBrandVar,String middleBrandVar,String smallBrandVar ,List<String> proIdClassStrlist) {
 
		
		String hql ="select distinct p.id, p.brandId , p.brandName from ProductBrand as p where p.brandName not like '%停用' and p.brandId like :smallBrandVar and p.id not in (:proIdClassStrlist) ";
	//	System.out.println("SQL :"+hql);
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();			
		return  session.createQuery(hql)
				.setString("smallBrandVar", smallBrandVar)	
				.setParameterList("proIdClassStrlist", proIdClassStrlist)
				.setCacheable(true).list();
		
		
	}

}