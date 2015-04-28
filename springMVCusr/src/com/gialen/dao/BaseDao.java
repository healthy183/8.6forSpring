package com.gialen.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.util.Assert;

import com.gialen.tools.Page;

/**
 * DAO���࣬����DAO����ֱ�Ӽ̳����DAO���������Ը��ù��õķ����������Ի�÷��͵ĺô���
 */
public class BaseDao<T>{
	private Class<T> entityClass;
	@Autowired
	private HibernateTemplate hibernateTemplate;
	/**
	 * ͨ�������ȡ����ȷ���ķ�����
	 */
	public BaseDao() { 
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class) params[0];
	}

	/**
	 * ����ID����POʵ��
	 * 
	 * @param id
	 * @return ������Ӧ�ĳ־û�POʵ��
	 */
	public T load(Serializable id) {
		return (T) getHibernateTemplate().load(entityClass, id);
	}

	/**
	 * ����ID��ȡPOʵ��
	 * 
	 * @param id
	 * @return ������Ӧ�ĳ־û�POʵ��
	 */
	public T get(Serializable id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * �������Ի�ȡPOʵ��
	 * 
	 * @param id
	 * @return ������Ӧ�ĳ־û�POʵ��(����)
	 */
	public List findByKey(T entity,String KeyName,Object KeyValue){
		String hql ="from "+entityClass.getName()+" where "+KeyName+" = "+KeyValue;
		return getHibernateTemplate().find(hql);
	}
	
	/**
	 * ��ȡPO�����ж���
	 * 
	 * @return
	 */
	public List<T> loadAll() {
		return getHibernateTemplate().loadAll(entityClass);
	}
	
	/**
	 * ����PO
	 * 
	 * @param entity
	 */
	public void save(T entity) {
		getHibernateTemplate().save(entity);
		
	}

	/**
	 * ɾ��PO
	 * 
	 * @param entity
	 */
	public void remove(T entity) {
		/*Session session = getSession();
		session.delete(entity);
		session.flush();*/
		getHibernateTemplate().delete(entity);
		getHibernateTemplate().flush();
		
	}

	/**
	 * ɾ�����������ж���
	 * 
	 * @param List
	 */
	public void deleteAll(Collection<T> c){
		getHibernateTemplate().deleteAll(c);
		//getHibernateTemplate().flush();
	}
	
	/**
	 * ����update PO
	 * 
	 * @param entity
	 */
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}
	
	/**
	 * ����merge PO
	 * 
	 * @param entity
	 */
	public T merge(T entity) {
		return getHibernateTemplate().merge(entity);
	}
	
	/**
	 * ����saveORupdate PO
	 * 
	 * @param entity
	 */
	public void attachDirty(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}
	/**
	 * ����saveORupdate collection
	 * 
	 * @param entity
	 */
	public void attachDirty(Collection<T> c) {
		getHibernateTemplate().saveOrUpdateAll(c);
	}
	
	/**
	 * ִ��HQL��ѯ
	 * 
	 * @param sql
	 * @return ��ѯ���
	 */
	public List find(String hql) {
		return this.getHibernateTemplate().find(hql);
	}

	/**
	 * ִ�д��ε�HQL��ѯ
	 * 
	 * @param sql
	 * @param params
	 * @return ��ѯ���
	 */
	public List find(String hql, Object... params) {
		return this.getHibernateTemplate().find(hql,params);
	}
    
	/**
	 * ���ӳټ��ص�ʵ��POִ�г�ʼ�� �йܶ��� ��ɳ־û�����
	 * @param entity
	 */
	public void initialize(T entity) {
		this.getHibernateTemplate().initialize(entity);
	}
	
	/**
	 * ���ӳټ��ص�ʵ��POִ�� ��ʼ���־û����� ��� �йܶ���
	 * @param entity
	 */
	public void attachClean(T entity) {
		this.getHibernateTemplate().lock(entity, LockMode.NONE);
	}
	
	/**
	 * ��ҳ��ѯ������ʹ��hql.
	 *
	 * @param pageNo ҳ��,��1��ʼ.
	 */
	public Page pagedQuery(String hql, int pageNo, int pageSize, Object... values) {
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count��ѯ
		String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
		List countlist = getHibernateTemplate().find(countQueryString, values);
		long totalCount = (Long) countlist.get(0);

		if (totalCount < 1)
			return new Page();
		// ʵ�ʲ�ѯ���ط�ҳ����
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		Query query = createQuery(hql, values);
		List list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();

		return new Page(startIndex, totalCount, pageSize, list);
	}

	/**
	 * ����Query����. ������Ҫfirst,max,fetchsize,cache,cacheRegion��������õĺ���,�����ڷ���Query����������.
	 * ���������������,���£�
	 * <pre>
	 * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list();
	 * </pre>
	 * ���÷�ʽ���£�
	 * <pre>
	 *        dao.createQuery(hql)
	 *        dao.createQuery(hql,arg0);
	 *        dao.createQuery(hql,arg0,arg1);
	 *        dao.createQuery(hql,new Object[arg0,arg1,arg2])
	 * </pre>
	 *
	 * @param values �ɱ����.
	 */
	public Query createQuery(String hql, Object... values) {
		Assert.hasText(hql);
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query;
	}
	/**
	 * ȥ��hql��select �Ӿ䣬δ����union�����,����pagedQuery.
	 *
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
		return hql.substring(beginPos);
	}
	
	/**
	 * ȥ��hql��orderby �Ӿ䣬����pagedQuery.
	 *
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
    public  Session getSession() {
    	//Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
    	return SessionFactoryUtils.getSession(hibernateTemplate.getSessionFactory(),true);
    }
    
    public void flush(){
    	getSession().flush();
    }
	
}