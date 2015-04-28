package com.kang.test;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.FetchMode;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.kang.model.SysUsers;
import com.kang.model.TReim;

public class OneToMany {

	// 由一的那方保存
	public void saveOneToMany(){
		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		
		Session session = configuration.buildSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		
		SysUsers leaders = new SysUsers();
		leaders.setUsrname("申请报销员工");
		
		TReim treimA = new TReim();
		treimA.setRmname("报销单A");
		leaders.getTReims().add(treimA);
		treimA.setSysUsers(leaders);
		
		TReim treimB = new TReim();
		treimB.setRmname("报销单B");
		leaders.getTReims().add(treimB);
		treimB.setSysUsers(leaders);
		
		session.save(leaders);
		tx.commit();
		session.close();
	}
	
	// 由多的那方保存
	public void saveManyToOne(){
		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		
		Session session = configuration.buildSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();	
		
		SysUsers leaders = new SysUsers();
		leaders.setUsrname("申请报销员工");
		
		TReim treimA = new TReim();
		treimA.setRmname("报销单A");
		leaders.getTReims().add(treimA);
		treimA.setSysUsers(leaders);
		
		TReim treimB = new TReim();
		treimB.setRmname("报销单B");
		leaders.getTReims().add(treimB);
		treimB.setSysUsers(leaders);
		
		session.save(treimA);
		session.save(treimB);
		
		tx.commit();
		session.close();
	}
	
	public void delOne(){
		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		
		Session session = configuration.buildSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();	
		
		/*String hql ="from SysUsers as s where s.usrid = 55";
		SysUsers user = (SysUsers)session.createQuery(hql).uniqueResult();
		session.delete(user);*/
		
		String hql2 = "delete from TReim as t where t.sysUsers.usrid = 57";
		String hql ="delete from SysUsers as s where s.usrid = 57";
		session.createQuery(hql2).executeUpdate();
		session.createQuery(hql).executeUpdate();
		tx.commit();
		session.close();
	}
	
	public void delMany(){
		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		
		Session session = configuration.buildSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();	
		
		//TReim t =(TReim)session.load(TReim.class,11);
		//TReim t =(TReim)session.get(TReim.class,17);
		//session.delete(t);
		
		String hql ="delete from TReim as t where t.rmid = 18";
		session.createQuery(hql).executeUpdate();
		tx.commit();
		session.close();
	}
	
	public void updtOne(){
		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		
		Session session = configuration.buildSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();	
		
		String hql ="from SysUsers as s where s.usrid = 62";
		SysUsers  user = (SysUsers)session.createQuery(hql).uniqueResult();
		
		String hql2 ="from TReim as t where t.sysUsers.usrid = 62";
		List<TReim> treimList =	session.createQuery(hql2).list();
		for(TReim t : treimList){
			t.setRmname("更新成功!");
		}
		
		String hql3 ="from TReim as t where t.rmid = 22";
		TReim t = (TReim)session.createQuery(hql3).uniqueResult();
		t.setRmname("添加更新");
		t.setSysUsers(user);
		user.getTReims().add(t);
		
		session.update(user);
		tx.commit();
		session.close();
	}

	
	
	public void updtMany(){
		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		
		Session session = configuration.buildSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();	
		
		String hql ="from SysUsers as s where s.usrid = 62";
		SysUsers  user = (SysUsers)session.createQuery(hql).uniqueResult();
		
		String hql3 = "from TReim as t where t.rmid = 21";
		TReim t = (TReim)session.createQuery(hql3).uniqueResult();
		t.setRmname("manyUpdt");
		
		t.setSysUsers(user);
		user.getTReims().add(t);
		
		session.update(t);
		
		tx.commit();
		session.close();
	}
	
	public void findOneToManyByLazyFalse(){
		
		Configuration configuration = new AnnotationConfiguration();
		Session session = configuration.configure().buildSessionFactory()
				.openSession();

		//org.hibernate.annotations.LazyCollectionOption.FALSE  
		//lazy=false的时候都发生1+N
		//SysUsers user =(SysUsers)session.get(SysUsers.class, 62);
		//SysUsers user =(SysUsers)session.load(SysUsers.class, 62);
		
		String hql ="from SysUsers as s where s.usrid = 62";
		SysUsers  user = (SysUsers)session.createQuery(hql).uniqueResult();
		System.out.println(user.getUsrname());
		
		Set<TReim> treimSet = user.getTReims();
		for(TReim t : treimSet){
			System.out.println(t.getRmname());
		}/**/
		
		session.close();
	}
	
	
	public void findOneToManyByLazyTrue() {

		Configuration configuration = new AnnotationConfiguration();
		Session session = configuration.configure().buildSessionFactory()
				.openSession();

		// org.hibernate.annotations.LazyCollectionOption.true
		// lazy=true 的时候都不会发生1+N,除非你getSet
		 
		SysUsers user =(SysUsers)session.load(SysUsers.class, 62);
		//SysUsers user =(SysUsers)session.get(SysUsers.class, 62);
		//String hql = "from SysUsers as s where s.usrid = 62";
		//SysUsers user = (SysUsers) session.createQuery(hql).uniqueResult();
		
		System.out.println(user.getUsrname());
		Set<TReim> treimSet = user.getTReims();
		for (TReim t : treimSet) {
			System.out.println(t.getRmname());
		}
		
		session.close();
	}
	
	
	public void findManyToOneLazyFALSE(){
		
		Configuration configuration = new AnnotationConfiguration();
		Session session = configuration.configure().buildSessionFactory()
				.openSession();
		
		//lazy =false 的时候就发生n+1
		//TReim t =(TReim)session.load(TReim.class, 19);
		TReim t =(TReim)session.get(TReim.class, 19);
		/*String hql ="from TReim as t where t.rmid = 19";
		TReim  t = (TReim)session.createQuery(hql).uniqueResult();*/
		
		System.out.println(t.getRmname());
		//lazy =NO_PROXY,PROXY 的时候就不发生n+1,直到你查询
		//Fetch表示是否使用左联查询 JOIN是 SELECT否
		//System.out.println(t.getSysUsers().getUsrname());
		
		//Use of FetchMode.SUBSELECT not allowed on ToOne associations
		session.close();
		
	}
	
	
	
	public static void main(String[] args) {

		OneToMany one = new OneToMany();
		one.findManyToOneLazyFALSE();
		//one.findOneToManyByLazyFalse();
		//one.findOneToManyByLazyTrue();
		//one.saveOneToMany();
		//one.saveManyToOne();
		//one.delOne();
		//one.delMany();
		//one.updtOne();
		//one.updtMany();
	}

}
