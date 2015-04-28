package com.kang.ehcache;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.kang.model.SysUsers;

public class TestUser {

	public void testReanWrite(){
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		SysUsers usr =(SysUsers)session.get(SysUsers.class, 1);
		System.out.println(usr.getUsrname());
		usr.setUsrname("good1");
		session.save(usr);
		tx.commit();
		session.flush();
		session.close();
		
		Session session2 = sessionFactory.openSession();
		SysUsers usr2 =(SysUsers)session2.get(SysUsers.class, 1);
		System.out.println(usr2.getUsrname());
		session2.close();
	}
	
	public void getWithSet(){
		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		SysUsers usr =(SysUsers)session.get(SysUsers.class, 1);
		
		Set<SysUsers> usrList =	usr.getSysUserses();
		for(SysUsers s : usrList){
			System.out.println(s.getUsrname());
		}
		session.close();
		System.out.println(session.isOpen());
		
		Session session2 = sessionFactory.openSession();
		SysUsers usr2 =(SysUsers)session2.get(SysUsers.class, 1);
		Set<SysUsers> usrList2 = usr2.getSysUserses();
		for(SysUsers s : usrList2){
			System.out.println(s.getUsrname());
		}
		session2.close();
	}
	
	public void testQueryCache(){
		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		String hql ="from SysUsers";
		
		Session session = sessionFactory.openSession();
		
		List<SysUsers> usrList = session.createQuery(hql).setCacheable(true).list();
		
		List<SysUsers> usrList2 = session.createQuery(hql).setCacheable(true).list();
		
		session.close();
		/*try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		/*Session session2 = sessionFactory.openSession();
		List<SysUsers> usrList2 = session2.createQuery(hql).setCacheable(true).list();
		session2.close();*/
	}
	
	
	public static void main(String[] args) {
		
		TestUser t = new TestUser();
		//t.testReanWrite();
		//t.getWithSet();//缓存集合不行
		t.testQueryCache();
	}

}