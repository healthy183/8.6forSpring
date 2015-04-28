package com.kang.base.service.ext;

import java.util.Collection;
import java.util.List;



import com.kang.base.dao.IbaseDao;
import com.kang.base.service.IbaseService;
import com.kang.tool.Page;

public abstract class 
	BaseHibernateService<M extends java.io.Serializable, PK extends java.io.Serializable> 
		implements IbaseService<M, PK> {

	 protected IbaseDao<M, PK> baseDao;
	 public abstract void setBaseDao(IbaseDao<M, PK> baseDao);

	 public void saveAll(List<?> list){
		 baseDao.saveAll(list);
	 }
	 
	 
	@Override
	public void save(Object model) {
		baseDao.save(model);
	}

	@Override
	public void saveOrUpdate(Object model) {
		baseDao.saveOrUpdate(model);		
	}

	@Override
	public void update(Object model) {
		baseDao.update(model);		
	}

	@Override
	public M merge(M model) {
		return baseDao.merge(model);
	}

	@Override
	public void delete(PK id) {
		baseDao.deleteById(id);		
	}

	@Override
	public void deleteObject(Object model) {
		baseDao.delete(model);
	}

	@Override
	public M get(PK id) {
		return baseDao.get(id);
	}

	@Override
	public M load(PK id){
		return baseDao.load(id);
	}
	
	@Override
	public int countAll() {
		return baseDao.countAll();
	}

	@Override
	public List<M> findAll() {
		return baseDao.findAll();
	}

	@Override
	public List<M> findAll(int pn, int pageSize) {
		return baseDao.findAll(pn, pageSize);
	}
	
	@Override
	public Page findAllForPage(int pn) {
		return baseDao.findAllForPage(pn);
	}
	
	@Override
	public Page findAllForPage(int pn, int pageSize) {
		return baseDao.findAllForPage(pn,pageSize);
	}
	

	
	
	
	
	@Override
	public Page pre(PK pk, int pn, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page next(PK pk, int pn, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page pre(PK pk, int pn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page next(PK pk, int pn) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
