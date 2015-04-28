package com.kang.test;

import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.kang.model.SysUsers;

public class OneToManyLinkedMyself {

	// 由一的那方保存
	public void saveOneToMany() {

		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");

		Session session = configuration.buildSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();

		SysUsers leaders = new SysUsers();
		leaders.setUsrname("领导");

		SysUsers workers = new SysUsers();
		workers.setUsrname("农民工一号");

		SysUsers workers2 = new SysUsers();
		workers2.setUsrname("农民工二号");

		leaders.getSysUserses().add(workers);
		workers.setSysUsers(leaders);

		leaders.getSysUserses().add(workers2);
		workers2.setSysUsers(leaders);

		session.save(leaders);
		tx.commit();
		session.close();
	}

	// 由多的那方保存
	// 保存失败 为什么？
	public void saveManyToOne() {

		// org.hibernate.TransientObjectException:
		// object references an unsaved transient instance - save the transient
		// instance before flushing:

		Configuration configuration = new AnnotationConfiguration();
		Session session = configuration.configure().buildSessionFactory()
				.openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();

		SysUsers leaders = new SysUsers();
		leaders.setUsrname("经理");

		SysUsers workers = new SysUsers();
		workers.setUsrname("员工一号");
		leaders.getSysUserses().add(workers);
		workers.setSysUsers(leaders);
		

		/**/
		SysUsers workers2 = new SysUsers();
		workers2.setUsrname("员工二号");
		leaders.getSysUserses().add(workers2);
		workers2.setSysUsers(leaders);
		// session.flush();
		// session.clear();
		session.save(workers);
		session.save(workers2);

		tx.commit();
		session.close();
	}

	// 新建一的那方 更新多的那方
	public void saveOneupdtMany() {

		Configuration configuration = new AnnotationConfiguration();
		Session session = configuration.configure().buildSessionFactory()
				.openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();

		String hql = "from SysUsers as s where s.usrid = 22";
		SysUsers user = (SysUsers) session.createQuery(hql).uniqueResult();
		user.setUsrname("新大佬3的小弟");

		String hql2 = "from SysUsers as s where s.usrid = 23";
		SysUsers user2 = (SysUsers) session.createQuery(hql2).uniqueResult();
		user2.setUsrname("新大佬3的小弟2");

		SysUsers newUser = new SysUsers();
		newUser.setUsrname("新大佬3");

		newUser.getSysUserses().add(user);
		user.setSysUsers(newUser);

		newUser.getSysUserses().add(user2);
		user2.setSysUsers(newUser);

		session.save(newUser);
		tx.commit();
		session.close();
	}

	// 新建多的那方 更新一的那方
	public void saveManyupdtOne() {
		
		Configuration configuration = new AnnotationConfiguration();
		Session session = configuration.configure().buildSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		
		String hql = "from SysUsers as s where s.usrid = 45";
		SysUsers user = (SysUsers) session.createQuery(hql).uniqueResult();
		user.setUsrname("大佬K3");
		
		SysUsers workers = new SysUsers();
		workers.setUsrname("K3小弟一号");
		workers.setSysUsers(user);
		user.getSysUserses().add(workers);
		
		SysUsers workers2 = new SysUsers();
		workers2.setUsrname("K3小弟二号");
		workers2.setSysUsers(user);
		user.getSysUserses().add(workers2);
		
		session.save(user);
		tx.commit();
		session.close();
	}
	
	
	
	// Set<SysUsers> fetch = FetchType.LAZY
	public void findOneToManyByLazy() {

		Configuration configuration = new AnnotationConfiguration();
		Session session = configuration.configure().buildSessionFactory()
				.openSession();

		// 当FetchType.LAZY的时候，无论get() load() 还是用hql查询,都不会发生1+N现象,除非当你需要获得其多对象

		// SysUsers user =(SysUsers)session.load(SysUsers.class, 18);
		// SysUsers user =(SysUsers)session.get(SysUsers.class, 18);
		
		  String hql ="from SysUsers as s where s.usrid = 18";
		  SysUsers user  =(SysUsers)session.createQuery(hql).uniqueResult();
		  System.out.println(user.getUsrname());
		  
		/*
		 * Set<SysUsers> users = user.getSysUserses(); for(SysUsers s : users){
		 * System.out.println(s.getUsrname()); }
		 */

		// 但getSysUsers @ManyToOne(fetch = FetchType.EAGER)的时候
		// get load会发生left outer join连其一方都查询,多的那方依然不会发生1+N现象
		/*SysUsers userLeader = (SysUsers) session.load(SysUsers.class, 19);

		System.out.println(userLeader.getSysUsers().getUsrname());
		// 当需要用的时候,才执行sql查询
		Set<SysUsers> userSet = userLeader.getSysUserses();
		for (SysUsers usr : userSet) {
			System.out.println(usr.getUsrname());
		}*/
		session.close();
	}

