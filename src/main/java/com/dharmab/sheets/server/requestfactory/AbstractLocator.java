package com.dharmab.sheets.server.requestfactory;

import com.dharmab.sheets.server.database.HasIdAndVersion;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Locator;
import org.hibernate.SessionFactory;

import java.io.Serializable;

public abstract class AbstractLocator<T extends HasIdAndVersion<I, V>, I extends Serializable, V extends Serializable> extends Locator<T, I> {
    private SessionFactory sessionFactory;

    @Inject
    public AbstractLocator(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public T create(Class<? extends T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T find(Class<? extends T> clazz, I id) {
        @SuppressWarnings("unchecked") T entity = (T) sessionFactory.getCurrentSession().get(clazz, id);
        return entity;
    }

    @Override
    public abstract Class<T> getDomainType();

    @Override
    public I getId(T domainObject) {
        return domainObject.getId();
    }

    @Override
    public abstract Class<I> getIdType();

    @Override
    public V getVersion(T domainObject) {
        return domainObject.getVersion();
    }
}
