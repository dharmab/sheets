package com.dharmab.sheets.server.database;

import com.dharmab.sheets.server.character.Character;
import com.google.common.base.Optional;
import com.google.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatabaseAccessorImpl implements DatabaseAccessor {
    private SessionFactory sessionFactory;
    private Logger logger;

    @Inject
    public DatabaseAccessorImpl(SessionFactory sessionFactory, Logger logger) {
        this.sessionFactory = sessionFactory;
        this.logger = logger;
    }

    @Override
    public void persist(Character character) {
        logger.log(Level.FINE, "persisting character");
        getCurrentSession().persist(character);
    }

    @Override
    public void delete(Character character) {
        getCurrentSession().delete(character);
    }

    @Override
    public Optional<Character> getCharacter(Integer id) {
        return Optional.fromNullable((Character) getCurrentSession().get(Character.class, id));
    }

    @Override
    public List<Character> getAll() {
        @SuppressWarnings("unchecked") List<Character> results = getCurrentSession().createQuery("from Character").list();
        return results;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
