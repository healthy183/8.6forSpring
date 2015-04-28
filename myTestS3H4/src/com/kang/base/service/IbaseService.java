package com.kang.base.service;
import java.util.List;

import com.kang.tool.Page;

public interface IbaseService<M extends java.io.Serializable, PK extends java.io.Serializable> {
    
    public void save(Object model);

    public void saveOrUpdate(Object model);
    
    public void update(Object model);
    
    public M merge(M model);

    public void delete(PK id);

    public void deleteObject(Object model);

    public M get(PK id);
    
    public M load(PK id);
    
    public int countAll();
    
    public List<M> findAll();
    
    public List<M> findAll(int pn, int pageSize);
    
    public Page findAllForPage(int pn);
    
    public Page findAllForPage(int pn, int pageSize);
    

    public Page pre(PK pk, int pn, int pageSize);
    
    public Page next(PK pk, int pn, int pageSize);
    
    public Page pre(PK pk, int pn);
    
    public Page next(PK pk, int pn);
}
