package com.dharmab.sheets.server;

import com.dharmab.sheets.server.database.DatabaseModule;
import com.dharmab.sheets.server.database.DatabaseSessionFilter;
import com.dharmab.sheets.server.requestfactory.InjectableRequestFactoryModule;
import com.dharmab.sheets.server.requestfactory.InjectableRequestFactoryServlet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.sql.DataSource;

public class AppContextListener extends GuiceServletContextListener {
    private final Injector injector;

    public AppContextListener() {
        injector = createInjector();
    }

    private Injector createInjector() {
        Injector injector;
        try {
            final DataSource dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/postgresql");
            injector = Guice.createInjector(
                    new DatabaseModule(dataSource),
                    new ServletModule() {
                        @Override
                        protected void configureServlets() {
                            // Hibernate thread-scoped sessions
                            filter("/*").through(DatabaseSessionFilter.class);
                            // RequestFactory servlet mapping
                            install(new InjectableRequestFactoryModule());
                            serve("/gwtRequest").with(InjectableRequestFactoryServlet.class);
                            // RPC servlet mappings go here
                            // example:
                            //serve("/sheets/myurl").with(MyServiceImpl.class);
                        }
                    });
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return injector;
    }

    @Override
    protected Injector getInjector() {
        return injector;
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        super.contextInitialized(servletContextEvent);

        // Run database migrations
        injector.getInstance(Flyway.class).migrate();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // Close database connections
        injector.getInstance(SessionFactory.class).close();

        super.contextDestroyed(servletContextEvent);
    }
}
