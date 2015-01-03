package com.dharmab.sheets.server.rpc;

import com.dharmab.sheets.client.rpc.CharacterService;
import com.dharmab.sheets.server.database.DatabaseAccessor;
import com.dharmab.sheets.shared.Character.Character;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;

import java.util.List;

public class CharacterServiceImpl extends RemoteServiceServlet implements CharacterService {
    private DatabaseAccessor database;

    @Inject
    public void setDatabase(DatabaseAccessor database) {
        this.database = database;
    }

    @Override
    public void persist(com.dharmab.sheets.shared.Character.Character character) {
        database.persist(character);
    }

    @Override
    public void delete(Character character) {
        database.delete(character);
    }

    @Override
    public void delete(Integer id) {
        database.delete(id);
    }

    @Override
    public Character get(Integer id) {
        return database.getCharacter(id).orNull();
    }

    @Override
    public List<Character> getAll() {
        return database.getAllCharacters();
    }
}
