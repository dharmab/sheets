package com.dharmab.sheets.server.character;

import com.dharmab.sheets.server.database.DatabaseAccessor;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class CharacterService {
    private DatabaseAccessor database;
    private Logger logger;

    @Inject
    public CharacterService(DatabaseAccessor database, Logger logger) {
        this.database = database;
        this.logger = logger;
    }

    public Character get(int id) {
        return database.getCharacter(id).orNull();
    }

    public void persist(Character character) {
        logger.log(Level.FINE, "before database.persist()");
        database.persist(character);
        logger.log(Level.FINE, "after database.persist()");

    }

    public void delete(Character character) {
        database.delete(character);
    }

    public void delete(Integer id) {
        database.delete(id);
    }

    public List<Character> getAll() {
        return database.getAll();
    }
}
