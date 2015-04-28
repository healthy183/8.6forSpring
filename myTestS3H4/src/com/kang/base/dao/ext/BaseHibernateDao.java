package com.kang.base.dao.ext;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Id;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.kang.base.dao.IbaseDao;
import com.kang.model.SysRoles;
import com.kang.tool.Page;

public abstract class 
	BaseHibernateDao<M extends java.io.Serializable, PK extends java.io.Serializable> 
			implements IbaseDao<M, PK> {
	
	 private final Class<M> entityClass;
	//M的主键属性
	//private String pkName = null;
	String findALLHql = "";
	
	public BaseHibernateDao() {
		//通过发射获得当前调用本通用hibernateDao的javaBean实体类
        this.entityClass = (Class<M>) ((ParameterizedType) getClass()
        		.getGenericSuperclass()).getActualTypeArguments()[0];
        //获得该javaBean所有全局变量(属性)?
       /* Field[] fields = this.entityClass.getDeclaredFields();
        for(Field f : fields) {
        	//获取标记了@Id的全局变量(即数据库主键)
            if(f.isAnnotationPresent(Id.class)) {
            	//付给本类的全局变量
                this.pkName = f.getName();
            }
        }*/
        //+" order by "+pkName
        findALLHql = "from "+entityClass.getSimpleName();
        
	}
	
	 @Autowired //从applicationContext.xml中注入Bean的id为sessionFactory对象
	 @Qualifier("sessionFactory")
	 private SessionFactory sessionFactory;

	 public Session getSession() {
	        //事务必须是开启的(Required)，否则获取不到 配置声明式事务需谨慎 
	        return sessionFactory.getCurrentSession();
	    }

	@Override
	public void save(Object model) {
		getSession().save(model);
	}

	@Override
	public void saveOrUpdate(Object model) {
		getSession().saveOrUpdate(model);
	}

	@Override
	public void update(Object model) {
		getSession().update(model);
	}

	@Override
	public M merge(M model) {
		return (M)getSession().merge(model);
	}

	@Override
	public void deleteById(PK id) {
		getSession().delete(this.load(id));
	}

	@Override
	public void delete(Object model) {
		getSession().delete(model);
	}

	@Override
    public M get(PK id) {
        return (M)getSession().get(this.entityClass, id);
    }

	@Override
	public M load(PK id) {
		return (M)getSession().load(this.entityClass, id);
	}
	
	@Override  //将实体PO从缓冲中清除？
	public void initialize(Object entity) {
		getSession().lock(entity,LockMode.NONE);
		
	}
	
	//mysql唔支持该属性
	@Override //批量保存
	public void saveAll(List<?> list) {
		Session session = getSession();
		for(int i = 0;i<list.size();i++){
			 session.save(list.get(i));
			  if(i % 50 == 0){
				  session.flush();
				  session.clear();
			  }
		}
	}

	@Override
    public int countAll() {
    	//从hibernate3.2开始执行count函数的hql返回的都必须是long
        //return total.intValue();//转换,但跟int a =(int)total有什么区别?;
	long counts  = (Long)getSession().createQuery("select count(*) from "+entityClass.getSimpleName()).uniqueResult();
    return (int)counts;
	}

	@Override //查询M所有对象
	public List<M> findAll() {
		return getSession().createQuery(findALLHql).list();
	}
	
	
	@Override
	public Page findAllForPage(int pn) {
		int pageSize = countAll();
		List<M> list =	getSession().createQuery(findALLHql).setFirstResult(pn).setMaxResults(pageSize).list();
		return new Page(pn, pageSize, pageSize, list);
	}

	@Override
	public Page findAllForPage(int pn, int pageSize) {
		int totalCount = countAll();
		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pn, pageSize);
		List<M> list =	getSession().createQuery(findALLHql).setFirstResult(startIndex).setMaxResults(pageSize).list();
		return new Page(startIndex, totalCount, pageSize, list);
	}
	
	//使用hql.分页查询函数
	public Page pageQuery(String hql, int pageNo, int pageSize, Object... paramlist) {
		//Assert.hasText(hql);
		//Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
		Query query = null;
		
		query = createQuery(countQueryString, paramlist);
		List countlist = query.list();
		long totalCount = (Long) countlist.get(0);//这样子也行啊？

		if (totalCount < 1)//没有对应记录
			return new Page();
		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		query = createQuery(hql, paramlist);
		List list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();

		return new Page(startIndex, totalCount, pageSize, list);
	}
	
	//使用带in()函数的hql.分页查询函数
	public Page pageQueryWithIn(String hql, int pageNo, int pageSize,
		final Map<String, Collection<?>> map, final Object... paramlist){
		
		String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
		Query query = null;
		
		query = createQuery(countQueryString, paramlist);
		setParameterList(query,map);
		List countlist = query.list();
		
		long totalCount = (Long) countlist.get(0);//这样子也行啊？

		if (totalCount < 1)//没有对应记录
			return new Page();
		
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		query = createQuery(hql, paramlist);
		setParameterList(query,map);
		
		List list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
		return new Page(startIndex, totalCount, pageSize, list);
	}
	
	//创建标准hqlQuery
	public List queryHql(String hql, Object... paramlist){
		return createQuery(hql,paramlist).list();
	}
	
	//创建带集合Collection 的hqlQuery
	public List queryHqlWithIn(final String hql,
		final Map<String, Collection<?>> map, Object... paramlist){
		Query query = createQuery(hql,paramlist);
		setParameterList(query,map);
		return query.list();
	}
	
	//设置带in函数的hql语句ParameterList
	public  void setParameterList(Query query,Map<String, Collection<?>> map){
		 for (Entry<String, Collection<?>> e : map.entrySet()) {
	            query.setParameterList(e.getKey(), e.getValue());
	        }
	}
	
	//创建hql的query对象
	public Query createQuery(String hql, Object[] paramlist) {
		//Assert.hasText(hql);
		Query query = getSession().createQuery(hql);
		setParameters(query,paramlist);
		return query;
	}
	/**
	 * 去除hql的select 子句，未考虑union的情况,用于pagedQuery.
	 *
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeSelect(String hql) {
		//Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		//Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
		return hql.substring(beginPos);
	}
	
	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 *
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeOrders(String hql) {
		//Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	
	 /**使用hql执行批处理语句.如 之间insert, update, delete 等. 返回影响行数  */
    protected int execteBulk(final String hql, final Object... paramlist) {
    	Query query = createQuery(hql, paramlist);
        Object result = query.executeUpdate();
        return result == null ? 0 : ((Integer) result).intValue();
    }
    
    /**使用标准sql执行批处理语句.如 之间insert, update, delete 等. 返回影响行数  */
    protected int execteNativeBulk(final String natvieSQL, final Object... paramlist) {
        Query query =  createSqlQuery(natvieSQL, paramlist);
        Object result = query.executeUpdate();
        return result == null ? 0 : ((Integer) result).intValue();
    }
	
    //执行标准sql查询
    public List querySql(String natvieSQL,  Object... paramlist){
    	return createSqlQuery(natvieSQL,paramlist).list();
    }
    
    
	//创建标准sqlQuery
	private Query createSqlQuery(String natvieSQL, Object[] paramlist) {
		 Query query = getSession().createSQLQuery(natvieSQL);
		 setParameters(query,paramlist);
		 return query;
	}

	//为query对象设置Parameters
	protected void setParameters(Query query, Object[] paramlist) {
        if (paramlist != null) {
            for (int i = 0; i < paramlist.length; i++) {
                if(paramlist[i] instanceof Date) {
                    //TODO 难道这是bug 使用setParameter不行？？
                    query.setTimestamp(i, (Date)paramlist[i]);
                } else {
                    query.setParameter(i, paramlist[i]);
                }
            }
        }
    }
	

	@Override
    public void flush() {
        getSession().flush();
    }

    @Override
    public void clear() {
        getSession().clear();
    }
    
    @Override
    public boolean exists(PK id) {
        return get(id) != null;
    }
    
    
	@Override //从第几页开始查询
	public List<M> findAll(int pn, int pageSize) {
		if(pn < 1){
			pn = 1;
		}
		return getSession().createQuery(findALLHql)
			.setFirstResult((pn-1)*pageSize).setMaxResults(pageSize).list();
	}

	
	public List findByProperty(String propertyName,Object object){
		Session session = getSession();
		String hql ="from "+entityClass.getSimpleName() +" as m where m."+propertyName+" = ?";
		return queryHql(hql,object);
	}
	
	
	
	
	@Override
	public List<M> pre(PK pk, int pn, int pageSize) {
		return null;
	}

	@Override
	public List<M> next(PK pk, int pn, int pageSize) {
		return null;
	}

	/*public List<SysRoles> queryHqlWithIn(String hql,
			Map<String, List<String>> roleIdMap) {
		return null;
	}*/

	

	
	
}
