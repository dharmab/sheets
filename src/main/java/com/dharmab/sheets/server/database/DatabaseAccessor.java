package com.dharmab.sheets.server.database;

import com.dharmab.sheets.server.character.Character;
import com.google.common.base.Optional;

import java.util.List;

public interface DatabaseAccessor {
    /**
     * If the given character exists in the database, update the record matching the given character.
     * Otherwise, save the given character as a new record.
     *
     * @param character Character to persist, identified by ID.
     */
    public void persist(Character character);

    /**
     * Delete the record matching the given character from the database.
     *
     * @param character Character to delete, identified by ID.
     */
    public void delete(Character character);

    /**
     * Retreive the record matching the given ID from the database.
     *
     * @param id ID of character to retrieve.
     * @return Character identified by the given ID
     */
    public Optional<Character> getCharacter(Integer id);

    List<Character> getAll();
}
