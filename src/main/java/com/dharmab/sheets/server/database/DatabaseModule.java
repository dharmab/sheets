package com.dharmab.sheets.server.database;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.throwingproviders.ThrowingProviderBinder;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

public class DatabaseModule extends AbstractModule {
    private DataSource dataSource;

    public DatabaseModule(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure() {
        install(ThrowingProviderBinder.forModule(this));
        bind(Database.class).to(DatabaseImpl.class);
        bind(SessionManager.class).to(SessionManagerImpl.class);
    }

    @Provides
    Flyway provideFlyway() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        return flyway;
    }
}
