package com.gialen.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.LastCountTable;
import com.gialen.model.OperatingMonth;
import com.gialen.model.SaleDailyProductPeopleSum;
import com.gialen.model.SaleDailyProductPeopleSumId;
import com.gialen.model.vo.SaleDailyProductPeopleSumVo;
import com.gialen.tools.Arith;

/**
 * A data access object (DAO) providing persistence and search support for
 * SaleDailyProductPeopleSum entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.gialen.model.SaleDailyProductPeopleSum
 * @author MyEclipse Persistence Tools
 */

public class SaleDailyProductPeopleSumDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(SaleDailyProductPeopleSumDAO.class);
	// property constants
	public static final String SALE_QTY = "saleQty";
	public static final String SALE_AMT = "saleAmt";
	public static final String SALE_WAGES = "saleWages";

	protected void initDao() {
		// do nothing
	}

	public void save(SaleDailyProductPeopleSum transientInstance) {
		log.debug("saving SaleDailyProductPeopleSum instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SaleDailyProductPeopleSum persistentInstance) {
		log.debug("deleting SaleDailyProductPeopleSum instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SaleDailyProductPeopleSum findById(
			com.gialen.model.SaleDailyProductPeopleSumId id) {
		log.debug("getting SaleDailyProductPeopleSum instance with id: " + id);
		try {
			SaleDailyProductPeopleSum instance = (SaleDailyProductPeopleSum) getHibernateTemplate()
					.get("com.gialen.model.SaleDailyProductPeopleSum", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SaleDailyProductPeopleSum instance) {
		log.debug("finding SaleDailyProductPeopleSum instance by example");
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
		log.debug("finding SaleDailyProductPeopleSum instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SaleDailyProductPeopleSum as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySaleQty(Object saleQty) {
		return findByProperty(SALE_QTY, saleQty);
	}

	public List findBySaleAmt(Object saleAmt) {
		return findByProperty(SALE_AMT, saleAmt);
	}

	public List findBySaleWages(Object saleWages) {
		return findByProperty(SALE_WAGES, saleWages);
	}

	public List findAll() {
		log.debug("finding all SaleDailyProductPeopleSum instances");
		try {
			String queryString = "from SaleDailyProductPeopleSum";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SaleDailyProductPeopleSum merge(
			SaleDailyProductPeopleSum detachedInstance) {
		log.debug("merging SaleDailyProductPeopleSum instance");
		try {
			SaleDailyProductPeopleSum result = (SaleDailyProductPeopleSum) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SaleDailyProductPeopleSum instance) {
		log.debug("attaching dirty SaleDailyProductPeopleSum instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SaleDailyProductPeopleSum instance) {
		log.debug("attaching clean SaleDailyProductPeopleSum instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SaleDailyProductPeopleSumDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (SaleDailyProductPeopleSumDAO) ctx
				.getBean("SaleDailyProductPeopleSumDAO");
	}

	public List<SaleDailyProductPeopleSum> findThisMonthPeopleWages(
			OperatingMonth thisOperatingMonth) {
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="from SaleDailyProductPeopleSum as s " +
			"where s.id.operatingMonth.operatingMonthId = :operatingMonthId order by s.id.branch.braId,s.id.employee.empId";
		return session.createQuery(hql)
				.setString("operatingMonthId",thisOperatingMonth.getOperatingMonthId()).list();
	}
	
	//���ô洢���
	public void delMiddleTable() {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		session.createSQLQuery("{call delMiddleTable()}").executeUpdate();
		
	}

	

	public void saveCountAll(List<Object[]> objList, String operatingMonthId) {

		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		Connection connection = session.connection();
		
		String sql = "insert into saleDaily_product_people_sum values(?,?,?,?,?,?)";
		
		try {
			PreparedStatement prepareStatement = connection
					.prepareStatement(sql);
			
			for(Object[] obj : objList){
				
				
				prepareStatement.setString(1,obj[0].toString());
				prepareStatement.setString(2,operatingMonthId);
				
				prepareStatement.setDouble(3, (Double) obj[1]);
				prepareStatement.setDouble(4, (Double)obj[2]);
				prepareStatement.setDouble(5, (Double)obj[3]);
				
				//prepareStatement.setDouble(3, (Double)obj[1]);
				//prepareStatement.setDouble(4,(Double)obj[2]);
				//prepareStatement.setDouble(5,(Double)obj[3]);
				
				prepareStatement.setString(6,obj[4].toString());
				
				prepareStatement.addBatch();//��sql�ŵ�һ��������
			}

			prepareStatement.executeBatch();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		session.flush();
		//session.close();
	}
	
	
	//��������ˮ��SaleDailyYymm.java�г�ȡ��Ʒ��Ŀ�к��е�������ˮ��saleDaily_product
		public void getSaleToSaleDailyProduct(Date startDate, Date endDate,
				List<String> thisMonthProjectsProidList) {
			
			SimpleDateFormat sdft  = new SimpleDateFormat("yyyy-MM-dd");
			
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			String sql ="insert into saleDaily_product " +
				"select * from sale_daily_yymm where saleDate between :startDate and :endDate " +
					"and ProId in (:dateList)  and SaleAmt != 0";
			session.createSQLQuery(sql).setString("startDate",sdft.format(startDate)).setString("endDate",sdft.format(endDate))
				.setParameterList("dateList",thisMonthProjectsProidList).executeUpdate();
			session.flush();
		}

		public List<SaleDailyProductPeopleSumVo> showUsrWages(String operatingMonthId) {
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		//t.psnaccount.cpcjobcode.names
		String hql ="select t.branch.braId,t.orgstdStruct.unitname,t.employee.personalId," +
			"t.employee.empId,t.employee.empName,names," +
				"t.jobWages,t.proSaleAmt,t.proSaleWages,t.branchSaleAmt," +
					"t.branchSaleWages,t.sumSaleAmt,t.sumsaleWages,t.orgstdStruct.orgstdStruct.unitname," +
						"t.orgstdStruct.orgstdStruct.orgstdStruct.unitname  from LastCountTable as t where " +
							"t.operatingMonth.operatingMonthId = :operatingMonthId " +
								"order by t.orgstdStruct.unitcode,t.employee.empId";	
		
		List<Object[]> objList = session.createQuery(hql)
				.setString("operatingMonthId",operatingMonthId).list();	
		
		List<SaleDailyProductPeopleSumVo> voList =new ArrayList<SaleDailyProductPeopleSumVo>();
		
		for(Object[] obj : objList){
			
			SaleDailyProductPeopleSumVo vo = new SaleDailyProductPeopleSumVo();
			vo.setBraId(obj[0].toString());
			vo.setBraName(obj[1] != null ? obj[1].toString():null);
			vo.setEmployeeid(obj[2] != null ? obj[2].toString():null);
			vo.setEmpId(obj[3].toString());
			vo.setEmpName(obj[4].toString());
			vo.setJobNames(obj[5] != null? obj[5].toString():null);
			vo.setJobWages((Double)obj[6]);
			
			vo.setProSaleAmt((Double)obj[7]);
			vo.setProSaleWages((Double)obj[8]);
			vo.setBraSaleCount((Double)obj[9]);
			vo.setBraSaleWages((Double)obj[10]);
			vo.setSaleAmt((Double)obj[11]);
			vo.setSaleWages((Double)obj[12]);
			
			vo.setFilmAreaName(obj[13].toString());
			vo.setBigAreaName(obj[14].toString());
			
			voList.add(vo);
			
		}
			return voList;
		}
		
		public SaleDailyProductPeopleSumVo showUsrWagesCount(
				String operatingMonthId) {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			String hql ="select " +
					"" +
					"sum(t.jobWages),sum(t.proSaleAmt),sum(t.proSaleWages),sum(t.branchSaleAmt)," +
						"sum(t.branchSaleWages),sum(t.sumSaleAmt),sum(t.sumsaleWages) " +
							"from LastCountTable as t where " +
								"t.operatingMonth.operatingMonthId = :operatingMonthId ";
									
		Object[] obj = (Object[]) session.createQuery(hql)
				.setString("operatingMonthId",operatingMonthId).uniqueResult();
		
		
		SaleDailyProductPeopleSumVo vo = new SaleDailyProductPeopleSumVo();
		if(obj != null){
			vo.setJobWages((Double)obj[0]);
			
			vo.setProSaleAmt((Double)obj[1]);
			vo.setProSaleWages((Double)obj[2]);
			vo.setBraSaleCount((Double)obj[3]);
			vo.setBraSaleWages((Double)obj[4]);
			vo.setSaleAmt((Double)obj[5]);
			vo.setSaleWages((Double)obj[6]);
			
		}
			
			return vo;
		}
		
		
		public List<SaleDailyProductPeopleSumVo> showUsrWagesNews(
				String operatingMonthId) {
			
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			List<SaleDailyProductPeopleSumVo> voList = new ArrayList<SaleDailyProductPeopleSumVo>();
			//saleDaily_product_people_sum��Ʒ���� ���� Ʒ�ƻ��� saleDaily_brand_people_sum
			String sql = "select p.BraId as BraId,c.UNITID as UNITID,o.UNITNAME as UNITNAME,r.BraName as BraName,"+
		"e.PersonalId as personalId,p.saleMan as saleMan,hrp.PERSONID as PERSONID,e.EmpName as EmpName,"+
			"sum(p.SaleAmt) as proSaleAmt,sum(p.saleWages) as proSaleWages,"+
				"sum(b.SaleAmt) as braSaleAmt,sum(b.saleWages) as braSaleWages,"+
						"sum(b.SaleAmt+p.SaleAmt) as sumSaleAmt,sum(b.saleWages+p.saleWages) as sumWages,"+
							"po.UNITNAME as pUNITNAME,ppo.UNITNAME as ppUNITNAME "+
			"from  saleDaily_product_people_sum as p "+
				"LEFT JOIN saleDaily_brand_people_sum as b on b.saleMan = p.saleMan and b.BraId = p.BraId and " +
							"b.operatingMonthId = p.operatingMonthId "+
				"LEFT JOIN branch as r on  p.BraId  = r.BraId "+
				"LEFT JOIN [corresponding] as c on p.BraId = c.BraId "+
				"LEFT JOIN ORGStdStruct as o on o.UNITID = c.UNITID "+
				"LEFT JOIN ORGStdStruct as po on po.UNITID = o.PUnitID "+
				"LEFT JOIN ORGStdStruct as ppo on ppo.UNITID = po.PUnitID "+
				"LEFT JOIN employee as e on p.saleMan = e.EmpId "+
				"left join PSNACCOUNT as hrp on hrp.EMPLOYEEID = e.PersonalId "+
				"LEFT JOIN operatingMonth as m on p.operatingMonthId = m.operatingMonthId "+ 
					 "where  m.operatingMonthId = :operatingMonthId GROUP BY "+
				"r.BraName,e.EmpName,p.saleMan,p.BraId,m.operatingMonthName,e.PersonalId,"+
					"o.UNITNAME,po.UNITNAME,ppo.UNITNAME,hrp.PERSONID,c.UNITID "+
						"order by p.BraId,p.saleMan;";
			
			SQLQuery query = session.createSQLQuery(sql);
			
			query.setString("operatingMonthId",operatingMonthId);
			
			query.addScalar("BraId",Hibernate.STRING);
			query.addScalar("UNITID",Hibernate.STRING);
			query.addScalar("UNITNAME",Hibernate.STRING);
			query.addScalar("BraName",Hibernate.STRING);
			query.addScalar("personalId",Hibernate.STRING);
			query.addScalar("saleMan",Hibernate.STRING);
			query.addScalar("PERSONID",Hibernate.STRING);
			query.addScalar("EmpName",Hibernate.STRING);
			query.addScalar("proSaleAmt",Hibernate.DOUBLE);
			query.addScalar("proSaleWages",Hibernate.DOUBLE);
			query.addScalar("braSaleAmt",Hibernate.DOUBLE);
			query.addScalar("braSaleWages",Hibernate.DOUBLE);
			query.addScalar("sumSaleAmt",Hibernate.DOUBLE);
			query.addScalar("sumWages",Hibernate.DOUBLE);
			query.addScalar("pUNITNAME",Hibernate.STRING);
			query.addScalar("ppUNITNAME",Hibernate.STRING);
			List<Object[]> objList = query.list();
			
			for(Object[] obj : objList){
				
				SaleDailyProductPeopleSumVo vo = new SaleDailyProductPeopleSumVo();

				vo.setJobWages(0.0);
				vo.setExceedAmt(0.0);
				vo.setExceedWages(0.0);
				vo.setTeamSaleAmt(0.0);
				vo.setTeamSaleWages(0.0);
				
				vo.setOperatingMonthId(operatingMonthId);
				
				vo.setBraId(obj[0].toString());
				if(obj[1] != null){
					vo.setUnitid(obj[1].toString());
					vo.setBraName(obj[2].toString());
				}
				
				if(obj[4] != null){
					vo.setEmployeeid(obj[4].toString());
				}
				
				vo.setEmpId(obj[5].toString());
				
				if(obj[6] != null){
					vo.setPersonalId(obj[6].toString());
				}
				vo.setEmpName(obj[7].toString());
				
				vo.setProSaleAmt((Double)obj[8]);
				vo.setProSaleWages((Double)obj[9]);
				
				if(obj[10] != null){
					vo.setBraSaleCount((Double)obj[10]);
					vo.setBraSaleWages((Double)obj[11]);
				}else{
					vo.setBraSaleCount(0.0);
					vo.setBraSaleWages(0.0);
				}
				
				if(obj[12] != null){
					vo.setSaleAmt((Double)obj[12]);
					vo.setSaleWages((Double)obj[13]);
				}else{
					vo.setSaleAmt(vo.getProSaleAmt());
					vo.setSaleWages(vo.getProSaleWages());
				}
				
				vo.setFilmAreaName(obj[14] == null?null:obj[14].toString());
				vo.setBigAreaName(obj[15] == null?null:obj[15].toString());
				
				voList.add(vo);
			}
			
			
			String sql2 = "select b.BraId as BraId,c.UNITID as UNITID,o.UNITNAME as UNITNAME,r.BraName as BraName," +
	"e.PersonalId as personalId,b.saleMan as saleMan,hrp.PERSONID as PERSONID,"+
		"e.EmpName as EmpName,sum(b.SaleAmt) as braSaleAmt,sum(b.saleWages) as braSaleWages,"+
					"po.UNITNAME as pUNITNAME ,ppo.UNITNAME as ppUNITNAME "+
			"from  saleDaily_brand_people_sum as b "+
				"LEFT JOIN branch as r on  b.BraId  = r.BraId "+
				"LEFT JOIN [corresponding] as c on b.BraId = c.BraId "+
				"LEFT JOIN ORGStdStruct as o on o.UNITID = c.UNITID "+
				"LEFT JOIN ORGStdStruct as po on po.UNITID = o.PUnitID "+
				"LEFT JOIN ORGStdStruct as ppo on ppo.UNITID = po.PUnitID "+
				"LEFT JOIN employee as e on b.saleMan = e.EmpId " +
				"left join PSNACCOUNT as hrp on hrp.EMPLOYEEID = e.PersonalId "+
				"LEFT JOIN operatingMonth as m on b.operatingMonthId = m.operatingMonthId "+
							"where  m.operatingMonthId = :operatingMonthId and  b.bpid not in "+
						"(select b.bpid from saleDaily_brand_people_sum as b,saleDaily_product_people_sum as p WHERE "+
				"b.saleMan = p.saleMan and b.BraId = p.BraId and b.operatingMonthId = p.operatingMonthId) "+
						"GROUP BY r.BraName,e.EmpName,b.saleMan,b.BraId,m.operatingMonthName,e.PersonalId, "+
								"po.UNITNAME,ppo.UNITNAME,hrp.PERSONID,c.UNITID,o.UNITNAME " +
									"order by b.BraId,b.saleMan";
				
			 query = session.createSQLQuery(sql2);
			
			query.setString("operatingMonthId",operatingMonthId);
			
			query.addScalar("BraId",Hibernate.STRING);
			query.addScalar("UNITID",Hibernate.STRING);
			query.addScalar("UNITNAME",Hibernate.STRING);
			query.addScalar("BraName",Hibernate.STRING);
			query.addScalar("personalId",Hibernate.STRING);
			query.addScalar("saleMan",Hibernate.STRING);
			query.addScalar("PERSONID",Hibernate.STRING);
			query.addScalar("EmpName",Hibernate.STRING);
			query.addScalar("braSaleAmt",Hibernate.DOUBLE);
			query.addScalar("braSaleWages",Hibernate.DOUBLE);
			query.addScalar("pUNITNAME",Hibernate.STRING);
			query.addScalar("ppUNITNAME",Hibernate.STRING);
			
			objList = query.list();
			for(Object[] obj : objList){
				
				SaleDailyProductPeopleSumVo vo = new SaleDailyProductPeopleSumVo();
				
				vo.setJobWages(0.0);
				vo.setExceedAmt(0.0);
				vo.setExceedWages(0.0);
				vo.setTeamSaleAmt(0.0);
				vo.setTeamSaleWages(0.0);
				
				vo.setOperatingMonthId(operatingMonthId);
				
				vo.setBraId(obj[0].toString());
				if(obj[1] != null){
					vo.setUnitid(obj[1].toString());
					vo.setBraName(obj[2].toString());
				}
				
				if(obj[4] != null){
					vo.setEmployeeid(obj[4].toString());
				}
				
				vo.setEmpId(obj[5].toString());
				
				if(obj[6] != null){
					vo.setPersonalId(obj[6].toString());
				}
				
				vo.setEmpName(obj[7].toString());
				
				if(obj[8] != null){
					vo.setBraSaleCount((Double)obj[8]);
					vo.setBraSaleWages((Double)obj[9]);
				}else{
					vo.setBraSaleCount(0.0);
					vo.setBraSaleWages(0.0);
				}
				
				//if(!vo.getEmpId().equals("00000")){}
					vo.setJobNames("����");
				
				
				vo.setProSaleAmt(0.0);
				vo.setProSaleWages(0.0);
				
				vo.setSaleAmt(vo.getBraSaleCount());
				vo.setSaleWages(vo.getBraSaleWages());
				
				vo.setFilmAreaName(obj[10] == null?null:obj[10].toString());
				vo.setBigAreaName(obj[11] == null?null:obj[11].toString());
				
				voList.add(vo);
			}
			
			//List<SaleDailyProductPeopleSumVo> newList = new ArrayList<SaleDailyProductPeopleSumVo>();
			
			Connection connection = session.connection();
			addLastCountTable(connection, voList);//����
			session.flush();
			
			//һ�Ǽ��곤 ���Ǽ� ���Ǽ��곤 ��code
			List<String> starList = new ArrayList<String>();
			starList.add("JB171");//һ�Ǽ��곤
			starList.add("JB177");//���Ǽ� �곤
			starList.add("JB178");//���Ǽ��곤
			
			String jobCode = null;
			
			 /*sql3 ="select distinct t.BraId as BraId from lastCountTable as t " +
					"where t.operatingMonthId = :operatingMonthId";*/
			
			 String sql3 = "select distinct o.UNITID as UNITID from ORGStdStruct as o "+ 
			"inner join lastCountTable as t on  o.UNITID  =  t.UNITID "+
				"where o.LabelLength = 24 and t.operatingMonthId = :operatingMonthId";
			
			List<String> ojbArray = session.createSQLQuery(sql3)
			.addScalar("UNITID",Hibernate.STRING)
				.setString("operatingMonthId",operatingMonthId).list();
			
			//���BraId����lastCountTable��ѯ�����±��ŵ�����Ա�����������
			//Ȼ���ȡ��¼,���±��걾Ա��ְλ���
			String sql4 = "select t.lastCountTableId as lastCountTableId, t.sumsaleWages as sumsaleWages,"+
				"s.oneStarManagergrundbonusMoney as oneStarManagergrundbonusMoney,"+
					"s.deputyManagergrundbonusMoney as deputyManagergrundbonusMoney,"+
						"s.positiveManagergrundbonusMoney as positiveManagergrundbonusMoney,"+
								"c.CODE as jobCode,c.[NAMES] as jobName "+
			"from lastCountTable as t  "+
			"Left join employee as e on  t.saleMan = e.EmpId "+
			"LEFT JOIN	PSNACCOUNT as p on e.PersonalId = p.EMPLOYEEID "+
			"Left join storeCount as s on t.UNITID = s.UNITID and t.operatingMonthId = s.operatingMonthId "+
			"left join CPCJOBCODE as c on p.JOBCODE = c.JOBCODEID "+
				 " where t.operatingMonthId = :operatingMonthId and t.UNITID = :UNITID "+
						"and e.PersonalId in (select p.EMPLOYEEID from PSNACCOUNT as p where p.BRANCHID = :UNITID)";
			
			//��ӱ��긱�곤����ְλ ��û�и���ְλ��ɵļ�¼ 
			String sql6 ="select distinct e.BraId as BraId,p.BRANCHID as UNITID,e.EmpId as EmpId,"+
"p.PERSONID as PERSONID,e.PersonalId as  PersonalId,e.EmpName as EmpName,c.code as code,c.[NAMES] as jobName,"+
   "s.oneStarManagergrundbonusMoney as oneStarManagergrundbonusMoney ,"+
	 " s.deputyManagergrundbonusMoney as deputyManagergrundbonusMoney,"+
		"s.positiveManagergrundbonusMoney as positiveManagergrundbonusMoney "+
			"from   PSNACCOUNT as p "+
				"LEFT JOIN  employee as e	on p.EMPLOYEEID = e.PersonalId "+
				"left join CPCJOBCODE as c on p.JOBCODE = c.JOBCODEID "+
				"left join ORGStdStruct as o on o.UNITID = p.BRANCHID "+
				"left join storeCount as s on p.BRANCHID = s.UNITID and s.operatingMonthId = :operatingMonthId "+
				"where  p.ACCESSIONSTATE != 4 and "+
							"c.CODE in ('JB171','JB177','JB178','JB184','JB172') and p.BRANCHID = :UNITID and "+ 
									"p.EMPLOYEEID not in ( "+
							" select e.PersonalId "+
			"from lastCountTable as t  "+
			"Left join employee as e on  t.saleMan = e.EmpId "+ 
			"LEFT JOIN	PSNACCOUNT as p on e.PersonalId = p.EMPLOYEEID "+
				 " where t.operatingMonthId = :operatingMonthId and t.UNITID = :UNITID "+
			"and e.PersonalId in (select p.EMPLOYEEID from PSNACCOUNT as p where p.BRANCHID = :UNITID))";
			
			
			
			List<SaleDailyProductPeopleSumVo> updtList = new ArrayList<SaleDailyProductPeopleSumVo>();
			List<SaleDailyProductPeopleSumVo> addList = new ArrayList<SaleDailyProductPeopleSumVo>();
			
			
			
			for(String UNITID : ojbArray){
				//���BraId����lastCountTable��ѯ�����±��ŵ�����Ա�����������
				//Ȼ���ȡ��¼,���±��걾Ա��ְλ���
			objList = session.createSQLQuery(sql4)
					.addScalar("lastCountTableId",Hibernate.INTEGER)//0
					.addScalar("sumsaleWages",Hibernate.DOUBLE)//1
					.addScalar("oneStarManagergrundbonusMoney",Hibernate.DOUBLE)//2
					.addScalar("deputyManagergrundbonusMoney",Hibernate.DOUBLE)	//3
					.addScalar("positiveManagergrundbonusMoney",Hibernate.DOUBLE)//4
					.addScalar("jobCode",Hibernate.STRING)//5
					.addScalar("jobName",Hibernate.STRING)//6
						.setString("operatingMonthId",operatingMonthId)
						.setString("UNITID",UNITID)
							.list();
			
			for(Object[] obj : objList ){
				
				if(obj[6] != null){
					
					SaleDailyProductPeopleSumVo vo = new SaleDailyProductPeopleSumVo();
					
					
					vo.setLastCountTableId((Integer)obj[0]);	
					
					vo.setJobNames(obj[6].toString());
					jobCode = obj[5].toString();
					
					if(jobCode.equals("JB184")){//�곤
						vo.setJobWages((Double)obj[4]);	
					}else if(jobCode.equals("JB172")){//���곤
						vo.setJobWages((Double)obj[3]);	
					}else if(starList.contains(jobCode)){//һ�Ǽ��곤 ���Ǽ� ���Ǽ��곤 ��code
						vo.setJobWages((Double)obj[2]);	
					}else{
						vo.setJobWages(0.0);	
					}
					
					//System.out.println(obj[1] == null);
					
					
					vo.setSaleWages((Double)obj[1]+vo.getJobWages());
					
					updtList.add(vo);
				}
				
			}

		System.out.println(objList.size());	
		
		//��ӱ��긱�곤����ְλ ��û�и���ְλ��ɵļ�¼ 
		 objList  =  session.createSQLQuery(sql6)
					.addScalar("BraId",Hibernate.STRING)
					.addScalar("UNITID",Hibernate.STRING)
					.addScalar("EmpId",Hibernate.STRING)
					.addScalar("PERSONID",Hibernate.STRING)
					.addScalar("PersonalId",Hibernate.STRING)
					.addScalar("EmpName",Hibernate.STRING)
					.addScalar("code",Hibernate.STRING)
					.addScalar("jobName",Hibernate.STRING)
					.addScalar("oneStarManagergrundbonusMoney",Hibernate.DOUBLE)
					.addScalar("deputyManagergrundbonusMoney",Hibernate.DOUBLE)
					.addScalar("positiveManagergrundbonusMoney",Hibernate.DOUBLE)
						.setString("operatingMonthId",operatingMonthId)
						.setString("UNITID",UNITID)
							.list();
		 
		 for(Object[] obj : objList){
			 
			 SaleDailyProductPeopleSumVo vo = new SaleDailyProductPeopleSumVo();
			 
			 if(obj != null){
				 vo.setJobWages(0.0);
				 vo.setExceedAmt(0.0);
				 vo.setExceedWages(0.0);
				 vo.setTeamSaleAmt(0.0);
				 vo.setTeamSaleWages(0.0);
				 
				 vo.setBraId(obj[0] != null ? obj[0].toString():null);
				 vo.setUnitid(obj[1].toString());
				 vo.setEmpId(obj[2] != null ? obj[2].toString():null);
				 vo.setPersonalId(obj[3].toString());
				 vo.setEmployeeid(obj[4] != null? obj[4].toString():null);
				 
				 vo.setJobCode(obj[6].toString());
				 vo.setJobNames(obj[7].toString());
				 
				 jobCode = vo.getJobCode();
				 
				 if(jobCode.equals("JB184")){//�곤
						vo.setJobWages((Double)obj[10]);	
					}else if(jobCode.equals("JB172")){//���곤
						vo.setJobWages((Double)obj[9]);	
					}else if(starList.contains(jobCode)){//һ�Ǽ��곤 ���Ǽ� ���Ǽ��곤 ��code
						vo.setJobWages((Double)obj[8]);	
					}
				 
				 vo.setOperatingMonthId(operatingMonthId);
				 vo.setProSaleAmt(0.0);
				 vo.setProSaleWages(0.0);
				 vo.setBraSaleCount(0.0);
				 vo.setBraSaleWages(0.0);
				 vo.setSaleAmt(0.0);
				 vo.setSaleWages(vo.getJobWages());
				 
				 addList.add(vo);
			 }
			 
			 
			
		 }
		 
	}
			
			addLastCountTable(connection, addList);//����
			session.flush();
			
			
		
		String sql5 = "update lastCountTable set NAMES = ?,jobWages = ?,  " +
				"sumsaleWages = ? where lastCountTableId = ? ";	
		
		
		try {
			PreparedStatement  prepareStatement = connection.prepareStatement(sql5);
		
			for(SaleDailyProductPeopleSumVo vo : updtList){
				
				prepareStatement.setString(1,vo.getJobNames());
				prepareStatement.setDouble(2,vo.getJobWages());
				prepareStatement.setDouble(3,vo.getSaleWages());
				prepareStatement.setInt(4,vo.getLastCountTableId());
				
				prepareStatement.addBatch();// ��sql�ŵ�һ��������
			}
		
			prepareStatement.executeBatch();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		session.flush();
		
		return voList;
		}

		public  void addLastCountTable(Connection connection,List<SaleDailyProductPeopleSumVo> voList){
			
			String sql10 ="insert into lastCountTable(BraId,UNITID,operatingMonthId,saleMan,PERSONID," +
					"EMPLOYEEID,NAMES,filmAreaName," +
						"bigAreaName,jobWages,exceedAmt,exceedWages," +
							"proSaleAmt,proSaleWages,branchSaleAmt,branchSaleWages," +
								"teamSaleAmt,teamSaleWages,sumSaleAmt,sumsaleWages) " +
									" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			try {
				PreparedStatement prepareStatement = connection
						.prepareStatement(sql10);
				
					for(int i= 0;i<voList.size();i++){
					
					SaleDailyProductPeopleSumVo vo = voList.get(i);
					
					String my =	vo.getBraId();
					
					prepareStatement.setString(1,vo.getBraId());
					prepareStatement.setString(2,vo.getUnitid());
					prepareStatement.setString(3,vo.getOperatingMonthId());
					prepareStatement.setString(4,vo.getEmpId());
					prepareStatement.setString(5,vo.getPersonalId());
					prepareStatement.setString(6,vo.getEmployeeid());
					
					String jobNames = vo.getJobNames();
					String empId = vo.getEmpId();
					if(jobNames != null){
						prepareStatement.setString(7,vo.getJobNames());//Ĭ�ϵ���
					}else if(!empId.equals("00000")){
						prepareStatement.setString(7,"����");//Ĭ�ϵ���
					}else{
						prepareStatement.setString(7,null);//Ĭ�ϵ���
					}
					
					
					prepareStatement.setString(8,vo.getFilmAreaName());
					prepareStatement.setString(9,vo.getBigAreaName());
					prepareStatement.setDouble(10,vo.getJobWages());
					prepareStatement.setDouble(11,vo.getExceedAmt());
					prepareStatement.setDouble(12,vo.getExceedWages());
					prepareStatement.setDouble(13,vo.getProSaleAmt());
					prepareStatement.setDouble(14,vo.getProSaleWages());
					prepareStatement.setDouble(15,vo.getBraSaleCount());
					prepareStatement.setDouble(16,vo.getBraSaleWages());
					prepareStatement.setDouble(17,vo.getTeamSaleAmt());
					prepareStatement.setDouble(18,vo.getTeamSaleWages());
					prepareStatement.setDouble(19,vo.getSaleAmt());
					prepareStatement.setDouble(20,vo.getSaleWages());
					
					prepareStatement.addBatch();// ��sql�ŵ�һ��������
				}
				
				prepareStatement.executeBatch();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		public List<SaleDailyProductPeopleSumVo> showUsrWagesNew(
				String operatingMonthId, List<String> braIdList) {

			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			String hql = "from LastCountTable as t where t.operatingMonth.operatingMonthId = :operatingMonthId and " +
				"t.branch.braId in (:braIdList)";
			
			List<LastCountTable> tableList = session.createQuery(hql)
				.setString("operatingMonthId",operatingMonthId).setParameterList("braIdList",braIdList).list();
			
			
			List<SaleDailyProductPeopleSumVo>  voList = new ArrayList<SaleDailyProductPeopleSumVo>();
			
			for(LastCountTable t : tableList){
				voList.add(SaleDailyProductPeopleSumVo.parse(t));
			}
			return voList;
		}

		public List<Double> getAllAndPubSumNew(String operatingMonthId,
				String unitid) {
			
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		
			String hql = "select sum(t.sumsaleWages) from LastCountTable as t where " +
					"t.operatingMonth.operatingMonthId = :operatingMonthId and " +
						"t.orgstdStruct.unitid = :unitid";	
			
			Double allSumsumsaleWages =	(Double)session.createQuery(hql).setString("operatingMonthId",operatingMonthId)
					.setString("unitid",unitid).uniqueResult();
				
				
				hql = "select t.sumsaleWages from LastCountTable as t where " +
						"t.operatingMonth.operatingMonthId = :operatingMonthId and " +
							"t.orgstdStruct.unitid = :unitid and t.psnaccount.personid = '00000'";
				
				Double pulWages = (Double)session.createQuery(hql).setString("operatingMonthId",operatingMonthId)
						.setString("unitid",unitid).uniqueResult();	
				
				
				hql = "select sum(t.sumsaleWages) from LastCountTable as t where " +
						"t.operatingMonth.operatingMonthId = :operatingMonthId and " +
							"t.orgstdStruct.unitid = :unitid and t.psnaccount.personid  != '00000'";
				
				Double allWages =(Double)session.createQuery(hql).setString("operatingMonthId",operatingMonthId)
						.setString("unitid",unitid).uniqueResult();	
				
				List<Double> doubleList = new ArrayList<Double>();	
			
				if(allWages == null){
					allWages =0.0;
				}
				if(pulWages == null){
					pulWages =0.0;
				}
				if(allSumsumsaleWages == null){
					allSumsumsaleWages =0.0;
				}
				
				doubleList.add(Arith.round(allWages,2));//����Ա��(���������˺�)
				 doubleList.add(Arith.round(pulWages,2));//�����˺� ��Ʒ��,Ʒ����� ����
				 doubleList.add(Arith.round(allSumsumsaleWages,2));// ����Ա��(�������˺�) ��Ʒ��,Ʒ����� ����
			
				 return doubleList;
		}

		public List<SaleDailyProductPeopleSumVo> collectUsrWages(
				String operatingMonthId) {
			
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			//t.psnaccount.cpcjobcode.names
			String hql ="select t.employee.empId,t.employee.personalId, " +
						"sum(t.jobWages),sum(t.proSaleAmt),sum(t.proSaleWages),sum(t.branchSaleAmt)," +
							"sum(t.branchSaleWages),sum(t.sumSaleAmt),sum(t.sumsaleWages) " +
								"from LastCountTable as t where " +
									"t.operatingMonth.operatingMonthId = :operatingMonthId and t.employee.empId != '00000' " +
				"group by t.employee.empId,t.employee.personalId " +
								"order by t.employee.empId";	
			//ͳ������Ա��(���˺�)�Ľ���
			List<Object[]> objList = session.createQuery(hql)
					.setString("operatingMonthId",operatingMonthId).list();	
			
			List<SaleDailyProductPeopleSumVo> voList = new ArrayList<SaleDailyProductPeopleSumVo>();
			
			//���Ա��personalId ���� ��Ӧ��� ,e.EmpName,e.EmpId,e.PersonalId,
			String sql = "select e.BraId,p.PERSONID,o.UNITID "+
					"from employee as e "+
					" left join PSNACCOUNT as p on p.EMPLOYEEID = e.PersonalId "+
					" left join ORGStdStruct as o on o.UNITID = p.BRANCHID	" +
					"inner join branch as b on b.BraId = e.BraId "+				
							"where e.EmpId = :empId";
			
			Object[] queryObj = null;
			
			for(Object[] obj : objList){
				
				SaleDailyProductPeopleSumVo vo = new SaleDailyProductPeopleSumVo();
				
				 vo.setJobWages(0.0);
				 vo.setExceedAmt(0.0);
				 vo.setExceedWages(0.0);
				 vo.setTeamSaleAmt(0.0);
				 vo.setTeamSaleWages(0.0);
				
				vo.setEmpId(obj[0].toString());
				
				queryObj = (Object[]) session.createSQLQuery(sql)
					.setString("empId",vo.getEmpId()).uniqueResult();
				
				if(queryObj != null){

					vo.setBraId(queryObj[0] != null ?queryObj[0].toString():null);
					
					vo.setPersonalId(queryObj[1] != null?queryObj[1].toString():null);
					vo.setUnitid(queryObj[2]!=null?queryObj[2].toString():null);
					vo.setOperatingMonthId(operatingMonthId);
					vo.setEmployeeid(obj[1] != null?obj[1].toString():null);
					vo.setJobWages((Double)obj[2]);
					vo.setProSaleAmt((Double)obj[3]);
					vo.setProSaleWages((Double)obj[4]);
					vo.setBraSaleCount((Double)obj[5]);
					vo.setBraSaleWages((Double)obj[6]);
					vo.setSaleAmt((Double)obj[7]);
					vo.setSaleWages((Double)obj[8]);
					voList.add(vo);
				}
				
				
			}
			
			String sql2 ="insert into collectUsrWages(BraId,UNITID,operatingMonthId,saleMan,PERSONID," +
					"EMPLOYEEID,NAMES,filmAreaName," +
						"bigAreaName,jobWages,exceedAmt,exceedWages," +
							"proSaleAmt,proSaleWages,branchSaleAmt,branchSaleWages," +
								"teamSaleAmt,teamSaleWages,sumSaleAmt,sumsaleWages) " +
									" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			Connection	connection = session.connection();
			
			try {
				
				PreparedStatement prepareStatement = connection.prepareStatement(sql2);
				
				for(SaleDailyProductPeopleSumVo vo : voList){
					
					String my =	vo.getBraId();
					
					prepareStatement.setString(1,vo.getBraId());
					prepareStatement.setString(2,vo.getUnitid());
					prepareStatement.setString(3,vo.getOperatingMonthId());
					prepareStatement.setString(4,vo.getEmpId());
					prepareStatement.setString(5,vo.getPersonalId());
					prepareStatement.setString(6,vo.getEmployeeid());
					prepareStatement.setString(7,null);
					prepareStatement.setString(8,vo.getFilmAreaName());
					prepareStatement.setString(9,vo.getBigAreaName());
					prepareStatement.setDouble(10,vo.getJobWages());
					prepareStatement.setDouble(11,vo.getExceedAmt());
					prepareStatement.setDouble(12,vo.getExceedWages());
					prepareStatement.setDouble(13,vo.getProSaleAmt());
					prepareStatement.setDouble(14,vo.getProSaleWages());
					prepareStatement.setDouble(15,vo.getBraSaleCount());
					prepareStatement.setDouble(16,vo.getBraSaleWages());
					prepareStatement.setDouble(17,vo.getTeamSaleAmt());
					prepareStatement.setDouble(18,vo.getTeamSaleWages());
					prepareStatement.setDouble(19,vo.getSaleAmt());
					prepareStatement.setDouble(20,vo.getSaleWages());
					
					prepareStatement.addBatch();// ��sql�ŵ�һ��������
					
				}
				
				prepareStatement.executeBatch();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			String sql3 ="insert into collectUsrWages" +
				"(BraId,UNITID,operatingMonthId,saleMan,PERSONID, EMPLOYEEID,[NAMES],filmAreaName,bigAreaName," +
					"jobWages,exceedAmt,exceedWages," +
						"proSaleAmt,proSaleWages,branchSaleAmt,branchSaleWages,teamSaleAmt," +
							"teamSaleWages,sumSaleAmt,sumsaleWages) " +
			"select BraId,UNITID,operatingMonthId,saleMan,PERSONID, EMPLOYEEID,[NAMES],filmAreaName,bigAreaName," +
					"jobWages,exceedAmt,exceedWages, " +
					"proSaleAmt,proSaleWages,branchSaleAmt,branchSaleWages,teamSaleAmt," +
						"teamSaleWages,sumSaleAmt,sumsaleWages from lastCountTable " +
					"where operatingMonthId = :operatingMonthId and saleMan = '00000' ";
		
			 session.createSQLQuery(sql3).
			 	setString("operatingMonthId",operatingMonthId).executeUpdate();
			
			 
			 
				return voList;
		}

		public List<SaleDailyProductPeopleSumVo> selectcollectUsrWages(
				String operatingMonthId) {
			

			
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			//t.psnaccount.cpcjobcode.names
			String hql ="select t.branch.braId,t.orgstdStruct.unitname,t.employee.personalId," +
				"t.employee.empId,t.employee.empName,t.psnaccount.cpcjobcode.names," +
					"t.jobWages,t.proSaleAmt,t.proSaleWages,t.branchSaleAmt," +
						"t.branchSaleWages,t.sumSaleAmt,t.sumsaleWages,t.orgstdStruct.orgstdStruct.unitname," +
							"t.orgstdStruct.orgstdStruct.orgstdStruct.unitname  from CollectUsrWages as t where " +
								"t.operatingMonth.operatingMonthId = :operatingMonthId " +
									"order by t.branch.braId,t.employee.empId";	
			
			List<Object[]> objList = session.createQuery(hql)
					.setString("operatingMonthId",operatingMonthId).list();	
			
			List<SaleDailyProductPeopleSumVo> voList =new ArrayList<SaleDailyProductPeopleSumVo>();
			
			for(Object[] obj : objList){
				
				SaleDailyProductPeopleSumVo vo = new SaleDailyProductPeopleSumVo();
				vo.setBraId(obj[0].toString());
				vo.setBraName(obj[1] != null ? obj[1].toString():null);
				vo.setEmployeeid(obj[2] != null ? obj[2].toString():null);
				vo.setEmpId(obj[3].toString());
				vo.setEmpName(obj[4].toString());
				vo.setJobNames(obj[5] != null? obj[5].toString():null);
				vo.setJobWages((Double)obj[6]);
				
				vo.setProSaleAmt((Double)obj[7]);
				vo.setProSaleWages((Double)obj[8]);
				vo.setBraSaleCount((Double)obj[9]);
				vo.setBraSaleWages((Double)obj[10]);
				vo.setSaleAmt((Double)obj[11]);
				vo.setSaleWages((Double)obj[12]);
				
				vo.setFilmAreaName(obj[13].toString());
				vo.setBigAreaName(obj[14].toString());
				
				voList.add(vo);
				
			}
				return voList;
			
			
		}

		

	
	
	
	
}