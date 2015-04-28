package com.gialen.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.OrgstdStruct;

/**
 	* A data access object (DAO) providing persistence and search support for OrgstdStruct entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.gialen.model.OrgstdStruct
  * @author MyEclipse Persistence Tools 
 */

public class OrgstdStructDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(OrgstdStructDAO.class);
		//property constants
	public static final String OID = "oid";
	public static final String LABEL = "label";
	public static final String LABEL_LENGTH = "labelLength";
	public static final String UNITINDEX = "unitindex";
	public static final String WORKERS = "workers";
	public static final String BELONGWORKERS = "belongworkers";
	public static final String UNITCODE = "unitcode";
	public static final String UNITNAME = "unitname";
	public static final String ISTEMPUNIT = "istempunit";
	public static final String CREATOR = "creator";
	public static final String CREATEDATE = "createdate";
	public static final String KILLER = "killer";
	public static final String KILLDATE = "killdate";
	public static final String LASTSAVEDATE = "lastsavedate";
	public static final String POSTCODE = "postcode";
	public static final String UNITDUTY = "unitduty";
	public static final String UNITGRADE = "unitgrade";
	public static final String ISCORECENTER = "iscorecenter";
	public static final String CORECENTERINFO = "corecenterinfo";
	public static final String ISLEGAL = "islegal";
	public static final String LEGALINFO = "legalinfo";
	public static final String FAX = "fax";
	public static final String DIRECTOR = "director";
	public static final String ADDRESS = "address";
	public static final String ASSISTANT = "assistant";
	public static final String UNITLEVEL = "unitlevel";
	public static final String ISMANAGEUNIT = "ismanageunit";
	public static final String UNITTYPE = "unittype";
	public static final String MANAGEUNIT = "manageunit";
	public static final String EDITION_ID = "editionId";
	public static final String LABELINDEX = "labelindex";
	public static final String ISDELETE = "isdelete";
	public static final String REMARK = "remark";
	public static final String CPCCHARACTER = "cpccharacter";
	public static final String ITEM1 = "item1";
	public static final String ITEM2 = "item2";
	public static final String ITEM3 = "item3";
	public static final String ITEM4 = "item4";
	public static final String ITEM5 = "item5";
	public static final String ITEM6 = "item6";
	public static final String ITEM7 = "item7";
	public static final String ITEM8 = "item8";
	public static final String ITEM9 = "item9";
	public static final String ITEM10 = "item10";
	public static final String ITEM11 = "item11";
	public static final String ITEM12 = "item12";
	public static final String ITEM13 = "item13";
	public static final String ITEM14 = "item14";
	public static final String ITEM15 = "item15";
	public static final String ITEM16 = "item16";
	public static final String ITEM17 = "item17";
	public static final String ITEM18 = "item18";
	public static final String ITEM19 = "item19";
	public static final String ITEM20 = "item20";
	public static final String LEGAL_ENTITY = "legalEntity";
	public static final String UNITENNAME = "unitenname";
	public static final String COSTCENTERID = "costcenterid";
	public static final String EFFECTDATE = "effectdate";
	public static final String STOPDATE = "stopdate";
	public static final String STOPOPERATIONDATE = "stopoperationdate";
	public static final String STOPUSERID = "stopuserid";



	protected void initDao() {
		//do nothing
	}
    
    public void save(OrgstdStruct transientInstance) {
        log.debug("saving OrgstdStruct instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(OrgstdStruct persistentInstance) {
        log.debug("deleting OrgstdStruct instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public OrgstdStruct findById( java.lang.String id) {
        log.debug("getting OrgstdStruct instance with id: " + id);
        try {
            OrgstdStruct instance = (OrgstdStruct) getHibernateTemplate()
                    .get("com.gialen.model.OrgstdStruct", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(OrgstdStruct instance) {
        log.debug("finding OrgstdStruct instance by example");
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
      log.debug("finding OrgstdStruct instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from OrgstdStruct as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByOid(Object oid
	) {
		return findByProperty(OID, oid
		);
	}
	
	public List findByLabel(Object label
	) {
		return findByProperty(LABEL, label
		);
	}
	
	public List findByLabelLength(Object labelLength
	) {
		return findByProperty(LABEL_LENGTH, labelLength
		);
	}
	
	public List findByUnitindex(Object unitindex
	) {
		return findByProperty(UNITINDEX, unitindex
		);
	}
	
	public List findByWorkers(Object workers
	) {
		return findByProperty(WORKERS, workers
		);
	}
	
	public List findByBelongworkers(Object belongworkers
	) {
		return findByProperty(BELONGWORKERS, belongworkers
		);
	}
	
	public List findByUnitcode(Object unitcode
	) {
		return findByProperty(UNITCODE, unitcode
		);
	}
	
	public List findByUnitname(Object unitname
	) {
		return findByProperty(UNITNAME, unitname
		);
	}
	
	public List findByIstempunit(Object istempunit
	) {
		return findByProperty(ISTEMPUNIT, istempunit
		);
	}
	
	public List findByCreator(Object creator
	) {
		return findByProperty(CREATOR, creator
		);
	}
	
	public List findByCreatedate(Object createdate
	) {
		return findByProperty(CREATEDATE, createdate
		);
	}
	
	public List findByKiller(Object killer
	) {
		return findByProperty(KILLER, killer
		);
	}
	
	public List findByKilldate(Object killdate
	) {
		return findByProperty(KILLDATE, killdate
		);
	}
	
	public List findByLastsavedate(Object lastsavedate
	) {
		return findByProperty(LASTSAVEDATE, lastsavedate
		);
	}
	
	public List findByPostcode(Object postcode
	) {
		return findByProperty(POSTCODE, postcode
		);
	}
	
	public List findByUnitduty(Object unitduty
	) {
		return findByProperty(UNITDUTY, unitduty
		);
	}
	
	public List findByUnitgrade(Object unitgrade
	) {
		return findByProperty(UNITGRADE, unitgrade
		);
	}
	
	public List findByIscorecenter(Object iscorecenter
	) {
		return findByProperty(ISCORECENTER, iscorecenter
		);
	}
	
	public List findByCorecenterinfo(Object corecenterinfo
	) {
		return findByProperty(CORECENTERINFO, corecenterinfo
		);
	}
	
	public List findByIslegal(Object islegal
	) {
		return findByProperty(ISLEGAL, islegal
		);
	}
	
	public List findByLegalinfo(Object legalinfo
	) {
		return findByProperty(LEGALINFO, legalinfo
		);
	}
	
	public List findByFax(Object fax
	) {
		return findByProperty(FAX, fax
		);
	}
	
	public List findByDirector(Object director
	) {
		return findByProperty(DIRECTOR, director
		);
	}
	
	public List findByAddress(Object address
	) {
		return findByProperty(ADDRESS, address
		);
	}
	
	public List findByAssistant(Object assistant
	) {
		return findByProperty(ASSISTANT, assistant
		);
	}
	
	public List findByUnitlevel(Object unitlevel
	) {
		return findByProperty(UNITLEVEL, unitlevel
		);
	}
	
	public List findByIsmanageunit(Object ismanageunit
	) {
		return findByProperty(ISMANAGEUNIT, ismanageunit
		);
	}
	
	public List findByUnittype(Object unittype
	) {
		return findByProperty(UNITTYPE, unittype
		);
	}
	
	public List findByManageunit(Object manageunit
	) {
		return findByProperty(MANAGEUNIT, manageunit
		);
	}
	
	public List findByEditionId(Object editionId
	) {
		return findByProperty(EDITION_ID, editionId
		);
	}
	
	public List findByLabelindex(Object labelindex
	) {
		return findByProperty(LABELINDEX, labelindex
		);
	}
	
	public List findByIsdelete(Object isdelete
	) {
		return findByProperty(ISDELETE, isdelete
		);
	}
	
	public List findByRemark(Object remark
	) {
		return findByProperty(REMARK, remark
		);
	}
	
	public List findByCpccharacter(Object cpccharacter
	) {
		return findByProperty(CPCCHARACTER, cpccharacter
		);
	}
	
	public List findByItem1(Object item1
	) {
		return findByProperty(ITEM1, item1
		);
	}
	
	public List findByItem2(Object item2
	) {
		return findByProperty(ITEM2, item2
		);
	}
	
	public List findByItem3(Object item3
	) {
		return findByProperty(ITEM3, item3
		);
	}
	
	public List findByItem4(Object item4
	) {
		return findByProperty(ITEM4, item4
		);
	}
	
	public List findByItem5(Object item5
	) {
		return findByProperty(ITEM5, item5
		);
	}
	
	public List findByItem6(Object item6
	) {
		return findByProperty(ITEM6, item6
		);
	}
	
	public List findByItem7(Object item7
	) {
		return findByProperty(ITEM7, item7
		);
	}
	
	public List findByItem8(Object item8
	) {
		return findByProperty(ITEM8, item8
		);
	}
	
	public List findByItem9(Object item9
	) {
		return findByProperty(ITEM9, item9
		);
	}
	
	public List findByItem10(Object item10
	) {
		return findByProperty(ITEM10, item10
		);
	}
	
	public List findByItem11(Object item11
	) {
		return findByProperty(ITEM11, item11
		);
	}
	
	public List findByItem12(Object item12
	) {
		return findByProperty(ITEM12, item12
		);
	}
	
	public List findByItem13(Object item13
	) {
		return findByProperty(ITEM13, item13
		);
	}
	
	public List findByItem14(Object item14
	) {
		return findByProperty(ITEM14, item14
		);
	}
	
	public List findByItem15(Object item15
	) {
		return findByProperty(ITEM15, item15
		);
	}
	
	public List findByItem16(Object item16
	) {
		return findByProperty(ITEM16, item16
		);
	}
	
	public List findByItem17(Object item17
	) {
		return findByProperty(ITEM17, item17
		);
	}
	
	public List findByItem18(Object item18
	) {
		return findByProperty(ITEM18, item18
		);
	}
	
	public List findByItem19(Object item19
	) {
		return findByProperty(ITEM19, item19
		);
	}
	
	public List findByItem20(Object item20
	) {
		return findByProperty(ITEM20, item20
		);
	}
	
	public List findByLegalEntity(Object legalEntity
	) {
		return findByProperty(LEGAL_ENTITY, legalEntity
		);
	}
	
	public List findByUnitenname(Object unitenname
	) {
		return findByProperty(UNITENNAME, unitenname
		);
	}
	
	public List findByCostcenterid(Object costcenterid
	) {
		return findByProperty(COSTCENTERID, costcenterid
		);
	}
	
	public List findByEffectdate(Object effectdate
	) {
		return findByProperty(EFFECTDATE, effectdate
		);
	}
	
	public List findByStopdate(Object stopdate
	) {
		return findByProperty(STOPDATE, stopdate
		);
	}
	
	public List findByStopoperationdate(Object stopoperationdate
	) {
		return findByProperty(STOPOPERATIONDATE, stopoperationdate
		);
	}
	
	public List findByStopuserid(Object stopuserid
	) {
		return findByProperty(STOPUSERID, stopuserid
		);
	}
	

	public List findAll() {
		log.debug("finding all OrgstdStruct instances");
		try {
			String queryString = "from OrgstdStruct";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public OrgstdStruct merge(OrgstdStruct detachedInstance) {
        log.debug("merging OrgstdStruct instance");
        try {
            OrgstdStruct result = (OrgstdStruct) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(OrgstdStruct instance) {
        log.debug("attaching dirty OrgstdStruct instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(OrgstdStruct instance) {
        log.debug("attaching clean OrgstdStruct instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static OrgstdStructDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (OrgstdStructDAO) ctx.getBean("OrgstdStructDAO");
	}

	//labelLength 查询所有片区(20) 大区(24)
	public List<OrgstdStruct> findAreaByLabelLength(int i) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="from OrgstdStruct as o where  o.labelLength = :i order by o.unitcode";
		return session.createQuery(hql).setInteger("i",i).list();
	}
	
	//根据片区unitCode 查找所有门店unitid
	public List<String> findStoreByFilmAreaUnitCode(String unitcode,int i) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="select distinct unitid from OrgstdStruct as o where o.unitcode like :unitcode and o.labelLength = :i";
		return session.createQuery(hql).setString("unitcode",unitcode+"%").setInteger("i",i).list();
	}

	//查询当前区域下所有门店 
	public List<OrgstdStruct> findStoreByAreaUnitCode(String unitcode, int i) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="select distinct o from OrgstdStruct as o where o.unitcode like :unitcode and o.labelLength = :i";
		return session.createQuery(hql).setString("unitcode",unitcode+"%").setInteger("i",i).list();
	}
}