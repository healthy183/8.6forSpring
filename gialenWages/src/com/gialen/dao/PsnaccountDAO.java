package com.gialen.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.Psnaccount;

/**
 * A data access object (DAO) providing persistence and search support for
 * Psnaccount entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.gialen.model.Psnaccount
 * @author MyEclipse Persistence Tools
 */

public class PsnaccountDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(PsnaccountDAO.class);
	// property constants
	public static final String CARDNUM = "cardnum";
	public static final String TRUENAME = "truename";
	public static final String CERTIFICATETYPEID = "certificatetypeid";
	public static final String CERTIFICATENUMBER = "certificatenumber";
	public static final String EVERINOURCORP = "everinourcorp";
	public static final String ACCESSIONSTATE = "accessionstate";
	public static final String FIRSTNAME = "firstname";
	public static final String MIDDLENAME = "middlename";
	public static final String LASTNAME = "lastname";
	public static final String COMPANYEMAIL = "companyemail";
	public static final String DATEOFBIRTH = "dateofbirth";
	public static final String GENDER = "gender";
	public static final String HOMEPLACE = "homeplace";
	public static final String NATIONALITYID = "nationalityid";
	public static final String SECONDNATIONALITYID = "secondnationalityid";
	public static final String MARRIAGEID = "marriageid";
	public static final String SETTLEAT = "settleat";
	public static final String GRADUATESCHOOLID = "graduateschoolid";
	public static final String PHOTOID = "photoid";
	public static final String LASTUPDATETIME = "lastupdatetime";
	public static final String NOPAYSTATUS = "nopaystatus";
	public static final String MAINACCSTATUS = "mainaccstatus";
	public static final String IFMAINNOTE = "ifmainnote";
	public static final String IMPORTLABEL = "importlabel";
	public static final String EDUCATIONALLEVELID = "educationallevelid";
	public static final String BELONGCORPID = "belongcorpid";
	public static final String POSITIONID = "positionid";
	public static final String RESPONSIBILITYID = "responsibilityid";
	public static final String TITLEID = "titleid";
	public static final String RANKID = "rankid";
	public static final String GRADEID = "gradeid";
	public static final String JOBSERIESID = "jobseriesid";
	public static final String ARRANGEMENTID = "arrangementid";
	public static final String JOBTYPEID = "jobtypeid";
	public static final String EMPLOYEEID = "employeeid";
	public static final String SERVICESENIORITY = "serviceseniority";
	public static final String STARTSERVICEDATE = "startservicedate";
	public static final String ATTENDONDATE = "attendondate";
	public static final String NATIVEPLACEPROPERTYID = "nativeplacepropertyid";
	public static final String EMPLOYEETYPEID = "employeetypeid";
	public static final String DLIDL = "dlidl";
	public static final String PROBATIONENDDATE = "probationenddate";
	public static final String LASTADJUSTUPDATETIME = "lastadjustupdatetime";
	public static final String EMPLOYEECHARID = "employeecharid";
	public static final String ORIGEMPLOYEEID = "origemployeeid";
	public static final String ISTALENT = "istalent";
	public static final String ISREGULAREEMPLOYEE = "isregulareemployee";
	public static final String ISINNERRETIRE = "isinnerretire";
	public static final String JOBCHARACTER = "jobcharacter";
	public static final String PROBATIONMATUREDATE = "probationmaturedate";
	public static final String SOCIETYENSURENUM = "societyensurenum";
	public static final String ENTERSOCIETYDATE = "entersocietydate";
	public static final String FIRSTATTENDDATE = "firstattenddate";
	public static final String ISASSIGNHOUSE = "isassignhouse";
	public static final String ASSIGNHOUSEDATE = "assignhousedate";
	public static final String REMARK = "remark";
	public static final String ACCITEM1 = "accitem1";
	public static final String ACCITEM2 = "accitem2";
	public static final String ACCITEM3 = "accitem3";
	public static final String ACCITEM4 = "accitem4";
	public static final String ACCITEM5 = "accitem5";
	public static final String ACCITEM6 = "accitem6";
	public static final String ACCITEM7 = "accitem7";
	public static final String ACCITEM8 = "accitem8";
	public static final String REGULARSTATE = "regularstate";
	public static final String ISADJUSTDEL = "isadjustdel";
	public static final String DIMISSIONDATE = "dimissiondate";
	public static final String IMMIGRATIONDAY = "immigrationday";
	public static final String ISEXPATRIATE = "isexpatriate";
	public static final String EXPINUNITID = "expinunitid";
	public static final String EXPATRIATESTATUS = "expatriatestatus";
	public static final String EXPINBUSINESSUNITID = "expinbusinessunitid";
	public static final String EXPINBELONGCORP = "expinbelongcorp";
	public static final String EXPINUNITLABEL = "expinunitlabel";
	public static final String FOSTERED = "fostered";
	public static final String AREAID = "areaid";
	public static final String WILL_LEAVE_RANGE = "willLeaveRange";
	public static final String FLOWER_EMP_ID = "flowerEmpId";
	public static final String TWINSURANCEIDENTITYTYPEID = "twinsuranceidentitytypeid";
	public static final String TWLSAIDENTITYTYPEID = "twlsaidentitytypeid";
	public static final String WORKYEARS = "workyears";

	protected void initDao() {
		// do nothing
	}

	public void save(Psnaccount transientInstance) {
		log.debug("saving Psnaccount instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Psnaccount persistentInstance) {
		log.debug("deleting Psnaccount instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Psnaccount findById(java.lang.String id) {
		log.debug("getting Psnaccount instance with id: " + id);
		try {
			Psnaccount instance = (Psnaccount) getHibernateTemplate().get(
					"com.gialen.model.Psnaccount", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Psnaccount instance) {
		log.debug("finding Psnaccount instance by example");
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
		log.debug("finding Psnaccount instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Psnaccount as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCardnum(Object cardnum) {
		return findByProperty(CARDNUM, cardnum);
	}

	public List findByTruename(Object truename) {
		return findByProperty(TRUENAME, truename);
	}

	public List findByCertificatetypeid(Object certificatetypeid) {
		return findByProperty(CERTIFICATETYPEID, certificatetypeid);
	}

	public List findByCertificatenumber(Object certificatenumber) {
		return findByProperty(CERTIFICATENUMBER, certificatenumber);
	}

	public List findByEverinourcorp(Object everinourcorp) {
		return findByProperty(EVERINOURCORP, everinourcorp);
	}

	public List findByAccessionstate(Object accessionstate) {
		return findByProperty(ACCESSIONSTATE, accessionstate);
	}

	public List findByFirstname(Object firstname) {
		return findByProperty(FIRSTNAME, firstname);
	}

	public List findByMiddlename(Object middlename) {
		return findByProperty(MIDDLENAME, middlename);
	}

	public List findByLastname(Object lastname) {
		return findByProperty(LASTNAME, lastname);
	}

	public List findByCompanyemail(Object companyemail) {
		return findByProperty(COMPANYEMAIL, companyemail);
	}

	public List findByDateofbirth(Object dateofbirth) {
		return findByProperty(DATEOFBIRTH, dateofbirth);
	}

	public List findByGender(Object gender) {
		return findByProperty(GENDER, gender);
	}

	public List findByHomeplace(Object homeplace) {
		return findByProperty(HOMEPLACE, homeplace);
	}

	public List findByNationalityid(Object nationalityid) {
		return findByProperty(NATIONALITYID, nationalityid);
	}

	public List findBySecondnationalityid(Object secondnationalityid) {
		return findByProperty(SECONDNATIONALITYID, secondnationalityid);
	}

	public List findByMarriageid(Object marriageid) {
		return findByProperty(MARRIAGEID, marriageid);
	}

	public List findBySettleat(Object settleat) {
		return findByProperty(SETTLEAT, settleat);
	}

	public List findByGraduateschoolid(Object graduateschoolid) {
		return findByProperty(GRADUATESCHOOLID, graduateschoolid);
	}

	public List findByPhotoid(Object photoid) {
		return findByProperty(PHOTOID, photoid);
	}

	public List findByLastupdatetime(Object lastupdatetime) {
		return findByProperty(LASTUPDATETIME, lastupdatetime);
	}

	public List findByNopaystatus(Object nopaystatus) {
		return findByProperty(NOPAYSTATUS, nopaystatus);
	}

	public List findByMainaccstatus(Object mainaccstatus) {
		return findByProperty(MAINACCSTATUS, mainaccstatus);
	}

	public List findByIfmainnote(Object ifmainnote) {
		return findByProperty(IFMAINNOTE, ifmainnote);
	}

	public List findByImportlabel(Object importlabel) {
		return findByProperty(IMPORTLABEL, importlabel);
	}

	public List findByEducationallevelid(Object educationallevelid) {
		return findByProperty(EDUCATIONALLEVELID, educationallevelid);
	}

	public List findByBelongcorpid(Object belongcorpid) {
		return findByProperty(BELONGCORPID, belongcorpid);
	}

	public List findByPositionid(Object positionid) {
		return findByProperty(POSITIONID, positionid);
	}

	public List findByResponsibilityid(Object responsibilityid) {
		return findByProperty(RESPONSIBILITYID, responsibilityid);
	}

	public List findByTitleid(Object titleid) {
		return findByProperty(TITLEID, titleid);
	}

	public List findByRankid(Object rankid) {
		return findByProperty(RANKID, rankid);
	}

	public List findByGradeid(Object gradeid) {
		return findByProperty(GRADEID, gradeid);
	}

	public List findByJobseriesid(Object jobseriesid) {
		return findByProperty(JOBSERIESID, jobseriesid);
	}

	public List findByArrangementid(Object arrangementid) {
		return findByProperty(ARRANGEMENTID, arrangementid);
	}

	public List findByJobtypeid(Object jobtypeid) {
		return findByProperty(JOBTYPEID, jobtypeid);
	}

	public List findByEmployeeid(Object employeeid) {
		return findByProperty(EMPLOYEEID, employeeid);
	}

	public List findByServiceseniority(Object serviceseniority) {
		return findByProperty(SERVICESENIORITY, serviceseniority);
	}

	public List findByStartservicedate(Object startservicedate) {
		return findByProperty(STARTSERVICEDATE, startservicedate);
	}

	public List findByAttendondate(Object attendondate) {
		return findByProperty(ATTENDONDATE, attendondate);
	}

	public List findByNativeplacepropertyid(Object nativeplacepropertyid) {
		return findByProperty(NATIVEPLACEPROPERTYID, nativeplacepropertyid);
	}

	public List findByEmployeetypeid(Object employeetypeid) {
		return findByProperty(EMPLOYEETYPEID, employeetypeid);
	}

	public List findByDlidl(Object dlidl) {
		return findByProperty(DLIDL, dlidl);
	}

	public List findByProbationenddate(Object probationenddate) {
		return findByProperty(PROBATIONENDDATE, probationenddate);
	}

	public List findByLastadjustupdatetime(Object lastadjustupdatetime) {
		return findByProperty(LASTADJUSTUPDATETIME, lastadjustupdatetime);
	}

	public List findByEmployeecharid(Object employeecharid) {
		return findByProperty(EMPLOYEECHARID, employeecharid);
	}

	public List findByOrigemployeeid(Object origemployeeid) {
		return findByProperty(ORIGEMPLOYEEID, origemployeeid);
	}

	public List findByIstalent(Object istalent) {
		return findByProperty(ISTALENT, istalent);
	}

	public List findByIsregulareemployee(Object isregulareemployee) {
		return findByProperty(ISREGULAREEMPLOYEE, isregulareemployee);
	}

	public List findByIsinnerretire(Object isinnerretire) {
		return findByProperty(ISINNERRETIRE, isinnerretire);
	}

	public List findByJobcharacter(Object jobcharacter) {
		return findByProperty(JOBCHARACTER, jobcharacter);
	}

	public List findByProbationmaturedate(Object probationmaturedate) {
		return findByProperty(PROBATIONMATUREDATE, probationmaturedate);
	}

	public List findBySocietyensurenum(Object societyensurenum) {
		return findByProperty(SOCIETYENSURENUM, societyensurenum);
	}

	public List findByEntersocietydate(Object entersocietydate) {
		return findByProperty(ENTERSOCIETYDATE, entersocietydate);
	}

	public List findByFirstattenddate(Object firstattenddate) {
		return findByProperty(FIRSTATTENDDATE, firstattenddate);
	}

	public List findByIsassignhouse(Object isassignhouse) {
		return findByProperty(ISASSIGNHOUSE, isassignhouse);
	}

	public List findByAssignhousedate(Object assignhousedate) {
		return findByProperty(ASSIGNHOUSEDATE, assignhousedate);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findByAccitem1(Object accitem1) {
		return findByProperty(ACCITEM1, accitem1);
	}

	public List findByAccitem2(Object accitem2) {
		return findByProperty(ACCITEM2, accitem2);
	}

	public List findByAccitem3(Object accitem3) {
		return findByProperty(ACCITEM3, accitem3);
	}

	public List findByAccitem4(Object accitem4) {
		return findByProperty(ACCITEM4, accitem4);
	}

	public List findByAccitem5(Object accitem5) {
		return findByProperty(ACCITEM5, accitem5);
	}

	public List findByAccitem6(Object accitem6) {
		return findByProperty(ACCITEM6, accitem6);
	}

	public List findByAccitem7(Object accitem7) {
		return findByProperty(ACCITEM7, accitem7);
	}

	public List findByAccitem8(Object accitem8) {
		return findByProperty(ACCITEM8, accitem8);
	}

	public List findByRegularstate(Object regularstate) {
		return findByProperty(REGULARSTATE, regularstate);
	}

	public List findByIsadjustdel(Object isadjustdel) {
		return findByProperty(ISADJUSTDEL, isadjustdel);
	}

	public List findByDimissiondate(Object dimissiondate) {
		return findByProperty(DIMISSIONDATE, dimissiondate);
	}

	public List findByImmigrationday(Object immigrationday) {
		return findByProperty(IMMIGRATIONDAY, immigrationday);
	}

	public List findByIsexpatriate(Object isexpatriate) {
		return findByProperty(ISEXPATRIATE, isexpatriate);
	}

	public List findByExpinunitid(Object expinunitid) {
		return findByProperty(EXPINUNITID, expinunitid);
	}

	public List findByExpatriatestatus(Object expatriatestatus) {
		return findByProperty(EXPATRIATESTATUS, expatriatestatus);
	}

	public List findByExpinbusinessunitid(Object expinbusinessunitid) {
		return findByProperty(EXPINBUSINESSUNITID, expinbusinessunitid);
	}

	public List findByExpinbelongcorp(Object expinbelongcorp) {
		return findByProperty(EXPINBELONGCORP, expinbelongcorp);
	}

	public List findByExpinunitlabel(Object expinunitlabel) {
		return findByProperty(EXPINUNITLABEL, expinunitlabel);
	}

	public List findByFostered(Object fostered) {
		return findByProperty(FOSTERED, fostered);
	}

	public List findByAreaid(Object areaid) {
		return findByProperty(AREAID, areaid);
	}

	public List findByWillLeaveRange(Object willLeaveRange) {
		return findByProperty(WILL_LEAVE_RANGE, willLeaveRange);
	}

	public List findByFlowerEmpId(Object flowerEmpId) {
		return findByProperty(FLOWER_EMP_ID, flowerEmpId);
	}

	public List findByTwinsuranceidentitytypeid(Object twinsuranceidentitytypeid) {
		return findByProperty(TWINSURANCEIDENTITYTYPEID,
				twinsuranceidentitytypeid);
	}

	public List findByTwlsaidentitytypeid(Object twlsaidentitytypeid) {
		return findByProperty(TWLSAIDENTITYTYPEID, twlsaidentitytypeid);
	}

	public List findByWorkyears(Object workyears) {
		return findByProperty(WORKYEARS, workyears);
	}

	public List findAll() {
		log.debug("finding all Psnaccount instances");
		try {
			String queryString = "from Psnaccount";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Psnaccount merge(Psnaccount detachedInstance) {
		log.debug("merging Psnaccount instance");
		try {
			Psnaccount result = (Psnaccount) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Psnaccount instance) {
		log.debug("attaching dirty Psnaccount instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Psnaccount instance) {
		log.debug("attaching clean Psnaccount instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PsnaccountDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PsnaccountDAO) ctx.getBean("PsnaccountDAO");
	}

	public List<Psnaccount> getThisStorePerson(String unitid) {
		//根据hr门店，获取门店下所有员工
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="select new Psnaccount(personid,employeeid) from Psnaccount as p where p.orgstdStruct.unitid = :unitid";
		return session.createQuery(hql).setParameter("unitid",unitid).list();
		
	}
	
	//从佳讯系统当前门店销售流水中查找员工,并根据employeeid获得hr员工
	public List<Psnaccount> getThisStorePersonBySaleDailyYymm(Date startDate,
			Date endDate, String braId, String thisMonthId,List<String> thisMonthProjectsProidList) {
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="from Psnaccount as p where p.employeeid != '00000' and p.employeeid in " +
		"(select distinct s.employee.personalId from  SaleDailyYymm as s " +
			"where s.id.saleDate between :startDate and :endDate and " +
				"s.id.branch.braId = :braId and s.saleAmt != 0 and " +
					"s.id.product.proId in " +
						"( :thisMonthProjectsProidList))";
		//.setString("thisMonthId",thisMonthId)
		return	session.createQuery(hql).setParameter("startDate",startDate)
			.setParameter("endDate", endDate).setString("braId",braId)
					.setParameterList("thisMonthProjectsProidList",thisMonthProjectsProidList).list();
		
		
		
	}
}