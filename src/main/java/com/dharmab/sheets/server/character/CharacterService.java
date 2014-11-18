package com.dharmab.sheets.server.character;

import com.dharmab.sheets.server.database.Database;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.List;

@Singleton
public class CharacterService {
    private Database database;

    @Inject
    public CharacterService(Database database) {
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

    public List<Character> getAll() {
        return database.getAll();
    }
}