	// Set<SysUsers> fetch = FetchType.EAGER的时候
	// getSysUsers @ManyToOne(fetch = FetchType.EAGER)将失效
	public void findOneToManyByEager() {

		Configuration configuration = new AnnotationConfiguration();
		Session session = configuration.configure().buildSessionFactory()
				.openSession();

		// 当FetchType.EAGER的时候，无论get() load() 还是用hql查询,都会发生1+N现象

		// get() load()都发生 left outer join 查询其一的那方
		SysUsers user = (SysUsers) session.load(SysUsers.class, 19);
		// SysUsers user =(SysUsers)session.get(SysUsers.class, 18);

		// hql就是纯的1+N现象
		// String hql ="from SysUsers as s where s.usrid = 18";
		// SysUsers user =(SysUsers)session.createQuery(hql).uniqueResult();
		System.out.println(user.getUsrname());
		Set<SysUsers> users = user.getSysUserses();
		for (SysUsers s : users) {
			System.out.println(s.getUsrname());
		}/**/

		session.close();
	}

	public void updtOneToManyByOne(){
		
		Configuration configuration = new AnnotationConfiguration();
		Session session = configuration.configure().buildSessionFactory()
				.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "from SysUsers as s where s.usrid = 47";
		SysUsers usr  = (SysUsers)session.createQuery(hql).uniqueResult();
		
		String hql2 = "from SysUsers as s where s.usrid = 22";
		SysUsers usr2  = (SysUsers)session.createQuery(hql2).uniqueResult();
		
		String hql3 = "from SysUsers as s where s.usrid = 23";
		SysUsers usr3  = (SysUsers)session.createQuery(hql3).uniqueResult();
		
		String hql5 = "from SysUsers as s where s.usrid = 55";
		SysUsers usr5  = (SysUsers)session.createQuery(hql5).uniqueResult();
		
		usr.setUsrname("发火哥");
		
		usr2.setUsrname("发火2");
		usr3.setUsrname("发火3");
		
		usr5.setUsrname("发火5");
		usr5.setSysUsers(usr);
		usr.getSysUserses().add(usr5);
		
		session.save(usr);
		tx.commit();
		session.close();
	}
	
	public void updtOneToManyByMany(){
		
		Configuration configuration = new AnnotationConfiguration();
		Session session = configuration.configure().buildSessionFactory()
				.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "from SysUsers as s where s.usrid = 47";
		SysUsers usr  = (SysUsers)session.createQuery(hql).uniqueResult();
		
		String hql2 = "from SysUsers as s where s.usrid = 22";
		SysUsers usr2  = (SysUsers)session.createQuery(hql2).uniqueResult();
		
		String hql3 = "from SysUsers as s where s.usrid = 23";
		SysUsers usr3  = (SysUsers)session.createQuery(hql3).uniqueResult();
		
		String hql5 = "from SysUsers as s where s.usrid = 55";
		SysUsers usr5  = (SysUsers)session.createQuery(hql5).uniqueResult();
		
		usr.setUsrname("发火哥1");
		
		usr2.setUsrname("发火21");
		usr3.setUsrname("发火31");
		
		usr5.setUsrname("发火51");
		usr5.setSysUsers(usr);
		usr.getSysUserses().add(usr5);
		
		session.update(usr2);
		session.update(usr3);
		session.update(usr5);
		tx.commit();
		session.close();
	}
	
	public void delOne(){
		
		Configuration configuration = new AnnotationConfiguration();
		Session session = configuration.configure().buildSessionFactory()
				.openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		
		/*String hql = "from SysUsers as s where s.usrid = 46";
		SysUsers usr  = (SysUsers)session.createQuery(hql).uniqueResult();
		session.delete(usr);*/
		
		String delHql ="delete from SysUsers as s where s.sysUsers.usrid = 49";
		String hql ="delete from SysUsers as s where s.usrid = 49";
		session.createQuery(delHql).executeUpdate();
		session.createQuery(hql).executeUpdate();/**/
		tx.commit();
		session.close();
	}
	
	public void delMany(){
		
		Configuration configuration = new AnnotationConfiguration();
		Session session = configuration.configure().buildSessionFactory()
				.openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		
		String hql = "from SysUsers as s where s.usrid = 48";
		SysUsers usr  = (SysUsers)session.createQuery(hql).uniqueResult();
		session.delete(usr);/**/
		
		/*String hql ="delete from SysUsers as s where s.usrid = 21";
		session.createQuery(hql).executeUpdate();*/
		tx.commit();
		session.close();
	}
	
	
	public static void main(String[] args) {

		OneToManyLinkedMyself one = new OneToManyLinkedMyself();
		//one.saveOneToMany();
		// one.saveManyToOne();//保存失败 为什么？
		//one.saveOneupdtMany();
		//one.saveManyupdtOne();
		//one.findOneToManyByLazy();
		// one.findOneToManyByEager();
		//one.updtOneToManyByOne();
		//one.updtOneToManyByMany();
		one.delOne();
		//one.delMany();
	}

}
