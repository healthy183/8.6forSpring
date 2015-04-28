package com.gialen.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.SaleDailyProduct;
import com.gialen.model.SaleDailyProductId;

/**
 * A data access object (DAO) providing persistence and search support for
 * SaleDailyProduct entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.gialen.model.SaleDailyProduct
 * @author MyEclipse Persistence Tools
 */

public class SaleDailyProductDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(SaleDailyProductDAO.class);
	// property constants
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
		// do nothing
	}

	public void save(SaleDailyProduct transientInstance) {
		log.debug("saving SaleDailyProduct instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SaleDailyProduct persistentInstance) {
		log.debug("deleting SaleDailyProduct instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SaleDailyProduct findById(com.gialen.model.SaleDailyProductId id) {
		log.debug("getting SaleDailyProduct instance with id: " + id);
		try {
			SaleDailyProduct instance = (SaleDailyProduct) getHibernateTemplate()
					.get("com.gialen.model.SaleDailyProduct", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SaleDailyProduct instance) {
		log.debug("finding SaleDailyProduct instance by example");
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
		log.debug("finding SaleDailyProduct instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SaleDailyProduct as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByHqid(Object hqid) {
		return findByProperty(HQID, hqid);
	}

	public List findByBarcode(Object barcode) {
		return findByProperty(BARCODE, barcode);
	}

	public List findByDonateType(Object donateType) {
		return findByProperty(DONATE_TYPE, donateType);
	}

	public List findByTransType(Object transType) {
		return findByProperty(TRANS_TYPE, transType);
	}

	public List findByProductPmtPlanNo(Object productPmtPlanNo) {
		return findByProperty(PRODUCT_PMT_PLAN_NO, productPmtPlanNo);
	}

	public List findByBrandPmtPlanNo(Object brandPmtPlanNo) {
		return findByProperty(BRAND_PMT_PLAN_NO, brandPmtPlanNo);
	}

	public List findByTransPmtPlanNo(Object transPmtPlanNo) {
		return findByProperty(TRANS_PMT_PLAN_NO, transPmtPlanNo);
	}

	public List findByProductType(Object productType) {
		return findByProperty(PRODUCT_TYPE, productType);
	}

	public List findBySaleTax(Object saleTax) {
		return findByProperty(SALE_TAX, saleTax);
	}

	public List findByPosNo(Object posNo) {
		return findByProperty(POS_NO, posNo);
	}

	public List findBySalerId(Object salerId) {
		return findByProperty(SALER_ID, salerId);
	}

	public List findBySaleQty(Object saleQty) {
		return findByProperty(SALE_QTY, saleQty);
	}

	public List findBySaleAmt(Object saleAmt) {
		return findByProperty(SALE_AMT, saleAmt);
	}

	public List findBySaleDisAmt(Object saleDisAmt) {
		return findByProperty(SALE_DIS_AMT, saleDisAmt);
	}

	public List findByTransDisAmt(Object transDisAmt) {
		return findByProperty(TRANS_DIS_AMT, transDisAmt);
	}

	public List findByNormalPrice(Object normalPrice) {
		return findByProperty(NORMAL_PRICE, normalPrice);
	}

	public List findByCurPrice(Object curPrice) {
		return findByProperty(CUR_PRICE, curPrice);
	}

	public List findByLastCostPrice(Object lastCostPrice) {
		return findByProperty(LAST_COST_PRICE, lastCostPrice);
	}

	public List findByMemCardNo(Object memCardNo) {
		return findByProperty(MEM_CARD_NO, memCardNo);
	}

	public List findByInvoiceId(Object invoiceId) {
		return findByProperty(INVOICE_ID, invoiceId);
	}

	public List findByPoints1(Object points1) {
		return findByProperty(POINTS1, points1);
	}

	public List findByPoints(Object points) {
		return findByProperty(POINTS, points);
	}

	public List findByReturnRat(Object returnRat) {
		return findByProperty(RETURN_RAT, returnRat);
	}

	public List findBySendflag(Object sendflag) {
		return findByProperty(SENDFLAG, sendflag);
	}

	public List findByClassPmtPlanNo(Object classPmtPlanNo) {
		return findByProperty(CLASS_PMT_PLAN_NO, classPmtPlanNo);
	}

	public List findByBrandClassPmtPlanNo(Object brandClassPmtPlanNo) {
		return findByProperty(BRAND_CLASS_PMT_PLAN_NO, brandClassPmtPlanNo);
	}

	public List findByProductClusterPmtPlanNo(Object productClusterPmtPlanNo) {
		return findByProperty(PRODUCT_CLUSTER_PMT_PLAN_NO,
				productClusterPmtPlanNo);
	}

	public List findByPcashPayAmt(Object pcashPayAmt) {
		return findByProperty(PCASH_PAY_AMT, pcashPayAmt);
	}

	public List findByIntegralPayAmt(Object integralPayAmt) {
		return findByProperty(INTEGRAL_PAY_AMT, integralPayAmt);
	}

	public List findAll() {
		log.debug("finding all SaleDailyProduct instances");
		try {
			String queryString = "from SaleDailyProduct";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SaleDailyProduct merge(SaleDailyProduct detachedInstance) {
		log.debug("merging SaleDailyProduct instance");
		try {
			SaleDailyProduct result = (SaleDailyProduct) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SaleDailyProduct instance) {
		log.debug("attaching dirty SaleDailyProduct instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SaleDailyProduct instance) {
		log.debug("attaching clean SaleDailyProduct instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SaleDailyProductDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (SaleDailyProductDAO) ctx.getBean("SaleDailyProductDAO");
	}

	//执行统计 groud by 门店 公共账号  单品
	public List<Object[]> getSaleByBrandPulIdPro() {
		Session session = 	getHibernateTemplate().getSessionFactory().getCurrentSession();
		
	/*	String sql ="select SaleMan,ProId,sum(SaleQty),sum(SaleAmt-PCashPayAmt),BraId from saleDaily_product " +
			"group by SaleMan,ProId,BraId";
		return session.createSQLQuery(sql).list();*/
		
		String hql = "select s.employee.empId,s.id.product.proId," +
			"sum(s.saleQty),sum(s.saleAmt - s.pcashPayAmt),s.id.branch.braId " +
				"from SaleDailyProduct as s " +
					"group by s.employee.empId,s.id.branch.braId,s.id.product.proId";
		return session.createQuery(hql).list();
		
		
	}

	//删除公共账号记录，提高查询效率
	public void delAllPubIdSale() {
		Session session = 	getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "delete from saleDaily_product where SaleMan = '00000'";
		session.createQuery(sql).executeUpdate();
		session.flush();
	}
}