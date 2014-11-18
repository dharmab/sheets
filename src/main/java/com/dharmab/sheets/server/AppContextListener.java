package com.dharmab.sheets.server;

import com.dharmab.sheets.server.character.CharacterModule;
import com.dharmab.sheets.server.database.Database;
import com.dharmab.sheets.server.database.DatabaseModule;
import com.dharmab.sheets.server.requestfactory.InjectableRequestFactoryModule;
import com.dharmab.sheets.server.requestfactory.InjectableRequestFactoryServlet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.sql.DataSource;

public class AppContextListener extends GuiceServletContextListener {
    private Injector injector;
    private DataSource dataSource;
    private Database database;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // Workaround for https://github.com/google/guice/issues/603
        try {
            InitialContext initialContext = new InitialContext();
            Context envContext = (Context) initialContext.lookup("java:comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/postgresql");
        } catch (NamingException ignored) {
        }

        super.contextInitialized(servletContextEvent);

        database = injector.getInstance(Database.class);
        database.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        super.contextDestroyed(servletContextEvent);
        database.stop();
    }

    @Override
    protected Injector getInjector() {
        injector = Guice.createInjector(
                new ServletModule() {
                    @Override
                    protected void configureServlets() {
                        // RequestFactory servlet mapping
                        install(new InjectableRequestFactoryModule());
                        install(new CharacterModule());
                        serve("/gwtRequest").with(InjectableRequestFactoryServlet.class);
                    }
                },
                new DatabaseModule(dataSource)
        );
        return injector;
    }
}
