package com.kang.hqlsql;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.Transformers;

import com.kang.model.SysUsers;
import com.kang.model.TReim;

public class TestSql {

	
	public void testAddScalar(){

		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		
		String hql ="select usrname from sys_users";
		List<String> strList =	
			session.createSQLQuery(hql).addScalar("usrname", Hibernate.STRING).list();
		
		for(String s :strList){
			System.out.println(s);
		}
		
		session.close();
		
	}
	
	public void testAddScalar2(){
		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		
		String hql = "select usrid,usrname from sys_users";
		List<Object[]> objArray =	session.createSQLQuery(hql)
			.addScalar("usrid", Hibernate.INTEGER).addScalar("usrname",Hibernate.STRING)
				.list();
		
		for(Object[] obj : objArray){
			System.out.println(obj[0]+","+obj[1]);
		}
		session.close();
	}
	
	public void testAddEntity(){
		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		
		String hql ="select * from sys_users";
		List<SysUsers> usrList = 
			session.createSQLQuery(hql).addEntity(SysUsers.class).list();
		
		for(SysUsers usr : usrList){
			System.out.println(usr.getUsrname());
		}
		
		session.close();
		
	}
	
	public void testAddEntityByName(){
		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		
		String sql ="select distinct {t.*} from t_reim as t inner join sys_users as s  where t.usrid = 1";
		
		List<TReim> treimList =	session.createSQLQuery(sql).addEntity("t",TReim.class).list();
		for(TReim t : treimList){
			System.out.println(t.getRmname());
		}
		
		session.close();
		
	}
	
	public void testAliasToBean(){
		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		
		String sql ="select usrid,usrname from sys_users";
		List<SysUsers> usrList =  session.createSQLQuery(sql)
			.addScalar("usrid",Hibernate.INTEGER).addScalar("usrname",Hibernate.STRING)
				.setResultTransformer(Transformers.aliasToBean(SysUsers.class)).list();
		
		for(SysUsers usr : usrList){
			System.out.println(usr.getUsrid()+","+usr.getUsrname());
		}
		session.close();
	}
	
	
	public static void main(String[] args) {

		
		TestSql  t = new TestSql();
		//t.testAddScalar();
		//t.testAddScalar2();
		//t.testAddEntity();
		//t.testAddEntityByName();
		t.testAliasToBean();
	}

}
