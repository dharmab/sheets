package com.dharmab.sheets.server.character;

import com.dharmab.sheets.server.database.DatabaseAccessor;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.web.bindery.requestfactory.shared.Locator;

import java.util.List;

@Singleton
public class CharacterService extends Locator<Character, Integer> {
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

    @Override
    public Character create(Class<? extends Character> clazz) {
        Character character = new Character();
        character.initializeDefaults();
        persist(character);
        return character;
    }

    @Override
    public Character find(Class<? extends Character> clazz, Integer id) {
        return get(id);
    }

    @Override
    public Class<Character> getDomainType() {
        return Character.class;
    }

    @Override
    public Integer getId(Character domainObject) {
        return domainObject.getId();
    }

    @Override
    public Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public Object getVersion(Character domainObject) {
        return domainObject.getVersion();
    }
}
