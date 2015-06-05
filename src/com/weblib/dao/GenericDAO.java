package com.weblib.dao;

import java.util.List;

public interface GenericDAO<T, ID> {
	
	public void insert(T entity);
//	public void insert(T... entity);
	
	public List<T> findAll();
	public T findById(ID id);
//	public List<T> findByIds(ID... ids);
//	
//	public void update(T entity);
//	public void update(T... entities);
//	
//	public void delete(T entity);
//	public void delete(T... entity);
//	public void deleteById(ID id);
//	public void deleteByIds(ID... id);
//	
//	public void flush();

}
