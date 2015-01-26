package com.dharmab.sheets.server.character;

import com.dharmab.sheets.server.database.DatabaseAccessor;
import com.google.inject.Inject;

import java.util.List;

public class CharacterService {
    private DatabaseAccessor database;

    @Inject
    public CharacterService(DatabaseAccessor database) {
        this.database = database;
    }

    public Character get(int id) {
        return database.getCharacter(id).orNull();
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

    public List<Character> getAll() {
        return database.getAllCharacters();
    }
    
    public void reset(Character character) {
        character.initializeDefaults();
        persist(character);
    }
}
