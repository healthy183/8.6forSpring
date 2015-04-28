package com.kang.hqlsql;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.kang.model.SysUsers;
import com.kang.model.TReim;

public class TestHql {
			
	public void testList(){
		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		
		
		String hql ="from SysUsers";
		
		List<SysUsers> usrList = session.createQuery(hql).list();
		
		for(SysUsers usr : usrList){
			System.out.println(usr.getUsrname());
		}
		session.close();
	}
	
	public void testIterate(){
		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		
		String hql ="from SysUsers";
		
		Iterator<SysUsers> iterator = session.createQuery(hql).iterate();
	
		SysUsers user = null;
		while(iterator.hasNext()){
			user = iterator.next();
			user.getUsrname();//iterator 会根据id查找
		}
		
		session.close();
	}
	
	public void findProperty(){
		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		
		String hql ="select usrname from SysUsers";
		List<String> str =	session.createQuery(hql).list();
		for(String s : str){
			System.out.println(s);
		}
		session.close();
	}
	
	public void fingObjectArray(){
		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		
		String hql ="select usrid,usrname from SysUsers";
		List<Object[]> str =session.createQuery(hql).list();
		for(Object[] obj : str){
			System.out.println("show:"+obj[0]+","+obj[1]);
		}
		session.close();
	}
	
	public void findByNew(){
		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		
		String hql ="select new SysUsers(usrid,usrname) from SysUsers";
		List<SysUsers> str =session.createQuery(hql).list();
		
		for(SysUsers s : str){
			System.out.println(s.getUsrname());
		}
		session.close();
	}
	
	public void findByMap(){
		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		
		/*String hql ="select new Map(usrid,usrname) from SysUsers";
		List<Map> str = session.createQuery(hql).list();
		
		for(Map m : str){
			System.out.println(m.get("1"));
		}*/
		
		String hql ="select new Map(usrid as id,usrname as usrName) from SysUsers";
		List<Map> str = session.createQuery(hql).list();
		
		for(Map m : str){
			System.out.println(m.get("usrName"));
		}
		
		session.close();
	}
	
	public void innerJoin(){
		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		// new TReim(rmid,rmname)
		//内连接(hql默认延迟加载)
		String hql ="select t from TReim as t inner join t.sysUsers as s where s.usrid = 1 ";
		//迫切内连接 同时把usr都查询出来
		//String hql ="select t from TReim as t inner join fetch t.sysUsers as s where s.usrid = 1 ";
		List<TReim> tList =	session.createQuery(hql).list();
		
		for(TReim t : tList){
			System.out.println(t.getRmname());
			//System.out.println(t.getSysUsers().getUsrname());
		}
		
		session.close();
	}
	
	
	
	
	public static void main(String[] args) {

		TestHql test = new TestHql();
		//test.testList();
		//test.testIterate();
		//test.findProperty();
		//test.fingObjectArray();
		//test.findByNew();
		//test.findByMap();
		test.innerJoin();
	}

}
