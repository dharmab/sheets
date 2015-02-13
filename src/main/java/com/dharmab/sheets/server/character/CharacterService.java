package com.dharmab.sheets.server.character;

import com.dharmab.sheets.server.database.EntityDao;
import com.google.inject.Inject;
import org.hibernate.SessionFactory;

import java.util.List;

public class CharacterService {
    private EntityDao<Character, Integer, Integer> database;

    @Inject
    public CharacterService(SessionFactory sessionFactory) {
        database = new EntityDao<>(Character.class, sessionFactory);
    }

    public void persist(Character character) {
        database.persist(character);
    }

    public void delete(Character character) {
        database.delete(character);
    }

    public void delete(Integer id) {
        database.delete(id);
    }

    public List<Character> get(Integer start, int maxLength) {
        return database.get(start, maxLength);
    }

    public Character get(Integer id) {
        return database.get(id).orNull();
    }
}
