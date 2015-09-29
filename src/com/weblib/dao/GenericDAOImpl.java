package com.weblib.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.weblib.hbm.util.HibernateUtil;

public class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Class<T> objectType;
	
	public GenericDAOImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		objectType = (Class) pt.getActualTypeArguments()[0];
	}
	
	public void insert(T entity) {
		Transaction transaction = null;
		try {
			Session session = getSession();
			transaction = session.beginTransaction();
			session.save(entity);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	@Override
	public List<T> findAll() {
		List<T> list = new ArrayList<T>();
		try {
			Session session = getSession();
			Query query = session.createQuery("from " + objectType.getSimpleName());
			list.addAll(query.list());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return list;
	}
	
	public Set<T> genericFind(String queryString) {
		Set<T> resultSet = new HashSet<T>();
		try {
			Session session = getSession();
			if (session != null) {
				Query query = session.createQuery(queryString);
				resultSet.addAll(query.list());
			}
		
		} catch (Exception e) {
			// throw new runtime exception and map it to a response
		} 
		
		return resultSet;
	}
	
	public T findByString(String varFieldName, String value) {
		return findByString(varFieldName, value, true);
	}
	
	public T findByString(String varFieldName, String value, boolean exactMatch) {
		T result = null;
		Transaction transaction = null;
		Session session = null;
		try {
			session = getSession();
			if (session != null) {
				transaction = session.beginTransaction();
				Criteria crit = session.createCriteria(objectType);
				if (!exactMatch) {
					value = "%" + value + "%";
				}
				crit.add(Restrictions.like(varFieldName, value));
				result  = (T) crit.uniqueResult();
				session.getTransaction().commit();;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
        
        return result;
	}
	
	public T findById(ID id) {
		checkId(id);
		Session session = getSession();
		Criteria crit = session.createCriteria(objectType);
        crit.add(Restrictions.idEq(id));
        T result = (T) crit.uniqueResult(); 
        
        return result;
	}
	
	public void checkId(ID id) {
		if (id == null) {
			throw new IllegalArgumentException("The id cannot be null)");
		}
	}
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Class<T> getObjectType() {
		return objectType;
	}
	
}
