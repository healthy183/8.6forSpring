package com.kang.book;

import java.util.List;
import java.util.Set;

import javax.persistence.FetchType;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.kang.model.SysRoles;
import com.kang.model.TReim;
import com.kang.model.TReimapp;
import com.kang.model.TReimitm;
import com.kang.model.UsrRoleLink;

public class BookOneToMany {

	public void outerJoinOneToMany(){
		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		Transaction tx	= session.beginTransaction();
		tx.begin();/**/
		
		//已有一方，新增多方,
		//TReim treim = (TReim)session.get(TReim.class, 35);
		TReim treim = (TReim)session.load(TReim.class, 35);//在没有必要获取一方的属性的时候，建议用load延迟加载
		/*String hql ="from TReim as t where t.rmid = 35";
		TReim treim =(TReim)session.createQuery(hql).uniqueResult();*/
		TReimitm t = new TReimitm();
		t.setRmitmname("123ffaa");
		t.setTReim(treim);
		//没必要加,set延迟加载的时候treim.getTReimitms()会触发一条新select sql
		//treim.getTReimitms().add(t);
		//session.save(treim);
		session.save(t);
		//System.out.println(treim.getRmname());
		//System.out.println(treim.getSysUsers().getUsrname());
		tx.commit();
		session.close();
	}
	
	
	public void newOneToMany(){
		Configuration configuration = new AnnotationConfiguration();
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		Transaction tx	= session.beginTransaction();
		tx.begin();/**/
		
		TReim treim = new TReim();
		treim.setRmname("最新");
		
		TReimitm treimitm = new TReimitm();
		treimitm.setRmitmname("最新1");
		
		TReimitm treimitm2 = new TReimitm();
		treimitm2.setRmitmname("最新2");
		
		
		TReimapp tReimapp = new TReimapp();
		tReimapp.setApptext("最新app");
		
		TReimapp tReimapp2 = new TReimapp();
		tReimapp2.setApptext("最新app2");
		
		treimitm.setTReim(treim);
		treimitm2.setTReim(treim);
		
		tReimapp.setTReim(treim);
		tReimapp2.setTReim(treim);
		
		//如果同时新增一对多,建议同时保存一和多对象，并且先保存一，然后是多,否则会触发update函数
		treim.getTReimitms().add(treimitm);
		treim.getTReimitms().add(treimitm2);
		
		treim.getTReimapps().add(tReimapp);
		treim.getTReimapps().add(tReimapp2);
		
		session.save(treim);
		for(TReimitm t : treim.getTReimitms()){
			session.save(t);
		}
		for(TReimapp t : treim.getTReimapps()){
			session.save(t);
		}
		//session.save(treimitm);
		//session.save(treimitm2);
		tx.commit();
		session.close();
	}
	
	public void delOne(){
		Configuration configuration = new AnnotationConfiguration();
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		Transaction tx	= session.beginTransaction();
		tx.begin();/**/
		//删除一对多的时候，还是不要太依靠级联删除，因为有多少个多，就执行多少条sql,效率低
		String hql ="delete from TReimitm as t where t.TReim.rmid = 1";
		session.createQuery(hql).executeUpdate();
		TReim t = (TReim)session.load(TReim.class, 1);
		//t.getRmname();t是load出来的延迟对象，每次调用方法都会执行一次select，要慎用
		//t.getRmname();
		session.delete(t);
		tx.commit();
		session.close();
	}
	
	public void oneToManyFetchJPA(){
		Configuration configuration = new AnnotationConfiguration();
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		
		//load出对象
		TReim  t = (TReim)session.load(TReim.class, 2);
		//当JPA的fetch = FetchType.LAZY 时候当只有需要的时候才执行select
		//当JPA的fetch = FetchType.EAGER就使用left outer join
		Set<TReimitm> tSet = t.getTReimitms();
		for(TReimitm treimitm : tSet){
			System.out.println(treimitm.getRmitmname());
		}
		//第二次需要的时候，不会重新select
		Set<TReimitm> tSet2 = t.getTReimitms();
		for(TReimitm treimitm : tSet2){
			System.out.println(treimitm.getRmitmname());
		}
		session.close();
	}
	
	public void oneToManyFetchAnonation(){
		Configuration configuration = new AnnotationConfiguration();
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		
		//SysRoles  r = (SysRoles)session.get(SysRoles.class,2);
		/* lazy = true
		 * 使用get的时候
		 *  set的Fetch = select 不需要的时候不执行select
		 *  set的Fetch = join 总是执行left outer join函数
		 */
		//SysRoles  r = (SysRoles)session.load(SysRoles.class,2);
		/** lazy = true
		 * 使用load的时候，需要的时候才执行sql
		 * set的Fetch = select 执行select
		 * set的Fetch = join 执行left outer join
		 */
		String hql ="from SysRoles";
		//String hql ="from SysRoles as s where s.roleId = 2";
		List<SysRoles> roleList = session.createQuery(hql).list();
		//SysRoles  r = roleList.get(0);
		/**使用hql查询默认使用延迟加载,需要的时候才执行sql
		 * set的Fetch = select 执行select
		 * set的Fetch = join 执行select
		 * set的Fetch = SUBSELECT 执行子查询 所以in()查询UsrRoleLink
		 * **/
		for(SysRoles s : roleList){
			Set<UsrRoleLink> urLinkSet = s.getUsrRoleLinks();
				for(UsrRoleLink u : urLinkSet){
					u.getLinkId();
			 }/**/
		}
		
		session.close();
	}
	
	
	public void manyToOneFetchAnonation(){
		
		Configuration configuration = new AnnotationConfiguration();
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		
		UsrRoleLink urLink =(UsrRoleLink)session.get(UsrRoleLink.class,7);
		/**LazyToOne(value = LazyToOneOption.PROXY)
		 * Fetch = SELECT 需要的时候就查询 SELECT
		 * Fetch = JOIN  总是查询 left outer join
		 * Fetch = subString 没有这种情况
		 * */
		
		/**LazyToOne(value = LazyToOneOption.False)
		 * Fetch = SELECT 总是查询 SELECT
		 * Fetch = JOIN  总是查询 left outer join
		 * Fetch = subString 没有这种情况
		 * */
		
		/**LazyToOne(value = LazyToOneOption.NO_PROXY)
		 * Fetch = SELECT 需要的时候就查询 SELECT
		 * Fetch = JOIN  总是查询 left outer join
		 * Fetch = subString 没有这种情况
		 * */
		
		//urLink.getSysUsers().getSysUsers();
		
		session.close();
		
	}
	
	public void manyToOneFetchJPA(){
		
		Configuration configuration = new AnnotationConfiguration();
		configuration.configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();
		//TReimapp  t = (TReimapp)session.load(TReimapp.class, 1);
		TReimapp  t = (TReimapp)session.get(TReimapp.class, 1);
		//fetch = FetchType.LAZY要的时候才select
		//t.getTReim().getRmname();
		//fetch = FetchType.EAGER不管要不要都马上select
		session.close();
	}
	
	
	
	public static void main(String[] args) {
		
		BookOneToMany book = new BookOneToMany();
		//book.outerJoinOneToMany();
		//book.newOneToMany();
		//book.delOne();
		//book.oneToManyFetchJPA();
		//book.manyToOneFetchJPA();
		//book.oneToManyFetchAnonation();
		book.manyToOneFetchAnonation();
	}
}
