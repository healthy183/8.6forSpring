package com.kang.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.kang.model.SysRoles;
import com.kang.model.SysUsers;
import com.kang.model.UsrRoleLink;

//这种是多对多拆成两个一对多
public class ManyToMany {

	//如果按默认的配置one都要save
	public void saveManyToMany(){
		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		
		Session session = configuration.buildSessionFactory().openSession();
		
		Transaction tx	= session.beginTransaction();
		tx.begin();
		
		UsrRoleLink usrRoleLink = new UsrRoleLink(); 
		
		SysUsers user = new SysUsers();
		user.setUsrname("多对多user");
		user.getUsrRoleLinks().add(usrRoleLink);
		usrRoleLink.setSysUsers(user);
		
		SysRoles role = new SysRoles();
		role.setRoleName("role");
		role.getUsrRoleLinks().add(usrRoleLink);
		usrRoleLink.setSysRoles(role);
		
		session.save(user);
		session.save(role);
		tx.commit();
		session.close();
		
	}
	
	
	public void saveByOnlyOne(){
		
		Configuration configuration = new AnnotationConfiguration();
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		Transaction tx	= session.beginTransaction();
		tx.begin();
		
		UsrRoleLink usrRoleLink = new UsrRoleLink(); 
		
		SysUsers user = new SysUsers();
		user.setUsrname("U1");
		user.getUsrRoleLinks().add(usrRoleLink);
		usrRoleLink.setSysUsers(user);
		
		SysRoles role =(SysRoles)session.get(SysRoles.class, 1);
		role.getUsrRoleLinks().add(usrRoleLink);
		usrRoleLink.setSysRoles(role);
		
		session.save(user);
		//session.save(role);
		//session.save(usrRoleLink);
		tx.commit();
		session.close();
	}
	
	public void saveByMany(){
		
		Configuration configuration = new AnnotationConfiguration();
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		Transaction tx	= session.beginTransaction();
		tx.begin();
		
		
		UsrRoleLink usrRoleLink = new UsrRoleLink(); 
		
		SysUsers user = (SysUsers)session.get(SysUsers.class,1);
		user.getUsrRoleLinks().add(usrRoleLink);
		usrRoleLink.setSysUsers(user);
		
		SysRoles role =(SysRoles)session.get(SysRoles.class, 1);
		role.getUsrRoleLinks().add(usrRoleLink);
		usrRoleLink.setSysRoles(role);
		
		session.save(usrRoleLink);
		
		tx.commit();
		session.close();
	}
	
	public void delManyToMany(){
		
		Configuration configuration = new AnnotationConfiguration();
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		Transaction tx	= session.beginTransaction();
		tx.begin();
		
		session.delete(session.get(UsrRoleLink.class,4));
		
		tx.commit();
		session.close();
	}
	
	public void delOne(){
		Configuration configuration = new AnnotationConfiguration();
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		Transaction tx	= session.beginTransaction();
		tx.begin();
		
		SysUsers user = (SysUsers)session.get(SysUsers.class,71);
		session.delete(user);
		
		tx.commit();
		session.close();
	}
	
	public void updtOne(){
		
		Configuration configuration = new AnnotationConfiguration();
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		Transaction tx	= session.beginTransaction();
		tx.begin();
		
		SysUsers user = (SysUsers)session.get(SysUsers.class,1);
		user.setUsrname("updtName");
		
		SysRoles role = (SysRoles)session.get(SysRoles.class,2);
		
		String hql ="from UsrRoleLink as u where u.sysUsers.usrid = 1";
		List<UsrRoleLink> linkList = session.createQuery(hql).list();
		
		for(UsrRoleLink u : linkList){
			u.setSysRoles(role);
			role.getUsrRoleLinks().add(u);
		}
		
		session.update(user);
		tx.commit();
		session.close();
	}
	
	
	
	public static void main(String[] args) {
		ManyToMany m =new ManyToMany();
		//m.saveManyToMany();
		//m.saveByOnlyOne();
		//m.saveByMany();
		//m.delManyToMany();
		m.updtOne();
	}
	
}
