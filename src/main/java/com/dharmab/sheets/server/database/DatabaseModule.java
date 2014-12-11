package com.dharmab.sheets.server.database;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.sql.DataSource;

public class DatabaseModule extends AbstractModule {
    private DataSource dataSource;

    public DatabaseModule(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure() {
        bind(DatabaseAccessor.class).to(DatabaseAccessorImpl.class);
    }

    @Provides
    Flyway provideFlyway() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        return flyway;
    }

    @Provides
    @Singleton
    SessionFactory provideSessionFactory() {
        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
