package com.gialen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gialen.model.PlanBrand;
import com.gialen.model.ProProjectRelationTable;
import com.gialen.model.ProProjectRelationTableGroup;
import com.gialen.model.Product;

/**
 * A data access object (DAO) providing persistence and search support for
 * ProProjectRelationTable entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.gialen.model.ProProjectRelationTable
 * @author MyEclipse Persistence Tools
 */

public class ProProjectRelationTableDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ProProjectRelationTableDAO.class);
	// property constants
	public static final String PRODUCT_PROJECT_ID = "productProjectId";

	protected void initDao() {
		// do nothing
	}

	public void save(ProProjectRelationTable transientInstance) {
		log.debug("saving ProProjectRelationTable instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ProProjectRelationTable persistentInstance) {
		log.debug("deleting ProProjectRelationTable instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ProProjectRelationTable findById(java.lang.Integer id) {
		log.debug("getting ProProjectRelationTable instance with id: " + id);
		try {
			ProProjectRelationTable instance = (ProProjectRelationTable) getHibernateTemplate()
					.get("com.gialen.model.ProProjectRelationTable", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ProProjectRelationTable instance) {
		log.debug("finding ProProjectRelationTable instance by example");
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
		log.debug("finding ProProjectRelationTable instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ProProjectRelationTable as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByProductProjectId(Object productProjectId) {
		return findByProperty(PRODUCT_PROJECT_ID, productProjectId);
	}

	public List findAll() {
		log.debug("finding all ProProjectRelationTable instances");
		try {
			String queryString = "from ProProjectRelationTable";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ProProjectRelationTable merge(
			ProProjectRelationTable detachedInstance) {
		log.debug("merging ProProjectRelationTable instance");
		try {
			ProProjectRelationTable result = (ProProjectRelationTable) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ProProjectRelationTable instance) {
		log.debug("attaching dirty ProProjectRelationTable instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ProProjectRelationTable instance) {
		log.debug("attaching clean ProProjectRelationTable instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProProjectRelationTableDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ProProjectRelationTableDAO) ctx
				.getBean("ProProjectRelationTableDAO");
	}

	public void saveProProjectRelationTables(Integer productProjectId,
			String[] checkboxVarstrForString) {

		// session记得关闭
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		// Transaction tr = session.beginTransaction();

		Connection connection = session.connection();

		String sql = "insert into ProProjectRelationTable(proId,productProjectId,brandId)" +
				" values(?,?,?)";

		try {

			PreparedStatement prepareStatement = connection
					.prepareStatement(sql);

			for (int i = 0; i < checkboxVarstrForString.length; i++) {
				
				
				Product product=find_Product_Brandid_ById(checkboxVarstrForString[i]);
				
				String Brandid=product.getProductBrand().getId();
				//System.out.println("Brandid "+Brandid);
				
				prepareStatement.setString(1, checkboxVarstrForString[i]);
				prepareStatement.setInt(2, productProjectId);//
				
				prepareStatement.setString(3, Brandid);//
				prepareStatement.addBatch();// 把sql放到一个容器中
			}

			prepareStatement.executeBatch();
			// tr.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		session.close();

	}
	
	//删除当前单品提成方案所有关系表
	public void delAllChlidren(Integer proProjectId) {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			String hql ="delete from ProProjectRelationTable as p " +
					"where p.productProject.productProjectId =:proProjectId";
			 session.createQuery(hql).setInteger("proProjectId", proProjectId).executeUpdate();
	}

	//根据项目id查找 该项目明细
	public List<ProProjectRelationTable> findProProjectRelationTableByProductProjectId(
			String productProjectId) {
		
		//System.out.println("明细在这里!");
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="from ProProjectRelationTable as p " +
				"where p.productProject.productProjectId =:proProjectId";
		return session.createQuery(hql)
				.setInteger("proProjectId",Integer.valueOf(productProjectId))
					.list();
		//.setCacheable(true)
	}
	
	
	
	
	
	
	
	//批量保存所有单品任务表 
	public void saveAllTable(List<ProProjectRelationTable> tableList) {
			
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		for(int i =0;i<tableList.size();i++){
			session.save(tableList.get(i));
				if(i %50 == 0){
					session.flush();
					session.clear();
				}
		}
	}

	//查询该单品 在 当前营运月 是否在某个单品方案中
	public List<ProProjectRelationTable> getProjectByProAndMonth(String proId,
			String thisMonthId) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql = "from ProProjectRelationTable as p where p.product.proId = :proId and " +
			"p.productProject.operatingMonth.operatingMonthId = :thisMonthId and " +
				"p.productProject.projectStatus = 0 ";
		return session.createQuery(hql)
			.setString("proId",proId).setString("thisMonthId",thisMonthId).list();
	}
	
	//查询这个营运月单品项目,重复的单品
	public List<ProProjectRelationTable> findRepeatProProject(String operatingMonthId) {
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql = "from ProProjectRelationTable as p where p.productProject.operatingMonth.operatingMonthId = :operatingMonthId and " +
			"p.product.proId in (select t.product.proId from ProProjectRelationTable as t where t.productProject.operatingMonth.operatingMonthId = :operatingMonthId and  t.productProject.projectStatus = 0 group by t.product.proId having count(t.product.proId) > 1) and " +
				" p.productProject.projectStatus = 0 order by p.product.proId,p.productProject.productProjectId";
		return session.createQuery(hql).setString("operatingMonthId",operatingMonthId).list();
	}

	//删除这个营运月单品项目,重复的单品
	public void delRepeatProProject(List<String> delProlist) {
		
		List<Integer> intList = new ArrayList<Integer>();
		for(String s : delProlist){
			intList.add(Integer.valueOf(s));
		}
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="delete from ProProjectRelationTable as p where p.proProjectRelationTableId in (:delProlist)";
		session.createQuery(hql).setParameterList("delProlist",intList).executeUpdate();
	}

	//查询今个月所有单品，方案的中间表 
	public List<ProProjectRelationTable> findByProIdAndMonthId(
			List<String> thisMonthProjectsProidList, String thisMonthId) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="from ProProjectRelationTable as p where p.product.proId in (:thisMonthProjectsProidList) and " +
				"p.productProject.operatingMonth.operatingMonthId = :thisMonthId";
		return session.createQuery(hql)
				.setParameterList("thisMonthProjectsProidList",thisMonthProjectsProidList)
					.setString("thisMonthId",thisMonthId).list();
	}
	
	
	
	

	// /alpha 2012-8-13

	public void saveProProject_RelationTables(Integer productProjectId,
			String[] checkboxVarstrForString,List<PlanBrand> pbs) {
		
		System.out.println("pbs "+pbs.size());
		
		
		// session记得关闭
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		// Transaction tr = session.beginTransaction();

		Connection connection = session.connection();
		String sql_product = "insert into PlanBrand (planbrandmainPlan,planBrandsecondPlan ) values(?,?)";
		try {
			PreparedStatement prepareStatement_product = connection.prepareStatement(sql_product);
			for (int i = 1; i < checkboxVarstrForString.length; i++) {

				String flag = checkboxVarstrForString[i].substring(
						checkboxVarstrForString[i].lastIndexOf(",") + 1,
						checkboxVarstrForString[i].length());				
				System.out.println("flag "+flag);				
				// checkboxVarstrForString[i].
				 if (flag == "1") {
					System.out.println("1");				
					prepareStatement_product.setString(1, "1");				
					prepareStatement_product.setString(2, "2");					
					prepareStatement_product.addBatch();// 把sql放到一个容器中
				 } 
//				else if (flag == "0"){
//					System.out.println("0");
//					prepareStatement_ProId.setString(1, checkboxVarstrForString[i]);
//					prepareStatement_ProId.addBatch();// 把sql放到一个容器中
//
//				}

//				prepareStatement_ProId.executeBatch();
				prepareStatement_product.executeBatch();
				// tr.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		session.close();

	}
	
	
	////alpha 2012-8-20
    public Product find_Product_Brandid_ById( java.lang.String id) {
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
    
    
    
    
    
    
    
    
    
    
    
    
	// 根据项目id查找 该项目明细
	public List<ProProjectRelationTable> findProProjectRelationTableByBrandId(
			Integer productProjectId) {

		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		System.out.println("productProjectId: "+productProjectId);
		
		String hql = "select p from ProProjectRelationTable as p where p.productProject.productProjectId =:proProjectId";
		return session.createQuery(hql).setInteger("proProjectId", productProjectId).list();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//商品集插入保存
	public void savePlanBrand(Integer productProjectId,
			String[] checkboxVarstrForString) {

		// session记得关闭
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		// Transaction tr = session.beginTransaction();

		Connection connection = session.connection();

		String sql = "insert into ProProjectRelationTable(proId,productProjectId,brandId) values(?,?,?)";

		try {

			PreparedStatement prepareStatement = connection
					.prepareStatement(sql);

			for (int i = 0; i < checkboxVarstrForString.length; i++) {
				
				
				Product product=find_Product_Brandid_ById(checkboxVarstrForString[i]);
				
				String Brandid=product.getProductBrand().getId();
				//System.out.println("Brandid "+Brandid);
				
				prepareStatement.setString(1, checkboxVarstrForString[i]);
				prepareStatement.setInt(2, productProjectId);//
				
				prepareStatement.setString(3, Brandid);//
				prepareStatement.addBatch();// 把sql放到一个容器中
			}

			prepareStatement.executeBatch();
			// tr.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		session.close();

	}
	
	//根据单品id 营运月id 查找单品方案

	
	
	
////alpha  2012-9-18 添加组合关系
	public void saveProProjectRelationTables_Group(Integer productProjectId,
			String[] checkboxVarstrForString) {

		// session记得关闭
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		// Transaction tr = session.beginTransaction();

		Connection connection = session.connection();

		String sql = "insert into ProProjectRelationTable_Group(proId,productProjectId,brandId,quantity) values(?,?,?,?)";

		try {

			PreparedStatement prepareStatement = connection
					.prepareStatement(sql);

			for (int i = 0; i <1; i++) {
				
				
				Product product=find_Product_Brandid_ById(checkboxVarstrForString[0]);
				
				String Brandid=product.getProductBrand().getId();
				//System.out.println("Brandid "+Brandid);
				
				prepareStatement.setString(1, checkboxVarstrForString[0]);
				prepareStatement.setInt(2, productProjectId);//
				
				prepareStatement.setString(3, Brandid);//
				//prepareStatement.setString(4, Brandid);//
				
				prepareStatement.setDouble(4, Double.valueOf(checkboxVarstrForString[1]));
				prepareStatement.addBatch();// 把sql放到一个容器中
				
				
			System.out.println(checkboxVarstrForString[0]+" "+checkboxVarstrForString[1]);
				
			}

		prepareStatement.executeBatch();
			// tr.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		session.close();

	}
	
	
	
	

	//alpha 2012-9-20    根据项目id查找 该组合项目明细
	public List<ProProjectRelationTableGroup> findProProjectRelationTableGroupByProductProjectId(
			Integer productProjectId) {
		
		//System.out.println("明细在这里!");
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="from ProProjectRelationTableGroup as p " +
				"where p.productProject.productProjectId =:proProjectId order by proProjectRelationTable_GroupId ";
		return session.createQuery(hql)
				.setInteger("proProjectId",productProjectId)
					.list();
		//.setCacheable(true)
	}
	
	
	
	
	///2012-9-21 统计同一方案里面  proid
	public List<Object[]> findTableGroup_ProidbyproProjectId(
			Integer productProjectId) {
		
		 //distinct
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
 
//		String sql ="select distinct(p.proid) from ProProjectRelationTable_Group as p where p.productProjectId="+productProjectId;
//		
//		return session.createSQLQuery(sql).list(); 
		
		
		String hql =" select distinct (p.product.proId)  , p.quantity from ProProjectRelationTableGroup p where p.productProject.productProjectId =:productProjectId";
		
		return session.createQuery(hql)
				.setInteger("productProjectId",productProjectId)
					.list();
	}	
	
	
	
	///2012-9-21 统计同一方案里面  proid
	public List<Object[]> findTableGroup_ProidbyproProjectId2(
			Integer productProjectId) {
		
		 //distinct
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
 
   		String sql ="select distinct(p.proid) from ProProjectRelationTable_Group as p where p.productProjectId="+productProjectId;
//		
 		return session.createSQLQuery(sql).list(); 
		
 
	}	
	
	
	
	
	
	
	///2012-9-21 统计同一方案里面  proid
	public List<Object[]> findSale_daily_byProid(
			String sql_proid) {
		
 
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
  
		String hql="select distinct (saleId) from sale_daily where "+ sql_proid;
		
 		return session.createSQLQuery(hql).list(); 
		
 
	}
	
	
	//2012-9-22统计组合
//	public void findGroupSale_daily_byProid(String[] proid,Integer productprojectid) {
//
//		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();	
//		String sql ="insert into  sale_daily_group select s.BraId ,s.saleid  ,s.saleman  , s.saleqty  from sale_daily s where s.proid='"+proid[0]+"' and s.saleid in (select y.saleid from sale_daily y where y.proid='"+(proid[1].substring(1, proid[1].length()))+"' and y.saleman=s.saleman and s.braid=y.braid)  order by braid";
// 		session.createSQLQuery(sql).executeUpdate();  
// 		
//		 	
//		String sqls ="insert into sale_daily_group_temp select s.BraId ,s.saleid  ,s.saleman  , "+productprojectid+" , s.saleqty  from sale_daily_group s ";		
 	
// 		session.createSQLQuery(sqls).executeUpdate(); 
//	}
 	
	
	
	
	
}

 		
 		
//      select s.braid , s.proid,s.saleid ,s.saleman ,s.saleqty from sale_daily s where s.proid='00510998' and s.saleid in (select y.saleid from sale_daily y where y.proid='00511032' and y.saleman=s.saleman and s.braid=y.braid)   order by braid		
//		String sql="insert into  sale_daily_group  select braid, saleid,saleman, proid, saleqty  from sale_daily where (  "+saleid+"   ) and ( "+sql_proid+" )  ";		
//	 	  session.createSQLQuery(sql).executeUpdate();		
// 		 String sql_temp= "insert into  sale_daily_group_temp select braid,saleid,saleman from  sale_daily where  ( "+sql_proid+" )   group  by braid ,saleid ,saleman having  count(distinct(proid)) > 1 ";	 
// 		 session.createSQLQuery(sql_temp).executeUpdate(); 	 
//  		 String sql_delete= "insert into  sale_daily_group_sum  select  braid,saleid,saleman,proid ,saleqty from sale_daily_group where saleid in (select saleid from sale_daily_group_temp )"; 
// 		 session.createSQLQuery(sql_delete).executeUpdate();  		 
	
	
	
