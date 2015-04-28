package com.gialen.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.main.action.ToolAction;
import com.gialen.model.Branch;
import com.gialen.model.OperatingMonth;
import com.gialen.model.SaleDailyYymm;
import com.gialen.model.SaleDailyYymmId;

/**
 	* A data access object (DAO) providing persistence and search support for SaleDailyYymm entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.gialen.model.SaleDailyYymm
  * @author MyEclipse Persistence Tools 
 */

public class SaleDailyYymmDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(SaleDailyYymmDAO.class);
		//property constants
	public static final String HQID = "hqid";
	public static final String BARCODE = "barcode";
	public static final String DONATE_TYPE = "donateType";
	public static final String TRANS_TYPE = "transType";
	public static final String PRODUCT_PMT_PLAN_NO = "productPmtPlanNo";
	public static final String BRAND_PMT_PLAN_NO = "brandPmtPlanNo";
	public static final String TRANS_PMT_PLAN_NO = "transPmtPlanNo";
	public static final String PRODUCT_TYPE = "productType";
	public static final String SALE_TAX = "saleTax";
	public static final String POS_NO = "posNo";
	public static final String SALER_ID = "salerId";
	public static final String SALE_QTY = "saleQty";
	public static final String SALE_AMT = "saleAmt";
	public static final String SALE_DIS_AMT = "saleDisAmt";
	public static final String TRANS_DIS_AMT = "transDisAmt";
	public static final String NORMAL_PRICE = "normalPrice";
	public static final String CUR_PRICE = "curPrice";
	public static final String LAST_COST_PRICE = "lastCostPrice";
	public static final String MEM_CARD_NO = "memCardNo";
	public static final String INVOICE_ID = "invoiceId";
	public static final String POINTS1 = "points1";
	public static final String POINTS = "points";
	public static final String RETURN_RAT = "returnRat";
	public static final String SENDFLAG = "sendflag";
	public static final String CLASS_PMT_PLAN_NO = "classPmtPlanNo";
	public static final String BRAND_CLASS_PMT_PLAN_NO = "brandClassPmtPlanNo";
	public static final String PRODUCT_CLUSTER_PMT_PLAN_NO = "productClusterPmtPlanNo";
	public static final String PCASH_PAY_AMT = "pcashPayAmt";
	public static final String INTEGRAL_PAY_AMT = "integralPayAmt";



	protected void initDao() {
		//do nothing
	}
    
    public void save(SaleDailyYymm transientInstance) {
        log.debug("saving SaleDailyYymm instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(SaleDailyYymm persistentInstance) {
        log.debug("deleting SaleDailyYymm instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public SaleDailyYymm findById( com.gialen.model.SaleDailyYymmId id) {
        log.debug("getting SaleDailyYymm instance with id: " + id);
        try {
            SaleDailyYymm instance = (SaleDailyYymm) getHibernateTemplate()
                    .get("com.gialen.model.SaleDailyYymm", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(SaleDailyYymm instance) {
        log.debug("finding SaleDailyYymm instance by example");
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
      log.debug("finding SaleDailyYymm instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from SaleDailyYymm as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByHqid(Object hqid
	) {
		return findByProperty(HQID, hqid
		);
	}
	
	public List findByBarcode(Object barcode
	) {
		return findByProperty(BARCODE, barcode
		);
	}
	
	public List findByDonateType(Object donateType
	) {
		return findByProperty(DONATE_TYPE, donateType
		);
	}
	
	public List findByTransType(Object transType
	) {
		return findByProperty(TRANS_TYPE, transType
		);
	}
	
	public List findByProductPmtPlanNo(Object productPmtPlanNo
	) {
		return findByProperty(PRODUCT_PMT_PLAN_NO, productPmtPlanNo
		);
	}
	
	public List findByBrandPmtPlanNo(Object brandPmtPlanNo
	) {
		return findByProperty(BRAND_PMT_PLAN_NO, brandPmtPlanNo
		);
	}
	
	public List findByTransPmtPlanNo(Object transPmtPlanNo
	) {
		return findByProperty(TRANS_PMT_PLAN_NO, transPmtPlanNo
		);
	}
	
	public List findByProductType(Object productType
	) {
		return findByProperty(PRODUCT_TYPE, productType
		);
	}
	
	public List findBySaleTax(Object saleTax
	) {
		return findByProperty(SALE_TAX, saleTax
		);
	}
	
	public List findByPosNo(Object posNo
	) {
		return findByProperty(POS_NO, posNo
		);
	}
	
	public List findBySalerId(Object salerId
	) {
		return findByProperty(SALER_ID, salerId
		);
	}
	
	public List findBySaleQty(Object saleQty
	) {
		return findByProperty(SALE_QTY, saleQty
		);
	}
	
	public List findBySaleAmt(Object saleAmt
	) {
		return findByProperty(SALE_AMT, saleAmt
		);
	}
	
	public List findBySaleDisAmt(Object saleDisAmt
	) {
		return findByProperty(SALE_DIS_AMT, saleDisAmt
		);
	}
	
	public List findByTransDisAmt(Object transDisAmt
	) {
		return findByProperty(TRANS_DIS_AMT, transDisAmt
		);
	}
	
	public List findByNormalPrice(Object normalPrice
	) {
		return findByProperty(NORMAL_PRICE, normalPrice
		);
	}
	
	public List findByCurPrice(Object curPrice
	) {
		return findByProperty(CUR_PRICE, curPrice
		);
	}
	
	public List findByLastCostPrice(Object lastCostPrice
	) {
		return findByProperty(LAST_COST_PRICE, lastCostPrice
		);
	}
	
	public List findByMemCardNo(Object memCardNo
	) {
		return findByProperty(MEM_CARD_NO, memCardNo
		);
	}
	
	public List findByInvoiceId(Object invoiceId
	) {
		return findByProperty(INVOICE_ID, invoiceId
		);
	}
	
	public List findByPoints1(Object points1
	) {
		return findByProperty(POINTS1, points1
		);
	}
	
	public List findByPoints(Object points
	) {
		return findByProperty(POINTS, points
		);
	}
	
	public List findByReturnRat(Object returnRat
	) {
		return findByProperty(RETURN_RAT, returnRat
		);
	}
	
	public List findBySendflag(Object sendflag
	) {
		return findByProperty(SENDFLAG, sendflag
		);
	}
	
	public List findByClassPmtPlanNo(Object classPmtPlanNo
	) {
		return findByProperty(CLASS_PMT_PLAN_NO, classPmtPlanNo
		);
	}
	
	public List findByBrandClassPmtPlanNo(Object brandClassPmtPlanNo
	) {
		return findByProperty(BRAND_CLASS_PMT_PLAN_NO, brandClassPmtPlanNo
		);
	}
	
	public List findByProductClusterPmtPlanNo(Object productClusterPmtPlanNo
	) {
		return findByProperty(PRODUCT_CLUSTER_PMT_PLAN_NO, productClusterPmtPlanNo
		);
	}
	
	public List findByPcashPayAmt(Object pcashPayAmt
	) {
		return findByProperty(PCASH_PAY_AMT, pcashPayAmt
		);
	}
	
	public List findByIntegralPayAmt(Object integralPayAmt
	) {
		return findByProperty(INTEGRAL_PAY_AMT, integralPayAmt
		);
	}
	

	public List findAll() {
		log.debug("finding all SaleDailyYymm instances");
		try {
			String queryString = "from SaleDailyYymm";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public SaleDailyYymm merge(SaleDailyYymm detachedInstance) {
        log.debug("merging SaleDailyYymm instance");
        try {
            SaleDailyYymm result = (SaleDailyYymm) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(SaleDailyYymm instance) {
        log.debug("attaching dirty SaleDailyYymm instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(SaleDailyYymm instance) {
        log.debug("attaching clean SaleDailyYymm instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static SaleDailyYymmDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (SaleDailyYymmDAO) ctx.getBean("SaleDailyYymmDAO");
	}
	
	//查询指定营运月 门店销售总金额(效率超低)
	public Double getthisStorethisMonthSaleSum(
			OperatingMonth thisOperatingMonth, Branch branch) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="select sum(saleAmt)-sum(pcashPayAmt)  from  SaleDailyYymm as s " +
				"where s.id.saleDate between :startDate and :endDate and " +
				"s.id.branch.braId = :branch";
		
		SimpleDateFormat smdt  = new SimpleDateFormat("yyyy.MM.dd");
		
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = smdt.parse(thisOperatingMonth.getOperatingStartDate());
			endDate = smdt.parse(thisOperatingMonth.getOperatingEndDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		Double sumSale  = (Double) session.createQuery(hql).setParameter("startDate",startDate)
			.setParameter("endDate",endDate)
				.setParameter("branch", branch.getBraId()).uniqueResult();
			
			return sumSale;
	}

	//查询当前门店 当前营运月销售记录
	public List<SaleDailyYymm> findthisMonththisBrandSaleList(
			OperatingMonth thisOperatingMonth, Branch branch) {

		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="select new SaleDailyYymm(saleAmt,pcashPayAmt) from  SaleDailyYymm as s " +
				"where s.id.saleDate between :startDate and :endDate and " +
				"s.id.branch.braId = :branch";
		
		List<Date> dateList = ToolAction.getMyFormatDate(thisOperatingMonth.getOperatingStartDate(),thisOperatingMonth.getOperatingEndDate());
		
		return session.createQuery(hql).setParameter("startDate",dateList.get(0))
			.setParameter("endDate",dateList.get(1))
				.setParameter("branch", branch.getBraId()).list();
		
	}
	
	//查询上个营运月的所有销售记录
	public List<SaleDailyYymm> findThisMonthSale(Date startDate,Date endDate) {
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql =" from  SaleDailyYymm as s " +
				"where s.id.saleDate between :startDate and :endDate";
		return session.createQuery(hql).setParameter("startDate", startDate)
			.setParameter("endDate", endDate).list();
	}

	//查询上个营运月本门店的所有销售记录
	public List<SaleDailyYymm> findThisMonthSale(Date startDate, Date endDate,
			String braId,String thisMonthId,List<String> thisMonthProjectsProidList) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="from  SaleDailyYymm as s " +
				"where s.id.saleDate between :startDate and :endDate and " +
				"s.id.branch.braId = :braId and s.saleAmt != 0 and " +
				"s.id.product.proId in (:thisMonthProjectsProidList)";
		//.setString("thisMonthId",thisMonthId)
		return	session.createQuery(hql).setParameter("startDate",startDate)
			.setParameter("endDate", endDate).setString("braId",braId)
			.setParameterList("thisMonthProjectsProidList",thisMonthProjectsProidList).list();
	
	}
}