package com.kang.base.dao;

import java.util.Collection;
import java.util.List;

import com.kang.tool.Page;
//通用dao
public interface IbaseDao<M extends java.io.Serializable, PK extends java.io.Serializable> {
    
    public void save(Object model);

    public void saveOrUpdate(Object model);
    
    public void update(Object model);
    
    //public void merge(M model);
    public M merge(M model);
    
    //根据id删除对象
    public void deleteById(PK id);

    public void delete(Object model);

    public M get(PK id);
    
    public M load(PK id);
    
    public int countAll();

  //将实体PO从缓冲中清除？
    public void initialize(Object entity);
    
    public List<M> findAll();
    
    public Page findAllForPage(int pn);
    
    public Page findAllForPage(int pn, int pageSize);
    	
    public List<M> findAll(int pn, int pageSize);
    
    //下一页
    public List<M> pre(PK pk, int pn, int pageSize);
    //上一页
    public List<M> next(PK pk, int pn, int pageSize);
    
    //根据主键判断对象是否存在
    boolean exists(PK id);
    
    public void flush();
    
    public void clear();

	public void saveAll(List<?> list);

	

	



}
