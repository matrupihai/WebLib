package com.weblib.hbm.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;


public class SessionRequestFilter implements Filter {
	
	private SessionFactory sessionFactory;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
        try {
        	sessionFactory.getCurrentSession().beginTransaction(); 
			filterChain.doFilter(request, response);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (StaleObjectStateException staleEx) {
			throw staleEx;
		} catch (Throwable ex) {
			ex.printStackTrace();  
            try {  
                if (sessionFactory.getCurrentSession().getTransaction().isActive()) {  
                	sessionFactory.getCurrentSession().getTransaction().rollback();  
                }  
            } catch (Throwable exc) {  
            	exc.printStackTrace();
            }  
  
            throw new ServletException(ex);  
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
