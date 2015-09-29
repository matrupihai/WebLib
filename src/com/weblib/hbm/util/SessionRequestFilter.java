package com.weblib.hbm.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;


public class SessionRequestFilter implements Filter {
	
	private SessionFactory sessionFactory;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
        
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
        	session.beginTransaction(); 
			filterChain.doFilter(request, response);
			transaction.commit();
		} catch (StaleObjectStateException staleEx) {
			throw staleEx;
		} catch (Exception ex) {
            try { 
            	if (transaction != null) {
            		transaction.rollback();  
            	}
            } catch (Throwable exc) {  
            	exc.printStackTrace();
            }  
  
            throw new ServletException(ex);  
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	@Override
	public void destroy() {
		
	}
	
}
