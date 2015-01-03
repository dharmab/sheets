package com.dharmab.sheets.server.database;

import com.dharmab.sheets.shared.Character.Character;
import com.google.common.base.Optional;

import java.util.List;

public interface DatabaseAccessor {
    /**
     * If the given character exists in the database, update the record matching the given character.
     * Otherwise, save the given character as a new record. This method should be used in RequestFactory services.
     *
     * @param character Character to persist, identified by ID.
     */
    public void persist(Character character);

    /**
     * If the given character exists in the database, update the record matching the given character.
     * Otherwise, save the given character as a new record. This method should be used in GWT-RPC services.
     *
     * @param character Character to merge, identified by ID.
     */
    void merge(Character character);

    /**
     * Delete the record matching the given character from the database.
     *
     * @param character Character to delete, identified by ID.
     */
    public void delete(Character character);

    /**
     * Delete the record matching the given character ID from the database.
     *
     * @param id ID of character to delete.
     */
    public void delete(Integer id);

    /**
     * Retrieve the record matching the given ID from the database.
     *
     * @param id ID of character to retrieve.
     * @return Character identified by the given ID
     */
    public Optional<Character> getCharacter(Integer id);

    List<Character> getAllCharacters();
}
