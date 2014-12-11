package com.dharmab.sheets.server.character;

import com.dharmab.sheets.server.database.DatabaseAccessor;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Locator;

public class CharacterLocator extends Locator<Character, Integer>{
    private DatabaseAccessor database;

    @Inject
    public CharacterLocator(DatabaseAccessor database) {
        this.database = database;
    }

    @Override
    public Character create(Class<? extends Character> aClass) {
        return new Character();
    }

    @Override
    public Character find(Class<? extends Character> aClass, Integer id) {
        return database.getCharacter(id).orNull();

    }

    @Override
    public Class<Character> getDomainType() {
        return Character.class;
    }

    @Override
    public Integer getId(Character character) {
        return character.getId();
    }

    @Override
    public Class<Integer> getIdType() {
        return Integer.class;
    }

    @Override
    public Object getVersion(Character character) {
        return character.getVersion();
    }
}
