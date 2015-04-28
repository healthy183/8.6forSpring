package com.kang.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.kang.model.SysUsers;

public class OnlyOne {
	
	public void saveone(){
		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		
		Session session = configuration.buildSessionFactory().openSession();
		Transaction tx	= session.beginTransaction();
		tx.begin();
		//SysUsers user =(SysUsers)session.get(SysUsers.class, 1); 
		//System.out.println(user.getUsrname());
		
		SysUsers newUser = new SysUsers();
		newUser.setUsrname("梁伟雄");
		newUser.setUsrpwd("123");
		session.save(newUser);
		//session.persist(newUser);
		
		System.out.println(newUser.getUsrid() == null);
		System.out.println("wfh");
		
		tx.commit();
		session.close();
	}

	public void delOne(){
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		
		Session session = configuration.buildSessionFactory().openSession();
		Transaction tx	= session.beginTransaction();
		tx.begin();
		//1:直接用hiberate session的delete()方法
		//SysUsers user =(SysUsers)session.get(SysUsers.class, 2);
		//session.delete(user);
		//写hql删除
		String hql ="delete from SysUsers as s where s.usrname like ?";
		int i =	session.createQuery(hql).setString(0,"%伟雄%").executeUpdate();
		System.out.println(i+"个对象被删除了!");
		tx.commit();
		session.close();
	}
	
	public  void updtOne(){
		// 加载配置文件
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		
		Session session = configuration.buildSessionFactory().openSession();
		Transaction tx	= session.beginTransaction();
		tx.begin();
		SysUsers user =(SysUsers)session.get(SysUsers.class, 1); 
		user.setUsrpwd("123");
		session.update(user);
		tx.commit();
		session.close();
	}
	
	public void getOrLoad() {

		// 加载配置文件
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		//SysUsers user = (SysUsers)session.get(SysUsers.class,100);//从一级缓存,二级缓存,数据库依次查询
		//load 直接一级缓存返回一个object,当有user.getUsrname()的时候就会从数据库中查询
		SysUsers user =(SysUsers)session.load(SysUsers.class,1000);
		System.out.println(user == null);
		System.out.println(user.getUsrname());//get空指针  load报 No row with the given identifier exists:
		session.close();
	}
	
	public void updtOrMerge() {
		Configuration configuration = new AnnotationConfiguration();
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		
		SysUsers newUser = new SysUsers();
		newUser.setUsrname("AAAA");
		newUser.setUsrpwd("123");
		//session.update(newUser);//报异常 The given object has a null identifier
		//session.merge(newUser);//直接插入数据(可以返回一个PO持久化对象)
		session.saveOrUpdate(newUser);//直接插入数据
		
		tx.commit();
		session.close();

	}
	
	public void  dirty(){
		Configuration configuration = new AnnotationConfiguration();
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		
		SysUsers user =(SysUsers)session.get(SysUsers.class, 1); 
		user.setUsrpwd("1232dd33");
		//session.update(user);//就算session没有调用该update方法
		tx.commit();//只要有事务提交,修改就有效果
		session.close();
	}
	
	public void saveOrUpdate(){
		Configuration configuration = new AnnotationConfiguration();
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		
		SysUsers user =(SysUsers)session.get(SysUsers.class, 1); 
		user.setUsrpwd("ddd"); 
		session.saveOrUpdate(user);
		tx.commit();
		session.close();
		
	}
	
	
	
	public static void main(String[] args) {
		
		OnlyOne o = new OnlyOne();
		//o.saveone();
		//o.delOne();
		//o.updtOne();
		//o.getOrLoad();
		//o.updtOrMerge();
		//o.dirty();
		o.saveOrUpdate();
	}

}
