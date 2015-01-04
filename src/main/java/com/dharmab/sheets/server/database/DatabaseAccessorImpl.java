package com.dharmab.sheets.server.database;

import com.dharmab.sheets.shared.character.Character;
import com.google.common.base.Optional;
import com.google.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;


public class DatabaseAccessorImpl implements DatabaseAccessor {
    private SessionFactory sessionFactory;

    @Inject
    public DatabaseAccessorImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void persist(Character character) {
        getCurrentSession().persist(character);
    }

    @Override
    public void merge(Character character) {
        getCurrentSession().merge(character);
    }

    @Override
    public void delete(Character character) {
        getCurrentSession().delete(character);
    }

    @Override
    public void delete(Integer id) {
        getCurrentSession().createQuery("delete from Character where id = :id").setInteger("id", id).executeUpdate();
    }

    @Override
    public Optional<Character> getCharacter(Integer id) {
        return Optional.fromNullable((Character) getCurrentSession().get(Character.class, id));
    }

    @Override
    public List<Character> getAllCharacters() {
        @SuppressWarnings("unchecked") List<Character> results = getCurrentSession().createQuery("from Character").list();
        return results;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
