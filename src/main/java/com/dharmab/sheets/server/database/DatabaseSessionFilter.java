package com.dharmab.sheets.server.database;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Logger;

@Singleton
public class DatabaseSessionFilter implements Filter {
    private SessionFactory sessionFactory;
    private Logger logger;

    @Inject
    public DatabaseSessionFilter(SessionFactory sessionFactory, Logger logger) {
        this.sessionFactory = sessionFactory;
        this.logger = logger;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.getTransaction().begin();
            chain.doFilter(request, response);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public void destroy() {

    }
}
