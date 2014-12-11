package com.dharmab.sheets.server.database;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.*;
import java.io.IOException;

@Singleton
public class DatabaseSessionFilter implements Filter {
    private SessionFactory sessionFactory;

    @Inject
    public DatabaseSessionFilter(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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
