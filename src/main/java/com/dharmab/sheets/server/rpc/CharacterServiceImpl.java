package com.dharmab.sheets.server.rpc;

import com.dharmab.sheets.client.rpc.CharacterService;
import com.dharmab.sheets.server.database.DatabaseAccessor;
import com.dharmab.sheets.shared.Character.Character;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.hibernate.HibernateException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

// Services are registered via Guice injection
@SuppressWarnings("GwtServiceNotRegistered")
@Singleton
public class CharacterServiceImpl extends RemoteServiceServlet implements CharacterService {
    private DatabaseAccessor database;
    private Validator validator;

    @Inject
    public CharacterServiceImpl(DatabaseAccessor database, Validator validator) {
        this.database = database;
        this.validator = validator;
    }

    @Override
    public Boolean merge(com.dharmab.sheets.shared.Character.Character character) {
        Set<ConstraintViolation<Character>> violations = validator.validate(character);
        if (!violations.isEmpty()) {
            return false;
        }

        try {
            database.merge(character);
            return true;
        } catch (HibernateException e) {
            return false;
        }
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
