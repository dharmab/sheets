package com.dharmab.sheets.server.database;

import com.dharmab.sheets.server.character.Character;
import com.google.common.base.Optional;
import com.google.inject.Inject;
import org.flywaydb.core.Flyway;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;


public class DatabaseImpl implements Database {
    private SessionManager sessionManager;
    private Flyway flyway;

    @Inject
    public DatabaseImpl(SessionManager sessionManager, Flyway flyway) {
        this.sessionManager = sessionManager;
        this.flyway = flyway;
    }

    @Override
    public void start() {
        flyway.migrate();
    }

    @Override
    public void stop() {
        sessionManager.close();
    }

    @Override
    public void persist(Character character) {
        Session session = sessionManager.openSession();
        try {
            session.getTransaction().begin();
            session.persist(character);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(final Character character) {
        Session session = sessionManager.openSession();
        try {
            session.getTransaction().begin();
            session.delete(character);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Character> getCharacter(Integer id) {
        Session session = sessionManager.openSession();
        try {
            Character character = (Character) session.get(Character.class, id);
            return Optional.fromNullable(character);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Character> getAll() {
        Session session = sessionManager.openSession();
        try {
            @SuppressWarnings("unchecked") List<Character> characters = session.createQuery("from Character").list();
            return characters;
        } finally {
            session.close();
        }
    }
}
