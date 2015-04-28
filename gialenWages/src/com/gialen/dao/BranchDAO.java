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

import com.gialen.model.Branch;

/**
 	* A data access object (DAO) providing persistence and search support for Branch entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.gialen.model.Branch
  * @author MyEclipse Persistence Tools 
 */

public class BranchDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(BranchDAO.class);
		//property constants
	public static final String HQ_ID = "hqId";
	public static final String BRA_NAME = "braName";
	public static final String BRA_SNAME = "braSname";
	public static final String BRA_TYPE = "braType";
	public static final String BRACODE = "bracode";
	public static final String ADDR = "addr";
	public static final String TEL = "tel";
	public static final String FAX = "fax";
	public static final String ZIP_CODE = "zipCode";
	public static final String MASTER = "master";
	public static final String SIZE_CODE = "sizeCode";
	public static final String SQUARE = "square";
	public static final String ALLO_PRIORITY = "alloPriority";
	public static final String ALLO_DISCOUNT = "alloDiscount";
	public static final String ALLOPRICELEVEL = "allopricelevel";
	public static final String ALLO_PEROID = "alloPeroid";
	public static final String RESERVE_AMT = "reserveAmt";
	public static final String MANAGEAMT = "manageamt";
	public static final String PAYDATE = "paydate";
	public static final String WHID = "whid";
	public static final String SPRICE_QUOTIETY = "spriceQuotiety";
	public static final String MPRICE_QUOTIETY = "mpriceQuotiety";
	public static final String VEHICLE_NUM = "vehicleNum";
	public static final String EMP_COUNT = "empCount";
	public static final String MANAGEMODE = "managemode";
	public static final String PLACE = "place";
	public static final String STORETYPE = "storetype";
	public static final String STATUS = "status";
	public static final String SERIALNO = "serialno";
	public static final String PURCHASE_ID = "purchaseId";
	public static final String SETTLE_METHOD = "settleMethod";
	public static final String PAY_METHOD = "payMethod";
	public static final String SETTLE_DAYS = "settleDays";
	public static final String SALETYPE = "saletype";
	public static final String ALLOC_TYPE = "allocType";
	public static final String MAC_ADDR = "macAddr";
	public static final String SYS_REG_NO = "sysRegNo";
	public static final String MAC_REG_NO = "macRegNo";
	public static final String USEPERIOD = "useperiod";
	public static final String CLIENTNUM = "clientnum";
	public static final String ACCOUNTTYPE = "accounttype";



	protected void initDao() {
		//do nothing
	}
    
    public void save(Branch transientInstance) {
        log.debug("saving Branch instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Branch persistentInstance) {
        log.debug("deleting Branch instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Branch findById( java.lang.String id) {
        log.debug("getting Branch instance with id: " + id);
        try {
            Branch instance = (Branch) getHibernateTemplate()
                    .get("com.gialen.model.Branch", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Branch instance) {
        log.debug("finding Branch instance by example");
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
      log.debug("finding Branch instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Branch as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByHqId(Object hqId
	) {
		return findByProperty(HQ_ID, hqId
		);
	}
	
	public List findByBraName(Object braName
	) {
		return findByProperty(BRA_NAME, braName
		);
	}
	
	public List findByBraSname(Object braSname
	) {
		return findByProperty(BRA_SNAME, braSname
		);
	}
	
	public List findByBraType(Object braType
	) {
		return findByProperty(BRA_TYPE, braType
		);
	}
	
	public List findByBracode(Object bracode
	) {
		return findByProperty(BRACODE, bracode
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
	
	public List findByFax(Object fax
	) {
		return findByProperty(FAX, fax
		);
	}
	
	public List findByZipCode(Object zipCode
	) {
		return findByProperty(ZIP_CODE, zipCode
		);
	}
	
	public List findByMaster(Object master
	) {
		return findByProperty(MASTER, master
		);
	}
	
	public List findBySizeCode(Object sizeCode
	) {
		return findByProperty(SIZE_CODE, sizeCode
		);
	}
	
	public List findBySquare(Object square
	) {
		return findByProperty(SQUARE, square
		);
	}
	
	public List findByAlloPriority(Object alloPriority
	) {
		return findByProperty(ALLO_PRIORITY, alloPriority
		);
	}
	
	public List findByAlloDiscount(Object alloDiscount
	) {
		return findByProperty(ALLO_DISCOUNT, alloDiscount
		);
	}
	
	public List findByAllopricelevel(Object allopricelevel
	) {
		return findByProperty(ALLOPRICELEVEL, allopricelevel
		);
	}
	
	public List findByAlloPeroid(Object alloPeroid
	) {
		return findByProperty(ALLO_PEROID, alloPeroid
		);
	}
	
	public List findByReserveAmt(Object reserveAmt
	) {
		return findByProperty(RESERVE_AMT, reserveAmt
		);
	}
	
	public List findByManageamt(Object manageamt
	) {
		return findByProperty(MANAGEAMT, manageamt
		);
	}
	
	public List findByPaydate(Object paydate
	) {
		return findByProperty(PAYDATE, paydate
		);
	}
	
	public List findByWhid(Object whid
	) {
		return findByProperty(WHID, whid
		);
	}
	
	public List findBySpriceQuotiety(Object spriceQuotiety
	) {
		return findByProperty(SPRICE_QUOTIETY, spriceQuotiety
		);
	}
	
	public List findByMpriceQuotiety(Object mpriceQuotiety
	) {
		return findByProperty(MPRICE_QUOTIETY, mpriceQuotiety
		);
	}
	
	public List findByVehicleNum(Object vehicleNum
	) {
		return findByProperty(VEHICLE_NUM, vehicleNum
		);
	}
	
	public List findByEmpCount(Object empCount
	) {
		return findByProperty(EMP_COUNT, empCount
		);
	}
	
	public List findByManagemode(Object managemode
	) {
		return findByProperty(MANAGEMODE, managemode
		);
	}
	
	public List findByPlace(Object place
	) {
		return findByProperty(PLACE, place
		);
	}
	
	public List findByStoretype(Object storetype
	) {
		return findByProperty(STORETYPE, storetype
		);
	}
	
	public List findByStatus(Object status
	) {
		return findByProperty(STATUS, status
		);
	}
	
	public List findBySerialno(Object serialno
	) {
		return findByProperty(SERIALNO, serialno
		);
	}
	
	public List findByPurchaseId(Object purchaseId
	) {
		return findByProperty(PURCHASE_ID, purchaseId
		);
	}
	
	public List findBySettleMethod(Object settleMethod
	) {
		return findByProperty(SETTLE_METHOD, settleMethod
		);
	}
	
	public List findByPayMethod(Object payMethod
	) {
		return findByProperty(PAY_METHOD, payMethod
		);
	}
	
	public List findBySettleDays(Object settleDays
	) {
		return findByProperty(SETTLE_DAYS, settleDays
		);
	}
	
	public List findBySaletype(Object saletype
	) {
		return findByProperty(SALETYPE, saletype
		);
	}
	
	public List findByAllocType(Object allocType
	) {
		return findByProperty(ALLOC_TYPE, allocType
		);
	}
	
	public List findByMacAddr(Object macAddr
	) {
		return findByProperty(MAC_ADDR, macAddr
		);
	}
	
	public List findBySysRegNo(Object sysRegNo
	) {
		return findByProperty(SYS_REG_NO, sysRegNo
		);
	}
	
	public List findByMacRegNo(Object macRegNo
	) {
		return findByProperty(MAC_REG_NO, macRegNo
		);
	}
	
	public List findByUseperiod(Object useperiod
	) {
		return findByProperty(USEPERIOD, useperiod
		);
	}
	
	public List findByClientnum(Object clientnum
	) {
		return findByProperty(CLIENTNUM, clientnum
		);
	}
	
	public List findByAccounttype(Object accounttype
	) {
		return findByProperty(ACCOUNTTYPE, accounttype
		);
	}
	

	public List findAll() {
		log.debug("finding all Branch instances");
		try {
			String queryString = "from Branch";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Branch merge(Branch detachedInstance) {
        log.debug("merging Branch instance");
        try {
            Branch result = (Branch) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Branch instance) {
        log.debug("attaching dirty Branch instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Branch instance) {
        log.debug("attaching clean Branch instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static BranchDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (BranchDAO) ctx.getBean("BranchDAO");
	}

	//查询现在所有门店
	public List<Branch> findNowBrand() {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql = "from Branch as b where b.status != '9' or b.status is null ";
		return session.createQuery(hql).list();
	}
	
	
	
	
	
	
	
	
	
	
//	///alpha 
//	 public Branch findById( java.lang.String id) {
//	        log.debug("getting Branch instance with id: " + id);
//	        try {
//	            Branch instance = (Branch) getHibernateTemplate()
//	                    .get("com.gialen.model.Branch", id);
//	            return instance;
//	        } catch (RuntimeException re) {
//	            log.error("get failed", re);
//	            throw re;
//	        }
//	    }
}