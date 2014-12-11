package com.dharmab.sheets.server;

import com.dharmab.sheets.server.character.CharacterModule;
import com.dharmab.sheets.server.database.DatabaseModule;
import com.dharmab.sheets.server.database.DatabaseSessionFilter;
import com.dharmab.sheets.server.requestfactory.InjectableRequestFactoryModule;
import com.dharmab.sheets.server.requestfactory.InjectableRequestFactoryServlet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import org.flywaydb.core.Flyway;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AppContextListener extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        Injector injector;
        try {
            final DataSource dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/postgresql");
            injector = Guice.createInjector(
                    new CharacterModule(),
                    new DatabaseModule(dataSource),
                    new ServletModule() {
                        @Override
                        protected void configureServlets() {
                            // Hibernate thread-scoped sessions
                            filter("/*").through(DatabaseSessionFilter.class);
                            // RequestFactory servlet mapping
                            install(new InjectableRequestFactoryModule());
                            serve("/gwtRequest").with(InjectableRequestFactoryServlet.class);
                        }
                    });
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

        // Run database migrations
        injector.getInstance(Flyway.class).migrate();
        return injector;
    }
}
